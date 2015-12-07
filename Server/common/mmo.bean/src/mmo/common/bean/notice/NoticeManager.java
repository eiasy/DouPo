package mmo.common.bean.notice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import mmo.common.bean.role.Role;
import mmo.common.config.version.VersionConfig;
import mmo.common.protocol.game.CommonGamePropertyKey;
import mmo.common.protocol.game.UserProtocol;
import mmo.common.protocol.ui.ClientUI;
import mmo.common.protocol.ui.main.Main_600_UiMain;
import mmo.tools.util.string.StringSplit;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

public class NoticeManager {
	private final static ComparatorApp comparator = new ComparatorApp();
	private static NoticeManager       instance   = new NoticeManager();

	public final static NoticeManager getInstance() {
		return instance;
	}

	public final static void setNoticeManager(NoticeManager noticeManager) {
		if (noticeManager != null) {
			instance = noticeManager;
		}
	}

	/** 解析上线时发送的公告<key=公告版本号version, value=公告内容text> */
	private String                 loginFirstTxt       = "";

	private String                 noticeKickout       = "您已经被踢下线，请联系：客服邮箱：gm@coco.cn 客服中心：http://gm.coco.cn/";
	private String                 noticeGm            = "GM客服";
	private String                 noticeAccountFreeze = StringSplit.transformString("您的账号已经被冻结，请联系：客服邮箱：gm@coco.cn 客服中心：http://gm.coco.cn/");
	private String                 noticeDeviceFreeze  = StringSplit.transformString("您的设备已经被冻结，请联系：客服邮箱：gm@coco.cn 客服中心：http://gm.coco.cn/");
	private String                 noticeRoleFreeze    = StringSplit.transformString("该角色已经被冻结，请联系：客服邮箱：gm@coco.cn 客服中心：http://gm.coco.cn/");
	private String                 noticeChatForbid    = "您已经被禁言，请联系：客服邮箱：gm@coco.cn 客服中心：http://gm.coco.cn/";
	private String                 noticeSpeed         = "您已经被踢下线，请联系：客服邮箱：gm@coco.cn 客服中心：http://gm.coco.cn/";
	private String                 noticeRepair        = "将要对系统进行维护，给你带来不便，敬请谅解！请联系：客服邮箱：gm@coco.cn 客服中心：http://gm.coco.cn/";
	private String                 noticeValidate      = "请正常登录游戏！请联系：客服邮箱：gm@coco.cn 客服中心：http://gm.coco.cn/";

	/** 系统公告 */
	private final List<GameNotice> systemNotice        = new ArrayList<GameNotice>();
	private final AtomicInteger    noticeIdGenerator   = new AtomicInteger(1);

	public void initGameNotice(GameNotice notice) {
		systemNotice.add(notice);
		Collections.sort(systemNotice, comparator);
		if (notice.getId() > noticeIdGenerator.get()) {
			noticeIdGenerator.set(notice.getId());
		}
	}

	public void setNoticeMaxId(int maxId) {
		noticeIdGenerator.set(maxId);
	}

	public String getNoticeRoleFreeze() {
		return noticeRoleFreeze;
	}

	public void setNoticeRoleFreeze(String noticeRoleFreeze) {
		this.noticeRoleFreeze = noticeRoleFreeze;
	}

	public final void addGameNotice(GameNotice gn) {
		gn.setId(noticeIdGenerator.incrementAndGet());
		systemNotice.add(gn);
		Collections.sort(systemNotice, comparator);
	}

	public List<GameNotice> getSystemNotice() {
		return systemNotice;
	}

	/**
	 * 解析notice.xml文件
	 * 
	 * @param file
	 * @return
	 */

	public String getNoticeChatForbid() {
		return noticeChatForbid;
	}

	public String getNoticeAccountFreeze() {
		return noticeAccountFreeze;
	}

	public String getNoticeDeviceFreeze() {
		return noticeDeviceFreeze;
	}

	public String getNoticeGm() {
		return noticeGm;
	}

	public String getLoginFirst() {
		return loginFirstTxt;
	}

	public String getLoginFirstTxt() {
		return loginFirstTxt;
	}

	public String getNoticeKickout() {
		return noticeKickout;
	}

	public String getNoticeSpeed() {
		return noticeSpeed;
	}

	public String getNoticeRepair() {
		return noticeRepair;
	}

	public String getNoticeValidate() {
		return noticeValidate;
	}

	public final void pushLoginNotice(Role role) {
		if (systemNotice.size() > 0) {
			IoBuffer buf = PacketBufferPool.getPacketBuffer();
			buf.setProtocol(UserProtocol.Server.PROS_6500_OPEN_UI);
			buf.setNetConfirm(-1);
			buf.setScriptName(VersionConfig.Client.NONE_SCRIPT);
			buf.setOverlap(false);

			buf.setCmd(ClientUI.UI.CLEAR_OBJECT_LIST);
			buf.setMain(Main_600_UiMain.main_610_notice);
			buf.setSub(0);

			buf.setCmd(ClientUI.UI.ITEM_LENGTH);
			buf.setMain(Main_600_UiMain.main_610_notice);
			buf.setSub(0);
			int position = buf.position();
			int count = 0;
			buf.putShort((short) (count));
			buf.endSub();

			buf.setCmd(ClientUI.UI.ADD_PROPERTY_GROUP);

			buf.setMain(Main_600_UiMain.main_610_notice);
			buf.setSub(0);

			GameNotice on = null;
			for (int ni = 0; ni < systemNotice.size() && count < systemNotice.size();) {
				on = systemNotice.get(ni);
				if (on.isInvalid()) {
					systemNotice.remove(ni);
					continue;
				}
				if (on.isEffect(role)) {
					buf.setSerial(count++);
					buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_DETAIL_23, on.getContentStyle());
					buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_NAME_6, on.getTitleStyle());
					buf.endProperty();
				}
				ni++;
			}
			buf.putShort(position, (short) (count));
			buf.endSerial();
			buf.endSub();

			buf.endMain();
			buf.endCmd();

			role.sendData(buf);
			if (count > 0) {
				IoBuffer bufWindow = PacketBufferPool.getPacketBuffer();
				bufWindow.setProtocol(UserProtocol.Server.PROS_6509_OPEN_WINDOW_OVERLAPP);
				bufWindow.setNetConfirm(-1);
				bufWindow.setScriptName(VersionConfig.Client.getUIScript(role.getClientVersion(), VersionConfig.UIIdentity.SYSTEM_NOTICE));
				bufWindow.put((byte) 5);
				bufWindow.endCmd();
				role.sendData(bufWindow);
			}
		}
	}

	public void setNoticeAccountFreeze(String stringValue) {
		if (stringValue != null) {
			noticeAccountFreeze = StringSplit.transformString(stringValue);
		}
	}

	public void setNoticeDeviceFreeze(String stringValue) {
		if (stringValue != null) {
			noticeDeviceFreeze = StringSplit.transformString(stringValue);
		}
	}

	public void setNoticeForbitChat(String stringValue) {
		if (stringValue != null) {
			noticeChatForbid = StringSplit.transformString(stringValue);
		}
	}

	public void setNoticeKickOut(String stringValue) {
		if (stringValue != null) {
			noticeKickout = StringSplit.transformString(stringValue);
		}
	}

	public void setNoticeLoginFirst(String stringValue) {
		if (stringValue != null) {
			loginFirstTxt = StringSplit.transformString(stringValue);
		}
	}

	public void setNoticeLoginValidate(String stringValue) {
		if (stringValue != null) {
			noticeValidate = StringSplit.transformString(stringValue);
		}
	}

	public void setNoticeSystemRepair(String stringValue) {
		if (stringValue != null) {
			noticeRepair = StringSplit.transformString(stringValue);
		}
	}

	public void setNoticeWaiGua(String stringValue) {
		if (stringValue != null) {
			noticeSpeed = StringSplit.transformString(stringValue);
		}
	}

}

class ComparatorApp implements Comparator<Object> {

	public int compare(Object arg0, Object arg1) {
		GameNotice pa01 = (GameNotice) arg0;
		GameNotice pa02 = (GameNotice) arg1;

		// 首先比较年龄，如果年龄相同，则比较名字
		if (pa01.getOrderValue() > pa02.getOrderValue()) {
			return -1;
		} else if (pa01.getOrderValue() == pa02.getOrderValue()) {
			return 0;
		} else {
			return 1;
		}
	}
}
