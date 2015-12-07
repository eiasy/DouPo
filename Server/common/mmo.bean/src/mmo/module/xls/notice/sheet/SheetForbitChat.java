package mmo.module.xls.notice.sheet;

import mmo.common.bean.notice.NoticeManager;
import mmo.common.xls.AParseSheet;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * 
 * @author 李天喜
 * 
 */
public class SheetForbitChat extends AParseSheet {
	private static final String COL_CONTENT = "公告内容";
	private NoticeManager       noticeManager;

	public SheetForbitChat(String sourceFile, HSSFSheet sheet, NoticeManager noticeManager) {
		super(sourceFile, sheet);
		this.noticeManager = noticeManager;
	}

	@Override
	public boolean parse() {
		HSSFRow row = null;
		int rowCount = getLastRowNum();
		for (int ri = 1; ri <= rowCount; ri++) {
			row = getRow(ri);
			if (row != null) {
				noticeManager.setNoticeForbitChat(getStringValue(row, COL_CONTENT));
			}
		}
		return true;
	}
}
