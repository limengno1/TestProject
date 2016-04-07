package com.limeng.socket.client;

import java.io.IOException;
import java.io.InputStream;

public class TestRecvThread extends Thread {
	
	public InputStream in ;
	
	
	
	@Override
	public void run() {
		while (!this.interrupted()) {
			try {
				recvMsg();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void recvMsg() throws IOException {
		byte[] lengthByte = new byte[4];
		in.read(lengthByte);
	}
}
