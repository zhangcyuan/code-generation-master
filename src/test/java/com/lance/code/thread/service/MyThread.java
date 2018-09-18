package com.lance.code.thread.service;


public class MyThread extends Thread {

	private int count;
	
	
	
	public MyThread(int count) {
		super();
		this.count = count;
	}


	@Override
	synchronized public void run() {
		while(count>0){
			super.run();
			count--;
			System.out.println("ÓÉ "+currentThread().getName()+" ¼ÆËã£¬count="+count);
		}
		
	}
}
