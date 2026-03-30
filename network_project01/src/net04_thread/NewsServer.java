package net04_thread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewsServer {
	
	private static DatagramSocket datagramSocket;
	//스레드풀 선언
	private static ExecutorService executorService = Executors.newFixedThreadPool(10);

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
	}//main
	
	public static void startServer() {
		
		// 작업 스레드 정의
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					//DatagramSocket 생성 및 Port 바인딩
					datagramSocket = new DatagramSocket(50001);
					System.out.println("[서버] 시작됨.");
					while(true) { //클라이언트 요청을 기다림
						//클라이언트가 구독하고 싶은 뉴스 주제 열기
						DatagramPacket receivePacket = new DatagramPacket(new byte[1024],1024);
						System.out.println("클라이언트의 희망뉴스 종류 선택 대기함...");
						//receive 상태가 되어야 다른 클라이언트 요청을 받을수 있음.
						datagramSocket.receive(receivePacket); //클라이언트 대기
						
						executorService.execute(()->{
							try {
								String newsKind = new String(receivePacket.getData(),0,receivePacket.getLength(),"UTF-8");
								//클라이언트의 IP와 pORT 정보가 있는 SocketAddress 열기
								SocketAddress socketAddress = receivePacket.getSocketAddress();
								//10개의 뉴스를 클라이언트로 전송
								for(int i=1;i<=100;i++) {
									String data = newsKind + ": 뉴스"+i;
									byte[] bytes = data.getBytes("UTF-8");
									DatagramPacket sendPacket = new DatagramPacket(bytes, 0,bytes.length,socketAddress);
									datagramSocket.send(sendPacket);
									Thread.sleep(1000); //한번에 너무 빨리 끝나니 1초동안 대기상태
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						});//executorService
					}//while
				} catch (Exception e) {
					e.printStackTrace();
				}
			}  
		};
		
		//작업 스레드 시작
		thread.start();
		
	}
	
	public static void stopServer() {
		//DatagramSocket을 닫고 Port 인바인딩
		datagramSocket.close();
		//마지막에는 무조건 스레드풀 종료를 꼭 해야 함.
		executorService.shutdownNow(); 
		System.out.println("[서버] 종료됨.");
	}//

}
