package net01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerEx01 {

	private static ServerSocket serverSocket;
	
	public static void main(String[] args) {
		System.out.println("-------------------------------------");
		System.out.println("서버를 종료하려면 q를 입력하세요.");
		System.out.println("-------------------------------------");

		//TCP서버 시작
		startServer();
		
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
		stopServer();
		
	}//
	
	public static void startServer() {
		//작업 스레드 정의
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					//ServerSocket 생성 및 Port 바인딩
					serverSocket = new ServerSocket(50001);
					System.out.println("[서버] 시작됨.");
					//여러 클라이언트에서 들어오는 요청을 실행하기 위해 무한루프
					while(true) {
						System.out.println("\n[서버] 연결 요청을 기다림\n");
						//연결수락
						Socket socket = serverSocket.accept(); //대기상태에서 접속을 기다림
						//연결된 클라이언트 정보 받기
						InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
						String clientIp = isa.getHostString(); //isa.getHostName();
						System.out.println("[서버] "+clientIp+"의 연결 요청을 수락함."); 
						//연결 끊기
						socket.close();
						System.out.println("[서버] "+clientIp+"의 연결을 끊음."); 
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		//스레드 시작
		thread.start();
	}
	
	public static void stopServer() {
		//ServerSocket을 닫고 Port 언바인딩
		try {
			serverSocket.close();
		} catch (IOException e) { e.printStackTrace();	}
		System.out.println("서버가 종료되었습니다!");
	}

}
