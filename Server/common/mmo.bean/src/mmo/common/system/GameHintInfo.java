package mmo.common.system;

import java.text.MessageFormat;

import mmo.common.xls.AParseSheet;
import mmo.common.xls.AParseXLS;
import mmo.tools.util.string.StringSplit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * 游戏提示信息
 * 
 * @author 肖琼
 * 
 */
public class GameHintInfo {
	protected static String repeatMission           = null;
	protected static String pointLevelMission       = null;
	protected static String noPointGoods            = null;
	protected static String noEquipFaBao            = null;
	protected static String noPointMission          = null;
	protected static String noBagToCommitMission    = null;
	protected static String goodsInCooling          = null;
	protected static String pointLevelGoods         = null;
	protected static String pointProfessionGoods    = null;
	protected static String pointRealmGoods         = null;
	protected static String pointRealmAcupoint      = null;
	protected static String canNotUseGoods          = null;
	protected static String existSkillPet           = null;
	protected static String openOneStand            = null;
	protected static String pointLevelCommitMission = null;
	protected static String equipOtherGoods         = null;
	protected static String bagIsFull               = null;
	protected static String noOpenHoleGoods         = null;
	protected static String onlyStone               = null;
	protected static String noStoneRemove           = null;
	protected static String openAllHole             = null;
	protected static String wordBossInfo            = null;
	protected static String needPointGoods          = null;
	protected static String failBuyGoods            = null;
	protected static String learnedSkill            = null;
	protected static String wordBossIsDeath         = null;
	protected static String dupNoCount              = null;
	protected static String noPointDup              = null;
	protected static String openWaiGua              = null;
	protected static String unExchangeFaBao         = null;
	protected static String openHoleSuc             = null;
	protected static String canNotChangeHoleColor   = null;
	protected static String noSweepDupCount         = null;
	protected static String moneyIsMax              = null;
	protected static String monsterAtkCityStart     = null;
	protected static String monsterAtkCityEnd       = null;
	protected static String monsterAtkCityBoss      = null;
	protected static String monsterAtkOpen          = null;
	protected static String acupointNoMaterial      = null;
	protected static String noPointPetModel         = null;
	protected static String worldBossDoing =null;

	/** 重复接任务 */
	public static String getRepeatMissionInfo() {
		return repeatMission;
	}

	/** 接等级任务 */
	public static String getPointLevelMissionInfo(short level) {
		return MessageFormat.format(pointLevelMission, level);
	}

	/** 物品不存在 */
	public static String getNoPointGoodsInfo() {
		return noPointGoods;
	}

	/** 未装备法宝 */
	public static String getNoEquipFaBaoInfo() {
		return noEquipFaBao;
	}

	/** 任务不存在 */
	public static String getNoPointMissionInfo() {
		return noPointMission;
	}

	/** 背包空间不足,无法提交任务 */
	public static String getNoBagToCommitMissionInfo() {
		return noBagToCommitMission;
	}

	/** 物品正在冷却中 */
	public static String getGoodsInCoolingInfo() {
		return goodsInCooling;
	}

	/** 等级不足,无法使用物品 */
	public static String getPointLevelGoodsInfo() {
		return pointLevelGoods;
	}

	/** 职业不符,无法使用物品 */
	public static String getPointProfessionGoodsInfo() {
		return pointProfessionGoods;
	}

	/** 境界不足,无法使用物品 */
	public static String getPointRealmGoodsInfo() {
		return pointRealmGoods;
	}

	/** 境界不足,穴位无法开启 */
	public static String getPointRealmAcupointInfo() {
		return pointRealmAcupoint;
	}

	/** 物品无法使用 */
	public static String getCanNotUseGoodsInfo() {
		return canNotUseGoods;
	}

	/** 技能召唤宝宝已存在 */
	public static String getExistSkillPetInfo() {
		return existSkillPet;
	}

	/** 开启一战到底,等级不足 */
	public static String getOpenOneStandInfo() {
		return MessageFormat.format(openOneStand, GameParameter.openOneStandLevel);
	}

	/** 等级不足,无法提交任务 */
	public static String getPointLevelCommitMission(short level) {
		return MessageFormat.format(pointLevelCommitMission, level);
	}

	/** 佩戴非装备物品时,提示无法佩戴 */
	public static String getEquipOtherGoodsInfo() {
		return equipOtherGoods;
	}

	/** 背包已满,无法放入物品提示 */
	public static String getBagIsFullInfo(String bagName) {
		return MessageFormat.format(bagIsFull, bagName);
	}

	/** 打孔器不足，无法开孔提示 */
	public static String getNoOpenHoleGoods(int count) {
		return MessageFormat.format(noOpenHoleGoods, count);
	}

	/** 只有宝石才能镶嵌 */
	public static String getOnlyStoneInfo() {
		return onlyStone;
	}

	/** 没有宝石可被摘除 */
	public static String getNoStoneRemoveInfo() {
		return noStoneRemove;
	}

	/** 镶嵌孔已全部打开 */
	public static String getOpenAllHoleInfo() {
		return openAllHole;
	}

	/** 世界首领提示信息 */
	public static String getOpenWordBossInfo() {
		return wordBossInfo;
	}

	/** 某物品不足，消耗某种指定的物品可获得 */
	public static String getNeedPointGoodsInfo(String goodsName, String needGoodsName) {
		return MessageFormat.format(needPointGoods, goodsName, needGoodsName, goodsName);
	}

	/** 购买失败，提示信息 */
	public static String getFailBuyGoodsInfo() {
		return failBuyGoods;
	}

	/** 法宝技能已领悟 */
	public static String getLearnedSkillInfo() {
		return learnedSkill;
	}

	/** 进入挑战时，发现世界BOSS已经死亡时提示 */
	public static String getWordBossIsDeathInfo() {
		return MessageFormat.format(wordBossIsDeath, GameParameter.openWordBossStr);
	}

	/** 副本的挑战次数已用完 */
	public static String getDupNoCountInfo() {
		return dupNoCount;
	}

	/** 副本不存在的提示 */
	public static String getNoPointDupInfo() {
		return noPointDup;
	}

	/** 挑战副本发现开挂提示 */
	public static String getOpenWaiGuaInfo() {
		return openWaiGua;
	}

	/** 还未兑换法宝，提示 */
	public static String getUnExchangeFaBaoInfo() {
		return unExchangeFaBao;
	}

	/** 开孔成功，提示 */
	public static String getOpenHoleSucInfo() {
		return openHoleSuc;
	}

	/** 必须开启所有孔才能洗孔 */
	public static String getNotChangeHoleColorInfo() {
		return canNotChangeHoleColor;
	}

	/** 扫荡次数不够提示 */
	public static String getNoSweepDupCountInfo() {
		return noSweepDupCount;
	}

	/** 货币已经达到上限值 */
	public static String getMoneyIsMaxInfo(String moneyName) {
		return MessageFormat.format(moneyIsMax, moneyName);
	}

	/** 怪物攻城活动开启 */
	public static String getMonsterAtkCityStartInfo() {
		return monsterAtkCityStart;
	}

	/** 怪物攻城活动结束 */
	public static String getMonsterAtkCityEndInfo() {
		return monsterAtkCityEnd;
	}

	/** 怪物攻城活动BOSS刷新 */
	public static String getMonsterAtkCityBossInfo() {
		return monsterAtkCityBoss;
	}

	/** 怪物攻城开始刷怪 */
	public static String getMonsterAtkOpenInfo() {
		return monsterAtkOpen;
	}

	/** 激活穴位材料不足时提示 */
	public static String getAcupointNoMaterialInfo() {
		return acupointNoMaterial;
	}

	/** 没有指定的宠物模型 或 宠物不存在时 提示 */
	public static String getNoPointPetModelInfo() {
		return noPointPetModel;
	}

	public static final void initHintInfo(final String filePath) {
		AParseXLS hintInfoXLS = new ParseHintInfoXls(filePath);
		hintInfoXLS.execute();
	}

	public static String getWorldBossDoing() {
		return worldBossDoing;
	}
}

class ParseHintInfoXls extends AParseXLS {
	/** 文件SHEET名 */
	private static final String GAME_HINT_INFO = "游戏提示内容";

	public ParseHintInfoXls(String sourceFile) {
		super(sourceFile);
	}

	@Override
	protected boolean parse() {
		if (hwb != null) {
			SheetHintInfo hintInfo = new SheetHintInfo(getSourceFile(), hwb.getSheet(GAME_HINT_INFO));
			hintInfo.execute();
		}
		return true;
	}

}

class SheetHintInfo extends AParseSheet {
	protected final static String COL_ID    = "序号";
	protected final static String COL_VALUE = "提示内容";

	public SheetHintInfo(String sourceFile, HSSFSheet sheet) {
		super(sourceFile, sheet);
	}

	@Override
	public boolean parse() {
		HSSFRow row = null;
		int rowCount = getLastRowNum();
		for (int ri = 1; ri <= rowCount; ri++) {
			row = getRow(ri);
			if (row != null) {
				int infoId = 0;
				try {
					infoId = getIntValueRelax(row, COL_ID);
					if (infoId > 0) {
						switch (infoId) {
							case 1: {
								GameHintInfo.repeatMission = getStringValue(row, COL_VALUE);
								break;
							}
							case 2: {
								GameHintInfo.pointLevelMission = getStringValue(row, COL_VALUE);
								break;
							}
							case 3: {
								GameHintInfo.noPointGoods = getStringValue(row, COL_VALUE);
								break;
							}
							case 4: {
								GameHintInfo.noEquipFaBao = getStringValue(row, COL_VALUE);
								break;
							}
							case 5: {
								GameHintInfo.noPointMission = getStringValue(row, COL_VALUE);
								break;
							}
							case 6: {
								GameHintInfo.noBagToCommitMission = getStringValue(row, COL_VALUE);
								break;
							}
							case 7: {
								GameHintInfo.goodsInCooling = getStringValue(row, COL_VALUE);
								break;
							}
							case 8: {
								GameHintInfo.pointLevelGoods = getStringValue(row, COL_VALUE);
								break;
							}
							case 9: {
								GameHintInfo.pointProfessionGoods = getStringValue(row, COL_VALUE);
								break;
							}
							case 10: {
								GameHintInfo.pointRealmGoods = getStringValue(row, COL_VALUE);
								break;
							}
							case 11: {
								GameHintInfo.pointRealmAcupoint = getStringValue(row, COL_VALUE);
								break;
							}
							case 12: {
								GameHintInfo.canNotUseGoods = getStringValue(row, COL_VALUE);
								break;
							}
							case 13: {
								GameHintInfo.existSkillPet = getStringValue(row, COL_VALUE);
								break;
							}
							case 14: {
								GameHintInfo.openOneStand = getStringValue(row, COL_VALUE);
								break;
							}
							case 15: {
								GameHintInfo.pointLevelCommitMission = getStringValue(row, COL_VALUE);
								break;
							}
							case 16: {
								GameHintInfo.equipOtherGoods = getStringValue(row, COL_VALUE);
								break;
							}
							case 17: {
								GameHintInfo.bagIsFull = getStringValue(row, COL_VALUE);
								break;
							}
							case 18: {
								GameHintInfo.noOpenHoleGoods = getStringValue(row, COL_VALUE);
								break;
							}
							case 19: {
								GameHintInfo.onlyStone = getStringValue(row, COL_VALUE);
								break;
							}
							case 20: {
								GameHintInfo.noStoneRemove = getStringValue(row, COL_VALUE);
								break;
							}
							case 21: {
								GameHintInfo.openAllHole = getStringValue(row, COL_VALUE);
								break;
							}
							case 22: {
								GameHintInfo.wordBossInfo = MessageFormat.format(getStringValue(row, COL_VALUE), GameParameter.openWordBossStr);
								break;
							}
							case 23: {
								GameHintInfo.needPointGoods = getStringValue(row, COL_VALUE);
								break;
							}
							case 24: {
								GameHintInfo.failBuyGoods = getStringValue(row, COL_VALUE);
								break;
							}
							case 25: {
								GameHintInfo.learnedSkill = getStringValue(row, COL_VALUE);
								break;
							}
							case 26: {
								GameHintInfo.wordBossIsDeath = getStringValue(row, COL_VALUE);
								break;
							}
							case 27: {
								GameHintInfo.dupNoCount = getStringValue(row, COL_VALUE);
								break;
							}
							case 28: {
								GameHintInfo.noPointDup = getStringValue(row, COL_VALUE);
								break;
							}
							case 29: {
								GameHintInfo.openWaiGua = getStringValue(row, COL_VALUE);
								break;
							}
							case 30: {
								GameHintInfo.unExchangeFaBao = getStringValue(row, COL_VALUE);
								break;
							}
							case 31: {
								GameHintInfo.openHoleSuc = getStringValue(row, COL_VALUE);
								break;
							}
							case 32: {
								GameHintInfo.canNotChangeHoleColor = getStringValue(row, COL_VALUE);
								break;
							}
							case 33: {
								GameHintInfo.noSweepDupCount = getStringValue(row, COL_VALUE);
								break;
							}
							case 34: {
								GameHintInfo.moneyIsMax = getStringValue(row, COL_VALUE);
								break;
							}
							case 35: {
								GameHintInfo.monsterAtkCityStart = getStringValue(row, COL_VALUE);
								break;
							}
							case 36: {
								GameHintInfo.monsterAtkCityEnd = getStringValue(row, COL_VALUE);
								break;
							}
							case 37: {
								GameHintInfo.monsterAtkCityBoss = getStringValue(row, COL_VALUE);
								break;
							}
							case 38: {
								GameHintInfo.noPointPetModel = getStringValue(row, COL_VALUE);
								break;
							}
							case 39: {
								GameHintInfo.acupointNoMaterial = getStringValue(row, COL_VALUE);
								break;
							}
							case 40: {
								GameHintInfo.monsterAtkOpen = getStringValue(row, COL_VALUE);
								break;
							}
							case 41: {
								GameHintInfo.worldBossDoing = getStringValue(row, COL_VALUE);
								break;
							}
						}
					}
				} catch (Exception e) {
					error(ri, COL_ID, getStringValue(row, COL_ID), e);
				}
			}
		}
		return true;
	}

	/**
	 * 获取一行中指定列的字符串值
	 * 
	 * @param row
	 *            行
	 * @param String
	 *            columnName 列
	 * @return 字符串值，找不到则返回NULL
	 */
	public String getStringValue(HSSFRow row, String columnName) {
		return StringSplit.transformString(super.getStringValue(row, columnName));
	}
}