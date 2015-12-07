package com.huayi.doupo.base.util.sdk.uc.session;

import java.util.HashMap;

public class ModelSession
{
	private long createTime;
	
	private long lastUseTime;
	
	private HashMap<String, Object> attributeMap = new HashMap<String, Object>();
	
	private HashMap<String, String> parameterMap = new HashMap<String, String>();

	public void setAttribute(String key, Object value)
	{
		this.attributeMap.put(key, value);
	}
	
	public Object getAttribute(String key)
	{
		return this.attributeMap.get(key);
	}
	
	public void setParameter(String key, String value) 
	{
		this.parameterMap.put(key, value);
	}
	
	public String getParameter(String key)
	{
		return this.parameterMap.get(key);
	}
	
	//=== get set ===
	public long getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(long createTime)
	{
		this.createTime = createTime;
	}

	public long getLastUseTime()
	{
		return lastUseTime;
	}

	public void setLastUseTime(long lastUseTime)
	{
		this.lastUseTime = lastUseTime;
	}


	
	
}
