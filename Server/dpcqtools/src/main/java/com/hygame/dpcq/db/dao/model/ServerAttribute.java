package com.hygame.dpcq.db.dao.model;

import io.netty.channel.Channel;

/**
 * 后来修改的服务器参数对象
 * @author mp
 * @date 2015-4-1 下午4:48:29
 */
public class ServerAttribute {
	
	public ServerAttribute () {
		
	}
	
	public ServerAttribute (int id, String name, int state, String ip, int port) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.ip = ip;
		this.port = port;
	}
	
	/**
	 * Id
	 */
	private int id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 状态
	 */
	private int state;
	
	/**
	 * IP
	 */
	private String ip;
	
	/**
	 * 端口
	 */
	private int port;
	
	/**
	 * Channel
	 */
	private Channel channel;
	
	
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
}
