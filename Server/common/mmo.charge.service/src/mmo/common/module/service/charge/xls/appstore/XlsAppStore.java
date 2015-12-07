package mmo.common.module.service.charge.xls.appstore;

import mmo.common.module.service.charge.xls.appstore.sheet.SheetAppStore;
import mmo.common.xls.AParseXLS;
import mmo.tools.log.LoggerError;

/**
 * 
 * @author 李天喜
 * 
 */
public class XlsAppStore extends AParseXLS {
	private static final String SHEET_APP_STORE = "APPStore_IAP";

	public XlsAppStore(String sourceFile, boolean redirect) {
		super(sourceFile, redirect);
	}

	@Override
	protected boolean parse() {
		if (hwb != null) {
			SheetAppStore parseTrunk = new SheetAppStore(getSourceFile(), hwb.getSheet(SHEET_APP_STORE));
			parseTrunk.execute();

		} else {
			LoggerError.messageLog.error("解析失败：" + sourceFile);
		}
		return true;
	}
}
