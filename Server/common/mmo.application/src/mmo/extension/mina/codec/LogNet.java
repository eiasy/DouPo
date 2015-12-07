package mmo.extension.mina.codec;

import org.apache.log4j.Logger;

public class LogNet {
	private static final String   logDivide = "|";                      // ##
	private static final String   KB_SPEED  = "KB/S";
	private static final String   KB        = "KB";
	private static final float    TO_KB     = 1024 * 8;
	/** 资源跟踪 */
	private static final String   NET_PKG   = "netpkg";
	protected static final Logger loggerNet = Logger.getLogger(NET_PKG);

	public final static void loggerNetPkg(String type, int pkgMax, int pkgCount, long total, long offset, String serviceType, String desc) {
		StringBuilder sb = new StringBuilder();
		sb.append(type);// 类型
		sb.append(logDivide).append(pkgMax);// 最大包
		sb.append(logDivide).append(pkgCount);// 最大包
		sb.append(logDivide).append(total / TO_KB).append(KB);// 总流量
		sb.append(logDivide).append(total *1000/ TO_KB / offset).append(KB_SPEED);// 平均速率
		sb.append(logDivide).append(offset);// 间隔毫秒
		sb.append(logDivide).append(serviceType);// 服务类型
		sb.append(logDivide).append(desc);// 描述
		loggerNet.info(sb.toString());
	}
}
