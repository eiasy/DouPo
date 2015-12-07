package mmo.module.xls;

import mmo.common.xls.AXLSManager;
import mmo.module.xls.notice.ParseNoticeXls;

/***
 * 负责解析XLS文件
 * 
 * @author 李天喜
 * 
 */
public class XLSFileManager extends AXLSManager {

	private final static XLSFileManager instance = new XLSFileManager();

	public final static XLSFileManager getInstance() {
		return instance;
	}

	private static final String XLS_NOTICE = "【数据】系统公告.xls";

	public void loadXLS(String path, boolean rename) {
		super.loadXLS(path, rename);
	}

	/**
	 * 加载XLS文件
	 */
	public void loadXLS() {

		// 系统公告
		ParseNoticeXls systemNotice = new ParseNoticeXls(XLS_NOTICE);
		systemNotice.execute();
	}
}
