package net5_chatting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.json.JSONObject;

public class ChatServer {
	
	//필드
	ServerSocket serverSocket;
	ExecutorService threadPool = Executors.newFixedThreadPool(100);
	Map<String, SocketClient> chatRoom = Collections.synchronizedMap(new HashMap<>());

	//채팅서버 시작
	public void start() throws IOException{
		serverSocket = new ServerSocket(50001);
		System.out.println("[서버] 시작됨.");
		Thread thread = new Thread(()->{
			try {
				//연결수락
				Socket socket = serverSocket.accept();
				SocketClient sc = new SocketClient(this, socket);
			}catch(Exception e) {
				e.printStackTrace();
			}
		});
		thread.start();
	}//
	
	//채팅방에 추가하는 메소드
	public void addSocketClient(SocketClient socketClient) {
		String key = socketClient.chatName+"@"+socketClient.clientIp;
		chatRoom.put(key, socketClient);
		System.out.println("입장: "+key);
		System.out.println("현재 채팅자 수: "+chatRoom.size()+"\n");
	}//
	
	//채팅방에서 퇴장하는 메소드
	public void removeSocketClient(SocketClient socketClient) {
		String key = socketClient.chatName+"@"+socketClient.clientIp;
		chatRoom.remove(key);
		System.out.println("퇴장: "+key);
		System.out.println("현재 채팅자 수: "+chatRoom.size()+"\n");
	}//
	
	// 모든 접속자들에게 메세지 전송메소드
	public void sendToAll(SocketClient sender,String message) {
		JSONObject root = new JSONObject();
		root.put("clientIp", sender.clientIp);
		root.put("chatName", sender.chatName);
		root.put("message", message);
		String json = root.toString();
		//각 클라이언트에 메세지 전달
		Collection<SocketClient> socketClients = chatRoom.values();
		for(SocketClient sc : socketClients) {
			if(sc == sender) continue;
			sc.send(json);
		}
	}//
	
	//채팅 종료
	public void stop() {
		try {
			serverSocket.close();
			threadPool.shutdownNow(); //즉시종료
			chatRoom.values().stream().forEach(sc->sc.close());
			System.out.println("[서버] 종료됨.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//
	
	
	//
	public static void main(String[] args) {
		try {
			ChatServer chatServer = new ChatServer();
			chatServer.start();
			
			System.out.println("-------------------------------------");
			System.out.println("서버를 종료하려면 q를 입력하세요.");
			System.out.println("-------------------------------------");

			//키보드 입력
			Scanner scanner = new Scanner(System.in);
			while(true) {
				String key = scanner.nextLine();
				if(key.toLowerCase().equals("q")) {
					break;
				}
			}
			scanner.close();
			
			//TCP 서버 종료
			chatServer.stop();
			
			
			
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("[서버] "+e.getMessage());
		}
		

	}

}
