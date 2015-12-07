package mmo.common.bean.role.realm;

public class RealmPoint {
	/** 穴位编号 */
	private short   identity;
	/** 穴位名称 */
	private String name;
	/** 开通穴位成功率 */
	private short  successRate;
	/** 阶段 ：前中后 */
	private byte   realmPhase;
	/** 消耗星数 */
	private short  star;
	/** 消耗灵石 */
	private int    realmLingshi;
	/** 生命 */
	private int    realmLife;
	/** 攻击 */
	private int    realmAttack;
	/** 防御 */
	private int    realmDefend;

	public RealmPoint(short identity) {
		this.identity = identity;
	}

	public short getIdentity() {
		return identity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getSuccessRate(byte failCount) {
		int rate = successRate + failCount;
		if (rate > 100) {
			rate = 100;
		}
		return (short)rate;
	}

	public void setSuccessRate(short successRate) {
		this.successRate = successRate;
	}

	public byte getRealmPhase() {
		return realmPhase;
	}

	public void setRealmPhase(byte realmSection) {
		this.realmPhase = realmSection;
	}

	public short getStar() {
		return star;
	}

	public void setStar(short star) {
		this.star = star;
	}

	public int getRealmLingshi() {
		return realmLingshi;
	}

	public void setRealmLingshi(int realmLingshi) {
		this.realmLingshi = realmLingshi;
	}

	public int getRealmLife() {
		return realmLife;
	}

	public void setRealmLife(int realmLife) {
		this.realmLife = realmLife;
	}

	public int getRealmAttack() {
		return realmAttack;
	}

	public void setRealmAttack(int realmAttack) {
		this.realmAttack = realmAttack;
	}

	public int getRealmdefend() {
		return realmDefend;
	}

	public void setRealmdefend(int realmdefend) {
		this.realmDefend = realmdefend;
	}

}
