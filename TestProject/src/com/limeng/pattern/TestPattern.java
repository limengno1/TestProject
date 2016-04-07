package com.limeng.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestPattern {

	/**
	 * Description: <br> 
	 * 组和捕获 
		捕获组可以通过从左到右计算其开括号来编号。例如，在表达式 ((A)(B(C))) 中，存在四个这样的组： 
		
		1     ((A)(B(C))) 
		2     \A 
		3     (B(C)) 
		4     (C) 
	 *  
	 * @author limeng<br>
	 * @taskId <br> <br>
	 */ 
	@Test
	public void testGroup() {
		// 版本：1; 1.3; 1.9.2
//		String regex = "((\\d+)(([.]\\d+)([.]\\d+)))"; // ^(\\d+\\.?\\d{0,2})\\.?(\\d{0,2})$
//		String regex = "^(\\d+)([.]\\d){0,2}";
		String regex = "(\\d+\\.?\\d?)[.](\\d+)?";
//		String regex = "^(\\d+\\.?\\d{0,2})\\.?(\\d{0,2})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("1");
		boolean isMatcher = matcher.find();
		System.out.println("matched : " + isMatcher);
		if (isMatcher) {
			int count = matcher.groupCount();
			String matcherGroup = matcher.group();
			System.out.println("count is : " + count);
			System.out.println("matcherGroup is : " + matcherGroup);
			for(int i=0; i<=count; i++) {
				System.out.println("----------" + matcher.group(i));
			}
//			System.out.println("count is : " + count);
//			System.out.println("matcherGroup is : " + matcherGroup);
		}
	}
	
	/**
	 * Description: <br> 
	 *  1=50|2=20
	 * @author limeng<br>
	 * @taskId <br> <br>
	 */ 
	@Test 
	public void test2() {
		String regex = "(\\w+)=(\\d+)";
		String scanCountStr = "1=50";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(scanCountStr);
		if (matcher.matches()) {
			int count = matcher.groupCount();
			String countStr1="";
			String countStr2="";
			System.out.println("count is : " + count);
			if (count == 2) {
				countStr1 = matcher.group(1);
				countStr2 = matcher.group(2);
			}
			System.out.println(countStr1);
			System.out.println(countStr2);
		}
		
	}
}
