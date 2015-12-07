package mmo.common.bean.mission.extension;

import mmo.common.bean.role.Role;
import mmo.common.config.MissionConfig;
import mmo.common.config.goods.GoodsConfig;
import mmo.common.config.role.RoleProfession;
import mmo.common.protocol.ui.ClientUI;

public class SectAward extends Award {
	private int sectGold;
	private int sectStone;
	private int sectWood;
	private int sectIron;

	public int getSectGold() {
		return sectGold;
	}

	public void setSectGold(int sectGold) {
		this.sectGold = sectGold;
	}

	public int getSectStone() {
		return sectStone;
	}

	public void setSectStone(int sectStone) {
		this.sectStone = sectStone;
	}

	public int getSectWood() {
		return sectWood;
	}

	public void setSectWood(int sectWood) {
		this.sectWood = sectWood;
	}

	public int getSectIron() {
		return sectIron;
	}

	public void setSectIron(int sectIron) {
		this.sectIron = sectIron;
	}

	public String getTag() {
		StringBuilder sb = new StringBuilder();
		sb.append("<award flag=\"").append(flag).append("\"");
		switch (flag) {
			case MissionConfig.Award.GOODS: {
				sb.append(" id=\"").append(id).append("\" count=\"").append(count).append("\" profession=\"").append(profession)
				        .append("\" binded=\"").append(binded).append("\" quality=\"").append(quality).append("\"");
				break;
			}
			case MissionConfig.Award.CONTRIBUTE:
			case MissionConfig.Award.EXPERIENCE:
			case MissionConfig.Award.MONEY:
			case MissionConfig.Award.REALM_POINT: {
				sb.append(" count=\"").append(count).append("\"");
				break;
			}
			case MissionConfig.Award.SECT_AWARD: {
				if (sectGold > 0) {
					sb.append(" sectgold=\"").append(sectGold).append("\"");
				}
				if (sectIron > 0) {
					sb.append(" sectiron=\"").append(sectIron).append("\"");
				}
				if (sectStone > 0) {
					sb.append(" sectstone=\"").append(sectStone).append("\"");
				}
				if (sectWood > 0) {
					sb.append(" sectwood=\"").append(sectWood).append("\"");
				}
				break;
			}
		}
		sb.append("/>");
		return sb.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		switch (flag) {
			case MissionConfig.Award.GOODS: {
				sb.append(count).append("个物品(").append(id);
				if (binded) {
					sb.append("绑定");
				} else {
					sb.append("不绑定");
				}
				sb.append(",").append(GoodsConfig.Quality.getQualityName(quality));
				sb.append(") 面向职业：" + RoleProfession.getProfessionNames(profession));
				break;
			}
			case MissionConfig.Award.EXPERIENCE: {
				sb.append("经验：").append(count).append(" ");
				break;
			}
			case MissionConfig.Award.MONEY: {
				sb.append("绑灵：").append(count).append(" ");
				break;
			}
			case MissionConfig.Award.CONTRIBUTE: {
				sb.append("贡献点：").append(count).append(" ");
				break;
			}
			case MissionConfig.Award.REALM_POINT: {
				sb.append("境界点：").append(count).append(" ");
				break;
			}
			case MissionConfig.Award.SECT_AWARD: {
				sb.append("宗门灵石：").append(sectGold).append(",宗门石料： ").append(sectStone).append(",宗门木材： ").append(sectWood).append(",宗门铁矿： ")
				        .append(sectIron);
				break;
			}
		}
		return sb.toString();
	}

	public boolean validate(Role role) {
		return true;
	}

	public String getAwardInfo() {
		StringBuilder sb = new StringBuilder();
		switch (flag) {
			case MissionConfig.Award.GOODS: {
				sb.append(count).append("个物品(").append(id);
				if (binded) {
					sb.append("绑定");
				} else {
					sb.append("不绑定");
				}
				sb.append(",").append(GoodsConfig.Quality.getQualityName(quality));
				sb.append(") " + RoleProfession.getProfessionNames(profession));
				break;
			}
			case MissionConfig.Award.EXPERIENCE: {
				sb.append("经验：").append(count).append(" ");
				break;
			}
			case MissionConfig.Award.MONEY: {
				sb.append("绑灵：").append(count).append(" ");
				break;
			}
			case MissionConfig.Award.CONTRIBUTE: {
				sb.append("贡献点：").append(count).append(" ");
				break;
			}
			case MissionConfig.Award.REALM_POINT: {
				sb.append("境界点：").append(count).append(" ");
				break;
			}
			case MissionConfig.Award.SECT_AWARD: {
				if (sectGold > 0) {
					sb.append(ClientUI.StringCommand.newLine()).append("宗门灵石：").append(sectGold);
				}
				if (sectStone > 0) {
					sb.append(ClientUI.StringCommand.newLine()).append("宗门石料：").append(sectStone);
				}
				if (sectWood > 0) {
					sb.append(ClientUI.StringCommand.newLine()).append("宗门木材：").append(sectWood);
				}
				if (sectIron > 0) {
					sb.append(ClientUI.StringCommand.newLine()).append("宗门铁矿：").append(sectIron);
				}
				break;
			}
		}
		return sb.toString();
	}
}
