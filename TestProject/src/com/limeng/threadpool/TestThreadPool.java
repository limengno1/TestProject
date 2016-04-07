package com.limeng.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testRejectHandler();
	}
	
	private static void testRejectHandler() {
		ArrayBlockingQueue arrayQueue = new ArrayBlockingQueue(2);
		RejectedExecutionHandler rejectedExecution = new MyRejectedExecutionHandler();
		ThreadFactory myThradPoolFactory = new MyThreadPoolFactory();
		ThreadPoolExecutor tp = new ThreadPoolExecutor(0, 2, 20, TimeUnit.SECONDS, arrayQueue, myThradPoolFactory, rejectedExecution);
		
		for(int i=0; i<10; i++){
			Runnable myRun = new MyRunnable();
			tp.execute(myRun);
		}
	}

}
