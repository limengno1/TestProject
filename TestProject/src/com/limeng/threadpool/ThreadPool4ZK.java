package com.limeng.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class ThreadPool4ZK {

	private static AtomicInteger count = new AtomicInteger(1);
	
	public static void main(String args[]) {
		ThreadPool4ZK tzk = new ThreadPool4ZK();
		ExecutorService es = Executors.newFixedThreadPool(5);
		try {
			for (;;) {
				es.execute(tzk.new AddCountThread() );
			}
		}
		catch(Exception e) {
			es.shutdown();
			e.printStackTrace();
		}
		
		
		
	}
	
	class AddCountThread extends Thread {
		
		public void run() {
			
			int num = count.getAndAdd(1);
			System.out.println(this.currentThread().getName() + "=====" + num);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
