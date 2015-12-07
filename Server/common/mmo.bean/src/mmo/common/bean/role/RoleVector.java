package mmo.common.bean.role;

import java.util.ArrayList;
import java.util.Collection;

public class RoleVector extends ArrayList<Role> {
	private static final long serialVersionUID = 1L;
	private int               pixelx            = 0;
	private int               pixely            = 0;
	private int               limitCount       = -1;

	public synchronized boolean add(Role role) {
		addRole2Vector(role);
		return true;
	}

	@Override
	public synchronized boolean addAll(Collection<? extends Role> c) {
		for (Role role : c) {
			addRole2Vector(role);
		}
		return true;
	}


	private void addRole2Vector(Role role) {
		int len = size();
		if (len == 0) {
			super.add(role);
			return;
		}

		for (int i = 0; i < size(); i++) {
			if (role.getPixelDistance(pixelx, pixely) < get(i).getPixelDistance(pixelx, pixely)) {
				super.add(i, role);
				return;
			}
		}
		super.add(role);
	}

	public void resetData(int tilex, int tiley, int limitCount) {
		clear();
		this.pixelx = tilex;
		this.pixely = tiley;
		this.limitCount = limitCount;
	}

	// /**
	// * 返回当前的角色，并指向下一个角色
	// *
	// * @return Role 当前角色，末尾返回null
	// */
	// public Role nextElement() {
	// if (limitCount == 0) {
	// clear();
	// return null;
	// }
	// Role role = null;
	// try {
	// if (elementCount > 0) {
	// limitCount--;
	// role = remove(0);
	// if (role == null) {
	// role = nextElement();
	// }
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return role;
	// }
}