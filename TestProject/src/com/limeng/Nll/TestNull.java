package com.limeng.Nll;

public class TestNull {

	static Long AA = Long.valueOf(111);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Long a = null;
		if (AA == a) {
			System.out.println(11);
		}
		
		if (0x00000058 == a) {
			System.out.println(22);
		}
	}

}
