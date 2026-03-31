package net5_chatting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.json.JSONObject;

public class SocketClient {
	//필드 - 클라이언트 채팅정보
	ChatServer chatServer;
	Socket socket;
	String clientIp;
	String chatName;
	DataInputStream dis;
	DataOutputStream dos;
	
	//생성자
	public SocketClient(ChatServer chatServer,Socket socket ) {
		try {
			this.chatServer = chatServer;
			this.socket = socket;
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
			InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
			this.clientIp = isa.getHostName(); //getHostName메소드는 InetSocketAddress객체에 있음
			receive();
		} catch (Exception e) {  }
	}
	
	//클라이언트에서 받는 메소드
	private void receive() {
		chatServer.threadPool.execute(()->{
			try {
			    while(true) {
					//전달받은형태 
					//{"command":"incoming","data":"chatName"}
					//{"command":"message","data":"xxxxx"}
					String receiveJson = dis.readUTF();
					JSONObject jsonObject = new JSONObject(receiveJson);
					String command = jsonObject.getString("command"); //키값:command
					
					switch(command) {
						case "incoming":
							this.chatName = jsonObject.getString("data");
							chatServer.sendToAll(this, "들어오셨습니다.");
							chatServer.addSocketClient(this);
							break;
						case "message":
							String message = jsonObject.getString("data");
							chatServer.sendToAll(this, message);
							break;
					}//switch
			    }//while
			} catch (IOException e) {
				chatServer.sendToAll(this, "나가셨습니다.");
				chatServer.removeSocketClient(this);
			}//try
		});
	}

	//클라이언트에서 보내는 메소드
	public void send(String json) {
		try {
			dos.writeUTF(json);
			dos.flush();
		} catch (IOException e) { 	}
	}

	//서버 종료
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {}
	}


}
