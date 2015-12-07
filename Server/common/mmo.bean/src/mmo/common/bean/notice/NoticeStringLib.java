package mmo.common.bean.notice;

import java.text.MessageFormat;

import mmo.common.protocol.ui.ClientUI;
import mmo.tools.util.string.StringSplit;

public class NoticeStringLib {
	// 次日留存领取话费中抽中话费
	private static String NEXT_DAY_ACTIVITY = "[bold][art:2,000000][color:ff0000]【活动公告】：[/color][/art][/bold][bold][art:2,000000][color:fff000]{0}[/color][/art][/bold]鸿运当头，在[bold][art:2,000000][color:fff000]“领话费”[/color][/art][/bold]活动中幸运的获得[bold][art:2,000000][color:0fff00]30元话费[/color][/art][/bold]大奖，大家欢呼吧！";
	// 1.普通召唤，高级召唤，伙伴列表里的召唤，邮件里获得的伙伴
	private static String AWARD_PET_COMMON  = "恭喜[bold][art:2,000000][color:fff000]{0}[/color][/art][/bold]获得伙伴[bold][art:2,000000][color:ff00f0]【{1}】[/color][/art][/bold]";
	// 2.限时伙伴召唤时，刚好召唤到活动里的伙伴时
	private static String AWARD_PET_LIMIT   = "恭喜[bold][art:2,000000][color:fff000]{0}[/color][/art][/bold]在限时活动中斩获了强大的伙伴[bold][art:2,000000][color:ff00f0]【{1}】[/color][/art][/bold]，大家来祝福吧！";
	// 1.在藏宝图中获得时装时（包括 时装武器、时装衣服、翅膀、坐骑等的碎片时）
	private static String CAN_BAO_TU        = "恭喜[bold][art:2,000000][color:fff000]{0}[/color][/art][/bold]在[bold][art:2,000000][color:0ff000]【{1}】[/color][/art][/bold]里获得[bold][art:2,000000][color:ff00f0]【{2}】[/color][/art][/bold]，大家来祝福吧！";
	// 3.获得上阶技能书，顶阶技能书，神阶技能书，上阶经验丹，顶阶经验丹，神阶经验丹，精力丸时
	private static String COMMON_GOODS      = "恭喜[bold][art:2,000000][color:fff000]{0}[/color][/art][/bold]获得[bold][art:2,000000]{1}[/art][/bold]";
	// 在秘境中获得钥匙，血液时
	private static String SECRET_GOODS      = "恭喜[bold][art:2,000000][color:fff000]{0}[/color][/art][/bold]获得[bold][art:2,000000][color:0ff000]【{1}】{2}[/color][/art][/bold]";

	/**
	 * 获取指定物品发送公告
	 * 
	 * @param userName
	 * @param goodsName
	 * @param quality
	 * @return
	 */
	public final static String getCommonGoodsNoticeStr(String userName, String goodsName, byte quality) {
		String str = ClientUI.StringCommand.toColor(ClientUI.Color.getQualityColor(quality), goodsName);
		return StringSplit.transformString(MessageFormat.format(COMMON_GOODS, userName, str));
	}

	/**
	 * 获取秘境获取血液钥匙时提示
	 * 
	 * @param userName
	 * @param secretName
	 * @param goodsName
	 * @return
	 */
	public final static String getSecretGoodsNoticeStr(String userName, String secretName, String goodsName) {
		return StringSplit.transformString(MessageFormat.format(SECRET_GOODS, userName, secretName, goodsName));
	}

	/**
	 * 获取藏宝图获取时装物品提示
	 * 
	 * @param userName
	 * @param sceneName
	 * @param goodsName
	 * @return
	 */
	public final static String getCanBaoTuNoticeStr(String userName, String sceneName, String goodsName) {
		return StringSplit.transformString(MessageFormat.format(CAN_BAO_TU, userName, sceneName, goodsName));
	}

	/**
	 * 获取次日领取话费活动公告
	 * 
	 * @param userName
	 * @return
	 */
	public final static String getNextDayNoticeStr(String userName) {
		return StringSplit.transformString(MessageFormat.format(NEXT_DAY_ACTIVITY, userName));
	}

	/**
	 * 获取普通召唤伙伴的通告内容
	 * 
	 * @param userName
	 * @param petName
	 * @return
	 */
	public final static String getAwardPetCommonNoticeStr(String userName, String petName) {
		return StringSplit.transformString(MessageFormat.format(AWARD_PET_COMMON, userName, petName));
	}

	/**
	 * 获取限时活动获得伙伴的通告内容
	 * 
	 * @param userName
	 * @param petName
	 * @return
	 */
	public final static String getAwardPetLimitNoticeStr(String userName, String petName) {
		return StringSplit.transformString(MessageFormat.format(AWARD_PET_LIMIT, userName, petName));
	}

}
