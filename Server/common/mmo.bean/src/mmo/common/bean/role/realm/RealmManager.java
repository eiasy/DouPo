package mmo.common.bean.role.realm;

import java.util.HashMap;
import java.util.Map;

import mmo.common.bean.role.Role;

/**
 * 境界管理
 * 
 * @author 李天喜
 * 
 */
public class RealmManager {
	private static RealmManager    instance    = new RealmManager();
	/** 所有的穴位 */
	private Map<Short, RealmPoint> realmPoints = new HashMap<Short, RealmPoint>();
	/** 所有境界 */
	private Map<Byte, Realm>       realms      = new HashMap<Byte, Realm>();
	/** 最大穴位 */
	private short                  realmPointMax;

	private RealmManager() {

	}

	public short getRealmPointMax() {
		return realmPointMax;
	}

	/**
	 * 获取唯一实例
	 * 
	 * @return 唯一实例
	 */
	public static final RealmManager getInstance() {
		if (instance == null) {
			instance = new RealmManager();
		}
		return instance;
	}

	/**
	 * 取得境界数据
	 * 
	 * @param realmValue
	 * @return
	 */
	public Realm getRealm(byte realmValue) {
		Realm realm = realms.get(realmValue);
		if (realm == null && realmValue > -1) {
			realm = new Realm(realmValue);
			realms.put(realmValue, realm);
		}
		return realm;
	}

	public void addRealmPoint(byte realmId, RealmPoint point) {
		Realm realm = realms.get(realmId);
		if (realm == null && realmId > -1) {
			realm = new Realm(realmId);
			realms.put(realmId, realm);
		}
		realmPoints.put(point.getIdentity(), point);
		realm.addRealmPoint(point.getIdentity());
		realmPointMax = point.getIdentity();
	}

	public Map<Byte, Realm> getRealms() {
		Map<Byte, Realm> realmsTemp = new HashMap<Byte, Realm>();
		realmsTemp.putAll(realms);
		return realmsTemp;
	}

	/**
	 * 取得空位信息
	 * 
	 * @param identity
	 * @return
	 */
	public RealmPoint getRealmPoint(short identity) {
		return realmPoints.get(identity);
	}

	public final void initRealmProperty(Role role) {
		if (role.getRealmPoint() < 0) {
			return;
		}

		RealmPoint ir = null;
		short max = role.getRealmPoint();
		for (short ii = 0; ii <= max; ii++) {
			ir = realmPoints.get(ii);
			if (ir != null) {
				role.changeHpRealmPro(ir.getRealmLife());
				role.changeAttackRealm(ir.getRealmAttack());
				role.changeDefenceRealm(ir.getRealmdefend());
			}
		}
	}

}
