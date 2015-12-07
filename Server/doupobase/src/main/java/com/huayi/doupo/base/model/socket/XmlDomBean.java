package com.huayi.doupo.base.model.socket;

/**
 * 协议解析对象
 * @author mp
 * @date 2014-5-6 上午11:04:27
 */
public class XmlDomBean {
	
	/**
	 * ID
	 */
	private int id;
	
	/**
	 * 类名
	 */
	private String clazz;
	
	/**
	 * 方法名
	 */
	private String method;
	
	/**
	 * 描述
	 */
	private String description;

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
