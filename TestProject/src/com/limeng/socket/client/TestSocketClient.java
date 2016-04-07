package com.limeng.socket.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.junit.Test;

import edu.emory.mathcs.backport.java.util.Arrays;

public class TestSocketClient {

	private String ip ;
	private int port;
	private InputStream in;
	private OutputStream out;
	private Socket socket;
	
	boolean isLogined = false;
	
	public static void main(String args[]) throws UnknownHostException, IOException, InterruptedException {

		TestSocketClient socketClient = new TestSocketClient();
		socketClient.init();
		byte[] bindMsg = socketClient.buildSgipBindMsg();
		System.out.println(Arrays.toString(bindMsg));
		socketClient.sendMsg(bindMsg);

		// 返回登录消息
		socketClient.readMsg();
		
		if (!socketClient.isLogined) {
			return;
		}
		
		byte[] reportMsg = socketClient.buildSgipReportMsg();
		System.out.println(Arrays.toString(reportMsg));
		while (true) {
			
			
			Thread.sleep(1000);
			
			socketClient.sendMsg(reportMsg);
			
			Thread.sleep(30000);
		}
		
		
	}
	
	@Test
	public void test2() throws IOException {
		readMsg();
	}
	
	private void readMsg() throws IOException {
		byte[] messageLength = new byte[4];
		in.read(messageLength);
		/*messageLength[0] = 0;
		messageLength[1] = 0;
		messageLength[2] = 0;
		messageLength[3] = 45;*/
		int length = ByteUtil.bytes2int(messageLength);
//		System.out.println("length is :  " + length);
		byte[] headerByte = new byte[16];
		in.read(headerByte);
		
		int bodyLength = length - 20;
		byte[] bodyByte = new byte[bodyLength];
		in.read(bodyByte);
		byte result = bodyByte[0];
		System.out.println("login result is : " + result);
		isLogined = result==0;
	}
	
	private void init() throws UnknownHostException, IOException {
		ip = "10.45.14.136";
		port = 8802;
		
		socket = new Socket(ip, port);
		
		out = socket.getOutputStream();
		in = socket.getInputStream();
				
	}
	
	private void sendMsg(byte[] msg) throws IOException {
		out = socket.getOutputStream();
		out.write(msg);
	}
	
	
	public void testSgipMsg() throws UnknownHostException, IOException {
		init();
		
		// 绑定服务端
		byte[] bindMsg = buildSgipBindMsg();
		System.out.println(Arrays.toString(bindMsg));
		
		sendMsg(bindMsg);
		
		byte[] reportMsg = buildSgipReportMsg();
		sendMsg(reportMsg);
	}
	
	private byte[] buildSgipReportMsg() {
		
		Integer length = 64;
		Integer commandId = 0x5;
		byte[] reportHeader = new byte[20];
		reportHeader = buildSgipHeader(length, commandId);
		
		byte[] reportBody = buildReportBody();

		byte[] reportMsg = new byte[64];
		System.arraycopy(reportHeader, 0, reportMsg, 0, 20);
		System.arraycopy(reportBody, 0, reportMsg, 20, 44);
		
		return reportMsg;
	}
	
	private byte[] buildReportBody(){
		/**
		 * <buffer id="SGIP_REPORT" seq="1" fieldname="SubmitSequenceNumber" type="6" length="12" defaultValue="1" comments="发送短消息的用户手机号"/>
		<buffer id="SGIP_REPORT" seq="2" fieldname="ReportType" type="1" length="1" defaultValue="" comments="SP的接入号码"/>
		<buffer id="SGIP_REPORT" seq="3" fieldname="UserNumber" type="8" length="21" defaultValue="" comments="GSM协议类型"/>
		<buffer id="SGIP_REPORT" seq="4" fieldname="State" type="1" length="1" defaultValue="" comments="GSM协议类型"/>
		<buffer id="SGIP_REPORT" seq="5" fieldname="ErrorCode" type="1" length="1" defaultValue="" comments="短消息的编码格式"/>
		<buffer id="SGIP_REPORT" seq="6" fieldname="Reserve" type="8" length="8" defaultValue="" comments="短消息的长度"/>
		
		 */
		// [-73, 49, -29, 97, 18, -117, 54, 103, 0, 0, -61, 94] -->3073500001	311113319	50014	
		byte[] submitSequenceNumber = {-73, 49, -29, 97, 18, -117, 54, 103, 0, 0, 117, 98};//3073500001	311113319	30050	
		byte reportType = 1;
		String userNumber = "11";
		byte state = 0;
		byte errorCode = 0;
		String reserve = "";
		
		byte[] userNumberByte = new byte[21];
		byte[] reserveByte = new byte[8];

		System.arraycopy(userNumber.getBytes(), 0, userNumberByte, 0, userNumber.getBytes().length);
		System.arraycopy(reserve.getBytes(), 0, reserveByte, 0, reserve.getBytes().length);
		
		byte[] reportBodyByte = new byte[44];
		reportBodyByte[12] = reportType;
		reportBodyByte[34] = state;
		reportBodyByte[35] = errorCode;
		System.arraycopy(submitSequenceNumber, 0, reportBodyByte, 0, 12);
		System.arraycopy(userNumberByte, 0, reportBodyByte, 13, 21);
		System.arraycopy(reserveByte, 0, reportBodyByte, 36, 8);
		
		return reportBodyByte;
	}

	private byte[] buildSgipBindMsg() {
		
		/**
		 * <buffer id="SGIP_BIND" seq="1" fieldname="LoginType" type="1" length="1" defaultValue="1" comments="登录类型"/>
		<buffer id="SGIP_BIND" seq="2" fieldname="LoginName" type="8" length="16" defaultValue="" comments="登录名"/>
		<buffer id="SGIP_BIND" seq="3" fieldname="LoginPassowrd" type="8" length="16" defaultValue="" comments="密码"/>
		<buffer id="SGIP_BIND" seq="4" fieldname="Reserve" type="8" length="8" defaultValue="" comments="保留"/>
		
		 */
		byte[] bodyBytes = buildBindBodyMsg();
		
		Integer length = 20 + bodyBytes.length;
		Integer commandId = 0x1;
		byte[] headerBytes = buildSgipHeader(length, commandId);
		
		byte[] bindMsg = new byte[length];
		
		System.arraycopy(headerBytes, 0, bindMsg, 0, 20);
		System.arraycopy(bodyBytes, 0, bindMsg, 20, bodyBytes.length);
		
		System.out.println("headerBytes is : " + Arrays.toString(headerBytes));
		System.out.println("bodyBytes is : " + Arrays.toString(bodyBytes));
		System.out.println("bindMsg is : " + Arrays.toString(bindMsg));
		return bindMsg;
	}

	private byte[] buildBindBodyMsg() {
		byte loginType = 2;
		byte[] loginNameByte = new byte[16];
		byte[] passwordByte = new byte[16];
		byte[] reserveByte = new byte[8];
		String loginName = "TEST";
		String password = "TEST";
		String reserve = "";
		System.arraycopy(loginName.getBytes(), 0, loginNameByte, 0, loginName.getBytes().length);
		System.arraycopy(password.getBytes(), 0, passwordByte, 0, password.getBytes().length);
		System.arraycopy(reserve.getBytes(), 0, reserveByte, 0, reserve.getBytes().length);
		
		/*appendBytes(loginNameByte, (byte)0x00);
		appendBytes(passwordByte, (byte)0x00);
		appendBytes(passwordByte, (byte)0x00);*/
		
		byte[] bodyBytes = new byte[41];
		bodyBytes[0] = loginType;
		System.arraycopy(loginNameByte, 0, bodyBytes, 1, 16);
		System.arraycopy(passwordByte, 0, bodyBytes, 17, 16);
		System.arraycopy(reserveByte, 0, bodyBytes, 33, 8);
		return bodyBytes;
	}
	
	@Test
	public void test1() {
		byte[] bindMsg = buildSgipBindMsg();
		System.out.println(Arrays.toString(bindMsg));
	}
	
	private byte[] buildSgipHeader(Integer messageLength, Integer commandId) {
		/**
		 * <buffer id="SGIP_HEADER" seq="1" fieldname="MessageLength" type="3" length="4" defaultValue="20" comments="消息的总长度(字节)"/>
		<buffer id="SGIP_HEADER" seq="2" fieldname="CommandID" type="3" length="4" defaultValue="1" comments="命令ID"/>
		<buffer id="SGIP_HEADER" seq="3" fieldname="SequenceNumber" type="6" length="12" defaultValue="0" comments="序列号"/>	
		 */
		ByteBuffer lengthBuffer = ByteBuffer.allocate(4);
		byte[] lengthByte = lengthBuffer.putInt(messageLength).array();
		
		ByteBuffer commandBuffer = ByteBuffer.allocate(4);
		byte[] commandByte = commandBuffer.putInt(commandId).array();
		byte[] sequence = {00,00,00,00,00,00,00,00,00,00,00,01};
		
		byte[] headerByte = new byte[lengthByte.length + commandByte.length + sequence.length];
		
		System.arraycopy(lengthByte, 0, headerByte, 0, lengthByte.length);
		System.arraycopy(commandByte, 0, headerByte, 4, commandByte.length);
		System.arraycopy(sequence, 0, headerByte, 8, sequence.length);
		ByteBuffer bb = ByteBuffer.allocate(20);
		headerByte = bb.put(headerByte).order(ByteOrder.LITTLE_ENDIAN).array();
		return headerByte;
	}

	private void destory() throws IOException {
		if (null != socket) {
			socket.close();
		}
	}
	
	public static byte[] getBytes(int data)  
    {  
        byte[] bytes = new byte[4];  
        bytes[0] = (byte) (data & 0xff);  
        bytes[1] = (byte) ((data & 0xff00) >> 8);  
        bytes[2] = (byte) ((data & 0xff0000) >> 16);  
        bytes[3] = (byte) ((data & 0xff000000) >> 24);  
        return bytes;  
    }  
	
	public final static byte[] appendBytes(byte sByte[], byte tByte) {
		byte buf[] = null;
		if (sByte == null) {
			buf = new byte[1];
			buf[0] = tByte;
			return buf;
		}
		int len = sByte.length + 1;
		buf = new byte[len];
		for (int i = 0; i < sByte.length; i++) {
			buf[i] = sByte[i];
		}
		buf[sByte.length] = tByte;
		return buf;
	}
	
}
