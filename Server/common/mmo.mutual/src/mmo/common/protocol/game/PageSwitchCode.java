package mmo.common.protocol.game;

public interface PageSwitchCode {
	/** 0: 一键注册成功 */
	public final static byte HTML_REGISTER_SUCC   = 0;
	/** 1: 一键注册失败 */
	public final static byte HTML_REGISTER_FAIL   = 1;
	/** 2： 修改密码成功 */
	public final static byte HTML_REPASSWORD_SUCC = 2;
	/** 3：修改密码失败 */
	public final static byte HTML_REPASSWORD_FAIL = 3;
	/** 4：手机验证, 询问是否解绑 */
	public final static byte BIND_PHONE_NEXT      = 4;
	/** 5：手机验证, 向手机发送短信, 等待用户确认绑定 */
	public final static byte VAL_CODE_SENT        = 5;
	/** 6: 手机绑定成功，询问是否进入游戏 */
	public final static byte BIND_PHONE_SUCC      = 6;
	/** 7： 登录失败 */
	public final static byte LOGIN_FAIL           = 7;
	/** 8： 登录成功，询问是否修改密码 */
	public final static byte ASK_RESET_PWD        = 8;
	/** 9： 登录成功, 询问是否绑定手机 */
	public final static byte ASK_BIND_PHONE       = 9;
	/** 10： 关闭浏览器，准备进入游戏 */
	public final static byte LOGIN_SUCC           = 10;
	/** 重置密码成功 */
	public final static byte RESET_PWD_SUCC       = 11;
	/** 重置密码失败 */
	public final static byte RESET_PWD_FAIL       = 12;

	/** 手机绑定失败 */
	public final static byte BIND_PHONE_FAIL      = 13;
	/** 非法的手机号 */
	public final static byte PHONE_ERROR          = 14;
	/** 财付通安全支付 */
	public final static byte CHARGE_CFT_SAFE      = 15;
	/** wap支付 */
	public final static byte CHARGE_WAP           = 16;
	/** 支付宝安全支付 */
	public final static byte CHARGE_ZFB_SAFE      = 17;
	/**充值失败*/
	public final static byte CHARGE_FAIL          = 18;

}
