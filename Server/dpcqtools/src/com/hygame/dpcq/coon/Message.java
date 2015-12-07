package com.hygame.dpcq.coon;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable{
	
	public Message(int header){
		this.header = header;
	}
	
	private int header;

	public int getHeader() {
		return header;
	}

	public void setHeader(int header) {
		this.header = header;
	}
	
}
