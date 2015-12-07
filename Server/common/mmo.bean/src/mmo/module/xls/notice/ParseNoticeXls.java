package mmo.module.xls.notice;

import mmo.common.bean.notice.NoticeManager;
import mmo.common.xls.AParseXLS;
import mmo.module.xls.notice.sheet.SheetAccountFreeze;
import mmo.module.xls.notice.sheet.SheetForbitChat;
import mmo.module.xls.notice.sheet.SheetKickOut;
import mmo.module.xls.notice.sheet.SheetLoginFirst;
import mmo.module.xls.notice.sheet.SheetLoginValidate;
import mmo.module.xls.notice.sheet.SheetSystemRepair;
import mmo.module.xls.notice.sheet.SheetWaiGua;
import mmo.tools.log.LoggerError;

/**
 * 解析活动表
 * 
 * @author 李天喜
 * 
 */
public class ParseNoticeXls extends AParseXLS {
//	private static final String SHEET_SYSTEM_NOTICE  = "系统公告";
	private static final String SHEET_SYSTEM_REPAIR  = "系统维护";
	private static final String SHEET_LOGIN_FIRST    = "首次登陆";
	private static final String SHEET_ACCONT_FREEZE  = "账号冻结";
	private static final String SHEET_KICK_OUT       = "踢下线";
	private static final String SHEET_WAI_GUA        = "外挂";
	private static final String SHEET_FORBIT_CHAT    = "禁言";
	private static final String SHEET_LOGIN_VALIDATE = "登录验证失败";

	public ParseNoticeXls(String sourceFile) {
		super(sourceFile);
	}

	@Override
	protected boolean parse() {
		if (hwb != null) {
			NoticeManager noticeManager = new NoticeManager();
			SheetAccountFreeze parseAccountFreeze = new SheetAccountFreeze(getSourceFile(), hwb.getSheet(SHEET_ACCONT_FREEZE), noticeManager);
			boolean result = parseAccountFreeze.execute();

			SheetForbitChat parseForbitChat = new SheetForbitChat(getSourceFile(), hwb.getSheet(SHEET_FORBIT_CHAT), noticeManager);
			result &= parseForbitChat.execute();

			SheetKickOut parseKickOut = new SheetKickOut(getSourceFile(), hwb.getSheet(SHEET_KICK_OUT), noticeManager);
			result &= parseKickOut.execute();

			SheetLoginFirst parseLoginFirst = new SheetLoginFirst(getSourceFile(), hwb.getSheet(SHEET_LOGIN_FIRST), noticeManager);
			result &= parseLoginFirst.execute();

			SheetLoginValidate parseLoginValidate = new SheetLoginValidate(getSourceFile(), hwb.getSheet(SHEET_LOGIN_VALIDATE), noticeManager);
			result &= parseLoginValidate.execute();

//			SheetSystemNotice parseSystemNotice = new SheetSystemNotice(getSourceFile(), hwb.getSheet(SHEET_SYSTEM_NOTICE), noticeManager);
//			result &= parseSystemNotice.execute();

			SheetSystemRepair parseSystemRepair = new SheetSystemRepair(getSourceFile(), hwb.getSheet(SHEET_SYSTEM_REPAIR), noticeManager);
			result &= parseSystemRepair.execute();

			SheetWaiGua parseWaiGua = new SheetWaiGua(getSourceFile(), hwb.getSheet(SHEET_WAI_GUA), noticeManager);
			result &= parseWaiGua.execute();
			if (result) {
				NoticeManager.setNoticeManager(noticeManager);
				return true;
			}
		} else {
			LoggerError.messageLog.error("解析失败：" + sourceFile);
		}
		return false;
	}
}
