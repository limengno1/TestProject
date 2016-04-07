package com.limeng.charByte;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class TestCharInt {

	@Test
	public void test0() {
//		String aa = "Я";
		String aa = "a";
		try {
			byte[] aab = aa.getBytes("utf-8");

			System.out.println(aab.length);
			System.out.println(aab);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test1() {
		char a = 'A';
		char a2 = 65;
		System.out.println(a2); // A
	}
	
	@Test
	public void test2() {
		Character c1 = new Character('A');
		System.out.println(c1.charValue() + 0);
	}
	
	@Test
	public void test3() {
		Character c1 = new Character('a'); 
		System.out.println(c1.charValue() + 0);
		
		char c2 = 0x61; // 16进制
		System.out.println(c2);
		
		char c3 = 0101; 
		// 8进制
		System.out.println(c3);
		
		char d = 'd';
		System.out.println((int)d);
		
	}
	
	/**
	 * String 包含多少个char
	 * Description: <br> 
	 *  
	 * @author limeng<br>
	 * @taskId <br> <br>
	 */
	public void test4() {
		String abc = "abc";
		abc.length();
	}
	
	@Test
	public void test5(){
		String ton = "17";
		byte tonB = new Byte(ton);
		System.out.println(tonB);
		
		System.out.println("0x" + Integer.toHexString(tonB));
	}
}
