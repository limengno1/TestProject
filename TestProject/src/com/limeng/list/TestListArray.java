package com.limeng.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestListArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	private static void test1() {
		String resultCode = "1|OK";
		String[] arrays = resultCode.split("\\|");
		List list = new ArrayList<String>();
		list = Arrays.asList(arrays);
		String flag = list.contains("OK") ? "true" : "false";
		System.out.println(flag);
	}

}
