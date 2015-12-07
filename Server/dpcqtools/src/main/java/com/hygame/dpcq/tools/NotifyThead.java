package com.hygame.dpcq.tools;

public class NotifyThead extends Thread {
	private Thread t = null;
public NotifyThead(Thread t){
	this.t = t;
}
public void run(){
	
	try{
	synchronized(t){
		t.notify();
	}
	}catch (Exception e)
	{
		this.interrupt(); 	
	}
}
}