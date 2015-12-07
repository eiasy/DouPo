package com.huayi.doupo.base.model;

import java.io.*;

/**
	活动Banner字典表
*/
@SuppressWarnings("serial")
public class DictActivityBanner implements Serializable
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
		标题
	*/
	private String title;
	public String getTitle(){
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		index = 2;
		result += index + "*String*" + title + "#";
	}

	public void setTitle(String title, int bs) {
		this.title = title;
	}

	/**
		Ui图片
	*/
	private String ui;
	public String getUi(){
		return ui;
	}
	public void setUi(String ui) {
		this.ui = ui;
		index = 3;
		result += index + "*String*" + ui + "#";
	}

	public void setUi(String ui, int bs) {
		this.ui = ui;
	}

	/**
		是否要去活动表去找[0-不去 1-去]
	*/
	private int isInActivity;
	public int getIsInActivity(){
		return isInActivity;
	}
	public void setIsInActivity(int isInActivity) {
		this.isInActivity = isInActivity;
		index = 4;
		result += index + "*int*" + isInActivity + "#";
	}

	public void setIsInActivity(int isInActivity, int bs) {
		this.isInActivity = isInActivity;
	}

	/**
		活动id-如果去活动表找的话
	*/
	private int activityId;
	public int getActivityId(){
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
		index = 5;
		result += index + "*int*" + activityId + "#";
	}

	public void setActivityId(int activityId, int bs) {
		this.activityId = activityId;
	}

	/**
		点击后的跳转
	*/
	private String skip;
	public String getSkip(){
		return skip;
	}
	public void setSkip(String skip) {
		this.skip = skip;
		index = 6;
		result += index + "*String*" + skip + "#";
	}

	public void setSkip(String skip, int bs) {
		this.skip = skip;
	}

	/**
		排名
	*/
	private String rank;
	public String getRank(){
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
		index = 7;
		result += index + "*String*" + rank + "#";
	}

	public void setRank(String rank, int bs) {
		this.rank = rank;
	}

	/**
		描述
	*/
	private String description;
	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
		index = 8;
		result += index + "*String*" + description + "#";
	}

	public void setDescription(String description, int bs) {
		this.description = description;
	}

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 9;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public DictActivityBanner clone(){
		DictActivityBanner extend=new DictActivityBanner();
		extend.setId(this.id);
		extend.setTitle(this.title);
		extend.setUi(this.ui);
		extend.setIsInActivity(this.isInActivity);
		extend.setActivityId(this.activityId);
		extend.setSkip(this.skip);
		extend.setRank(this.rank);
		extend.setDescription(this.description);
		extend.setVersion(this.version);
		return extend;
	}
}
