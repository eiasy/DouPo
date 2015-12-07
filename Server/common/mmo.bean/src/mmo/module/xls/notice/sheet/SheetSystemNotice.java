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
public class SheetSystemNotice extends AParseSheet {
	private static final String COL_TITLE     = "标题";
	private static final String COL_LEVEL_MIN = "最低等级";
	private static final String COL_LEVEL_MAX = "最高等级";
	private static final String COL_CONTENT   = "公告内容";
	private NoticeManager       noticeManager;
	public SheetSystemNotice(String sourceFile, HSSFSheet sheet, NoticeManager noticeManager) {
		super(sourceFile, sheet);this.noticeManager = noticeManager;
	}

	@Override
	public boolean parse() {
		HSSFRow row = null;
		int rowCount = getLastRowNum();
		for (int ri = 1; ri <= rowCount; ri++) {
			row = getRow(ri);
			if (row != null) {
				String title = getStringValue(row, COL_TITLE);
				if (title != null) {
					short levelSub = getShortValueRelax(row, COL_LEVEL_MIN);
					short levelSup = getShortValueRelax(row, COL_LEVEL_MAX);
					String content = getStringValue(row, COL_CONTENT);
//					noticeManager.addSystemNotice(title, content, levelSub, levelSup);
				}
			}
		}
		return true;
	}
}
