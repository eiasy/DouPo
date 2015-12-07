package com.huayi.doupo.base.model;

import java.io.*;

/**
	玩家邮件实例表
*/
@SuppressWarnings("serial")
public class InstPlayerMail implements Serializable
{
	private int index;
	public String result = "";
	/**
		编号
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
		玩家实例Id
	*/
	private int instPlayerId;
	public int getInstPlayerId(){
		return instPlayerId;
	}
	public void setInstPlayerId(int instPlayerId) {
		this.instPlayerId = instPlayerId;
		index = 2;
		result += index + "*int*" + instPlayerId + "#";
	}

	public void setInstPlayerId(int instPlayerId, int bs) {
		this.instPlayerId = instPlayerId;
	}

	/**
		敌人(对方）名字
	*/
	private String enemyName;
	public String getEnemyName(){
		return enemyName;
	}
	public void setEnemyName(String enemyName) {
		this.enemyName = enemyName;
		index = 3;
		result += index + "*String*" + enemyName + "#";
	}

	public void setEnemyName(String enemyName, int bs) {
		this.enemyName = enemyName;
	}

	/**
		邮件类型 1-抢夺 2-竞技场 3-社交 4-系统
	*/
	private int type;
	public int getType(){
		return type;
	}
	public void setType(int type) {
		this.type = type;
		index = 4;
		result += index + "*int*" + type + "#";
	}

	public void setType(int type, int bs) {
		this.type = type;
	}

	/**
		数值 跟随邮件类型不同 1时-碎片字典Id 2时-竞技场滑落到的名次
	*/
	private int value;
	public int getValue(){
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		index = 5;
		result += index + "*int*" + value + "#";
	}

	public void setValue(int value, int bs) {
		this.value = value;
	}

	/**
		结果 0-防守失败 1-防守成功 只用于竞技场/强碎片是否防守成功,和社交和系统无关
	*/
	private int results;
	public int getResults(){
		return results;
	}
	public void setResults(int results) {
		this.results = results;
		index = 6;
		result += index + "*int*" + results + "#";
	}

	public void setResults(int results, int bs) {
		this.results = results;
	}

	/**
		邮件内容-只用于社交和系统,和战斗无关，字数限制为40
	*/
	private String content;
	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		index = 7;
		result += index + "*String*" + content + "#";
	}

	public void setContent(String content, int bs) {
		this.content = content;
	}

	/**
		版本号
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 8;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	/**
		添加时间
	*/
	private String insertTime;
	public String getInsertTime(){
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
		index = 9;
		result += index + "*String*" + insertTime + "#";
	}

	public void setInsertTime(String insertTime, int bs) {
		this.insertTime = insertTime;
	}

	/**
		更新时间
	*/
	private String updateTime;
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		index = 10;
		result += index + "*String*" + updateTime + "#";
	}

	public void setUpdateTime(String updateTime, int bs) {
		this.updateTime = updateTime;
	}

	public String getResult(){
		return result;
	}

	public InstPlayerMail clone(){
		InstPlayerMail extend=new InstPlayerMail();
		extend.setId(this.id);
		extend.setInstPlayerId(this.instPlayerId);
		extend.setEnemyName(this.enemyName);
		extend.setType(this.type);
		extend.setValue(this.value);
		extend.setResults(this.results);
		extend.setContent(this.content);
		extend.setVersion(this.version);
		extend.setInsertTime(this.insertTime);
		extend.setUpdateTime(this.updateTime);
		return extend;
	}
}
