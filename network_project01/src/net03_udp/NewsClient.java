package net03_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.spi.InetAddressResolver;
import java.net.spi.InetAddressResolver.LookupPolicy;
import java.util.stream.Stream;

public class NewsClient {

	public static void main(String[] args) {
		try {
			//DatagramSocket 생성
			DatagramSocket datagramSocket = new DatagramSocket();
			
			//구독하고 싶은 뉴스 주제 보내기
			String data = "경제뉴스 선택";
			byte[] bytes = data.getBytes("UTF-8");
			DatagramPacket sendPacket = new DatagramPacket(bytes, 0,bytes.length,new InetSocketAddress("localhost", 50001));
			datagramSocket.send(sendPacket);
			
			while(true) {
				//뉴스 받기 - 1024byte 1024개 만큼 받기
				DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
				datagramSocket.receive(receivePacket);
				
				//문자열로 변환
				String news = new String(receivePacket.getData(),0,receivePacket.getLength(),"UTF-8");
				System.out.println(news);
				
				//10번째 뉴스를 받으면 while문을 종료
				if(news.contains("뉴스10")) {
					break;
				}
			}
			//DatagramSocket 닫기
			datagramSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
