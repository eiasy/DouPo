package mmo.common.bean.pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import mmo.common.bean.role.Role;
import mmo.common.config.ServerRoleProperty;

/**
 * 渡劫单元
 * 
 * @author 肖琼
 * @version 20140904
 */
public class Elevate {
	private int                 elevateId  = 0;
	private int                 elevateNum = 0;   // 渡劫序号
	private byte                realm      = 0;   // 该单元归属的境界
	private short               needLevel  = 0;   // 等级需求
	private List<Integer>       acupoints  = null; // 该单元包含的穴位集合
	private Map<Short, Integer> proValues  = null; // 该单元对属性的加成

	public Elevate() {
		this.acupoints = new ArrayList<Integer>();
		this.proValues = new HashMap<Short, Integer>();
	}

	public int getElevateId() {
		return elevateId;
	}

	public void setElevateId(int elevateId) {
		this.elevateId = elevateId;
	}

	public byte getRealm() {
		return realm;
	}

	public short getNeedLevel() {
		return needLevel;
	}

	public void setNeedLevel(short needLevel) {
		this.needLevel = needLevel;
	}

	public void setRealm(byte realm) {
		this.realm = realm;
	}

	public int getElevateNum() {
		return elevateNum;
	}

	public void setElevateNum(int elevateNum) {
		this.elevateNum = elevateNum;
	}

	public List<Integer> getAcupoints() {
		return acupoints;
	}

	public void addAcupoint(int acupointId) {
		this.acupoints.add(acupointId);
	}

	public Map<Short, Integer> getProValues() {
		return proValues;
	}

	public void addProValue(short proKey, int value) {
		this.proValues.put(proKey, value);
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

	public int getAcupointIndex(int acupointId) {
		return acupoints.indexOf(acupointId);
	}

	@Override
	public String toString() {
		return "Elevate [realm=" + realm + ", acupoints=" + acupoints + ", proValues=" + proValues + "]";
	}

}
