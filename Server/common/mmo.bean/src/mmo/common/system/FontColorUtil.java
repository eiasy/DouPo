package mmo.common.system;

import mmo.common.bean.goods.AGoods;
import mmo.common.config.goods.GoodsConfig;
import mmo.common.protocol.ui.ClientUI;
import mmo.tools.util.string.StringSplit;

public class FontColorUtil {
	private static byte MAN     = 0;
	private static byte LINGXIU = 1;

	/** 获取有颜色的物品不足的提示信息*/
	public static String getInfoWithColor(AGoods goods, String otherText){
		StringBuilder sb = new StringBuilder();
		sb.append(getQualityColor(goods.getName(), goods.getQuality()));
		sb.append(otherText);
		
		return sb.toString();
	}
	
	/**
	 * 获取红色字符串
	 * */
	public static String getRedColor(String content) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClientUI.StringCommand.fontStyle(ClientUI.ColorId.RED));
		sb.append(content);
		sb.append(ClientUI.StringCommand.CMD_FONT_END);
		
		return StringSplit.transformString(sb.toString());
	}

	/**
	 * 封装场景地点颜色
	 * 
	 * @param name
	 *            场景名称
	 * @return 带颜色的场景名称
	 */
	public static String getMapColor(String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClientUI.StringCommand.fontStyle(ClientUI.ColorId.ADDRESS_COLOR));
		sb.append(name);
		sb.append(ClientUI.StringCommand.CMD_FONT_END);
		return StringSplit.transformString(sb.toString());
	}

	/**
	 * 封装角色名称的颜色
	 * 
	 * @param user
	 *            角色
	 * @return 带有颜色的角色名称
	 */
	public static String getUsernameColor(String name, byte sex) {
		StringBuilder sb = new StringBuilder();
		if (sex == MAN) {// 男角色
			sb.append(ClientUI.StringCommand.fontStyle(ClientUI.ColorId.MAN_COLOR));
		} else {
			sb.append(ClientUI.StringCommand.fontStyle(ClientUI.ColorId.WOMAN_COLOR));
		}
		sb.append(name);
		sb.append(ClientUI.StringCommand.CMD_FONT_END);
		return StringSplit.transformString(sb.toString());
	}

	/**
	 * 封装怪物名称的颜色
	 * 
	 * @param name
	 *            怪物名称
	 * @return 带颜色的怪物名称
	 */
	public static String getMonsterColor(String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClientUI.StringCommand.fontStyle(ClientUI.ColorId.MONSTERCOLOR));
		sb.append(name);
		sb.append(ClientUI.StringCommand.CMD_FONT_END);
		return StringSplit.transformString(sb.toString());
	}

	/**
	 * 封装宗门名称的颜色
	 * 
	 * @param name
	 *            宗门名称
	 * @param group
	 *            势力
	 * @return 带颜色的宗门名称
	 */
	public static String getSectNameColor(String name, byte group) {
		StringBuilder sb = new StringBuilder();
		if (group == LINGXIU) {// 灵修
			sb.append(ClientUI.StringCommand.fontStyle(ClientUI.ColorId.LINGXIU_COLOR));
		} else {
			sb.append(ClientUI.StringCommand.fontStyle(ClientUI.ColorId.MOXIU_COLOR));
		}
		sb.append(name);
		sb.append(ClientUI.StringCommand.CMD_FONT_END);
		return StringSplit.transformString(sb.toString());
	}

	/** 封装物品品阶对应的颜色 */
	public static String getQualityColor(String content, byte quality) {
		switch (quality) {
			case GoodsConfig.Quality.QUALITY_LOW:
				return getWhiteString(content);
			case GoodsConfig.Quality.QUALITY_MID:
				return getGreenString(content);
			case GoodsConfig.Quality.QUALITY_TOP:
				return getBlueString(content);
			case GoodsConfig.Quality.QUALITY_GREAT:
				return getPurpleString(content);
			case GoodsConfig.Quality.QUALITY_SHEN:
				return getOrangeString(content);
			default:
				return getWhiteString(content);
		}
	}

	/** 封装白色的字符串 */
	public static String getWhiteString(String content) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClientUI.StringCommand.fontStyle(ClientUI.Color.getQualityColor(GoodsConfig.Quality.QUALITY_LOW)));
		sb.append(content);
		sb.append(ClientUI.StringCommand.CMD_FONT_END);
		return StringSplit.transformString(sb.toString());
	}

	/** 封装绿色的字符串 */
	public static String getGreenString(String content) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClientUI.StringCommand.fontStyle(ClientUI.Color.getQualityColor(GoodsConfig.Quality.QUALITY_MID)));
		sb.append(content);
		sb.append(ClientUI.StringCommand.CMD_FONT_END);
		return StringSplit.transformString(sb.toString());
	}

	/** 封装蓝色的字符串 */
	public static String getBlueString(String content) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClientUI.StringCommand.fontStyle(ClientUI.Color.getQualityColor(GoodsConfig.Quality.QUALITY_TOP)));
		sb.append(content);
		sb.append(ClientUI.StringCommand.CMD_FONT_END);
		return StringSplit.transformString(sb.toString());
	}

	/** 封装紫色的字符串 */
	public static String getPurpleString(String content) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClientUI.StringCommand.fontStyle(ClientUI.Color.getQualityColor(GoodsConfig.Quality.QUALITY_GREAT)));
		sb.append(content);
		sb.append(ClientUI.StringCommand.CMD_FONT_END);
		return StringSplit.transformString(sb.toString());
	}

	/** 封装橙色的字符串 */
	public static String getOrangeString(String content) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClientUI.StringCommand.fontStyle(ClientUI.Color.getQualityColor(GoodsConfig.Quality.QUALITY_SHEN)));
		sb.append(content);
		sb.append(ClientUI.StringCommand.CMD_FONT_END);
		return StringSplit.transformString(sb.toString());
	}
}
