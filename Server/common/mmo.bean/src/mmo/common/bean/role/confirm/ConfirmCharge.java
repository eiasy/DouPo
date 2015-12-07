package mmo.common.bean.role.confirm;

import mmo.common.bean.role.Role;
import mmo.common.protocol.game.UserProtocol.Message;
import mmo.common.protocol.game.UserProtocol.Opcode;
import mmo.common.protocol.game.UserProtocol.Tips;

public class ConfirmCharge extends AConfirmEvent {
	private int roleId;

	public ConfirmCharge(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

	@Override
	public void agree(Role role) {
		if (role.getId() != roleId) {
			return;
		}
//		IoBuffer buf = PacketBufferPool.getPacketBuffer();
//		buf.setProtocol(UserProtocol.Server.PROS_6500_OPEN_UI);
//		buf.setNetConfirm(-1);
//		buf.setScriptName(VersionConfig.Client.getUIScript(role.getClientVersion(), VersionConfig.UIIdentity.TO_CHARGE));
//		buf.setOverlap(true);
//		buf.endCmd();
//		role.sendData(buf);
		role.sendOpcode(Tips.flash, Message.info, Opcode.FAIL, "货币不足！", -1);
	}

}
