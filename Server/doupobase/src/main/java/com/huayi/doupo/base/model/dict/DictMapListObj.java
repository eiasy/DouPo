package com.huayi.doupo.base.model.dict;

/**
 * 存储字典数据中应该组织成map格式的数据
 * @author mp
 * @date 2013-10-15 下午5:59:06
 */
public class DictMapListObj {
	
	private String talbeName;
	
	private String filedIdName;
	
	private String [] orderFileds;
	

	public String[] getOrderFileds() {
		return orderFileds;
	}

	public void setOrderFileds(String[] orderFileds) {
		this.orderFileds = orderFileds;
	}

	public String getTalbeName() {
		return talbeName;
	}

	public void setTalbeName(String talbeName) {
		this.talbeName = talbeName;
	}

	public String getFiledIdName() {
		return filedIdName;
	}

	public void setFiledIdName(String filedIdName) {
		this.filedIdName = filedIdName;
	}
	
	
}
