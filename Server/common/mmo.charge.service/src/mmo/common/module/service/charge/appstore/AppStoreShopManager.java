package mmo.common.module.service.charge.appstore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AppStoreShopManager {
	private final static AppStoreShopManager instance = new AppStoreShopManager();

	public final static AppStoreShopManager getInstance() {
		return instance;
	}

	private Map<Integer, AppStoreGoods> goodsList = new HashMap<Integer, AppStoreGoods>();

	private AppStoreShopManager() {

	}

	public final void addAppStoreGoods(AppStoreGoods goods) {
		if (goods != null) {
			goodsList.put(goods.getGoodsId(), goods);
		}
	}

	public final AppStoreGoods getAppStoreGoods(int goodsId) {
		return goodsList.get(goodsId);
	}

	public final AppStoreGoods getAppStoreGoods(String proId) {
		if (proId == null) {
			return null;
		}
		Collection<AppStoreGoods> values = goodsList.values();
		for (AppStoreGoods goods : values) {
			if (proId.equals(goods.getProId())) {
				return goods;
			}
		}
		return null;
	}

}
