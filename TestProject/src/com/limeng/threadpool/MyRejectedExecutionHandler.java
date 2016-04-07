package com.limeng.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejectedExecutionHandler implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r);
		System.out.print("taskCount is : " + executor.getTaskCount());
		System.out.println("\t rejected ... " + t.getName());
	}

}
