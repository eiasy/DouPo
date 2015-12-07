package mmo.common.module.service.charge.xls.appstore.sheet;

import mmo.common.module.service.charge.appstore.AppStoreGoods;
import mmo.common.module.service.charge.appstore.AppStoreShopManager;
import mmo.common.xls.AParseSheet;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * 解析协战AI
 * 
 * @author 肖琼
 * 
 */
public class SheetAppStore extends AParseSheet {
	private final static String COL_GOODS_ID = "道具编号";
	private final static String COL_TITLE    = "title";
	private final static String COL_PRO_ID   = "ProID";
	private final static String COL_IAPID    = "IAPID";
	private final static String COL_DOLLAR   = "Dollar（$）";
	private final static String COL_NOTICE   = "描述";

	public SheetAppStore(String sourceFile, HSSFSheet sheet) {
		super(sourceFile, sheet);
	}

	@Override
	public boolean parse() {
		HSSFRow row = null;

		int rowCount = getLastRowNum();
		for (int ri = 1; ri <= rowCount; ri++) {
			row = getRow(ri);
			if (row != null) {
				int goodsId = getIntValueRelax(row, COL_GOODS_ID);
				if (goodsId > 0) {
					String proId = getStringValue(row, COL_PRO_ID);
					String iapid = getStringValue(row, COL_IAPID);
					String priceDesc = getStringValue(row, COL_DOLLAR);
					priceDesc = priceDesc.substring(priceDesc.indexOf('/') + 2);
					AppStoreGoods goods = new AppStoreGoods();
					goods.setGoodsId(goodsId);
					goods.setIAPID(iapid);
					goods.setProId(proId);
					goods.setGoodsName(getStringValue(row, COL_TITLE));
					goods.setDesc(getStringValue(row, COL_NOTICE));
					goods.setPrice((int) Float.parseFloat(getStringValue(row, COL_DOLLAR)));
					AppStoreShopManager.getInstance().addAppStoreGoods(goods);
				}
			}
		}
		return true;
	}
}
