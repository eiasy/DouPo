package mmo.common.bean.role.realm;

/**
 * 境界
 * 
 * @author 李天喜
 * 
 */
public class Realm {
	/** 未学习 */
	public static final byte state_0_off = 0;
	/** 已开启 */
	public static final byte state_1_on  = 1;
	/** 境界编号 */
	private byte             realm;
	private short            realmPointSub;
	private short            realmPointSup;

	public Realm(byte realm) {
		this.realm = realm;
	}

	protected void addRealmPoint(short realmPoint) {
		if (realmPointSub < 1) {
			this.realmPointSub = realmPoint;
		}
		this.realmPointSup = realmPoint;
	}

	public int getRealmPointCount() {
		return realmPointSup - realmPointSub + 1;
	}

	public short getRealmPointSub() {
		return realmPointSub;
	}

	public short getRealmPointSup() {
		return realmPointSup;
	}

	public byte getRealm() {
		return realm;
	}

}
