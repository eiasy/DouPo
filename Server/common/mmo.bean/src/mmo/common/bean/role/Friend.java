package mmo.common.bean.role;

import mmo.common.config.version.VersionConfig;
import mmo.common.protocol.game.CommonGamePropertyKey;
import mmo.common.protocol.game.UserProtocol;
import mmo.common.protocol.ui.ClientUI;
import mmo.common.protocol.ui.main.Main_800_Social;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

public class Friend {
	/** 客服专用ID */
	public static final int  GAME_SERVICE_ID = 9000280;

	/** 好友 */
	public static final byte CATE_FRIEND     = 0;
	/** 陌生人 */
	public static final byte CATE_TEMP       = 2;

	/** 数据库ID */
	private long             id;
	/** 角色编号 */
	protected int            roleId;
	/** 好友编号 **/
	protected int            friendId;
	/** 关系类型 */
	protected byte           ftype;
	/** 亲密度 */
	protected int            intimate;
	/*** 建立关系的时间 */
	protected long           jtime;
	/** 下一次可以协战的时间 */
	protected long           xieZhanTime;
	/** 累计获得该好后赠送的 */
	private int              jingLiCount;

	public Friend() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getJtime() {
		return jtime;
	}

	public void setJtime(long jtime) {
		this.jtime = jtime;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public byte getFtype() {
		return ftype;
	}

	public final boolean isFriend() {
		return ftype == CATE_FRIEND;
	}

	public final boolean isTemp() {
		return ftype == CATE_TEMP;
	}

	public void setFtype(byte ftype) {
		this.ftype = ftype;
	}

	public int getIntimate() {
		return intimate;
	}

	public void setIntimate(int intimate) {
		this.intimate = intimate;
	}

	public final static Friend newInstance() {
		return new Friend();
	}

	public int getJingLiCount() {
		return jingLiCount;
	}

	public void setJingLiCount(int jingLiCount) {
		this.jingLiCount = jingLiCount;
	}

	public static final Friend createFriend(int roleId, int friendId) {
		Friend f = new Friend();
		f.jtime = System.currentTimeMillis();
		f.friendId = friendId;
		f.ftype = CATE_FRIEND;
		f.roleId = roleId;
		return f;
	}

	public long getXieZhanTime() {
		return xieZhanTime;
	}

	public void setXieZhanTime(long xieZhanTime) {
		this.xieZhanTime = xieZhanTime;
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", roleId=" + roleId + ", friendId=" + friendId + ", ftype=" + ftype + ", intimate=" + intimate + ", jtime="
		        + jtime + ", xieZhanTime=" + xieZhanTime + "]";
	}

	public void handselJingLi(int count) {
		this.jingLiCount += count;
		Role role = RoleManager.getOLUserRole(friendId);
		if (role != null) {
			IoBuffer buf = PacketBufferPool.getPacketBuffer();
			buf.setProtocol(UserProtocol.Server.PROS_6500_OPEN_UI);
			buf.setNetConfirm(-1);
			buf.setScriptName(VersionConfig.Client.NONE_SCRIPT);
			buf.setOverlap(false); // false 替换 true 新窗口打开
			buf.setCmd(ClientUI.UI.UPDATE_OBJECT);
			buf.setMain(Main_800_Social.main_802_socrial);
			buf.setSub(Main_800_Social.Sub_802.sub_802_0_friend);
			buf.setSearchID(CommonGamePropertyKey.CommonKey.COMMON_ID_5, roleId);
			buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_CATE_18, 1); 
			buf.endProperty();
			buf.endCmd();
			role.sendData(buf);
		}
	}
}
