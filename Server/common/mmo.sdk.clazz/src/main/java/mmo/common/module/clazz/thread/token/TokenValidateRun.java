package mmo.common.module.clazz.thread.token;

import net.sf.json.JSONObject;
import mmo.common.module.clazz.callback.ChannelLib;
import mmo.common.module.clazz.callback.LoginSDKCallback;
import mmo.common.module.sdk.token.AClientData;
import mmo.common.module.sdk.token.TokenData;
import mmo.common.module.sdk.token.TokenManager;
import mmo.common.module.sdk.token.run.ITokenRun;

public class TokenValidateRun implements ITokenRun {

	private AClientData      clientData;
	private LoginSDKCallback sdk;

	public TokenValidateRun() {
		super();
	}

	public void setSdk(LoginSDKCallback sdk) {
		this.sdk = sdk;
	}

	public void setClientData(AClientData clientData) {
		this.clientData = clientData;
	}

	@Override
	public void run() {
		switch (clientData.getChannelId()) {
			case ChannelLib.androidChuKong01: {
				TokenData data = TokenManager.getInstance().getTokendata(clientData.getToken());
				if (data != null) {
					data.resetTime(TokenManager.OVERTIME + System.currentTimeMillis());
					clientData.setUserid(data.getUserid());
					clientData.setUsername(data.getUsername());
					clientData.setChannelSub(data.getChannelSub());
					clientData.userChannelLogin();
				} else {
					clientData.validateFail(AClientData.RT_2_OVERDATE, AClientData.MSG_2_OVERDATE);
				}
				break;
			}
			case ChannelLib.iosPPHelper01: {
				TokenData data = TokenManager.getInstance().getTokendata(clientData.getToken());
				if (data != null) {
					data.resetTime(TokenManager.OVERTIME + System.currentTimeMillis());
					clientData.setUserid(data.getUserid());
					clientData.setUsername(data.getUsername());
					clientData.userChannelLogin();
				} else {
					sdk.validatePP(clientData);
				}
				break;
			}
			case ChannelLib.iosITools: {
				TokenData data = TokenManager.getInstance().getTokendata(clientData.getToken());
				if (data != null) {
					data.resetTime(TokenManager.OVERTIME + System.currentTimeMillis());
					clientData.setUserid(data.getUserid());
					clientData.setUsername(data.getUsername());
					clientData.userChannelLogin();
				} else {
					sdk.validateItools(clientData);
				}
				break;
			}
			case ChannelLib.ios91: {
				TokenData data = TokenManager.getInstance().getTokendata(clientData.getToken());
				if (data != null) {
					data.resetTime(TokenManager.OVERTIME + System.currentTimeMillis());
					clientData.setUserid(data.getUserid());
					clientData.setUsername(data.getUsername());
					clientData.userChannelLogin();
				} else {
					sdk.validate91(clientData);
				}
				break;
			}
			case ChannelLib.iosTongBu: {
				TokenData data = TokenManager.getInstance().getTokendata(clientData.getToken());
				if (data != null) {
					data.resetTime(TokenManager.OVERTIME + System.currentTimeMillis());
					clientData.setUserid(data.getUserid());
					clientData.setUsername(data.getUsername());
					clientData.userChannelLogin();
				} else {
					sdk.validateTongBuTui(clientData);
				}
				break;
			}
			case ChannelLib.appStore: {
				TokenData data = TokenManager.getInstance().getTokendata(clientData.getToken());
				if (data != null) {
					data.resetTime(TokenManager.OVERTIME + System.currentTimeMillis());
					clientData.setUserid(data.getUserid());
					clientData.setUsername(data.getUsername());
					clientData.userChannelLogin();
				} else {
					sdk.validateAppStore(clientData);
				}
				break;
			}
			case ChannelLib.xy: {
				TokenData data = TokenManager.getInstance().getTokendata(clientData.getToken());
				if (data != null) {
					data.resetTime(TokenManager.OVERTIME + System.currentTimeMillis());
					clientData.setUserid(data.getUserid());
					clientData.setUsername(data.getUsername());
					clientData.userChannelLogin();
				} else {
					sdk.validateXY(clientData);
				}
				break;
			}
			case ChannelLib.tencent: {
				JSONObject json = JSONObject.fromObject(clientData.getCustomData());
				TokenData data = TokenManager.getInstance().getTokendata(json.getString("accesstoken"));
				if (data != null) {
					data.resetTime(TokenManager.OVERTIME + System.currentTimeMillis());
					clientData.setUserid(data.getUserid());
					clientData.setUsername(data.getUsername());
					clientData.userChannelLogin();
				} else {
					sdk.validateTencent(clientData);
				}
				break;
			}
			default: {
				clientData.validateFail(AClientData.RT_3_CHANNEL_NO, AClientData.MSG_3_CHANNEL_NO);
			}
		}
	}
}
