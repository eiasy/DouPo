package mmo.common.bean.mission.extension;

import mmo.common.bean.role.Role;
import mmo.common.config.MissionConfig;
import mmo.common.config.MoneyConfig;
import mmo.common.config.goods.GoodsConfig;
import mmo.common.config.role.RoleProfession;
import mmo.common.protocol.ui.ClientUI;

public class Award {
	protected int     flag;
	protected int     id;
	protected int     count;
	protected boolean binded;
	protected byte    quality;
	protected byte    profession;
	protected int     bindId;
	protected boolean preview = true;

	public int getBindId() {
		return bindId;
	}

	public void setBindId(int bindId) {
		this.bindId = bindId;
	}

	public byte getQuality() {
		return quality;
	}

	public void setQuality(byte quality) {
		this.quality = quality;
	}

	public boolean isBinded() {
		return binded;
	}

	public void setBinded(boolean binded) {
		this.binded = binded;
	}

	public Award() {

	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public byte getProfession() {
		return profession;
	}

	public void setProfession(byte profession) {
		this.profession = profession;
	}

	public boolean isPreview() {
		return preview;
	}

	public void setPreview(boolean preview) {
		this.preview = preview;
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
			case MissionConfig.Award.MONEY: {
				sb.append(" id=\"").append(id).append("\" count=\"").append(count).append("\"");
				break;
			}
			case MissionConfig.Award.CONTRIBUTE:
			case MissionConfig.Award.EXPERIENCE:
			case MissionConfig.Award.REALM_POINT: {
				sb.append(" count=\"").append(count).append("\"");
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
				sb.append(MoneyConfig.getName(id)).append("：").append(count);
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
		}
		return sb.toString();
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
				sb.append("经验：").append(count);
				break;
			}
			case MissionConfig.Award.MONEY: {
				if (id == MoneyConfig.LING_SHI_1000 || id == MoneyConfig.LING_SHI_1000) {
					sb.append(MoneyConfig.getName(id)).append("：").append(ClientUI.StringCommand.getMoneyInfo(id, count));
				} else {
					sb.append(MoneyConfig.getName(id)).append("：").append(count);
				}
				break;
			}
			case MissionConfig.Award.CONTRIBUTE: {
				sb.append("贡献点：").append(count);
				break;
			}
			case MissionConfig.Award.REALM_POINT: {
				sb.append("境界点：").append(count);
				break;
			}
		}
		return sb.toString();
	}

	public boolean validateGoods(Role role) {
		if (flag != MissionConfig.Award.GOODS) {
			return false;
		}

		if (profession == 0) {
			return true;
		}
		return (profession & role.getProfession()) != 0;
	}

	public boolean validate(Role role) {
		switch (flag) {
			case MissionConfig.Award.GOODS: {
				if (profession == 0) {
					return true;
				}
				return (profession & role.getProfession()) != 0;
			}
			default: {
				return true;
			}
		}
	}

	public boolean equals(Object obj) {
		if (obj instanceof Award) {
			Award tmp = (Award) obj;
			if (tmp.flag == flag) {
				if (flag == MissionConfig.Award.GOODS) {
					return tmp.id == id;
				} else if (flag == MissionConfig.Award.MONEY) {
					return tmp.getId() == id;
				}
				return true;
			}
		}
		return false;
	}

	public int getSectGold() {
		return 0;
	}

	public void setSectGold(int sectGold) {
	}

	public int getSectStone() {
		return 0;
	}

	public void setSectStone(int sectStone) {
	}

	public int getSectWood() {
		return 0;
	}

	public void setSectWood(int sectWood) {
	}

	public int getSectIron() {
		return 0;
	}

	public void setSectIron(int sectIron) {
	}
}
