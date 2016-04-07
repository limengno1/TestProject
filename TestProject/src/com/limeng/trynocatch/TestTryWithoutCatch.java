package com.limeng.trynocatch;

public class TestTryWithoutCatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			tryWithoutCatch();
			System.out.println("tryWithoutCatch...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void tryWithoutCatch() throws Exception{
		try {
			int i = Integer.valueOf("s1");
			System.out.println(i);
		} 
		/*catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		finally {
			System.out.println("finally");
		}
		
		System.out.println("end....");
	}

}
