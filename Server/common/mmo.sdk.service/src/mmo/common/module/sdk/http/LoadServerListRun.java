package mmo.common.module.sdk.http;

import mmo.common.module.sdk.server.ServerList;
import mmo.common.module.sdk.server.ServerManager;
import mmo.common.module.sdk.token.run.ITokenRun;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.util.FileUtil;
import mmo.tools.util.StringUtil;

public class LoadServerListRun implements ITokenRun {

	public LoadServerListRun() {
		super();
	}

	@Override
	public void run() {
		try {
			int[] platforms = StringUtil.string2IntArray(ProjectCofigs.getParameter("server_platform"), ',');
			for (int pi = 0; pi < platforms.length; pi++) {
				int subStart = Integer.MAX_VALUE;
				String subPre = ProjectCofigs.getParameter("sub_pre_" + platforms[pi]);
				if (subPre != null) {
					try {
						subStart = Integer.parseInt(ProjectCofigs.getParameter("sub_" + platforms[pi]));
					} catch (Exception e) {
						subStart = Integer.MAX_VALUE;
					}
				}
				ServerManager.getInstance().setServerList(platforms[pi], new ServerList(FileUtil.getFileBText(ProjectCofigs.getFile("servers_all_" + platforms[pi])), FileUtil.getFileBText(ProjectCofigs.getFile("servers_suggest_" + platforms[pi])), FileUtil.getFileBText(ProjectCofigs.getFile("servers_test_" + platforms[pi])), subStart, subPre));
			}
		} catch (Exception ex) {
			LoggerError.error("热加载服务器列表异常", ex);
		}
	}
}
