package mmo.common.config.role;

public class RoleRobotData {
	/** 账号ID */
	private int     accountId;
	/** 等级 */
	private short   level;
	/** 名称 */
	private String  name;
	/** 职业 */
	private byte    roleProfession;
	/** 开启状态 */
	private boolean open;
	private int     petCard;
	private short   petLevel;
	private byte    petRealm;
	private byte    petGrowLevel;
	/** 是否进榜 */
	private boolean enterRank;

	private int     petCard2;
	private short   petLevel2;
	private byte    petRealm2;
	private byte    petGrowLevel2;

	public boolean isEnterRank() {
		return enterRank;
	}

	public void setEnterRank(boolean enterRank) {
		this.enterRank = enterRank;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getRoleProfession() {
		return roleProfession;
	}

	public void setRoleProfession(byte roleProfession) {
		this.roleProfession = roleProfession;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public int getPetCard() {
		return petCard;
	}

	public void setPetCard(int petCard) {
		this.petCard = petCard;
	}

	public short getPetLevel() {
		return petLevel;
	}

	public void setPetLevel(short petLevel) {
		this.petLevel = petLevel;
	}

	public byte getPetRealm() {
		return petRealm;
	}

	public void setPetRealm(byte petRealm) {
		this.petRealm = petRealm;
	}

	public byte getPetGrowLevel() {
		return petGrowLevel;
	}

	public void setPetGrowLevel(byte petGrowLevel) {
		this.petGrowLevel = petGrowLevel;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getPetCard2() {
		return petCard2;
	}

	public void setPetCard2(int petCard2) {
		this.petCard2 = petCard2;
	}

	public short getPetLevel2() {
		return petLevel2;
	}

	public void setPetLevel2(short petLevel2) {
		this.petLevel2 = petLevel2;
	}

	public byte getPetRealm2() {
		return petRealm2;
	}

	public void setPetRealm2(byte petRealm2) {
		this.petRealm2 = petRealm2;
	}

	public byte getPetGrowLevel2() {
		return petGrowLevel2;
	}

	public void setPetGrowLevel2(byte petGrowLevel2) {
		this.petGrowLevel2 = petGrowLevel2;
	}

}
