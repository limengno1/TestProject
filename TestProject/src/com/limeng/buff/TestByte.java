package com.limeng.buff;

import java.nio.ByteBuffer;

import org.junit.Test;

import edu.emory.mathcs.backport.java.util.Arrays;

/** 
 * <Description> <br> 
 *  
 * @author limeng<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016年2月18日 <br>
 * @since V7.3<br>
 * @see com.limeng.buff <br>
 */
public class TestByte {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ByteBuffer bb = ByteBuffer.allocate(4);
		byte[] bArray = new byte[4];
		bArray[0] = (byte)0;
		bArray[1] = (byte)0;
		bArray[2] = (byte)2;
		bArray[3] = (byte)(14*16 + 15);
		
		bb.put(bArray);
		bb.rewind();
		printInt(bb);
	}
	
	public static void printInt(ByteBuffer bb){
		// 00-00-02-EF
		int i = bb.getInt();
		System.out.println(i);
	}
	
	@Test
	public void testInt2byte() {
		Integer i = 200;
		byte b = i.byteValue();
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
	
	public static byte[] getBytes_12(long data)  
    {  
        byte[] bytes = new byte[12];  
        bytes[0] = (byte) (data & 0xff);  
        bytes[1] = (byte) ((data >> 8) & 0xff);  
        bytes[2] = (byte) ((data >> 16) & 0xff);  
        bytes[3] = (byte) ((data >> 24) & 0xff); 
        
        bytes[4] = (byte) ((data >> 32) & 0xff);  
        bytes[5] = (byte) ((data >> 40) & 0xff);  
        bytes[6] = (byte) ((data >> 48) & 0xff);  
        bytes[7] = (byte) ((data >> 56) & 0xff); 
        
        bytes[8] = (byte) ((data >> 64) & 0xff);  
        bytes[9] = (byte) ((data >> 72) & 0xff);  
        bytes[10] = (byte) ((data >> 80) & 0xff);  
        bytes[11] = (byte) ((data >> 88) & 0xff);
        return bytes;  
   }
	
	// 将byte数组bRefArr转为一个整数,字节数组的低位是整型的低字节位
	public static int toInt(byte[] bRefArr) {
	    int iOutcome = 0;
	    byte bLoop;

	    for (int i = 0; i < bRefArr.length; i++) {
	        bLoop = bRefArr[i];
	        iOutcome += (bLoop & 0xFF) << (8 * i);
	    }
	    return iOutcome;
	}
	
	
	/**
	 * Description: <br>
	 * int :4
	 * long : 8
	 * double:8 
	 *  
	 * @author limeng<br>
	 * @taskId <br> <br>
	 */ 
	@Test
	public void testDouble() {
		ByteBuffer bb = ByteBuffer.allocate(12);
		bb.putDouble(1);
		System.out.println(Arrays.toString(bb.array()));
		
		byte[] bytes = bb.array();
		
	}
	
	@Test
	public void test12() {
		byte[] b = getBytes_12(43720009);
		System.out.println(Arrays.toString(b));
		Integer i = toInt(b);
		System.out.println(i);
	}
	
	@Test
	public void test12String() {
		String sequence = "  0043720009";
		byte[] bytes = sequence.getBytes();
		System.out.println(bytes);
		System.out.println(new String(bytes));
	}

	@Test
	public void buildReportRequest() {
		// 消息头 ，消息体
		ByteBuffer header = ByteBuffer.allocate(20);
		byte [] lengthByte = getBytes(64);
		byte [] commandIdByte = getBytes(0x5);
		byte [] sequenceByte = getBytes(1);
		
//		header.put(lengthByte).put(commandIdByte).put(sequenceByte).put(sequenceByte).put(sequenceByte);
		header.putInt(64);
		header.putInt(0x5);
		header.putInt(1);
		header.putInt(1);
		header.putInt(1);

		System.out.println(Arrays.toString(header.array()));
		
		ByteBuffer body = ByteBuffer.allocate(44);
		int SubmitSequenceNumber = 43720009; //12
		byte ReportType = 0;
		String UserNumber = "123456789012345678901";
		byte State = 0;
		byte ErrorCode = 0;
		String Reserve = "00000000";
		
//		body.putDouble(value)
		body.put(getBytes_12(SubmitSequenceNumber));
		body.put(ReportType);
		body.put(UserNumber.getBytes());
		body.put(State);
		body.put(ErrorCode);
		body.put(Reserve.getBytes());

		System.out.println(Arrays.toString(body.array()));
		
		ByteBuffer reportReqBuff = ByteBuffer.allocate(64);
//		System.arraycopy(header, 0, reportReqBuff, 0, 20);
//		System.arraycopy(body, 0, reportReqBuff, 20, 44);
		reportReqBuff.put(header.array());
		reportReqBuff.put(body.array());
		System.out.println(Arrays.toString(reportReqBuff.array()));
//		return reportReqBuff;
	}
	
	@Test
	public void testChineseByte() {
		char c = '牛';
		System.out.println((int)c);
	}
	
}
