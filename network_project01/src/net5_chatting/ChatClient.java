package net5_chatting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.json.JSONObject;

public class ChatClient {

	//필드
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	String chatName;
	
	//클라이언트 접속메소드
	public void connect() throws Exception {
		socket = new Socket("localhost",50001);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		System.out.println("[클라이언트] 서버에 연결됨");
	}//
	
	//메소드 : json데이터 받기
	public void receive() {
		Thread thread = new Thread(()->{
			while(true) {
				try {
					String json = dis.readUTF(); //데이터가 오기전까지 블로킹 상태
					JSONObject root = new JSONObject(json);
					String clientIp = root.getString("clientIp");
					String chatName = root.getString("chatName");
					String message = root.getString("message");
					System.out.println("["+chatName+"@"+clientIp+"]"+message);
					
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("[클라이언트] 서버 연결 끊김");
					System.exit(0);
				}
			}//while
		});
		thread.start();
	}//receive
	
	//클라이언트가 메세지 보냄
	public void send(String json) throws Exception {
		dos.writeUTF(json);
		dos.flush();
	}//
	
	//연결종료
	public void unconnect() throws Exception{
		socket.close();
	}
	
	public static void main(String[] args) {
		try {
			ChatClient chatClient = new ChatClient();
			chatClient.connect();
			
			//대화명입력
			Scanner scanner = new Scanner(System.in);
			System.out.println("대화명 입력: ");
			chatClient.chatName = scanner.nextLine();
			
			//Json데이터 전달
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("command", "incoming");
			jsonObject.put("data", chatClient.chatName);
			String json = jsonObject.toString();
			//대화방 입장
			chatClient.send(json);
			//서버에서 데이터 받기 대기상태
			chatClient.receive();
			
			
		} catch (Exception e) {	e.printStackTrace(); }
	}
	
	
	
	
}//class
