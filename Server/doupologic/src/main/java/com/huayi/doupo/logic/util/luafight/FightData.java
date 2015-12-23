package com.huayi.doupo.logic.util.luafight;

import java.util.Map;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

import com.huayi.doupo.logic.util.luafight.FightData.CardData.Pyros;

/**
 * 战斗相关数据类
 * 
 * @author caijinlong
 * @date 2015-11-20 上午10:25:00
 */
public class FightData {

	// 衰减值
	private float reduction = 1f;

	public float getReduction() {
		return reduction;
	}

	public void setReduction(float reduction) {
		this.reduction = reduction;
	}

	/** 主力英雄 */
	private Map<Integer, CardData> mainForce;

	/** 替补英雄 */
	private Map<Integer, CardData> substitute;

	/** 战力值 */
	private int power;

	public Map<Integer, CardData> getMainForce() {
		return mainForce;
	}

	public void setMainForce(Map<Integer, CardData> mainForce) {
		this.mainForce = mainForce;
	}

	public Map<Integer, CardData> getSubstitute() {
		return substitute;
	}

	public void setSubstitute(Map<Integer, CardData> substitute) {
		this.substitute = substitute;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * 卡牌战斗相关数据（战斗相关数据类的内部类）
	 */
	public class CardData {

		private String name;

		private boolean showBanner;

		private int cardID;

		private int frameID;

		private float hp;

		private int hpCur;

		private float hit;

		private float dodge;

		private float crit;

		private float renxing;

		private float hitRatio;

		private float dodgeRatio;

		private float critRatio;

		private float renxingRatio;

		private float attPhsc;

		private float attMana;

		private float defPhsc;

		private float defMana;

		private float critRatioDHAdd;

		private float critRatioDHSub;

		private float critPercentAdd;

		private float critPercentSub;

		private float bufBurnReduction;

		private float bufPoisonReduction;

		private float bufCurseReduction;

		private float defPhscRatio;

		private float defManaRatio;

		private float attPhscRatio;

		private float attManaRatio;

		private float shuxingzengzhi;

		private float damageIncrease;

		private int jjCur;

		private int jjMax;

		private int yokeID;

		private int yokeLV;

		private boolean yokeEnable;

		private Sks skill1;

		private Sks skill2;

		private Sks skill3;

		private Pyros[] pyros;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isShowBanner() {
			return showBanner;
		}

		public void setShowBanner(boolean showBanner) {
			this.showBanner = showBanner;
		}

		public int getCardID() {
			return cardID;
		}

		public void setCardID(int cardID) {
			this.cardID = cardID;
		}

		public int getFrameID() {
			return frameID;
		}

		public void setFrameID(int frameID) {
			this.frameID = frameID;
		}

		public float getHp() {
			return hp;
		}

		public void setHp(float hp) {
			this.hp = hp;
		}

		public int getHpCur() {
			return hpCur;
		}

		public void setHpCur(int hpCur) {
			this.hpCur = hpCur;
		}

		public float getHit() {
			return hit;
		}

		public void setHit(float hit) {
			this.hit = hit;
		}

		public float getDodge() {
			return dodge;
		}

		public void setDodge(float dodge) {
			this.dodge = dodge;
		}

		public float getCrit() {
			return crit;
		}

		public void setCrit(float crit) {
			this.crit = crit;
		}

		public float getRenxing() {
			return renxing;
		}

		public void setRenxing(float renxing) {
			this.renxing = renxing;
		}

		public float getHitRatio() {
			return hitRatio;
		}

		public void setHitRatio(float hitRatio) {
			this.hitRatio = hitRatio;
		}

		public float getDodgeRatio() {
			return dodgeRatio;
		}

		public void setDodgeRatio(float dodgeRatio) {
			this.dodgeRatio = dodgeRatio;
		}

		public float getCritRatio() {
			return critRatio;
		}

		public void setCritRatio(float critRatio) {
			this.critRatio = critRatio;
		}

		public float getRenxingRatio() {
			return renxingRatio;
		}

		public void setRenxingRatio(float renxingRatio) {
			this.renxingRatio = renxingRatio;
		}

		public float getAttPhsc() {
			return attPhsc;
		}

		public void setAttPhsc(float attPhsc) {
			this.attPhsc = attPhsc;
		}

		public float getAttMana() {
			return attMana;
		}

		public void setAttMana(float attMana) {
			this.attMana = attMana;
		}

		public float getDefPhsc() {
			return defPhsc;
		}

		public void setDefPhsc(float defPhsc) {
			this.defPhsc = defPhsc;
		}

		public float getDefMana() {
			return defMana;
		}

		public void setDefMana(float defMana) {
			this.defMana = defMana;
		}

		public float getCritRatioDHAdd() {
			return critRatioDHAdd;
		}

		public void setCritRatioDHAdd(float critRatioDHAdd) {
			this.critRatioDHAdd = critRatioDHAdd;
		}

		public float getCritRatioDHSub() {
			return critRatioDHSub;
		}

		public void setCritRatioDHSub(float critRatioDHSub) {
			this.critRatioDHSub = critRatioDHSub;
		}

		public float getCritPercentAdd() {
			return critPercentAdd;
		}

		public void setCritPercentAdd(float critPercentAdd) {
			this.critPercentAdd = critPercentAdd;
		}

		public float getCritPercentSub() {
			return critPercentSub;
		}

		public void setCritPercentSub(float critPercentSub) {
			this.critPercentSub = critPercentSub;
		}

		public float getBufBurnReduction() {
			return bufBurnReduction;
		}

		public void setBufBurnReduction(float bufBurnReduction) {
			this.bufBurnReduction = bufBurnReduction;
		}

		public float getBufPoisonReduction() {
			return bufPoisonReduction;
		}

		public void setBufPoisonReduction(float bufPoisonReduction) {
			this.bufPoisonReduction = bufPoisonReduction;
		}

		public float getBufCurseReduction() {
			return bufCurseReduction;
		}

		public void setBufCurseReduction(float bufCurseReduction) {
			this.bufCurseReduction = bufCurseReduction;
		}

		public float getDefPhscRatio() {
			return defPhscRatio;
		}

		public void setDefPhscRatio(float defPhscRatio) {
			this.defPhscRatio = defPhscRatio;
		}

		public float getDefManaRatio() {
			return defManaRatio;
		}

		public void setDefManaRatio(float defManaRatio) {
			this.defManaRatio = defManaRatio;
		}

		public float getAttPhscRatio() {
			return attPhscRatio;
		}

		public void setAttPhscRatio(float attPhscRatio) {
			this.attPhscRatio = attPhscRatio;
		}

		public float getAttManaRatio() {
			return attManaRatio;
		}

		public void setAttManaRatio(float attManaRatio) {
			this.attManaRatio = attManaRatio;
		}

		public float getShuxingzengzhi() {
			return shuxingzengzhi;
		}

		public void setShuxingzengzhi(float shuxingzengzhi) {
			this.shuxingzengzhi = shuxingzengzhi;
		}

		public float getDamageIncrease() {
			return damageIncrease;
		}

		public void setDamageIncrease(float damageIncrease) {
			this.damageIncrease = damageIncrease;
		}

		public int getJjCur() {
			return jjCur;
		}

		public void setJjCur(int jjCur) {
			this.jjCur = jjCur;
		}

		public int getJjMax() {
			return jjMax;
		}

		public void setJjMax(int jjMax) {
			this.jjMax = jjMax;
		}

		public int getYokeID() {
			return yokeID;
		}

		public void setYokeID(int yokeID) {
			this.yokeID = yokeID;
		}

		public int getYokeLV() {
			return yokeLV;
		}

		public void setYokeLV(int yokeLV) {
			this.yokeLV = yokeLV;
		}

		public boolean isYokeEnable() {
			return yokeEnable;
		}

		public void setYokeEnable(boolean yokeEnable) {
			this.yokeEnable = yokeEnable;
		}

		public Sks getSkill1() {
			return skill1;
		}

		public void setSkill1(Sks skill1) {
			this.skill1 = skill1;
		}

		public Sks getSkill2() {
			return skill2;
		}

		public void setSkill2(Sks skill2) {
			this.skill2 = skill2;
		}

		public Sks getSkill3() {
			return skill3;
		}

		public void setSkill3(Sks skill3) {
			this.skill3 = skill3;
		}

		public Pyros[] getPyros() {
			return pyros;
		}

		public void setPyros(Pyros[] pyros) {
			this.pyros = pyros;
		}

		public class Sks {

			private int lv;

			private int id;

			public Sks(int lv, int id) {
				super();
				this.lv = lv;
				this.id = id;
			}

			public int getLv() {
				return lv;
			}

			public void setLv(int lv) {
				this.lv = lv;
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public LuaTable toLuaTable() {
				LuaTable sk = new LuaTable();
				sk.set("id", LuaValue.valueOf(id));
				sk.set("lv", LuaValue.valueOf(lv));
				return sk;
			}
		}

		public class Pyros {

			private int id;

			private int lv;

			public Pyros(int id, int lv) {
				super();
				this.id = id;
				this.lv = lv;
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public int getLv() {
				return lv;
			}

			public void setLv(int lv) {
				this.lv = lv;
			}

			public LuaTable toLuaTable() {
				LuaTable sk = new LuaTable();
				sk.set("id", LuaValue.valueOf(id));
				sk.set("lv", LuaValue.valueOf(lv));
				return sk;
			}
		}

	}

	LuaValue getFFFFFFFF(CardData cardData) {
		LuaTable lt = new LuaTable();
		lt.set("name", LuaValue.valueOf(cardData.getName()));
		lt.set("scale", LuaValue.NIL); // NIL
		lt.set("isBoss", LuaValue.valueOf(false));
		lt.set("showBanner", LuaValue.valueOf(cardData.isShowBanner()));
		lt.set("cardID", LuaValue.valueOf(cardData.getCardID()));
		lt.set("frameID", LuaValue.valueOf(cardData.getFrameID()));
		lt.set("hp", LuaValue.valueOf((int) (cardData.getHp() * (1 - getReduction()))));
		lt.set("hpCur", LuaValue.valueOf((int) (cardData.getHp() * (1 - getReduction())))); // 取hp
		lt.set("hit", LuaValue.valueOf(cardData.getHit()));
		lt.set("dodge", LuaValue.valueOf(cardData.getDodge()));
		lt.set("hitRatio", LuaValue.valueOf(cardData.getHitRatio()));
		lt.set("dodgeRatio", LuaValue.valueOf(cardData.getDodgeRatio()));
		lt.set("crit", LuaValue.valueOf(cardData.getCrit()));
		lt.set("renxing", LuaValue.valueOf(cardData.getRenxing()));
		lt.set("critRatio", LuaValue.valueOf(cardData.getCritRatio()));
		lt.set("renxingRatio", LuaValue.valueOf(cardData.getRenxingRatio()));
		lt.set("critRatioDHAdd", LuaValue.valueOf(cardData.getCritRatioDHAdd()));
		lt.set("critRatioDHSub", LuaValue.valueOf(cardData.getCritRatioDHSub()));
		lt.set("critPercentAdd", LuaValue.valueOf(cardData.getCritPercentAdd()));
		lt.set("critPercentSub", LuaValue.valueOf(cardData.getCritPercentSub()));
		lt.set("bufBurnReduction", LuaValue.valueOf(cardData.getBufBurnReduction()));
		lt.set("bufPoisonReduction", LuaValue.valueOf(cardData.getBufPoisonReduction()));
		lt.set("bufCurseReduction", LuaValue.valueOf(cardData.getBufCurseReduction()));
		lt.set("attPhsc", LuaValue.valueOf((int) (cardData.getAttPhsc() * (1 - reduction))));
		lt.set("attMana", LuaValue.valueOf((int) (cardData.getAttMana() * (1 - reduction))));
		lt.set("defPhsc", LuaValue.valueOf((int) (cardData.getDefPhsc() * (1 - reduction))));
		lt.set("defMana", LuaValue.valueOf((int) (cardData.getDefMana() * (1 - reduction))));
		lt.set("attPhscRatio", LuaValue.valueOf(cardData.getAttPhscRatio()));
		lt.set("attManaRatio", LuaValue.valueOf(cardData.getAttManaRatio()));
		lt.set("defPhscRatio", LuaValue.valueOf(cardData.getDefPhscRatio()));
		lt.set("defManaRatio", LuaValue.valueOf(cardData.getDefManaRatio()));
		lt.set("shuxingzengzhi", LuaValue.valueOf(cardData.getShuxingzengzhi()));
		lt.set("damageIncrease", LuaValue.valueOf(cardData.getDamageIncrease()));
		lt.set("jjCur", LuaValue.valueOf(cardData.getJjCur()));
		lt.set("jjMax", LuaValue.valueOf(cardData.getJjMax()));
		LuaTable sks = new LuaTable();
		lt.set("sks", sks);
		{
			sks.set(1, cardData.getSkill1() == null ? LuaValue.NIL : cardData.getSkill1().toLuaTable());
			sks.set(2, cardData.getSkill2() == null ? LuaValue.NIL : cardData.getSkill2().toLuaTable());
			sks.set(3, cardData.getSkill3() == null ? LuaValue.NIL : cardData.getSkill3().toLuaTable());
		}
		LuaTable pyros = new LuaTable();
		lt.set("pyros", pyros);
		Pyros[] ps = cardData.getPyros();
		if (ps != null) {
			for (int i = 0; i < ps.length; ++i) {
				pyros.set(i + 1, ps[i] == null ? LuaValue.NIL : ps[i].toLuaTable());
			}
		}
		return lt;
	}

	public LuaValue toLua() {
		LuaTable fst = new LuaTable();
		fst.set("power", getPower());
		Map<Integer, CardData> mainForce = getMainForce();
		if (mainForce != null && mainForce.size() > 0) {
			LuaValue myMF = new LuaTable();
			fst.set("mainForce", myMF);
			for (Map.Entry<Integer, CardData> temp : mainForce.entrySet()) {
				CardData cardData = temp.getValue();
				myMF.set(temp.getKey(), getFFFFFFFF(cardData));
			}
		}
		Map<Integer, CardData> substitute = getSubstitute();
		if (substitute != null && substitute.size() > 0) {
			LuaValue subs = new LuaTable();
			fst.set("substitute", subs);
			for (Map.Entry<Integer, CardData> temp : substitute.entrySet()) {
				CardData cardData = temp.getValue();
				subs.set(temp.getKey(), getFFFFFFFF(cardData));
			}
		}
		return fst;
	}

}
