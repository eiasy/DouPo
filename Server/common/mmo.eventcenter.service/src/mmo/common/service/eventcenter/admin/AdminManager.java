package mmo.common.service.eventcenter.admin;

import mmo.tools.util.MD5;
import mmo.tools.util.MathUtil;

public class AdminManager {
	public final static String generateSession(String userId, String ip){
		return MD5.getHashString(userId+ip+System.currentTimeMillis()+MathUtil.getRandom(10000,9999999));
	}
}
