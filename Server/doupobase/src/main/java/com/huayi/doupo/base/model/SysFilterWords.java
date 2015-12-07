package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class SysFilterWords implements Serializable
{
	private int index;
	public String result = "";
	/**
		
	*/
	private int id;
	public int getId(){
		return id;
	}
	public void setId(int id) {
		this.id = id;
		index = 1;
		result += index + "*int*" + id + "#";
	}

	public void setId(int id, int bs) {
		this.id = id;
	}

	/**
		名称
	*/
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		index = 2;
		result += index + "*String*" + name + "#";
	}

	public void setName(String name, int bs) {
		this.name = name;
	}

	/**
		
	*/
	private int level;
	public int getLevel(){
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		index = 3;
		result += index + "*int*" + level + "#";
	}

	public void setLevel(int level, int bs) {
		this.level = level;
	}

	/**
		是否过滤聊天 1是0否
	*/
	private int isChat;
	public int getIsChat(){
		return isChat;
	}
	public void setIsChat(int isChat) {
		this.isChat = isChat;
		index = 4;
		result += index + "*int*" + isChat + "#";
	}

	public void setIsChat(int isChat, int bs) {
		this.isChat = isChat;
	}

	/**
		是否过滤playerName 1是0否
	*/
	private int isPlayerName;
	public int getIsPlayerName(){
		return isPlayerName;
	}
	public void setIsPlayerName(int isPlayerName) {
		this.isPlayerName = isPlayerName;
		index = 5;
		result += index + "*int*" + isPlayerName + "#";
	}

	public void setIsPlayerName(int isPlayerName, int bs) {
		this.isPlayerName = isPlayerName;
	}

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 6;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public SysFilterWords clone(){
		SysFilterWords extend=new SysFilterWords();
		extend.setId(this.id);
		extend.setName(this.name);
		extend.setLevel(this.level);
		extend.setIsChat(this.isChat);
		extend.setIsPlayerName(this.isPlayerName);
		extend.setVersion(this.version);
		return extend;
	}
}
