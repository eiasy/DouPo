package com.huayi.doupo.base.model;

import java.io.*;

/**
	系统兑换码字典表
*/
@SuppressWarnings("serial")
public class SysCdKey implements Serializable
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
		兑换码类型Id
	*/
	private int cdKeyTypeId;
	public int getCdKeyTypeId(){
		return cdKeyTypeId;
	}
	public void setCdKeyTypeId(int cdKeyTypeId) {
		this.cdKeyTypeId = cdKeyTypeId;
		index = 2;
		result += index + "*int*" + cdKeyTypeId + "#";
	}

	public void setCdKeyTypeId(int cdKeyTypeId, int bs) {
		this.cdKeyTypeId = cdKeyTypeId;
	}

	/**
		兑换码
	*/
	private String cdk;
	public String getCdk(){
		return cdk;
	}
	public void setCdk(String cdk) {
		this.cdk = cdk;
		index = 3;
		result += index + "*String*" + cdk + "#";
	}

	public void setCdk(String cdk, int bs) {
		this.cdk = cdk;
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
		index = 4;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public SysCdKey clone(){
		SysCdKey extend=new SysCdKey();
		extend.setId(this.id);
		extend.setCdKeyTypeId(this.cdKeyTypeId);
		extend.setCdk(this.cdk);
		extend.setVersion(this.version);
		return extend;
	}
}
