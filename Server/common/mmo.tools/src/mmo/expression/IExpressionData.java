package mmo.expression;

public interface IExpressionData {

	short getLevel();

	short getVipLevel();

	byte getProfession();

	int getExpressParameter();

	void setExpressParameter(int parameter);

	int getExpressParameter2();

	void setExpressParameter2(int parameter2);

	/**
	 * 获取道具累计购买数量
	 * 
	 * @param goodsId
	 * @return
	 */
	int getTotalBuyCount(int shopType, int goodsId);

	/**
	 * 获取道具当天购买数量
	 * 
	 * @param goodsId
	 * @return
	 */
	int getDayBuyCount(int shopType, int goodsId);

	/**
	 * 是否强制开放
	 * 
	 * @param goodsId
	 * @return
	 */
	boolean enforceOpen(int goodsId);
}
