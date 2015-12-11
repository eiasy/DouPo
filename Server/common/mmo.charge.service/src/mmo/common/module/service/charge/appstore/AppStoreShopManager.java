package mmo.common.module.service.charge.appstore;

import java.util.HashMap;
import java.util.Map;

public class AppStoreShopManager {
	private final static AppStoreShopManager instance = new AppStoreShopManager();

	public final static AppStoreShopManager getInstance() {
		return instance;
	}

	private Map<String, AppStoreGoods> goodsList = new HashMap<String, AppStoreGoods>();

	private AppStoreShopManager() {

	}

	public final void addAppStoreGoods(AppStoreGoods goods) {
		if (goods != null) {
			goodsList.put(goods.getProId(), goods);
		}
	}

	public final AppStoreGoods getAppStoreGoods(String proId) {
		return goodsList.get(proId);
	}

}
