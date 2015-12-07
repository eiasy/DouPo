package mmo.common.bean.sect.building;

import java.util.ArrayList;
import java.util.List;

import mmo.common.bean.sect.IGameSect;
import mmo.common.config.MoneyConfig;
import mmo.common.protocol.game.CommonGamePropertyKey;

import org.apache.mina.core.buffer.IoBuffer;

public abstract class ABuildingLevel {
	protected final List<Short> table = new ArrayList<Short>();
	/** 建筑等级 */
	protected short             level;
	/** 消耗的绑灵 */
	protected int               costGold;
	/** 消耗木材 */
	private int                 costIron;
	/** 消耗石料 */
	private int                 costStone;
	/** 消耗木材 */
	private int                 costWood;
	protected long              limitValue;
	protected int               perfect;

	public int getPerfect() {
		return perfect;
	}

	public void setPerfect(int perfect) {
		this.perfect = perfect;
	}

	/**
	 * 获取建筑等级
	 * 
	 * @return 等级
	 */
	public short getLevel() {
		return level;
	}

	/**
	 * 设置建筑等级
	 * 
	 * @param level
	 *            等级
	 */
	public void setLevel(short level) {
		this.level = level;
	}

	public int getCostIron() {
		return costIron;
	}

	public void setCostIron(int costIron) {
		this.costIron = costIron;
		if (costIron > 0) {
			// table.add(GamePropertyKey.moneyIron_327);
		}
	}

	public int getCostStone() {
		return costStone;
	}

	public void setCostStone(int costStone) {
		this.costStone = costStone;
		if (costStone > 0) {
			// table.add(GamePropertyKey.moneyStone_328);
		}
	}

	/**
	 * 获取消耗的绑灵
	 * 
	 * @return 消耗的绑灵
	 */
	public int getCostGold() {
		return costGold;
	}

	/**
	 * 设置消耗的绑灵
	 * 
	 * @param costGold
	 *            消耗的绑灵
	 */
	public void setCostGold(int costGold) {
		this.costGold = costGold;
		if (costGold > 0) {
			table.add(MoneyConfig.getKeyLingshi());
		}
	}

	public int getCostWood() {
		return costWood;
	}

	public void setCostWood(int costWood) {
		this.costWood = costWood;
		if (costWood > 0) {
			// table.add(GamePropertyKey.moneyWood_329);
		}
	}

	/**
	 * 扣除消耗
	 * 
	 * @param sect
	 *            宗门
	 * @return true扣除成功，false扣除失败
	 */
	public boolean checkoff(IGameSect sect) {
		if (validate(sect)) {
			if (costGold > 0) {
				sect.changeMoney(MoneyConfig.LING_SHI_1000, -costGold);
			}
			// if (costStone > 0) {
			// sect.changeMoney(MoneyConfig.stone, -costStone);
			// }
			// if (costIron > 0) {
			// sect.changeMoney(MoneyConfig.iron, -costIron);
			// }
			// if (costWood > 0) {
			// sect.changeMoney(MoneyConfig.wood, -costWood);
			// }
			return true;
		}
		return false;
	}

	/**
	 * 验证建筑是否可以升级
	 * 
	 * @param sect
	 *            宗门
	 * @return true可以升级，false不能升级
	 */
	public boolean validate(IGameSect sect) {
		// return !(sect.getMoney(MoneyConfig.LING_SHI_1000) < costGold || sect.getMoney(MoneyConfig.iron) < costIron
		// || sect.getMoney(MoneyConfig.stone) < costStone || sect.getMoney(MoneyConfig.wood) < costWood);

		return true;
	}

	/**
	 * 封装升级消耗
	 * 
	 * @param buf
	 *            缓存对象
	 */
	public void packetCost(IGameSect sect, IoBuffer buf) {
		if (costGold > 0) {
			buf.setProperty(MoneyConfig.getKeyLingshi(), costGold);

		}
		// if (costStone > 0) {
		// buf.setProperty(GamePropertyKey.moneyStone_328, costStone);
		//
		// }
		// if (costIron > 0) {
		// buf.setProperty(GamePropertyKey.moneyIron_327, costIron);
		// }
		// if (costWood > 0) {
		// buf.setProperty(GamePropertyKey.moneyWood_329, costWood);
		// }
		buf.setPropertyList(CommonGamePropertyKey.CommonKey.COMMON_EXPERIENCE_2, table);
	}

	/**
	 * 获取守护兽的最大等级（兽园建筑）
	 * 
	 * @return 守护兽的最大等级
	 */
	public short getMaxBeastLevel() {
		return 0;
	}

	/**
	 * 获取该等级的府第可以容纳的人数上限
	 * 
	 * @return 府第可以容纳的人数上限
	 */
	public int getMaxMember() {
		return 0;
	}

	/**
	 * 获取该等级的金库可以容纳绑灵的数量
	 * 
	 * @return 金库可以容纳绑灵的数量
	 */
	public long getMaxLings() {
		return 0;
	}

	/**
	 * 获取该等级的库房可以容纳单个物品数量
	 * 
	 * @return 库房可以容纳单个物品数量
	 */
	public int getMaxGoods() {
		return 0;
	}

	/**
	 * 获取该等级的兽园开启的技能
	 * 
	 * @return 兽园开启的技能
	 */
	public int getOpenSkill() {
		return 0;
	}

	/**
	 * 获取该等级的书院购买的阵法可以使用次数
	 * 
	 * @return 书院购买的阵法可以使用次数
	 */
	public int getMaxReuse() {
		return 0;
	}

	/**
	 * 获取该等级的兵部可以容纳储备军的数量
	 * 
	 * @return 兵部可以容纳储备军的数量
	 */
	public int getMaxArmament() {
		return 0;
	}

}
