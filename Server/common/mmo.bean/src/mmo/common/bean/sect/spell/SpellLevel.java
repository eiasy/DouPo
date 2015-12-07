package mmo.common.bean.sect.spell;

import java.util.ArrayList;
import java.util.List;

import mmo.common.bean.sect.IGameSect;
import mmo.common.config.MoneyConfig;
import mmo.common.protocol.game.CommonGamePropertyKey;

import org.apache.mina.core.buffer.IoBuffer;

public class SpellLevel {
	protected final List<Short> table = new ArrayList<Short>();
	/** 阵法等级 */
	private short               level;
	/** 升级消耗-灵石 */
	private int                 lingshi;
	/** 升级消耗-石料 */
	private int                 stone;
	/** 升级消耗-铁矿 */
	private int                 iron;
	/** 升级消耗-木材 */
	private int                 wood;
	/** 宗战传入场景-应战方 */
	private int                 responseSceneId;
	/** 宗战传入TileX坐标-应战方 */
	private short               responseTileX;
	/** 宗战传入TileY坐标-应战方 */
	private short               responseTileY;
	/** 宗战结束传出场景-应战方 */
	private int                 responseOutSceneId;
	/** 宗战结束传出TileX-应战方 */
	private short               responseOutTileX;
	/** 宗战结束传出TileY-应战方 */
	private short               responseOutTileY;
	/** 宗战传入场景-攻方 */
	private int                 applySceneId;
	/** 宗战传入TileX坐标-攻方 */
	private short               applyTileX;
	/** 宗战传入TileY坐标-攻方 */
	private short               applyTileY;
	/** 宗战结束传出场景-应战方 */
	private int                 applyOutSceneId;
	/** 宗战结束传出TileX-应战方 */
	private short               applyOutTileX;
	/** 宗战结束传出TileY-应战方 */
	private short               applyOutTileY;
	/** 增加的血量 */
	private int                 addHp;
	private String              note;

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
		if (wood > 0) {
//			table.add(GamePropertyKey.moneyWood_329);
		}
	}

	public SpellLevel() {

	}

	public short getLevel() {
		return level;
	}

	public short getApplyTileX() {
		return applyTileX;
	}

	public int getAddHp() {
		return addHp;
	}

	public void setAddHp(int addHp) {
		this.addHp = addHp;
	}

	public void setApplyTileX(short tileX) {
		this.applyTileX = tileX;
	}

	public short getApplyTileY() {
		return applyTileY;
	}

	public void setApplyTileY(short tileY) {
		this.applyTileY = tileY;
	}

	public short getResponseTileX() {
		return responseTileX;
	}

	public void setResponseTileX(short responseTileX) {
		this.responseTileX = responseTileX;
	}

	public short getResponseTileY() {
		return responseTileY;
	}

	public int getResponseOutSceneId() {
		return responseOutSceneId;
	}

	public void setResponseOutSceneId(int responseOutSceneId) {
		this.responseOutSceneId = responseOutSceneId;
	}

	public short getResponseOutTileX() {
		return responseOutTileX;
	}

	public void setResponseOutTileX(short responseOutTileX) {
		this.responseOutTileX = responseOutTileX;
	}

	public short getResponseOutTileY() {
		return responseOutTileY;
	}

	public void setResponseOutTileY(short responseOutTileY) {
		this.responseOutTileY = responseOutTileY;
	}

	public int getApplyOutSceneId() {
		return applyOutSceneId;
	}

	public void setApplyOutSceneId(int applyOutSceneId) {
		this.applyOutSceneId = applyOutSceneId;
	}

	public short getApplyOutTileX() {
		return applyOutTileX;
	}

	public void setApplyOutTileX(short applyOutTileX) {
		this.applyOutTileX = applyOutTileX;
	}

	public short getApplyOutTileY() {
		return applyOutTileY;
	}

	public void setApplyOutTileY(short applyOutTileY) {
		this.applyOutTileY = applyOutTileY;
	}

	public void setResponseTileY(short responseTileY) {
		this.responseTileY = responseTileY;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public int getLingshi() {
		return lingshi;
	}

	public void setLingshi(int lingshi) {
		this.lingshi = lingshi;
		if (lingshi > 0) {
			table.add(MoneyConfig.getKeyLingshi());
		}
	}

	public int getStone() {
		return stone;
	}

	public void setStone(int stone) {
		this.stone = stone;
		if (stone > 0) {
//			table.add(GamePropertyKey.moneyStone_328);
		}
	}

	public int getIron() {
		return iron;
	}

	public void setIron(int iron) {
		this.iron = iron;
		if (iron > 0) {
//			table.add(GamePropertyKey.moneyIron_327);
		}
	}

	public int getApplySceneId() {
		return applySceneId;
	}

	public void setApplySceneId(int applySceneId) {
		this.applySceneId = applySceneId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getResponseSceneId() {
		return responseSceneId;
	}

	public void setResponseSceneId(int responseSceneId) {
		this.responseSceneId = responseSceneId;
	}

	@Override
	public String toString() {
		return "SpellLevel [level=" + level + ", gold=" + lingshi + ", stone=" + stone + ", iron=" + iron + ", wood=" + wood + ", sceneId="
		        + applySceneId + ", tileX=" + applyTileX + ", tileY=" + applyTileY + "]";
	}

	public boolean checkoff(IGameSect sect) {
		if (sect.getMoney(MoneyConfig.LING_SHI_1000) < lingshi) {
			return false;
		}
//		if (sect.getMoney(MoneyConfig.stone) < stone) {
//			return false;
//		}
//		if (sect.getMoney(MoneyConfig.wood) < wood) {
//			return false;
//		}
//		if (sect.getMoney(MoneyConfig.iron) < iron) {
//			return false;
//		}
		sect.changeMoney(MoneyConfig.LING_SHI_1000, -lingshi);
//		sect.changeMoney(MoneyConfig.stone, -stone);
//		sect.changeMoney(MoneyConfig.wood, -wood);
//		sect.changeMoney(MoneyConfig.iron, -iron);
		return true;
	}

	/**
	 * 封装升级消耗
	 * 
	 * @param buf
	 *            缓存对象
	 */
	public void packetCost(IGameSect sect, IoBuffer buf) {
		if (lingshi > 0) {
			buf.setProperty(MoneyConfig.getKeyLingshi(), lingshi);

		}
//		if (stone > 0) {
//			buf.setProperty(GamePropertyKey.moneyStone_328, stone);
//
//		}
//		if (iron > 0) {
//			buf.setProperty(GamePropertyKey.moneyIron_327, iron);
//		}
//		if (wood > 0) {
//			buf.setProperty(GamePropertyKey.moneyWood_329, wood);
//		}
		buf.setPropertyList(CommonGamePropertyKey.CommonKey.COMMON_EXPERIENCE_2, table);
	}
}
