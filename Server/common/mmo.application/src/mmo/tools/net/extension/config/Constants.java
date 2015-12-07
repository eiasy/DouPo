package mmo.tools.net.extension.config;

import java.lang.management.ManagementFactory;

public class Constants {
	public static final String APPLICATION_ID = ManagementFactory.getRuntimeMXBean().getName();
	/** 记录当前主机的CPU个数 */
	public static final int    CPU_NUM        = Runtime.getRuntime().availableProcessors();
	public static final int    PACKET_BLOCK   = 0xFFFFFF;
}
