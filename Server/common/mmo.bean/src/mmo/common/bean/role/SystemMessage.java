package mmo.common.bean.role;

import java.util.List;

import mmo.common.bean.sect.IGameSect;
import mmo.common.bean.sect.battle.ISectBattle;
import mmo.common.bean.zhenYan.ZhenYanManager;
import mmo.common.config.StringLib;
import mmo.common.config.version.VersionConfig;
import mmo.common.protocol.game.CommonGamePropertyKey;
import mmo.common.protocol.game.UserProtocol;
import mmo.common.protocol.game.operate.ChatChannel;
import mmo.common.protocol.ui.ClientUI;
import mmo.common.protocol.ui.main.Main_0_account;
import mmo.common.protocol.ui.main.Main_800_Social;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

public class SystemMessage {
	/**
	 * 服务器通知
	 * 
	 * @param index
	 *            序号
	 * @param name
	 *            名称
	 * @param note
	 *            描述
	 */
	public final static void serverNotice(Role role, byte index, String name, String note) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6500_OPEN_UI);
		buf.setNetConfirm(-1);
		buf.setScriptName(StringLib.CommonStr.commonNo.hashCode());
		buf.setOverlap(false);
		buf.setCmd(ClientUI.UI.ADD_OBJECT_PROPERTY);
		buf.setMain(Main_0_account.main_44_notice);
		buf.setSub(Main_0_account.Sub_44.sub_0_ServerNotice);
		buf.setSerial(index);
		buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_NAME_6, name);
		buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_DESCRIBE_24, note);
		buf.endProperty();
		buf.endCmd();
		role.sendData(buf);
	}

	/**
	 * 通知新邮件
	 * 
	 */
	public final static void newEmailNotice(Role role) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6500_OPEN_UI);
		buf.setNetConfirm(-1);
		buf.setScriptName(VersionConfig.Client.NONE_SCRIPT);
		buf.setOverlap(false);
		buf.setCmd(ClientUI.UI.ADD_OBJECT_PROPERTY);
		buf.setMain(Main_800_Social.main_804_email);// 主类别
		buf.setSub(Main_800_Social.Sub_804.sub_2_newEmail);// 子类别
		buf.setSerial(0);
		buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_STATUS_17, 1);
		buf.endProperty();
		buf.endCmd();
		role.sendData(buf);
	}

	/**
	 * 击杀的怪物是绑定有阵法传送口的阵眼，则在改宗门频道发消息
	 * 
	 * @param killed
	 *            被击杀的怪物
	 */
	public final static void sendSectNotice(Role role, List<Role> killed) {
		/**
		 * @alter
		 * @name：xiaoqiong
		 * @date：13-05-29
		 * @note：若死亡的怪物是绑定有阵法传送口的阵眼，则在改宗门频道发消息
		 */
		// 获取角色所在宗门
		IGameSect sect = role.getGameSect();
		// 获取正在进行的宗战
		ISectBattle sectBattle = null;
		if (sect != null) {
			// 作为攻击方 进入宗战场景
			sectBattle = sect.getSectApplyBattle();
			if (sectBattle != null) {
				ZhenYanManager instance = ZhenYanManager.getInstance();
				int monsterId = 0;
				for (int i = 0; i < killed.size(); i++) {
					monsterId = killed.get(i).getId();
					if (instance.isExitMonster(monsterId)) {
						IoBuffer buf = PacketBufferPool.getPacketBuffer();
						buf.setProtocol(UserProtocol.Server.PROS_5020_CHAT);
						buf.put(ChatChannel.CHANNEL_MARTIAL);
						buf.putInt(role.getProductId());
						buf.put(role.getSex());
						buf.putString(StringLib.CommonStr.commonNo);

						buf.put((byte) 0);
						buf.putString(instance.getNote(monsterId));

						RoleManager.getInstance().chatSect(buf, sect.getMembers());
						break;
					}
				}
			}
		}
	}
}
