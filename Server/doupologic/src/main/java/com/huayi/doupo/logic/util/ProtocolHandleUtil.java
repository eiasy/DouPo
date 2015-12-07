package com.huayi.doupo.logic.util;

import java.util.HashMap;

import com.huayi.doupo.base.config.PathConfig;
import com.huayi.doupo.base.model.socket.XmlDomBean;
import com.huayi.doupo.base.util.logic.socket.RequestXmlUtil;


/**
 * 协议处理工具类
 * @author mp
 * @date 2012-4-25 下午4:16:01
 */
public class ProtocolHandleUtil {
	
	/**
	 * 私有构造方法
	 */
	private ProtocolHandleUtil() {
		
	}

	private static HashMap<Integer, XmlDomBean> requestXmlMap = null;

	/**
	 * 获取缓存协议文件内容的Map
	 * @author mp
	 * @date 2012-4-25 下午4:16:41
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static HashMap<Integer, XmlDomBean> getMap() throws Exception {
		if (requestXmlMap == null){
			requestXmlMap = RequestXmlUtil.loadXml(ProtocolHandleUtil.class.getResource(PathConfig.REQUESTGAMEXML).toString());
		}
		return requestXmlMap;
	}

	/**
	 * 根据协议号获取协议对象
	 * @author mp
	 * @date 2012-4-25 下午4:18:16
	 * @param key
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static XmlDomBean getXmlDomBean(int key) throws Exception {
		if (getMap().containsKey(key)) {
			return ProtocolHandleUtil.getMap().get(key);
		}
		return null;
	}
}
