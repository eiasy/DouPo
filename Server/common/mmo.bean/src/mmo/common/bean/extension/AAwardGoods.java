package mmo.common.bean.extension;

import java.util.List;

import mmo.common.bean.goods.AGoods;
import mmo.common.bean.goods.GoodsItem;
import mmo.common.bean.role.Role;

public abstract class AAwardGoods {

	public AAwardGoods() {
	}

	abstract public int getAwardCount();

	abstract public String award(Role role, String operate, String reason, String orderFormId, int referGoodsId);

	abstract public List<AGoods> getAwardGoods(Role role, int referGoodsId);

	abstract public void award(Role role, List<AGoods> result, String operate, String project, String orderFormId, int referGoodsId);

	abstract public String call(Role role, String operate, String project, String orderFormId, int referGoodsId);

	public List<GoodsItem> getGoodsItems(Role role) {
		return null;
	}

	public GoodsItem getItemOnlyOne(Role role) {
		return null;
	}

	public List<GoodsItem> getCommonItems() {
		return null;
	}
	
	public void addCommonItems(GoodsItem item){
		
	}
	
}
