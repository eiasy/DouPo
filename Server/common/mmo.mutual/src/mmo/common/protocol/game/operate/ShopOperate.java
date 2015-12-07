package mmo.common.protocol.game.operate;

/**
 * 摆摊操作
 * @author fanren
 *
 */
public interface ShopOperate {
	/** 指令-进入摆摊状态 */
	public static final byte cmdEnterShop = 1;
	/** 指令-脱离摆摊状态 */
	public static final byte cmdExitShop  = 2;

}
