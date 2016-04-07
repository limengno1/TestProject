package com.limeng.threadpool.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class ProducerAndResumerThreadPool {

	@Test
	public void test() throws InterruptedException {
//		System.out.println(11);
		BlockingQueue bq = new ArrayBlockingQueue(200);
//		BlockingQueue bq = new SynchronousQueue();
//		BlockingQueue bq = new LinkedBlockingDeque();
		AtomicInteger count = new AtomicInteger(0);
		ThreadPoolExecutor producerExecutor = new ThreadPoolExecutor(2, 5, 1, TimeUnit.SECONDS, bq);
		for(int i=0; i<5; i++) {
			producerExecutor.execute(new Producer(bq, count));
		}
//		producerExecutor.execute(new Producer(bq, count));

		ThreadPoolExecutor resumerExecutor = new ThreadPoolExecutor(2, 5, 1, TimeUnit.SECONDS, bq);
		for(int i=0; i<5; i++) {
			resumerExecutor.execute(new Resumer(bq));
		}
//		resumerExecutor.execute(new Resumer(bq));
		
//		Thread.sleep(5000);
		
		while (!Thread.currentThread().isInterrupted()) {
			
		}
		
		producerExecutor.shutdown();
		resumerExecutor.shutdown();
	}
	
	class Resumer extends Thread {
		BlockingQueue bq ;
		public Resumer(BlockingQueue bq) {
			
			this.bq = bq;
		}
		
		public void run(){
			try {
				while (!Thread.currentThread().isInterrupted()) {
					Object o = bq.take();
					System.out.println(Thread.currentThread().getName() + " take : " + o);
					Thread.sleep(500);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	class Producer extends Thread {
		
		BlockingQueue bq ;
		AtomicInteger count;
		public Producer(BlockingQueue bq, AtomicInteger count) {
			
			this.bq = bq;
			this.count = count;
		}
		
		public void run(){
			try {
				while(!Thread.currentThread().isInterrupted()) {
					int i = count.addAndGet(1);
					bq.put(i);
					System.out.println(Thread.currentThread().getName() + " : put " + i);
					Thread.sleep(100);
				}
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
