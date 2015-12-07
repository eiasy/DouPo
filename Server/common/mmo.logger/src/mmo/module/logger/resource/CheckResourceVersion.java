package mmo.module.logger.resource;

import java.util.List;

import mmo.common.bean.TestAccountLib;
import mmo.common.config.update.ClientOperate;
import mmo.common.config.update.ClientUrl;
import mmo.common.config.update.PhoneType;
import mmo.common.config.version.VersionConfig;
import mmo.common.protocol.game.CommonGamePropertyKey;
import mmo.common.protocol.game.UserProtocol;
import mmo.common.protocol.game.UserProtocol.Message;
import mmo.common.protocol.game.UserProtocol.Opcode;
import mmo.common.protocol.game.UserProtocol.Tips;
import mmo.common.protocol.ui.ClientUI;
import mmo.common.protocol.ui.main.Main_0_account;
import mmo.common.resource.DataFile;
import mmo.common.resource.version2.DataDirectory;
import mmo.common.resource.version2.ResVersion2;
import mmo.common.resource.version2.ResVersionManager2;
import mmo.module.logger.account.ILauchOperate;
import mmo.module.logger.account.LoggerAccount;
import mmo.tools.config.NetAddress;
import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;
import mmo.tools.net.extension.session.NetRole;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

public class CheckResourceVersion {

	/** 强制更新 */
	public static final byte FORBID = 0;
	/** 小版本提升，可以进入游戏 */
	public static final byte UPDATE = 1;

	public final static boolean checkCodeAndResVersion(NetRole role) {
		if (TestAccountLib.isTestAccount(role.getAccountId())) {
			role.setHightVersion(true);
		}
		PhoneType pt = ClientUrl.getPhoneType(role.getChannelId(), role.getClientVersion());
		if (pt != null) {
			byte result = pt.validateVersion(role.getCodeVersion());
			String url = pt.getUrl();
			if (role.getChannelId() == 600001) {
				NetAddress address = ProjectCofigs.getNetAddress(role.getChannelSub());
				if (address != null) {
					url = address.getUrl();
				}
			}
			switch (result) {
				case ClientOperate.OPT_ERR_VERSION:
				case ClientOperate.OPT_DOWN_CLIENT: {
					clientDownload(role, pt.getVersion(), url, FORBID);
					return false;
				}
				case ClientOperate.OPT_UP_VERSION: {
					clientDownload(role, pt.getVersion(), url, UPDATE);
					return true;
				}
				case ClientOperate.OPT_HIGHT_VERSION: {
					IoBuffer buf = PacketBufferPool.getPacketBuffer();
					buf.setProtocol(UserProtocol.Server.PROS_6507_RESOURCE_VER);
					buf.putBoolean(false);
					role.sendData(buf);
					role.setHightVersion(true);
					LoggerAccount.checkCodeVersion(role, pt.getVersion(), ILauchOperate.clientNone);
					return true;
				}
				case ClientOperate.OPT_OK: {
					IoBuffer buf = PacketBufferPool.getPacketBuffer();
					buf.setProtocol(UserProtocol.Server.PROS_6507_RESOURCE_VER);
					buf.putBoolean(false);
					role.sendData(buf);
					LoggerAccount.checkCodeVersion(role, pt.getVersion(), ILauchOperate.clientNone);
					return true;
				}
				// case ClientOperate.OPT_TEST_REDIR: {
				// IoBuffer buf = IoBuffer.getPacketBuffer();
				// buf.setProtocol(UserProtocol.Server.PROC_8008_REDIRECT);
				// buf.putString(ClientUrl.getToip());
				// buf.putInt(ClientUrl.getToport());
				// buf.putBoolean(false);
				// role.sendData(buf);
				// LoggerAccount.checkCodeVersion(role, pt.getVersion(), ILauchOperate.clientRedirect);
				// break;
				// }
			}
			return false;
		} else {
			LoggerError.messageLog.error("渠道编号 = " + role.getChannelId() + "  机型编号 = " + role.getClientVersion() + " 找不到资源类型！");
			return false;
		}
	}

	public final static void checkCodeAndResVersion(NetRole role, int clientResVersion, boolean firstActive) {
		ResVersion2 rv = ResVersionManager2.getInstance().getResVersion(role.getClientVersion());
		DataDirectory dataDirectory = rv.getNextDataDirectory(clientResVersion);
		int resNewVersion = rv.getVersion();
		if (dataDirectory != null) {
			resNewVersion = dataDirectory.getVersion();
		}
		if (firstActive) {
			LoggerAccount.checkResource(role, resNewVersion, clientResVersion, ILauchOperate.FIRST);
			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(UserProtocol.Server.PROS_5082_FIRST_START);
			role.sendData(buf);
		}
		resCheck(role, resNewVersion, clientResVersion, dataDirectory);
	}

	private final static void resCheck(NetRole role, int resNewVersion, int clientResVersion, DataDirectory dataDirectory) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6526_RES_INFO);
		if (dataDirectory == null) {
			LoggerAccount.checkResource(role, resNewVersion, clientResVersion, ILauchOperate.resNone);
			buf.putBoolean(false);
		} else {
			LoggerAccount.checkResource(role, resNewVersion, clientResVersion, ILauchOperate.resUpdate);
			buf.putBoolean(true);
			buf.putInt(dataDirectory.getVersion());
			List<DataFile> fileList = dataDirectory.getFileList();
			int listSize = fileList.size();
			buf.putInt(listSize);
			DataFile df = null;
			for (int fi = 0; fi < listSize; fi++) {
				df = fileList.get(fi);
				buf.putInt(df.getId());
				buf.putString(df.getFileName());
				buf.putInt(df.getSize());
				buf.putString(df.getMD5());
			}
		}
		role.sendData(buf);

	}

	public final static void clientDownload(NetRole role, String clientCodeVersion, String url, byte operate) {
		if (operate == FORBID) {
			LoggerAccount.checkCodeVersion(role, clientCodeVersion, ILauchOperate.clientEnforeDownload);
		} else {
			LoggerAccount.checkCodeVersion(role, clientCodeVersion, ILauchOperate.clientCompatibleDownload);
		}
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6507_RESOURCE_VER);
		buf.putBoolean(true);
		role.sendData(buf);

		IoBuffer packet = PacketBufferPool.getPacketBuffer();
		packet.setProtocol(UserProtocol.Server.PROS_6500_OPEN_UI);
		packet.setNetConfirm(-1);
		packet.setScriptName(VersionConfig.Client.getUIScript(role.getClientVersion(), VersionConfig.UIIdentity.UPDATE_CLIENT));
		packet.setOverlap(false);
		packet.setCmd(ClientUI.UI.ADD_OBJECT_PROPERTY);
		packet.setMain(Main_0_account.main_1_common);
		packet.setSub(Main_0_account.Sub_1.sub_17_Clienturl);
		packet.setSerial((short) 0);
		packet.setProperty(CommonGamePropertyKey.CommonKey.COMMON_NAME_6, "更新提示");
		packet.setProperty(CommonGamePropertyKey.CommonKey.COMMON_DESCRIBE_24, "版本过低，请下载最新版本" + clientCodeVersion + "。");
		packet.setProperty(CommonGamePropertyKey.RoleLoginKey.URL_1153, url);
		packet.setProperty(CommonGamePropertyKey.CommonKey.COMMON_STATUS_17, operate);// 强制更新
		packet.endProperty();
		packet.endCmd();
		role.sendData(packet);
	}

	public final static void checkClientCodeVersion(NetRole role, int connectId) {
		PhoneType pt = ClientUrl.getPhoneType(role.getChannelId(), role.getClientVersion());
		if (pt != null) {
			byte result = pt.validateVersion(role.getCodeVersion());
			String url = pt.getUrl();
			if (role.getChannelId() == 600001) {
				NetAddress address = ProjectCofigs.getNetAddress(role.getChannelSub());
				if (address != null) {
					url = address.getUrl();
				}
			}
			switch (result) {
				case ClientOperate.OPT_ERR_VERSION:
				case ClientOperate.OPT_DOWN_CLIENT: {
					clientDownload(role, pt.getVersion(), url, FORBID);
					break;
				}
				case ClientOperate.OPT_UP_VERSION: {
					clientDownload(role, pt.getVersion(), url, UPDATE);
					break;
				}
				case ClientOperate.OPT_HIGHT_VERSION: {
					role.setHightVersion(true);
					role.sendOpcode(Tips.flash, Message.info, Opcode.FAIL, FINAL_VERSION, connectId);
					break;
				}
				case ClientOperate.OPT_OK: {
					role.sendOpcode(Tips.flash, Message.info, Opcode.FAIL, FINAL_VERSION, connectId);
					break;
				}
				// case ClientOperate.OPT_TEST_REDIR: {
				// LoggerAccount.checkCodeVersion(role, pt.getVersion(), ILauchOperate.clientRedirect);
				// role.sendOpcode(Tips.flash, Message.info, Opcode.FAIL, FINAL_VERSION, connectId);
				// break;
				// }
				default: {
					role.sendOpcode(Tips.flash, Message.info, Opcode.FAIL, FINAL_VERSION, connectId);
				}
			}
		} else {
			role.sendOpcode(Tips.flash, Message.info, Opcode.FAIL, WAIT, connectId);
			LoggerError.messageLog.error("渠道编号 = " + role.getChannelId() + "  机型编号 = " + role.getClientVersion() + " 找不到资源类型！");
		}
	}

	static final String FINAL_VERSION = "已经是最新版本！";
	static final String WAIT          = "校验失败，请稍后再试！";
}
