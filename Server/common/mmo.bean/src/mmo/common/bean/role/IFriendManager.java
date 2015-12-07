package mmo.common.bean.role;

public interface IFriendManager {

	public Friend getFriend(int roleId, int targetId);

	public Friend getFriend(int roleId, String targetName);
}
