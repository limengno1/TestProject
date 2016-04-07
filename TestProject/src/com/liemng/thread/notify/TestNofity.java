package com.liemng.thread.notify;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class TestNofity {

	@Test
	public void test() {
		Producer[] producers = new Producer[10];
		Consumer[] consumers = new Consumer[10];
		TestSyncQueue queue = new TestSyncQueue();
		AtomicInteger autoInt = new AtomicInteger(0);
		
		for(int i=0;i<producers.length;i++){
			producers[i] = new Producer();
			producers[i].setName("P_" + i);
			producers[i].queue = queue;
			producers[i].autoInt = autoInt;
			producers[i].setDaemon(true);
			
			consumers[i] = new Consumer();
			consumers[i].setName("C_" + i);
			consumers[i].queue = queue;
			consumers[i].setDaemon(true);
			
			System.out.printnl("haha");
		}
		
		for(int i=0;i<producers.length;i++){
			producers[i].start();
			consumers[i].start();
		}
		
		long current = System.currentTimeMillis();
		long last = System.currentTimeMillis();
		while(true){

			last = System.currentTimeMillis();
			
			if((last-current)/1000 > 30){
				break;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private class Producer extends Thread{
		public TestSyncQueue queue;
		public AtomicInteger autoInt = new AtomicInteger(0);
		
		public void run () {
			int obj = 0;
			while (!this.interrupted()) {
				obj = autoInt.addAndGet(1);
				queue.put(obj);
				System.out.println(this.currentThread().getName() + "----put Obj----" + obj);
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class Consumer extends Thread {
		public TestSyncQueue queue;
		
		public void run(){

			Object obj = 0;
			while(!this.interrupted()){
				obj = queue.take();
				System.out.println(this.currentThread().getName() + "----take Obj----" + obj);
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
