package net04_thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost",50001);
			System.out.println("[클라이언트] 연결 성공");
			//----------------------------------------------------------------
			//01-1.데이터 보내기
			String sendMassage = "나는 자바수업을 하고 있습니다.";
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(sendMassage);
			dos.flush();
			System.out.println("[클라이언트] 데이터 보냄: "+sendMassage);
			
			//02-1.데이터 받기
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			String receiveMessage = dis.readUTF();
			System.out.println("[클라이언트] 데이터 받음: "+receiveMessage);
			
			//----------------------------------------------------------------
			socket.close();
			System.out.println("[클라이언트] 연결 끊음");
		} catch (UnknownHostException e) { e.printStackTrace(); //IP,도메인주소 없을 시
		} catch (IOException e) { e.printStackTrace(); } // IP,Port 없을 시 

	}

}
