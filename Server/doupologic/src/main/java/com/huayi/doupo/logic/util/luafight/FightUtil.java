package com.huayi.doupo.logic.util.luafight;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictAdvance;
import com.huayi.doupo.base.model.DictCard;
import com.huayi.doupo.base.model.DictCardExp;
import com.huayi.doupo.base.model.DictCardLuck;
import com.huayi.doupo.base.model.DictCoefficient;
import com.huayi.doupo.base.model.DictConstell;
import com.huayi.doupo.base.model.DictEquipAdvance;
import com.huayi.doupo.base.model.DictEquipSuit;
import com.huayi.doupo.base.model.DictEquipSuitRefer;
import com.huayi.doupo.base.model.DictEquipment;
import com.huayi.doupo.base.model.DictFightProp;
import com.huayi.doupo.base.model.DictFightSoulUpgradeProp;
import com.huayi.doupo.base.model.DictMagic;
import com.huayi.doupo.base.model.DictMagicLevel;
import com.huayi.doupo.base.model.DictPill;
import com.huayi.doupo.base.model.DictThing;
import com.huayi.doupo.base.model.DictTitle;
import com.huayi.doupo.base.model.DictTitleDetail;
import com.huayi.doupo.base.model.DictWingAdvance;
import com.huayi.doupo.base.model.DictWingLuck;
import com.huayi.doupo.base.model.DictWingStrengthen;
import com.huayi.doupo.base.model.InstEquipGem;
import com.huayi.doupo.base.model.InstPlayerCard;
import com.huayi.doupo.base.model.InstPlayerConstell;
import com.huayi.doupo.base.model.InstPlayerEquip;
import com.huayi.doupo.base.model.InstPlayerFightSoul;
import com.huayi.doupo.base.model.InstPlayerFormation;
import com.huayi.doupo.base.model.InstPlayerLineup;
import com.huayi.doupo.base.model.InstPlayerMagic;
import com.huayi.doupo.base.model.InstPlayerPartner;
import com.huayi.doupo.base.model.InstPlayerTrain;
import com.huayi.doupo.base.model.InstPlayerWing;
import com.huayi.doupo.base.model.InstPlayerYFire;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticEquipQuality;
import com.huayi.doupo.base.model.statics.StaticEquipType;
import com.huayi.doupo.base.model.statics.StaticFightProp;
import com.huayi.doupo.base.model.statics.StaticQuality;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.logic.util.luafight.FightData.CardData;
import com.huayi.doupo.logic.util.luafight.FightData.CardData.Pyros;

/**
 * 战斗相关数据类
 * 
 * @author caijinlong
 * @date 2015-11-13 下午3:39:00
 */
public class FightUtil extends DALFactory {

	private static class DictYFireProp {
//		private int id;
//		private String name;
		private int qualityId;
		private int starLevelId;
		private int equipFireCount;
		private String fightPropIds;
		private String fightPropValues;

		public DictYFireProp(int id, String name, int qualityId, int starLevelId, int equipFireCount, String fightPropIds, String fightPropValues) {
			super();
//			this.id = id;
//			this.name = name;
			this.qualityId = qualityId;
			this.starLevelId = starLevelId;
			this.equipFireCount = equipFireCount;
			this.fightPropIds = fightPropIds;
			this.fightPropValues = fightPropValues;
		}
	}

	private static class FireEquipGrid {
//		private int position;
		private int qualityId;
		private int starLevelId;

		public FireEquipGrid(int position, int qualityId, int starLevelId) {
			super();
//			this.position = position;
			this.qualityId = qualityId;
			this.starLevelId = starLevelId;
		}
	}

	private static final Map<Integer, DictYFireProp> DictYFirePropMap = new HashMap<Integer, DictYFireProp>();
	private static final Map<Integer, FireEquipGrid> FireEquipGridMap = new HashMap<Integer, FireEquipGrid>();

	/**
	 * 战力值的转换比例
	 */
	private static final Map<Integer, Float> FIGHT_VALUE_FACTOR = new HashMap<Integer, Float>();

	static {
		FIGHT_VALUE_FACTOR.put(StaticFightProp.blood, 1.0f);
		FIGHT_VALUE_FACTOR.put(StaticFightProp.wAttack, 0.53f);
		FIGHT_VALUE_FACTOR.put(StaticFightProp.fAttack, 0.53f);
		FIGHT_VALUE_FACTOR.put(StaticFightProp.dodge, 1.0f);
		FIGHT_VALUE_FACTOR.put(StaticFightProp.crit, 1.0f);
		FIGHT_VALUE_FACTOR.put(StaticFightProp.hit, 4.0f);
		FIGHT_VALUE_FACTOR.put(StaticFightProp.flex, 4.0f);
		FIGHT_VALUE_FACTOR.put(StaticFightProp.wDefense, 0.2f);
		FIGHT_VALUE_FACTOR.put(StaticFightProp.fDefense, 0.2f);

		DictYFirePropMap.put(1, new FightUtil.DictYFireProp(1, "天火一玄变", 4, 4, 1, "1;8;9", "20;20;20"));
		DictYFirePropMap.put(2, new FightUtil.DictYFireProp(2, "天火二玄变", 5, 4, 2, "1;8;9", "40;40;40"));
		DictYFirePropMap.put(3, new FightUtil.DictYFireProp(3, "天火三玄变", 6, 4, 3, "1;8;9", "80;80;80"));
		FireEquipGridMap.put(1, new FightUtil.FireEquipGrid(1, 4, 3));
		FireEquipGridMap.put(2, new FightUtil.FireEquipGrid(2, 5, 2));
		FireEquipGridMap.put(3, new FightUtil.FireEquipGrid(3, 6, 2));
	}

	private static FightUtil instance = null;

	public static FightUtil getInstance() {
		if (instance == null) {
			instance = new FightUtil();
		}
		return instance;
	}

	public static void destroyInstance() {
		instance = null;
	}

	private FightUtil() {

	}

	private List<Integer> isInSuit = null;
	private float _fightSoulValue = 0;
	private int _yokeId;
	private int _yokeLv;
	private boolean _yokeEnable;
	private Map<Integer, Float> magicPercent = null;

	/**
	 * 获取战斗相关的数据(具体参考FightData.java)
	 * 
	 * @param instPlayerId
	 *            玩家实例表ID
	 * @return FightData对象
	 */
	public FightData getFightData(int instPlayerId) {
		FightData fightData = null;
		List<InstPlayerFormation> instPlayerFormations = getInstPlayerFormationDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
		if (instPlayerFormations != null && instPlayerFormations.size() > 0) {
			fightData = new FightData();
			Map<Integer, CardData> mainForce = null;
			Map<Integer, CardData> substitute = null;
			float fightValue = 0;
			for (InstPlayerFormation instPlayerFormation : instPlayerFormations) {
				CardData cardData = fightData.new CardData();
				InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(instPlayerFormation.getInstCardId(), instPlayerId);
				DictCard dictCard = DictMap.dictCardMap.get("" + instPlayerCard.getCardId());
				Map<Integer, Float> attribute = getCardAttribute(instPlayerCard, instPlayerId);
				cardData.setName(dictCard.getName());
				cardData.setShowBanner(true);
				cardData.setCardID(dictCard.getId());
				cardData.setFrameID(instPlayerCard.getQualityId());
				if (instPlayerCard.getQualityId() >= StaticQuality.purple) {
					cardData.setJjCur(DictMap.dictStarLevelMap.get("" + instPlayerCard.getStarLevelId()).getLevel());
					cardData.setJjMax(DictMap.dictQualityMap.get("" + instPlayerCard.getQualityId()).getMaxStarLevel());
				}
				cardData.setHp(attribute.get(StaticFightProp.blood));
//				cardData.setHpCur(0); // 丹塔使用
				cardData.setHit(attribute.get(StaticFightProp.hit));
				cardData.setDodge(attribute.get(StaticFightProp.dodge));
				cardData.setCrit(attribute.get(StaticFightProp.crit));
				cardData.setRenxing(attribute.get(StaticFightProp.flex));
				cardData.setHitRatio(magicPercent.get(StaticFightProp.hit) != null ? magicPercent.get(StaticFightProp.hit) : 0);
				cardData.setDodgeRatio(magicPercent.get(StaticFightProp.dodge) != null ? magicPercent.get(StaticFightProp.dodge) : 0);
				cardData.setCritRatio(magicPercent.get(StaticFightProp.crit) != null ? magicPercent.get(StaticFightProp.crit) : 0);
				cardData.setRenxingRatio(magicPercent.get(StaticFightProp.flex) != null ? magicPercent.get(StaticFightProp.flex) : 0);
				cardData.setAttPhsc(attribute.get(StaticFightProp.wAttack));
				cardData.setAttMana(attribute.get(StaticFightProp.fAttack));
				cardData.setDefPhsc(attribute.get(StaticFightProp.wDefense));
				cardData.setDefMana(attribute.get(StaticFightProp.fDefense));
				cardData.setCritRatioDHAdd(attribute.get(StaticFightProp.addCrit));
				cardData.setCritRatioDHSub(attribute.get(StaticFightProp.cutCrit));
				cardData.setCritPercentAdd(attribute.get(StaticFightProp.addCritDam));
				cardData.setCritPercentSub(attribute.get(StaticFightProp.cutCritDam));
				cardData.setBufBurnReduction(attribute.get(StaticFightProp.cutFireDam));
				cardData.setBufPoisonReduction(attribute.get(StaticFightProp.cutPoisonDam));
				cardData.setBufCurseReduction(attribute.get(StaticFightProp.cutCurseDam));
				DictCoefficient dictCoefficient = DictMap.dictCoefficientMap.get("" + dictCard.getCoefficientId());
				cardData.setDefPhscRatio(dictCoefficient.getWDefensePer());
				cardData.setDefManaRatio(dictCoefficient.getFDefensePer());
				cardData.setAttPhscRatio(dictCoefficient.getWAttackPer());
				cardData.setAttManaRatio(dictCoefficient.getFAttackPer());
				dictCoefficient = null;
				DictCardExp dictCardExp = DictMap.dictCardExpMap.get("" + instPlayerCard.getLevel());
				cardData.setShuxingzengzhi(dictCardExp != null ? dictCardExp.getPropFormula() : 0);
				dictCardExp = null;
				DictTitle dictTitle = null;
				DictTitleDetail dictTitleDetail = DictMap.dictTitleDetailMap.get("" + instPlayerCard.getTitleDetailId());
				if (dictTitleDetail != null) {
					dictTitle = DictMap.dictTitleMap.get("" + dictTitleDetail.getTitleId());
				}
				cardData.setDamageIncrease(dictTitle != null ? dictTitle.getLinden() : 0);
				dictTitle = null;
				dictTitleDetail = null;

				cardData.setYokeID(_yokeId);
				cardData.setYokeLV(_yokeLv);
				cardData.setYokeEnable(_yokeEnable);

				cardData.setSkill1(cardData.new Sks(1, dictCard.getSkillOne()));
				cardData.setSkill2(cardData.new Sks(1, dictCard.getSkillTwo()));
				if (instPlayerCard.getQualityId() >= StaticQuality.purple) {
					cardData.setSkill3(cardData.new Sks(1, dictCard.getSkillThree()));
				}

				Pyros[] pyros = null;
				Map<Integer, InstPlayerYFire> yFireData = getCardYFireList(instPlayerCard, instPlayerId, true);
				if (yFireData != null && yFireData.size() > 0) {
					if (pyros == null) {
						pyros = new Pyros[yFireData.size()];
					}
					int _index = 0;
					for (Map.Entry<Integer, InstPlayerYFire> _fireData : yFireData.entrySet()) {
						InstPlayerYFire instPlayerYFire = _fireData.getValue();
						pyros[_index++] = cardData.new Pyros(instPlayerYFire.getFireId(), getFireState(instPlayerYFire));
						instPlayerYFire = null;
					}
				}
				cardData.setPyros(pyros);
				yFireData.clear();
				yFireData = null;
				pyros = null;

				if (instPlayerFormation.getType() == 1) {
					if (mainForce == null) {
						mainForce = new HashMap<Integer, CardData>();
					}
					mainForce.put(instPlayerFormation.getPosition(), cardData);
				} else {
					if (substitute == null) {
						substitute = new HashMap<Integer, CardData>();
					}
					substitute.put(instPlayerFormation.getPosition(), cardData);
				}
				for (Map.Entry<Integer, Float> cardAtt : attribute.entrySet()) {
					if (FIGHT_VALUE_FACTOR.get(cardAtt.getKey()) != null) {
						fightValue += cardAtt.getValue() / FIGHT_VALUE_FACTOR.get(cardAtt.getKey());
					}
				}
				fightValue += _fightSoulValue;
				attribute = null;
				dictCard = null;
				instPlayerCard = null;
				cardData = null;
			}
			fightData.setMainForce(mainForce);
			fightData.setSubstitute(substitute);
			substitute = null;
			mainForce = null;
			fightData.setPower((int) fightValue);
		}
		instPlayerFormations = null;
		return fightData;
	}

	@SuppressWarnings("unchecked")
	private Map<Integer, Float> getCardAttribute(InstPlayerCard instPlayerCard, int instPlayerId) {
		Map<Integer, Float> attribute = new HashMap<Integer, Float>();
		List<DictFightProp> dictFightProps = DictList.dictFightPropList;
		if (dictFightProps != null) {
			for (DictFightProp dictFightProp : dictFightProps) {
				attribute.put(new Integer(dictFightProp.getId()), 0f);
			}
			dictFightProps = null;
		}
		_fightSoulValue = 0;
		_yokeId = 0;
		_yokeLv = 0;
		_yokeEnable = false;
		if (magicPercent != null) {
			magicPercent.clear();
			magicPercent = null;
		}
		magicPercent = new HashMap<Integer, Float>();
		if (instPlayerCard != null) {
			DictCard dictCard = DictMap.dictCardMap.get("" + instPlayerCard.getCardId());

			// ---------------------------【基础属性数据（等级+卡边颜色+星级）】---------------------------
			attribute.put(StaticFightProp.blood, getCardBlood(instPlayerCard, dictCard));
			attribute.put(StaticFightProp.wAttack, getCardWuAttack(instPlayerCard, dictCard));
			attribute.put(StaticFightProp.wDefense, getCardWuDefense(instPlayerCard, dictCard));
			attribute.put(StaticFightProp.fAttack, getCardFaAttack(instPlayerCard, dictCard));
			attribute.put(StaticFightProp.fDefense, getCardFaDefense(instPlayerCard, dictCard));
			attribute.put(StaticFightProp.dodge, getCardDodge(instPlayerCard.getLevel(), dictCard));
			attribute.put(StaticFightProp.crit, getCardCrit(instPlayerCard.getLevel(), dictCard));
			attribute.put(StaticFightProp.hit, getCardHit(instPlayerCard.getLevel(), dictCard));
			attribute.put(StaticFightProp.flex, getCardFlex(instPlayerCard.getLevel(), dictCard));

			// ---------------------------【称号属性数据】---------------------------
			List<DictTitleDetail> dictTitleDetails = DictList.dictTitleDetailList;
			for (DictTitleDetail dictTitleDetail : dictTitleDetails) {
				if (instPlayerCard.getTitleDetailId() >= dictTitleDetail.getId()) {
					String[] _tempData = dictTitleDetail.getEffects().split(";");
					for (String obj : _tempData) {
						if (obj.length() > 0) {
							String[] _fightPropData = obj.split("_");
							Integer _fightPropId = new Integer(_fightPropData[0]);
							int _value = Integer.parseInt(_fightPropData[1]);
							attribute.put(_fightPropId, attribute.get(_fightPropId) + _value);
							_fightPropData = null;
						}
					}
					_tempData = null;
				}
			}

			if (instPlayerCard.getInTeam() == 1) {

				InstPlayerFormation instPlayerFormation = null;
				List<InstPlayerFormation> _tempIPFData = getInstPlayerFormationDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCard.getId(), instPlayerId);
				if (_tempIPFData != null && _tempIPFData.size() == 1) {
					instPlayerFormation = _tempIPFData.get(0);
				}
				_tempIPFData = null;
				// 翅膀属性数据
				List<InstPlayerWing> instPlayerWings = getInstPlayerWingDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCard.getId(), instPlayerId);

				if (instPlayerFormation != null) {

					// ---------------------------【装备基础属性数据（不包括镶嵌宝石）】---------------------------
					List<InstPlayerLineup> instPlayerLineups = getInstPlayerLineupDAL().getList("instPlayerId = " + instPlayerId + " and instPlayerFormationId = " + instPlayerFormation.getId(), instPlayerId);
					if (instPlayerLineups != null && instPlayerLineups.size() > 0) {
						for (InstPlayerLineup instPlayerLineup : instPlayerLineups) {
							Map<Integer, Float> equipAtt = getEquipAttribute(getInstPlayerEquipDAL().getModel(instPlayerLineup.getInstPlayerEquipId(), instPlayerId));
							for (Map.Entry<Integer, Float> fightPropData : equipAtt.entrySet()) {
								attribute.put(fightPropData.getKey(), attribute.get(fightPropData.getKey()) + fightPropData.getValue());
							}
							equipAtt = null;
						}
					}

					// ---------------------------【斗魂属性数据】---------------------------
					List<InstPlayerFightSoul> instPlayerFightSouls = getInstPlayerFightSoulDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCard.getId(), instPlayerId);
					if (instPlayerFightSouls != null && instPlayerFightSouls.size() > 0) {
						for (InstPlayerFightSoul instPlayerFightSoul : instPlayerFightSouls) {
							List<DictFightSoulUpgradeProp> dictFightSoulUpgradeProps = (List<DictFightSoulUpgradeProp>) DictMapList.dictFightSoulUpgradePropMap.get(instPlayerFightSoul.getFightSoulId());
							if (dictFightSoulUpgradeProps != null && dictFightSoulUpgradeProps.size() > 0) {
								for (DictFightSoulUpgradeProp dictFightSoulUpgradeProp : dictFightSoulUpgradeProps) {
									if (instPlayerFightSoul.getLevel() == dictFightSoulUpgradeProp.getLevel()) {
										Integer _fightPropId = new Integer(dictFightSoulUpgradeProp.getFightPropId());
										attribute.put(_fightPropId, attribute.get(_fightPropId) + dictFightSoulUpgradeProp.getFightPropValue());
										_fightSoulValue += dictFightSoulUpgradeProp.getFightValue();
										break;
									}
								}
							}
							dictFightSoulUpgradeProps = null;
						}
					}
					instPlayerFightSouls = null;

					// ---------------------------【翅膀属性数据】---------------------------
					if (instPlayerWings != null && instPlayerWings.size() > 0) {
						for (InstPlayerWing instPlayerWing : instPlayerWings) {
							String[] fightPropIds = null;
							String[] fightPropValues = null;
							List<DictWingStrengthen> dictWingStrengthens = (List<DictWingStrengthen>) DictMapList.dictWingStrengthenMap.get(instPlayerWing.getWingId());
							if (dictWingStrengthens != null && dictWingStrengthens.size() > 0) {
								for (DictWingStrengthen dictWingStrengthen : dictWingStrengthens) {
									if (dictWingStrengthen.getLevel() == instPlayerWing.getLevel()) {
										String _tempStr = dictWingStrengthen.getFightPropValueList();
										if (_tempStr != null && _tempStr.length() > 0) {
											fightPropValues = _tempStr.split(";");
										}
										_tempStr = null;
									}
								}
							}
							dictWingStrengthens = null;
							List<DictWingAdvance> dictWingAdvances = (List<DictWingAdvance>) DictMapList.dictWingAdvanceMap.get(instPlayerWing.getWingId());
							if (dictWingAdvances != null && dictWingAdvances.size() > 0) {
								for (DictWingAdvance dictWingAdvance : dictWingAdvances) {
									if (dictWingAdvance.getStarNum() == instPlayerWing.getStarNum()) {
										String _tempStr = dictWingAdvance.getOpenFightPropIdList();
										if (_tempStr != null && _tempStr.length() > 0) {
											fightPropIds = _tempStr.split(";");
										}
										_tempStr = null;
									}
								}
							}
							dictWingAdvances = null;
							if (fightPropIds != null && fightPropIds.length > 0) {
								for (String idString : fightPropIds) {
									Integer _fightPropId = new Integer(idString);
									float _fightPropValue = 0;
									if (fightPropValues != null && fightPropValues.length > 0) {
										for (String valueString : fightPropValues) {
											String[] _temp = valueString.split("_");
											if (_temp != null && _temp.length > 1 && Integer.parseInt(_temp[0]) == _fightPropId.intValue()) {
												_fightPropValue = Float.parseFloat(_temp[1]);
												break;
											}
											_temp = null;
										}
									}
									attribute.put(_fightPropId, attribute.get(_fightPropId) + _fightPropValue);
								}
							}
							fightPropIds = null;
							fightPropValues = null;
						}
					}
					instPlayerWings = null;

					// ---------------------------【修炼属性数据】---------------------------
					List<InstPlayerTrain> instPlayerTrains = getInstPlayerTrainDAL().getList("instPlayerId = " + instPlayerId + " and instPlayerCardId = " + instPlayerCard.getId(), instPlayerId);
					for (InstPlayerTrain instPlayerTrain : instPlayerTrains) {
						Integer _fightPropId = new Integer(instPlayerTrain.getFightPropId());
						attribute.put(_fightPropId, attribute.get(_fightPropId) + instPlayerTrain.getFightPropValue() * FIGHT_VALUE_FACTOR.get(_fightPropId));
						_fightPropId = null;
					}
					instPlayerTrains = null;

					// ---------------------------【装备套装属性】---------------------------
					if (isInSuit != null) {
						isInSuit.clear();
						isInSuit = null;
					}
					isInSuit = new ArrayList<Integer>();
					if (instPlayerLineups != null && instPlayerLineups.size() > 0) {
						for (InstPlayerLineup instPlayerLineup : instPlayerLineups) {
							InstPlayerEquip instPlayerEquip = getInstPlayerEquipDAL().getModel(instPlayerLineup.getInstPlayerEquipId(), instPlayerId);
							if (instPlayerEquip != null) {
								DictEquipment dictEquipment = DictMap.dictEquipmentMap.get("" + instPlayerEquip.getEquipId());
								if (dictEquipment != null && dictEquipment.getEquipQualityId() >= StaticEquipQuality.blue) { // 只有紫色以上可能有套装属性
									boolean isSame = false;
									for (int value : isInSuit) {
										if (value == instPlayerEquip.getId()) {
											isSame = true;
											break;
										}
									}
									if (!isSame) {
										Map<Integer, Float> suitAtt = getEquipSuitAttribute(instPlayerLineups, instPlayerEquip, instPlayerId);
										if (suitAtt != null && suitAtt.size() > 0) {
											for (Map.Entry<Integer, Float> _suitAtt : suitAtt.entrySet()) {
												attribute.put(_suitAtt.getKey(), attribute.get(_suitAtt.getKey()) + _suitAtt.getValue());
											}
										}
										suitAtt = null;
									}
								}
								dictEquipment = null;
							}
							instPlayerEquip = null;
						}
					}
					isInSuit.clear();
					isInSuit = null;

					// ---------------------------【缘分属性数据】---------------------------
					List<DictCardLuck> dictCardLucks = (List<DictCardLuck>) DictMapList.dictCardLuckMap.get(instPlayerCard.getCardId());
					if (dictCardLucks != null && dictCardLucks.size() > 0) {
						for (DictCardLuck dictCardLuck : dictCardLucks) {
							if (isCardLuck(dictCardLuck, instPlayerCard.getId(), instPlayerId)) {
								String[] luckFightValues = dictCardLuck.getFightValues().split(";");
								if (luckFightValues != null && luckFightValues.length > 0) {
									for (String lfvStr : luckFightValues) {
										String[] temp = lfvStr.split("_");
										if (temp != null && temp.length > 1) {
											Integer _fightPropId = new Integer(temp[0]);
											float _luckAddValue = Float.parseFloat(temp[1]) / 100;
											attribute.put(_fightPropId, (float) (attribute.get(_fightPropId) + Math.floor(attribute.get(_fightPropId) * _luckAddValue)));
										}
										temp = null;
									}
								}
								luckFightValues = null;
							}
						}
					}
					dictCardLucks = null;

					// ---------------------------【装备镶嵌宝石属性数据】---------------------------
					for (InstPlayerLineup instPlayerLineup : instPlayerLineups) {
						Map<Integer, Float> equipGemAtt = getEquipGemAttribute(getInstPlayerEquipDAL().getModel(instPlayerLineup.getInstPlayerEquipId(), instPlayerId), instPlayerId);
						for (Map.Entry<Integer, Float> fightPropData : equipGemAtt.entrySet()) {
							attribute.put(fightPropData.getKey(), attribute.get(fightPropData.getKey()) + fightPropData.getValue());
						}
						equipGemAtt = null;
					}
					instPlayerLineups = null;

					// ---------------------------【法宝和功法属性数据（具体值）】---------------------------
					List<InstPlayerMagic> instPlayerMagics = getInstPlayerMagicDAL().getList("instPlayerId = " + instPlayerId + " and instCardId = " + instPlayerCard.getId(), instPlayerId);
					if (instPlayerMagics != null && instPlayerMagics.size() > 0) {
						for (InstPlayerMagic instPlayerMagic : instPlayerMagics) {
							DictMagicLevel dictMagicLevel = DictMap.dictMagicLevelMap.get("" + instPlayerMagic.getMagicLevelId());
							int magicLevel = (dictMagicLevel != null ? dictMagicLevel.getLevel() : 0);
							dictMagicLevel = null;
							DictMagic dictMagic = DictMap.dictMagicMap.get("" + instPlayerMagic.getMagicId());
							if (dictMagic != null) {
								for (int _valueI = 1; _valueI <= 6; _valueI++) {
									String _valueStr = "_";
									switch (_valueI) {
									case 1:
										_valueStr = dictMagic.getValue1();
										break;
									case 2:
										_valueStr = dictMagic.getValue2();
										break;
									case 3:
										_valueStr = dictMagic.getValue3();
										break;
									case 4:
										_valueStr = dictMagic.getValue4();
										break;
									case 5:
										_valueStr = dictMagic.getValue5();
										break;
									case 6:
										_valueStr = dictMagic.getValue6();
										break;
									}
									_valueStr = (_valueStr != null ? _valueStr : "_");
									String[] _values = _valueStr.split("_");
									if (_values != null && _values.length > 0) {
										if (_valueI <= 3) {
											Integer _fightPropId = new Integer(_values[1]);
											// 功法/法宝具体属性值计算公式 (初始值 + (功法/法宝等级 - 1) * 加成值)
											Float _fightPropValue = Float.parseFloat(_values[2]) + (magicLevel - 1) * Float.parseFloat(_values[3]);
											if (Integer.parseInt(_values[0]) == 1) {
												Float _prevMagicPercent = magicPercent.get(_fightPropId);
												magicPercent.put(_fightPropId, (_prevMagicPercent != null ? _prevMagicPercent : 0) + _fightPropValue);
											} else {
												attribute.put(_fightPropId, attribute.get(_fightPropId) + _fightPropValue);
											}
										} else {
											if ((_valueI == 4 && magicLevel >= 10) || (_valueI == 5 && magicLevel >= 20) || (_valueI == 6 && magicLevel >= 40)) {
												Integer _fightPropId = new Integer(_values[0]);
												Float _prevMagicPercent = magicPercent.get(_fightPropId);
												magicPercent.put(_fightPropId, (_prevMagicPercent != null ? _prevMagicPercent : 0) + Float.parseFloat(_values[1]));
											}
										}
									}
									_values = null;
								}
							}
							dictMagic = null;
						}
					}
					instPlayerMagics = null;

				}

				// ---------------------------【命宫属性数据】---------------------------
				String[] constellIds = instPlayerCard.getInstPlayerConstells().split(";");
				if (constellIds != null && constellIds.length > 0) {
					for (String constellId : constellIds) {
						if (constellId.length() > 0) {
							InstPlayerConstell instPlayerConstell = getInstPlayerConstellDAL().getModel(Integer.parseInt(constellId), instPlayerId);
							if (instPlayerConstell != null) {
								DictConstell dictConstell = DictMap.dictConstellMap.get("" + instPlayerConstell.getConstellId());
								if (dictConstell != null) {
									String[] _useIds = instPlayerConstell.getPills().split(";");
									String[] _pillIds = dictConstell.getPills().split(";");
									if (_useIds != null && _pillIds != null && _useIds.length == _pillIds.length) {
										for (int i = 0; i < _pillIds.length; i++) {
											DictPill dictPill = DictMap.dictPillMap.get(_pillIds[i]);
											if (dictPill != null && Integer.parseInt(_useIds[i]) == 1) {
												if (dictPill.getTableTypeId() == StaticTableType.DictFightProp) {
													Integer _fightPropId = new Integer(dictPill.getTableFieldId());
													attribute.put(_fightPropId, attribute.get(_fightPropId) + dictPill.getValue());
												}
											}
											dictPill = null;
										}
									}
									_useIds = null;
									_pillIds = null;
								}
								dictConstell = null;
							}
							instPlayerConstell = null;
						}
					}
				}
				constellIds = null;

				// ---------------------------【法宝和功法属性数据（百分比值）】---------------------------
				for (Map.Entry<Integer, Float> _magicPercent : magicPercent.entrySet()) {
					Integer _fightPropId = _magicPercent.getKey();
					if (_fightPropId.intValue() == StaticFightProp.blood.intValue() || _fightPropId.intValue() == StaticFightProp.wAttack.intValue() || _fightPropId.intValue() == StaticFightProp.fAttack.intValue() || _fightPropId.intValue() == StaticFightProp.wDefense.intValue() || _fightPropId.intValue() == StaticFightProp.fDefense.intValue()) {
						attribute.put(_fightPropId, (float) Math.floor(attribute.get(_fightPropId) * (1 + _magicPercent.getValue() / 100)));
					}
				}
//				magicPercent.clear();
//				magicPercent = null;

				// ---------------------------【异火属性数据】---------------------------
				Map<Integer, Float> fireAtt = new HashMap<Integer, Float>();
				Map<Integer, InstPlayerYFire> instPlayerYFires = getCardYFireList(instPlayerCard, instPlayerId, false);
				for (Map.Entry<Integer, DictYFireProp> dictYFireProp : DictYFirePropMap.entrySet()) {
					boolean _flag = false;
					if (instPlayerCard.getQualityId() >= dictYFireProp.getValue().qualityId) {
						if (instPlayerCard.getQualityId() == dictYFireProp.getValue().qualityId) {
							if (instPlayerCard.getStarLevelId() >= dictYFireProp.getValue().starLevelId) {
								_flag = true;
							}
						} else {
							_flag = true;
						}
					}
					if (_flag && instPlayerYFires.size() >= dictYFireProp.getValue().equipFireCount) {
						String[] fightPropIds = dictYFireProp.getValue().fightPropIds.split(";");
						String[] fightPropValues = dictYFireProp.getValue().fightPropValues.split(";");
						for (int i = 0; i < fightPropIds.length; i++) {
							Integer _fightPropId = new Integer(fightPropIds[i]);
							Float _prevFireValue = fireAtt.get(_fightPropId);
							fireAtt.put(_fightPropId, (_prevFireValue != null ? _prevFireValue : 0) + Float.parseFloat(fightPropValues[i]));
						}
						fightPropIds = null;
						fightPropValues = null;
					}
				}
				instPlayerYFires = null;
				for (Map.Entry<Integer, Float> _fireAtt : fireAtt.entrySet()) {
					Float _fightPropValue = attribute.get(_fireAtt.getKey()) * (1 + _fireAtt.getValue() / 100);
					attribute.put(_fireAtt.getKey(), _fightPropValue);
				}
				fireAtt = null;

				float _titleHPAdd = 0;
				// ---------------------------【境界称号的生命加成属性数据】---------------------------
				DictTitleDetail dictTitleDetail = DictMap.dictTitleDetailMap.get("" + instPlayerCard.getTitleDetailId());
				if (dictTitleDetail != null) {
					DictTitle dictTitle = DictMap.dictTitleMap.get("" + dictTitleDetail.getTitleId());
					if (dictTitle != null) {

						if (dictTitle.getDescription() != null && dictTitle.getDescription().length() > 0) {
							_titleHPAdd = Float.parseFloat(dictTitle.getDescription());
						}

					}
					dictTitle = null;
				}
				dictTitleDetail = null;

				// ---------------------------【翅膀天赋属性数据】---------------------------
				if (instPlayerWings != null && instPlayerWings.size() > 0) {
					List<DictWingLuck> dictWingLucks = (List<DictWingLuck>) DictMapList.dictWingLuckMap.get(instPlayerCard.getCardId());
					if (dictWingLucks != null && dictWingLucks.size() > 0) {
						for (InstPlayerWing instPlayerWing : instPlayerWings) {
							_yokeId = instPlayerWing.getWingId();
							_yokeLv = instPlayerWing.getLevel();
							for (DictWingLuck dictWingLuck : dictWingLucks) {
								String[] lucksArray = null;
								String[] fightValuesArray = null;
								String lucks = dictWingLuck.getLucks();
								String fightValues = dictWingLuck.getFightValues();
								if (lucks != null && lucks.length() > 0) {
									lucksArray = lucks.split(";");
								}
								if (fightValues != null && fightValues.length() > 0) {
									fightValuesArray = fightValues.split(";");
								}
								if (lucksArray != null && lucksArray.length > 1 && fightValuesArray != null && fightValuesArray.length > 1) {
									if (instPlayerWing.getStarNum() >= Integer.parseInt(lucksArray[0])) {
										String[] _temp = fightValuesArray[0].split("_");
										if (_temp != null && _temp.length > 1) {
											Integer _fightPropId = new Integer(_temp[0]);
											if (_fightPropId.intValue() == StaticFightProp.blood.intValue()) {
												_titleHPAdd += Float.parseFloat(_temp[1]) * 100;
											} else {
												attribute.put(_fightPropId, attribute.get(_fightPropId) + Float.parseFloat(_temp[1]) / 2);
											}
										}
										_temp = null;
									}
									if (instPlayerWing.getStarNum() >= Integer.parseInt(lucksArray[1])) {
										String[] _temp = fightValuesArray[1].split("_");
										if (_temp != null && _temp.length > 1) {
											Integer _fightPropId = new Integer(_temp[0]);
											if (_fightPropId.intValue() == StaticFightProp.blood.intValue()) {
												_titleHPAdd += Float.parseFloat(_temp[1]) * 100;
											} else {
												attribute.put(_fightPropId, attribute.get(_fightPropId) + Float.parseFloat(_temp[1]) / 2);
											}
										}
										_temp = null;
									}
									// ---战斗中使用的
									if (lucksArray[2] != null && _yokeId == Integer.parseInt(lucksArray[2])) {
										_yokeEnable = true;
									}
								}
								fightValuesArray = null;
								lucksArray = null;
							}
						}
					}
					dictWingLucks = null;
				}
				instPlayerWings = null;

				// 称号和翅膀生命统一加百分比
				attribute.put(StaticFightProp.blood, attribute.get(StaticFightProp.blood) * (1 + _titleHPAdd / 100));
			}

		}

		return attribute;
	}

	@SuppressWarnings("unchecked")
	private Object getDictData(int _qualityId, int _starLevelId, DictCard _dictCard) {
		Object dictData = null;
		if (_qualityId == StaticQuality.white || (_dictCard.getQualityId() == _qualityId && _dictCard.getStarLevelId() == _starLevelId)) {
			dictData = _dictCard;
		} else {
			_starLevelId--;
			if (_starLevelId == 0) {
				_qualityId--;
				_starLevelId = DictMap.dictQualityMap.get("" + _qualityId).getMaxStarLevel() + 1;
			}
			List<DictAdvance> dictAdvances = (List<DictAdvance>) DictMapList.dictAdvanceMap.get(_dictCard.getId());
			for (DictAdvance dictAdvance : dictAdvances) {
				if (dictAdvance.getQualityId() == _qualityId && dictAdvance.getStarLevelId() == _starLevelId) {
					dictData = dictAdvance;
					break;
				}
			}
			dictAdvances = null;
		}
		return dictData;
	}

	/**
	 * 获取卡牌的血量值
	 * 
	 * @param _instPlayerCard
	 *            卡牌实例表对象
	 * @param _dictCard
	 *            卡牌字典数据
	 * @return 卡牌的基础血量值（等级+卡边颜色+星级）
	 */
	private float getCardBlood(InstPlayerCard _instPlayerCard, DictCard _dictCard) {
		int _cardLevel = _instPlayerCard.getLevel();
		int _qualityId = _instPlayerCard.getQualityId();
		int _starLevelId = _instPlayerCard.getStarLevelId();
		Object dictData = getDictData(_qualityId, _starLevelId, _dictCard);
		if (dictData != null) {
			if (dictData instanceof DictCard) {
				DictCard tempData = (DictCard) dictData;
				return tempData.getBlood() + (_cardLevel - 1) * tempData.getBloodAdd();
			} else if (dictData instanceof DictAdvance) {
				DictAdvance tempData = (DictAdvance) dictData;
				return tempData.getBlood() + (_cardLevel - 1) * tempData.getBloodAdd();
			}
		}
		dictData = null;
		return 0;
	}

	/**
	 * 获取卡牌的物攻值
	 * 
	 * @param _instPlayerCard
	 *            卡牌实例表对象
	 * @param _dictCard
	 *            卡牌字典数据
	 * @return 卡牌的基础物攻值（等级+卡边颜色+星级）
	 */
	private float getCardWuAttack(InstPlayerCard _instPlayerCard, DictCard _dictCard) {
		int _cardLevel = _instPlayerCard.getLevel();
		int _qualityId = _instPlayerCard.getQualityId();
		int _starLevelId = _instPlayerCard.getStarLevelId();
		Object dictData = getDictData(_qualityId, _starLevelId, _dictCard);
		if (dictData != null) {
			if (dictData instanceof DictCard) {
				DictCard tempData = (DictCard) dictData;
				return tempData.getWuAttack() + (_cardLevel - 1) * tempData.getWuAttackAdd();
			} else if (dictData instanceof DictAdvance) {
				DictAdvance tempData = (DictAdvance) dictData;
				return tempData.getWuAttack() + (_cardLevel - 1) * tempData.getWuAttackAdd();
			}
		}
		dictData = null;
		return 0;
	}

	/**
	 * 获取卡牌的物防值
	 * 
	 * @param _instPlayerCard
	 *            卡牌实例表对象
	 * @param _dictCard
	 *            卡牌字典数据
	 * @return 卡牌的基础物防值（等级+卡边颜色+星级）
	 */
	private float getCardWuDefense(InstPlayerCard _instPlayerCard, DictCard _dictCard) {
		int _cardLevel = _instPlayerCard.getLevel();
		int _qualityId = _instPlayerCard.getQualityId();
		int _starLevelId = _instPlayerCard.getStarLevelId();
		Object dictData = getDictData(_qualityId, _starLevelId, _dictCard);
		if (dictData != null) {
			if (dictData instanceof DictCard) {
				DictCard tempData = (DictCard) dictData;
				return tempData.getWuDefense() + (_cardLevel - 1) * tempData.getWuDefenseAdd();
			} else if (dictData instanceof DictAdvance) {
				DictAdvance tempData = (DictAdvance) dictData;
				return tempData.getWuDefense() + (_cardLevel - 1) * tempData.getWuDefenseAdd();
			}
		}
		dictData = null;
		return 0;
	}

	/**
	 * 获取卡牌的法攻值
	 * 
	 * @param _instPlayerCard
	 *            卡牌实例表对象
	 * @param _dictCard
	 *            卡牌字典数据
	 * @return 卡牌的基础法攻值（等级+卡边颜色+星级）
	 */
	private float getCardFaAttack(InstPlayerCard _instPlayerCard, DictCard _dictCard) {
		int _cardLevel = _instPlayerCard.getLevel();
		int _qualityId = _instPlayerCard.getQualityId();
		int _starLevelId = _instPlayerCard.getStarLevelId();
		Object dictData = getDictData(_qualityId, _starLevelId, _dictCard);
		if (dictData != null) {
			if (dictData instanceof DictCard) {
				DictCard tempData = (DictCard) dictData;
				return tempData.getFaAttack() + (_cardLevel - 1) * tempData.getFaAttackAdd();
			} else if (dictData instanceof DictAdvance) {
				DictAdvance tempData = (DictAdvance) dictData;
				return tempData.getFaAttack() + (_cardLevel - 1) * tempData.getFaAttackAdd();
			}
		}
		dictData = null;
		return 0;
	}

	/**
	 * 获取卡牌的法防值
	 * 
	 * @param _instPlayerCard
	 *            卡牌实例表对象
	 * @param _dictCard
	 *            卡牌字典数据
	 * @return 卡牌的基础法防值（等级+卡边颜色+星级）
	 */
	private float getCardFaDefense(InstPlayerCard _instPlayerCard, DictCard _dictCard) {
		int _cardLevel = _instPlayerCard.getLevel();
		int _qualityId = _instPlayerCard.getQualityId();
		int _starLevelId = _instPlayerCard.getStarLevelId();
		Object dictData = getDictData(_qualityId, _starLevelId, _dictCard);
		if (dictData != null) {
			if (dictData instanceof DictCard) {
				DictCard tempData = (DictCard) dictData;
				return tempData.getFaDefense() + (_cardLevel - 1) * tempData.getFaDefenseAdd();
			} else if (dictData instanceof DictAdvance) {
				DictAdvance tempData = (DictAdvance) dictData;
				return tempData.getFaDefense() + (_cardLevel - 1) * tempData.getFaDefenseAdd();
			}
		}
		dictData = null;
		return 0;
	}

	/**
	 * 获取卡牌的命中值
	 * 
	 * @param _cardLevel
	 *            卡牌等级
	 * @param _dictCard
	 *            卡牌字典数据
	 * @return 卡牌的基础命中值（等级）
	 */
	private float getCardHit(int _cardLevel, DictCard _dictCard) {
		return _dictCard.getHit() + (_cardLevel - 1) * _dictCard.getHitAdd();
	}

	/**
	 * 获取卡牌的闪避值
	 * 
	 * @param _cardLevel
	 *            卡牌等级
	 * @param _dictCard
	 *            卡牌字典数据
	 * @return 卡牌的基础闪避值（等级）
	 */
	private float getCardDodge(int _cardLevel, DictCard _dictCard) {
		return _dictCard.getDodge() + (_cardLevel - 1) * _dictCard.getDodgeAdd();
	}

	/**
	 * 获取卡牌的暴击值
	 * 
	 * @param _cardLevel
	 *            卡牌等级
	 * @param _dictCard
	 *            卡牌字典数据
	 * @return 卡牌的基础暴击值（等级）
	 */
	private float getCardCrit(int _cardLevel, DictCard _dictCard) {
		return _dictCard.getCrit() + (_cardLevel - 1) * _dictCard.getCritAdd();
	}

	/**
	 * 获取卡牌的抗暴值
	 * 
	 * @param _cardLevel
	 *            卡牌等级
	 * @param _dictCard
	 *            卡牌字典数据
	 * @return 卡牌的基础抗暴值（等级）
	 */
	private float getCardFlex(int _cardLevel, DictCard _dictCard) {
		return _dictCard.getFlex() + (_cardLevel - 1) * _dictCard.getFlexAdd();
	}

	/**
	 * 获取装备的基础属性值
	 * 
	 * @param _instPlayerEquip
	 *            装备实例表对象
	 * @return 装备的基础属性值（强化+进阶）
	 */
	private Map<Integer, Float> getEquipAttribute(InstPlayerEquip _instPlayerEquip) {
		Map<Integer, Float> attribute = new HashMap<Integer, Float>();
		DictEquipment dictEquipment = DictMap.dictEquipmentMap.get("" + _instPlayerEquip.getEquipId());
		float _attAddValue = 0;
		List<DictEquipAdvance> dictEquipAdvances = DictList.dictEquipAdvanceList;
		for (DictEquipAdvance dictEquipAdvance : dictEquipAdvances) {
			if (_instPlayerEquip.getEquipTypeId() == dictEquipAdvance.getEquipTypeId() && dictEquipment.getEquipQualityId() == dictEquipAdvance.getEquipQualityId() && _instPlayerEquip.getEquipAdvanceId() == dictEquipAdvance.getId()) {
				_attAddValue += Float.parseFloat(dictEquipAdvance.getPropAndAdd());
			}
		}
		String[] propData = dictEquipment.getPropAndAdd().split(";");
		for (String obj : propData) {
			String[] _fightPropData = obj.split("_");
			Integer _fightPropId = new Integer(_fightPropData[0]);
			int _initValue = Integer.parseInt(_fightPropData[1]);
			int _addValue = Integer.parseInt(_fightPropData[2]);
			// 装备强化计算公式 (初始值 + 装备等级 * 加成值)
			float _value = _initValue + _instPlayerEquip.getLevel() * (_addValue + _attAddValue);
			Float _prevValue = attribute.get(_fightPropId);
			attribute.put(_fightPropId, (_prevValue != null ? _prevValue : 0) + _value);
			_fightPropData = null;
		}
		propData = null;
		return attribute;
	}

	/**
	 * 判断卡牌缘分是否点亮
	 * 
	 * @param _dictCardLuck
	 *            卡牌缘分字典表对象
	 * @param _instCardId
	 *            玩家卡牌实例表ID
	 * @param _instPlayerId
	 *            玩家实例表ID
	 * @return true:缘分点亮，false:未点亮
	 */
	private boolean isCardLuck(DictCardLuck _dictCardLuck, int _instCardId, int _instPlayerId) {
		boolean _isLuck = false;
		String[] lucks = _dictCardLuck.getLucks().split(";");
		if (lucks.length > 0) {
			boolean[] _luckFlags = new boolean[lucks.length];
			for (int i = 0; i < lucks.length; i++) {
				String[] temp = lucks[i].split("_");
				if (temp.length > 1) {
					int tableTypeId = Integer.parseInt(temp[0]);
					int tableFieldId = Integer.parseInt(temp[1]);
					if (tableTypeId == StaticTableType.DictCard.intValue()) {
						List<InstPlayerFormation> ipf = getInstPlayerFormationDAL().getList("instPlayerId = " + _instPlayerId + " and cardId = " + tableFieldId, _instPlayerId);
						if (ipf != null && ipf.size() > 0) {
							_luckFlags[i] = true;
						}
						ipf = null;
						List<InstPlayerPartner> ipp = getInstPlayerPartnerDAL().getList("instPlayerId = " + _instPlayerId + " and cardId = " + tableFieldId, _instPlayerId);
						if (ipp != null && ipp.size() > 0) {
							_luckFlags[i] = true;
						}
						ipp = null;
					} else if (tableTypeId == StaticTableType.DictMagic) {
						List<InstPlayerMagic> ipm = getInstPlayerMagicDAL().getList("instPlayerId = " + _instPlayerId + " and instCardId = " + _instCardId + " and magicId = " + tableFieldId, _instPlayerId);
						if (ipm != null && ipm.size() > 0) {
							_luckFlags[i] = true;
						}
						ipm = null;
					}
				}
				temp = null;
			}
			_isLuck = true;
			for (boolean flag : _luckFlags) {
				if (!flag) {
					_isLuck = false;
					break;
				}
			}
			_luckFlags = null;
		}
		lucks = null;
		return _isLuck;
	}

	/**
	 * 获取装备镶嵌的宝石属性值
	 * 
	 * @param _instPlayerEquip
	 *            装备实例表对象
	 * @param _instPlayerId
	 *            玩家实例表ID
	 * @return 装备镶嵌的宝石属性值
	 */
	private Map<Integer, Float> getEquipGemAttribute(InstPlayerEquip _instPlayerEquip, int _instPlayerId) {
		Map<Integer, Float> attribute = new HashMap<Integer, Float>();
		List<InstEquipGem> instEquipGems = getInstEquipGemDAL().getList("instPlayerId = " + _instPlayerId + " and instPlayerEquipId = " + _instPlayerEquip.getId() + " and thingId > 0", _instPlayerId);
		if (instEquipGems != null && instEquipGems.size() > 0) {
			for (InstEquipGem instEquipGem : instEquipGems) {
				DictThing dictThing = DictMap.dictThingMap.get("" + instEquipGem.getThingId());
				if (dictThing != null) {
					Integer _fightPropId = new Integer(dictThing.getFightPropId());
					Float _prevValue = attribute.get(_fightPropId);
					attribute.put(_fightPropId, (_prevValue != null ? _prevValue : 0) + dictThing.getFightPropValue());
				}
				dictThing = null;
			}
		}
		instEquipGems = null;
		return attribute;
	}

	/**
	 * 获取卡牌装备的异火
	 * 
	 * @param _instPlayerCard
	 *            玩家卡牌实例表对象
	 * @param _instPlayerId
	 *            玩家实例表ID
	 * @param _isCheck
	 *            是否检测品阶值
	 * @return 该卡牌装备的异火实例对象集合
	 */
	private Map<Integer, InstPlayerYFire> getCardYFireList(InstPlayerCard _instPlayerCard, int _instPlayerId, boolean _isCheck) {
		Map<Integer, InstPlayerYFire> yFireData = new HashMap<Integer, InstPlayerYFire>();
		List<InstPlayerYFire> instPlayerYFires = getInstPlayerYFireDAL().getList("instPlayerId = " + _instPlayerId, _instPlayerId);
		if (instPlayerYFires != null && instPlayerYFires.size() > 0) {
			for (InstPlayerYFire instPlayerYFire : instPlayerYFires) {
				String owner = instPlayerYFire.getOwner();
				if (owner != null && owner.length() > 0) {
					String[] ownerStr = owner.split(";");
					if (ownerStr != null && ownerStr.length > 0) {
						for (String _owner : ownerStr) {
							String[] _temp = _owner.split("_");
							if (_temp != null && _temp.length > 1) {
								if (Integer.parseInt(_temp[0]) == _instPlayerCard.getId()) {
									int _position = Integer.parseInt(_temp[1]);
									if (_isCheck) {
										boolean _gridState = false;
										FireEquipGrid _fireEquipGrid = FireEquipGridMap.get(_position);
										if (_fireEquipGrid != null) {
											if (_instPlayerCard.getQualityId() >= _fireEquipGrid.qualityId) {
												if (_instPlayerCard.getQualityId() == _fireEquipGrid.qualityId) {
													if (_instPlayerCard.getStarLevelId() >= _fireEquipGrid.starLevelId) {
														_gridState = true;
													}
												} else {
													_gridState = true;
												}
											}
											if (_gridState) {
												yFireData.put(_position, instPlayerYFire);
												break;
											}
										}
										_fireEquipGrid = null;
									} else {
										yFireData.put(_position, instPlayerYFire);
										break;
									}
								}
							}
							_temp = null;
						}
					}
					ownerStr = null;
					if (yFireData.size() == FireEquipGridMap.size())
						break;
				}
				owner = null;
			}
		}
		return yFireData;
	}

	/**
	 * 获取异火的状态
	 * 
	 * @param _instPlayerYFire
	 *            异火实例表对象
	 * @return 异火的状态（0-未激活，1-旺盛，2-狂暴）
	 */
	private int getFireState(InstPlayerYFire _instPlayerYFire) {
		int _fireState = 0;
		if (_instPlayerYFire != null) {
			try {
				_fireState = _instPlayerYFire.getState();
				String[] _cardList = _instPlayerYFire.getOwner().split(";");
				if (_fireState == 2 && _cardList != null && _cardList.length > 0) {
					long fireTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(_instPlayerYFire.getFireTime()).getTime();
					int _minute = (int) ((System.currentTimeMillis() - fireTime) / 1000.0f / 60.0f);
					int _fireHP = _minute * _instPlayerYFire.getSpeed() * _cardList.length;
					int _curFireHP = _instPlayerYFire.getHp() - _fireHP;
					if (_curFireHP <= 0) { // 进入旺盛状态
						_fireState = 1;
					}
				}
				_cardList = null;
			} catch (Exception e) {
				e.printStackTrace();
				_fireState = 0;
			}
		}
		return _fireState;
	}

	/**
	 * 获取装备套装属性值
	 * 
	 * @param _instPlayerLineups
	 * @param _instPlayerEquip
	 * @param _instPlayerId
	 * @return 装备的套装属性值
	 */
	@SuppressWarnings("unchecked")
	private Map<Integer, Float> getEquipSuitAttribute(List<InstPlayerLineup> _instPlayerLineups, InstPlayerEquip _instPlayerEquip, int _instPlayerId) {
		Map<Integer, Float> attribute = new HashMap<Integer, Float>();
		if (_instPlayerEquip != null) {
			List<DictEquipSuitRefer> dictEquipSuitReferList = (List<DictEquipSuitRefer>) DictMapList.dictEquipSuitReferMap.get(_instPlayerEquip.getEquipId());
			if (dictEquipSuitReferList != null && dictEquipSuitReferList.size() > 0) {
				DictEquipSuit dictEquipSuit = DictMap.dictEquipSuitMap.get("" + dictEquipSuitReferList.get(0).getEquipSuitId());
				String[] suitEquipTable = dictEquipSuit.getSuitEquipIdList().split(";");
				InstPlayerEquip[] curSuitEquipData = new InstPlayerEquip[suitEquipTable.length];

				int suitCount = 0;
				int suitStarLvl = 5;
//				int count = 0;
				for (InstPlayerLineup instPlayerLineup : _instPlayerLineups) {
					InstPlayerEquip instEquipData = null;
					if (instPlayerLineup.getInstPlayerEquipId() == _instPlayerEquip.getId()) {
						instEquipData = _instPlayerEquip;
					} else {
						instEquipData = getInstPlayerEquipDAL().getModel(instPlayerLineup.getInstPlayerEquipId(), _instPlayerId);
					}
					int equipTypeId = instEquipData.getEquipTypeId();
					int equipStarLvl = 0;
					if (instEquipData.getEquipAdvanceId() > 0) {
						DictEquipAdvance dictEquipAdvance = DictMap.dictEquipAdvanceMap.get("" + instEquipData.getEquipAdvanceId());
						if (dictEquipAdvance != null) {
							equipStarLvl = dictEquipAdvance.getStarLevel();
						}
						dictEquipAdvance = null;
					}
					int _index = -1;
					if (equipTypeId == StaticEquipType.outerwear.intValue()) {
						_index = 1;
					} else if (equipTypeId == StaticEquipType.pants.intValue()) {
						_index = 2;
					} else if (equipTypeId == StaticEquipType.necklace.intValue()) {
						_index = 3;
					} else if (equipTypeId == StaticEquipType.equip.intValue()) {
						_index = 0;
					}
					if (_index >= 0) {
						boolean isSuit = false;
						if (instEquipData.getEquipId() == Integer.parseInt(suitEquipTable[_index])) {
							isSuit = true;
							curSuitEquipData[_index] = instEquipData;
						}
//						count++;
						if (_instPlayerEquip.getId() != instEquipData.getId() && isSuit) {
							suitCount++;
							if (isInSuit != null)
								isInSuit.add(instEquipData.getId());
						}
						if (equipStarLvl < suitStarLvl) {
							suitStarLvl = equipStarLvl;
						}
					}
				}
				for (int i = 2; i <= 4; i++) {
					String propStr = null;
					switch (i) {
					case 2:
						propStr = dictEquipSuit.getSuit2NumProp();
						break;
					case 3:
						propStr = dictEquipSuit.getSuit3NumProp();
						break;
					case 4:
						propStr = dictEquipSuit.getSuit4NumProp();
						break;
					}
					if (i < 2 + suitCount) {
						propStr = (propStr != null ? propStr : ";");
						String[] propTable = propStr.split(";");
						if (propTable != null && propTable.length > 0) {
							for (String value : propTable) {
								String[] temp = value.split("_");
								if (temp != null && temp.length > 1) {
									Integer _fightPropId = new Integer(temp[0]);
									float _propValue = Float.parseFloat(temp[1]);
									Float _prevValue = attribute.get(_fightPropId);
									_prevValue = (_prevValue != null ? _prevValue : 0);
									if (Float.parseFloat(temp[1]) < 1) {
										float propAdd = 0f;
										int _index = -1;
										if (_fightPropId.intValue() == StaticFightProp.blood.intValue()) {
											_index = 3;
										} else if (_fightPropId.intValue() == StaticFightProp.wAttack || _fightPropId.intValue() == StaticFightProp.fAttack.intValue()) {
											_index = 0;
										} else if (_fightPropId.intValue() == StaticFightProp.wDefense.intValue()) {
											_index = 1;
										} else if (_fightPropId.intValue() == StaticFightProp.fDefense.intValue()) {
											_index = 2;
										}
										if (_index >= 0 && curSuitEquipData[_index] != null) {
											Map<Integer, Float> equipAtt = getEquipAttribute(curSuitEquipData[_index]);
											if (equipAtt.get(_fightPropId) != null) {
												propAdd = (float) Math.floor(equipAtt.get(_fightPropId) * _propValue);
											}
											equipAtt = null;
										}
										attribute.put(_fightPropId, _prevValue + propAdd);
									} else {
										attribute.put(_fightPropId, _prevValue + _propValue);
									}
								}
								temp = null;
							}
						}
						propTable = null;
					}
					propStr = null;
				}
				for (int i = 1; i <= 5; i++) {
					String propStr = null;
					switch (i) {
					case 1:
						propStr = dictEquipSuit.getSuit1StarProp();
						break;
					case 2:
						propStr = dictEquipSuit.getSuit2StarProp();
						break;
					case 3:
						propStr = dictEquipSuit.getSuit3StarProp();
						break;
					case 4:
						propStr = dictEquipSuit.getSuit4StarProp();
						break;
					case 5:
						propStr = dictEquipSuit.getSuit5StarProp();
						break;
					}
					if (suitCount >= 3 && i <= suitStarLvl) {
						propStr = (propStr != null ? propStr : ";");
						String[] propTable = propStr.split(";");
						if (propTable != null && propTable.length > 0) {
							for (String value : propTable) {
								String[] temp = value.split("_");
								if (temp != null && temp.length > 1) {
									Integer _fightPropId = new Integer(temp[0]);
									float _propValue = Float.parseFloat(temp[1]);
									Float _prevValue = attribute.get(_fightPropId);
									_prevValue = (_prevValue != null ? _prevValue : 0);
									if (Float.parseFloat(temp[1]) < 1) {
										float propAdd = 0f;
										int _index = -1;
										if (_fightPropId.intValue() == StaticFightProp.blood.intValue()) {
											_index = 3;
										} else if (_fightPropId.intValue() == StaticFightProp.wAttack || _fightPropId.intValue() == StaticFightProp.fAttack.intValue()) {
											_index = 0;
										} else if (_fightPropId.intValue() == StaticFightProp.wDefense.intValue()) {
											_index = 1;
										} else if (_fightPropId.intValue() == StaticFightProp.fDefense.intValue()) {
											_index = 2;
										}
										if (_index >= 0 && curSuitEquipData[_index] != null) {
											Map<Integer, Float> equipAtt = getEquipAttribute(curSuitEquipData[_index]);
											if (equipAtt.get(_fightPropId) != null) {
												propAdd = (float) Math.floor(equipAtt.get(_fightPropId) * _propValue);
											}
											equipAtt = null;
										}
										attribute.put(_fightPropId, _prevValue + propAdd);
									} else {
										attribute.put(_fightPropId, _prevValue + _propValue);
									}
								}
								temp = null;
							}
						}
						propTable = null;
					}
					propStr = null;
				}
				dictEquipSuit = null;
				suitEquipTable = null;
				curSuitEquipData = null;
			} else {
				dictEquipSuitReferList = null;
				attribute = null;
				// 此装备无套装属性
				return null;
			}
			dictEquipSuitReferList = null;
		}
		return attribute;
	}

}
