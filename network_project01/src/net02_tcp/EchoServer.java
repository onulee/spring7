package net02_tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

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
						//accept 상태가 되어야 다른 클라이언트 요청을 받을수 있음.
						Socket socket = serverSocket.accept(); //대기상태에서 접속을 기다림
						//연결된 클라이언트 정보 받기
						InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
						String clientIp = isa.getHostString(); //isa.getHostName();
						System.out.println("[서버] "+clientIp+"의 연결 요청을 수락함."); 
						//----------------------------------------------------------------
						//01-2.데이터 받기 - DataInputStream 사용
						DataInputStream dis = new DataInputStream(socket.getInputStream());
						String message = dis.readUTF();
						
						//02-2.데이터 보내기
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						dos.writeUTF(message);
						dos.flush();
						System.out.println("[서버] 받은 데이터를 다시 보냄: "+message);
						
						//01.데이터 받기 - InputStream 사용
//						InputStream is = socket.getInputStream();
//						byte[] bytes = new byte[1024];
//						int readByteCount = is.read(bytes);
//						String message = new String(bytes,0,readByteCount,"UTF-8");
						
						//02.데이터 보내기
//						OutputStream os = socket.getOutputStream();
//						bytes = message.getBytes("UTF-8");
//						os.write(bytes);
//						os.flush();
//						System.out.println("[서버] 받은 데이터를 다시 보냄: "+message);
						
						
						//----------------------------------------------------------------
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
