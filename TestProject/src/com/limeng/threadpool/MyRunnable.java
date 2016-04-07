package com.limeng.threadpool;

import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnable implements Runnable {

	private static AtomicInteger aInt = new AtomicInteger(0);
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(aInt.getAndIncrement());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
