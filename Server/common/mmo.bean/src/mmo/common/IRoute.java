package mmo.common;

import java.util.List;
import java.util.Map;

public interface IRoute {
	/**
	 * 把路由表写入文件
	 * 
	 * @param file
	 *            文件
	 * @param routeTable
	 *            路由表
	 */
	public void writeFile(String file, Map<Integer, List<Integer>[]> routeTable);

	/**
	 * 从文件中解析出路由表
	 * @param file
	 * @return
	 */
	public Map<Integer, List<Integer>[]> parseRoute(String file);
}
