package mmo.common.config.role;

public interface BindPhoneResult {
	/** 绑定成功 */
	byte BIND_OK     = 1;
	/** 绑定失败 */
	byte BIND_FAIL   = 2;
	/** 绑定失败-验证码错误 */
	byte VCODE_ERROR = 3;
	/** 绑定失败-密码错误 */
	byte PWD_ERROR   = 4;
	byte PHONE_ERROR = 5;

}
