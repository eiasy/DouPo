package mmo.common.script;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import mmo.common.bean.role.Role;
import mmo.common.config.ServerRoleProperty;
import mmo.common.config.StringLib;
import mmo.common.config.version.VersionConfig;
import mmo.common.protocol.game.CommonGamePropertyKey;
import mmo.common.protocol.game.UserProtocol;
import mmo.common.protocol.game.sub.SubPro_1117_mutual;
import mmo.common.protocol.ui.ClientUI;
import mmo.common.protocol.ui.main.Main_0_account;
import mmo.common.protocol.ui.main.Main_600_UiMain;
import mmo.tools.util.MathUtil;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

public class Script {
	/** 提示信息 */
	public static int       KEY_INFO       = 1;
	/** 初始化 */
	public static final int SKILL_INIT     = 0;
	/** 预判 */
	public static final int SKILL_PREJUDGE = 1;
	/** 施法 */
	public static final int SKILL_PLAY     = 2;
	/** 技能等级描述 */
	public static final int SKILL_NOTE     = 3;

	private static Random   _random        = new Random();

	/**
	 * 得到两个非负数范围内的随机值(包括这两个数,第二个数大于等于第一个数)
	 * 
	 * @param sub
	 *            较小的非负数
	 * @param sup
	 *            较大的非负数
	 * @return 两数范围内的一个数,包括边界
	 */
	public final static int getRandom(int sub, int sup) {
		if (sub > sup) {
			int t = sub;
			sub = sup;
			sup = t;
		}
		return sub + Math.abs(_random.nextInt() % (sup - sub + 1));
	}

	/**
	 * 进行一次几率运算（百分比）
	 * 
	 * @param value
	 *            百分比比例
	 * @return true选中，false未选中
	 */
	public final static boolean validate(int value) {
		return getRandom(0, 100) < value;
	}

	/**
	 * 进行一次几率运算（百分比）
	 * 
	 * @param value
	 *            百分比比例
	 * @param baseValue
	 *            基础值
	 * @return true选中，false未选中
	 */
	public final static boolean validate(int value, int baseValue) {
		return getRandom(0, baseValue) <= value;
	}

	/**
	 * 验证是否添加BUF
	 * 
	 * @param addBuffPercent
	 *            添加几率
	 * @return true 添加 false 不添加
	 */
	protected final boolean validateAddBuf(int addBuffPercent) {
		int random = MathUtil.getRandom(1, 100);
		if (random <= addBuffPercent * 100) {
			return true;
		}
		return false;
	}

	/**
	 * 计算击退后目标对象的最终坐标
	 * 
	 * @param ax
	 *            施法者X坐标
	 * @param ay
	 *            施法者Y坐标
	 * @param bx
	 *            目标对象当前X坐标
	 * @param by
	 *            目标对象当前Y坐标
	 * @param dis
	 *            击退距离
	 * @return
	 */
	public int[] getEndxAndEndy(int ax, int ay, int bx, int by, int dis) {
		int[] ends = new int[2];
		if (by == ay) {
			if (bx > ax) {
				ends[0] = bx + dis;
			} else {
				ends[0] = bx - dis;
			}
			ends[1] = by;
		} else {
			if (ax == bx) {
				if (by > ay) {
					ends[1] = by + dis;
				} else {
					ends[1] = by - dis;
				}
				ends[0] = bx;
			} else {
				double k = ((by - ay) * 1.0) / (bx - ax);// 斜率
				int dx = getXpointByPoints(k, dis);
				int dy = (int) Math.abs(k * dx);
				if (bx > ax) {
					if (by > ay) {
						ends[0] = bx + dx;
						ends[1] = by + dy;
					} else {
						ends[0] = bx + dx;
						ends[1] = by - dy;
					}
				} else {
					if (by > ay) {
						ends[0] = bx - dx;
						ends[1] = by + dy;
					} else {
						ends[0] = bx - dx;
						ends[1] = by - dy;
					}
				}
			}
		}
		return ends;
	}

	/**
	 * 获取坐标差量
	 * 
	 * @param k
	 *            斜率
	 * @param dis
	 *            击退距离
	 * @return
	 */
	public int getXpointByPoints(double k, int dis) {
		double dx = dis * dis / (k * k + 1);
		return (int) Math.sqrt(dx);
	}

	/**
	 * 通过真实ID转化为装备穿戴者的唯一ID
	 * 
	 * @param role
	 * @param realId
	 * @return
	 */
	public static int getEquipRoleId(Role role, int realId) {
		if (realId < 1) {
			return 0;
		}
		if (role.getId() == realId) {
			return realId;
		} else {
			return 0;
		}
	}

	/**
	 * 播放效果动画
	 * 
	 * @param role
	 *            角色
	 * @param uiScript
	 *            UI脚本
	 * @param props
	 *            播放值(UI属性key,值)
	 */
	public final static void playEffectAnimation(Role role, Map<Short, Integer> props) {
		if (props != null && !props.isEmpty()) {
			int index = 0;
			Set<Entry<Short, Integer>> values = props.entrySet();
			IoBuffer buf = PacketBufferPool.getPacketBuffer();
			buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
			buf.setNetConfirm(-1);
			buf.setScriptName(VersionConfig.Client.getUIScript(role.getClientVersion(), VersionConfig.UIIdentity.PLAY_EFFECT_ANI));
			buf.put((byte) 9);// 打开window类型
			buf.setCmd(ClientUI.UI.CLEAR_OBJECT_LIST);
			buf.setMain(Main_600_UiMain.main_607_effectAni);
			buf.setSub(0);

			buf.setCmd(ClientUI.UI.ITEM_LENGTH);
			buf.setMain(Main_600_UiMain.main_607_effectAni);
			buf.setSub(0);
			buf.putShort((short) props.size());
			buf.endSub();

			buf.setCmd(ClientUI.UI.ADD_PROPERTY_GROUP);
			buf.setMain(Main_600_UiMain.main_607_effectAni);
			buf.setSub(0);
			if (values != null) {
				for (Entry<Short, Integer> value : values) {
					buf.setSerial(index++);
					buf.setProperty(CommonGamePropertyKey.Moneys.MONEY_KEY_500, ServerRoleProperty.getClientKey(value.getKey()));
					buf.setProperty(CommonGamePropertyKey.Moneys.MONEY_VALUE_501, value.getValue());
					buf.endProperty();
				}
			}
			buf.endSerial();
			buf.endSub();
			buf.endMain();
			buf.endCmd();
			role.sendData(buf);
		}
	}

	/**
	 * 播放效果动画
	 * 
	 * @param role
	 *            角色
	 * @param proKey
	 *            属性key
	 * @param value
	 *            值
	 */
	public final static void playEffectAnimation(Role role, short proKey, int value) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
		buf.setNetConfirm(-1);
		buf.setScriptName(VersionConfig.Client.getUIScript(role.getClientVersion(), VersionConfig.UIIdentity.PLAY_EFFECT_ANI));
		buf.put((byte) 9);
		buf.setCmd(ClientUI.UI.CLEAR_OBJECT_LIST);
		buf.setMain(Main_600_UiMain.main_607_effectAni);
		buf.setSub(0);

		buf.setCmd(ClientUI.UI.ADD_PROPERTY_GROUP);
		buf.setMain(Main_600_UiMain.main_607_effectAni);
		buf.setSub(0);
		buf.setSerial(0);
		buf.setProperty(CommonGamePropertyKey.Moneys.MONEY_KEY_500, ServerRoleProperty.getClientKey(proKey));
		buf.setProperty(CommonGamePropertyKey.Moneys.MONEY_VALUE_501, value);
		buf.endProperty();
		buf.endSerial();
		buf.endSub();
		buf.endMain();
		buf.endCmd();
		role.sendData(buf);
	}

	/**
	 * 播放效果动画
	 * 
	 * @param role
	 *            角色
	 * @param proKey
	 *            属性key
	 * @param value
	 *            值
	 */
	public final static void playEffectAnimation(Role role, int len, short proKey, int value) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
		buf.setNetConfirm(-1);
		buf.setScriptName(VersionConfig.Client.getUIScript(role.getClientVersion(), VersionConfig.UIIdentity.PLAY_EFFECT_ANI));
		buf.put((byte) 9);
		buf.setCmd(ClientUI.UI.CLEAR_OBJECT_LIST);
		buf.setMain(Main_600_UiMain.main_607_effectAni);
		buf.setSub(0);

		buf.setCmd(ClientUI.UI.ITEM_LENGTH);
		buf.setMain(Main_600_UiMain.main_607_effectAni);
		buf.setSub(0);
		buf.putShort((short) len);
		buf.endSub();

		buf.setCmd(ClientUI.UI.ADD_PROPERTY_GROUP);
		buf.setMain(Main_600_UiMain.main_607_effectAni);
		buf.setSub(0);
		for (short i = 0; i < len; i++) {
			buf.setSerial(i);
			buf.setProperty(CommonGamePropertyKey.Moneys.MONEY_KEY_500, ServerRoleProperty.getClientKey(proKey));
			buf.setProperty(CommonGamePropertyKey.Moneys.MONEY_VALUE_501, value);
			buf.endProperty();
		}
		buf.endSerial();
		buf.endSub();
		buf.endMain();
		buf.endCmd();
		role.sendData(buf);
	}

	/**
	 * 打开指定的脚本,打开的界面是正常的
	 * 
	 * @param script
	 */
	public final static void openScriptUi(Role role, int connectId, int script) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
		buf.setNetConfirm(connectId);
		buf.setScriptName(VersionConfig.Client.getUIScript(role.getClientVersion(), script));
		buf.put((byte) 5);
		buf.endCmd();
		role.sendData(buf);
	}

	/**
	 * 打开指定的脚本,打开的界面是正常的
	 * 
	 * @param script
	 */
	public final static void openScriptUi_cmd2(Role role, int connectId, int script) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
		buf.setNetConfirm(connectId);
		buf.setScriptName(VersionConfig.Client.getUIScript(role.getClientVersion(), script));
		buf.put((byte) 2);
		buf.endCmd();
		role.sendData(buf);
	}

	/**
	 * 打开指定的脚本,打开的界面是正常的
	 * 
	 * @param script
	 */
	public final static void openScriptUi_cmd5(Role role, int connectId, int script, int... parameters) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
		buf.setNetConfirm(connectId);
		buf.setScriptName(VersionConfig.Client.getUIScript(role.getClientVersion(), script));
		buf.put((byte) 5);
		buf.endCmd();
		for (int parameter : parameters) {
			buf.putInt(parameter);
		}
		role.sendData(buf);
	}

	/**
	 * 打开指定的脚本,打开的界面是正常的
	 * 
	 * @param script
	 */
	public final static void openScriptUi_cmd2(Role role, int connectId, int script, int... parameters) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
		buf.setNetConfirm(connectId);
		buf.setScriptName(VersionConfig.Client.getUIScript(role.getClientVersion(), script));
		buf.put((byte) 2);
		buf.endCmd();
		for (int parameter : parameters) {
			buf.putInt(parameter);
		}
		role.sendData(buf);
	}

	public static final void openScriptUi(Role role, int connectId, String script) {
		openScriptUi(role, connectId, script, (byte) 5);
	}

	public static final void openScriptUi(Role role, int connectId, String script, byte windowType) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
		buf.setNetConfirm(connectId);
		buf.setScriptName(script);
		buf.put(windowType);
		buf.endCmd();
		role.sendData(buf);
	}

	public static final void openScriptUiByType9(Role role, int connectId, String script) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
		buf.setNetConfirm(connectId);
		buf.setScriptName(script);
		buf.put((byte) 9);
		buf.endCmd();
		role.sendData(buf);
	}

	public static final void openScriptUi(Role role, int connectId, String script, int... parameters) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
		buf.setNetConfirm(connectId);
		buf.setScriptName(script);
		buf.put((byte) 5);
		buf.endCmd();
		for (int parameter : parameters) {
			buf.putInt(parameter);
		}
		role.sendData(buf);
	}

	public static final void openScriptUiByType2(Role role, int connectId, String script) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
		buf.setNetConfirm(connectId);
		buf.setScriptName(script);
		buf.put((byte) 2);
		buf.endCmd();
		role.sendData(buf);
	}

	/**
	 * 打开指定的脚本,打开的界面是抖动的
	 * 
	 * @param script
	 */
	public final static void openScriptShakeUi(Role role, int connectId, int script) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
		buf.setNetConfirm(connectId);
		buf.setScriptName(VersionConfig.Client.getUIScript(role.getClientVersion(), script));
		buf.put((byte) -1);
		buf.put((byte) 5);
		buf.endCmd();
		role.sendData(buf);
	}

	/***
	 * 运行场景脚本
	 * 
	 * @param role
	 * @param script
	 */
	public final static void sceneScript(Role role, String script) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6529_SCENE_SCRIPT);
		buf.putString(script);
		role.sendData(buf);
	}

	public final static void openMutual(Role userRole, String title, String content, int proLeft, byte cmdLeft, String dataLeft, String btnLeft,
	        int proRight, byte cmdRight, String dataRight, String btnRight, int connectId) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6501_OPEN_WINDOW);
		buf.setNetConfirm(-1);
		buf.setScriptName(VersionConfig.Client.getUIScript(userRole.getClientVersion(), VersionConfig.UIIdentity.SYSTEM_INFO));
		buf.put((byte) 9);
		buf.setCmd(ClientUI.UI.ADD_OBJECT_PROPERTY);
		buf.setMain(Main_0_account.main_1_common);
		buf.setSub(13);
		buf.setSerial(0);

		buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_DESCRIBE_24, content);
		buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_NAME_6, title);

		buf.setProperty(CommonGamePropertyKey.Protocol.PROTOCOL01_2000, proLeft); // 协议
		buf.setProperty(CommonGamePropertyKey.Parameter01.PARAMETER01_2050, cmdLeft);
		buf.setProperty(CommonGamePropertyKey.Parameter02.PARAMETER02_2100, dataLeft); // 指令串
		buf.setProperty(CommonGamePropertyKey.Parameter02.PARAMETER02_2150, btnLeft);// 按钮名称

		buf.setProperty(CommonGamePropertyKey.Protocol.PROTOCOL01_2001, proRight); // 协议
		buf.setProperty(CommonGamePropertyKey.Parameter01.PARAMETER01_2051, cmdRight); // 指令串
		buf.setProperty(CommonGamePropertyKey.Parameter02.PARAMETER02_2101, dataLeft); // 指令串
		buf.setProperty(CommonGamePropertyKey.Parameter02.PARAMETER02_2151, btnRight);// 按钮名称
		buf.endProperty();
		buf.endCmd();
		userRole.sendData(buf);
		userRole.responseConnect(connectId);
	}

	public final static void openMutual(Role userRole, String title, String content, int proLeft, byte cmdLeft, String dataLeft, String btnLeft,
	        int connectId) {
		openMutual(userRole, title, content, proLeft, cmdLeft, dataLeft, btnLeft, UserProtocol.Client.PROC_1117_BUY_POINT_GOODS,
		        SubPro_1117_mutual.CANCEL_8, "", StringLib.Button.btnCancel, connectId);
	}
}
