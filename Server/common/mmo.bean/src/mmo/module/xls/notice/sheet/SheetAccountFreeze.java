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
public class SheetAccountFreeze extends AParseSheet {
	private static final String COL_CONTENT = "公告内容";
	private static final String COL_TYPE    = "类型";
	private NoticeManager       noticeManager;

	public SheetAccountFreeze(String sourceFile, HSSFSheet sheet, NoticeManager noticeManager) {
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
				if ("设备".equals(getStringValue(row, COL_TYPE))) {
					noticeManager.setNoticeDeviceFreeze(getStringValue(row, COL_CONTENT));
				} else if ("账号".equals(getStringValue(row, COL_TYPE))) {
					noticeManager.setNoticeAccountFreeze(getStringValue(row, COL_CONTENT));
				} else if ("角色".equals(getStringValue(row, COL_TYPE))) {
					noticeManager.setNoticeRoleFreeze(getStringValue(row, COL_CONTENT));
				}
			}
		}
		return true;
	}
}
