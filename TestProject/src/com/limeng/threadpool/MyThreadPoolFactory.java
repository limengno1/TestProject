package com.limeng.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPoolFactory implements ThreadFactory {

	private AtomicInteger auotInt = new AtomicInteger(0);
	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r);
		t.setName(new StringBuffer("thread_").append(auotInt.getAndIncrement()).toString());
		return t;
	}

}
