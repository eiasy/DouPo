package com.huayi.doupo.base.config;

/**
 * 路径配置接口
 * 
 * @author mp
 * @date 2014-5-6 上午10:38:44
 */
public interface PathConfig {

	/**
	 * spring配置文件
	 */
	public static final String SPRINGXML = "/config/spring/*.xml";
	
	/**
	 * 名字文件路径
	 */
	public static final String NAME_FILE_PATH = "/config/name";

	/**
	 * 系统配置文件
	 */
	public static final String SYSCONFIG = "/config/system/sysConfig.properties";

	/**
	 * 网络请求配置文件
	 */
	public static final String REQUESTGAMEXML = "/config/socket/protocol.xml";

	/**
	 * 数据配置文件
	 */
	public static final String DATACONFIG = "/config/system/dataConfig.properties";

}
