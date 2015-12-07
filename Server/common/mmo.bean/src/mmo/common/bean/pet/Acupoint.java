package mmo.common.bean.pet;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import mmo.common.bean.role.Role;
import mmo.common.config.ServerRoleProperty;

/**
 * 穴位单元
 * 
 * @author 肖琼
 * @version 20140903
 */
public class Acupoint {
	private int                   acupointId = 0;   // 穴位ID
	private Map<Integer, Integer> needGoods  = null; // 激活穴位需要的物品与数量<物品ID,数量>
	private Map<Short, Integer>   proValues  = null; // 激活穴位后属性的加成<属性键,值>
	private short                 needLv     = 0;   // 等级要求

	public Acupoint() {
		needGoods = new HashMap<Integer, Integer>();
		proValues = new HashMap<Short, Integer>();
	}

	public int getAcupointId() {
		return acupointId;
	}

	public void setAcupointId(int acupointId) {
		this.acupointId = acupointId;
	}

	public void addNeedGoods(int goodsId, int count) {
		if (goodsId < 1) {
			return;
		}
		this.needGoods.put(goodsId, count);
	}

	public Map<Integer, Integer> getNeedGoods() {
		return needGoods;
	}

	public void addProValues(short proKey, int value) {
		if (proKey < 1) {
			return;
		}
		this.proValues.put(proKey, value);
	}

	public Map<Short, Integer> getProValues() {
		return proValues;
	}

	public short getNeedLv() {
		return needLv;
	}

	public void setNeedLv(short needLv) {
		this.needLv = needLv;
	}

	/**
	 * 检测激活穴位的物品是否足够
	 * 
	 * @param role
	 * @return
	 */
	public boolean checkNeedGoods(Role role) {
		if (this.needGoods.size() < 1) {
			return false;
		}
		Set<Entry<Integer, Integer>> entrys = this.needGoods.entrySet();
		for (Entry<Integer, Integer> entry : entrys) {
			if (role.getGoodsCountByGoodsId(entry.getKey()) < entry.getValue()) {
				return false;
			}
		}
		return true;
	}

	public void takeEffect(Role role) {
		Set<Entry<Short, Integer>> entrys = this.proValues.entrySet();
		int value = 0;
		for (Entry<Short, Integer> entry : entrys) {
			value = entry.getValue();
			switch (entry.getKey()) {
			/** 战斗属性 */
				case ServerRoleProperty.ATTACK: {// 攻击
					role.changeAttackMedicine(value);
					break;
				}
				case ServerRoleProperty.DEFENCE: {// 防御
					role.changeDefenceMedicine(value);
					break;
				}
				case ServerRoleProperty.HP_MAX: {// 最大生命
					role.changeHpMedicinePro(value);
					break;
				}
				case ServerRoleProperty.CRIT: {// 暴击
					role.changeCruelMedicine(value);
					break;
				}
				case ServerRoleProperty.DUCK: {// 闪避
					role.changeDuckChanceMedicine(value);
					break;
				}
				case ServerRoleProperty.RATIO: {// 命中
					role.changeAttackChanceMedicine(value);
					break;
				}
				case ServerRoleProperty.TENACITY: {
					role.changeTenacityMedicine(value);
					break;
				}
				case ServerRoleProperty.BLOCK: {
					role.changeFenderMedicine(value);
					break;
				}
				case ServerRoleProperty.BREAK: {
					role.changeDestroyMedicine(value);
					break;
				}
				case ServerRoleProperty.REDUCE: {
					role.changeReduceDefenceMedicine(value);
					break;
				}
				case ServerRoleProperty.RESIST: {
					role.changeResistMedicine(value);
					break;
				}
				case ServerRoleProperty.SUCK: {
					role.changeSuckHpMedicine(value);
					break;
				}
				case ServerRoleProperty.REBOUND: {
					role.changeReboundMedicine(value);
					break;
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Acupoint [acupointId=" + acupointId + ", needGoods=" + needGoods + ", proValues=" + proValues + "]";
	}

}
