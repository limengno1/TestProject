package com.liemng.thread.notify;

import java.util.ArrayList;
import java.util.List;

public class TestSyncQueue {

	private List queue = new ArrayList();
	
	public synchronized void put(Object obj) {
		
		queue.add(obj);
		this.notifyAll();
//		this.notify();
	}
	
	public synchronized Object take() {
		
		if (queue.isEmpty()) {
			try {
				this.wait(1000);
				System.out.println(Thread.currentThread().getName() + "wait one second.");
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		return queue.remove(0);
	}
}
