package com.limeng.buff;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.junit.Test;

import edu.emory.mathcs.backport.java.util.Arrays;

public class TestSpecialLang {

	@Test
	public void testBosilang() throws UnsupportedEncodingException {
//		String str = "هزینه:62ريال";
//		String str = "汉子44bi桂";
		String str = "abcdef";
		
		/*Properties pps=System.getProperties();
		pps.put("file.encoding","ISO-8859-1");
		
		pps.list(System.out);
		System.out.println("properties is : " + pps);*/
		
		byte[] strBytes = str.getBytes();
		byte[] strBytes4Utf8 = str.getBytes("utf-8");
		
		System.out.println(Arrays.toString(strBytes) + "\t " + new String(strBytes));
		System.out.println(Arrays.toString(strBytes4Utf8) + "\t " + new String(strBytes4Utf8,"iso-8859-1"));
		
	}
	
	@Test
	public void testSystempro() {
		Properties pps=System.getProperties();

        pps.list(System.out);      // 以列表的方式查看java虚拟机的所有属性

        pps.put("file.encoding","ISO-8859-1"); // 更改java虚拟机的默认编码集
	}
}
