package net01;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientEx01 {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost",50001);
			System.out.println("[클라이언트] 연결 성공");
			socket.close();
			System.out.println("[클라이언트] 연결 끊음");
		} catch (UnknownHostException e) { e.printStackTrace(); //IP,도메인주소 없을 시
		} catch (IOException e) { e.printStackTrace(); } // IP,Port 없을 시 

	}

}
