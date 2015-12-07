package mmo.common.module.account.doupo.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import mmo.common.module.account.doupo.cache.thread.AccountValidateHeartbeat;
import mmo.common.module.account.doupo.cache.thread.validate.AddSecurityCodeRunnable;
import mmo.tools.thread.runnable.IExecuteEntity;

public class SecurityCodeManager implements IExecuteEntity {
	private final static SecurityCodeManager instance = new SecurityCodeManager();

	public final static SecurityCodeManager getInstance() {
		return instance;
	}

	private static final Map<String, SecurityCode> securityCodes = new HashMap<String, SecurityCode>();
	private final static Queue<SecurityCode> codeQueue = new ConcurrentLinkedQueue<SecurityCode>();
	private final static Queue<SecurityCode> reorderQueue = new ConcurrentLinkedQueue<SecurityCode>();

	private static final Map<String, TokenData> tokenMap = new HashMap<String, TokenData>();
	private final static Queue<TokenData> tokenQueue = new ConcurrentLinkedQueue<TokenData>();
	private final static Queue<TokenData> tokenReorderQueue = new ConcurrentLinkedQueue<TokenData>();

	public final static void addSecurityCode(SecurityCode securityCode) {
		securityCodes.put(securityCode.getSecurityCode(), securityCode);
		reorderQueue.offer(securityCode);
	}

	public final static SecurityCode generateSecurityCode(String securityCode, int accountId, String channelId, int clientVersion, int productId, String userId, long accountRegisterTime, String channelSub, int belongto, String loginServer) {
		SecurityCode sc = new SecurityCode(securityCode);
		sc.setAccountId(accountId);
		sc.setChannelId(channelId);
		sc.setClientVersion(clientVersion);
		sc.setGameId(productId);
		sc.setUserId(userId);
		sc.setRegisterTime(accountRegisterTime);
		sc.setChannelSub(channelSub);
		sc.setBelongto(belongto);
		sc.setLoginServer(loginServer);
		AccountValidateHeartbeat.getInstance().execute(new AddSecurityCodeRunnable(sc));

		return sc;
	}

	public static void addTokenData(TokenData tokenData) {
		tokenMap.put(tokenData.getKey(), tokenData);
		tokenReorderQueue.offer(tokenData);
	}

	/**
	 * 验证验证码是否有效，有效则重置时间
	 * 
	 * @param securityCode
	 * @return
	 */
	public static SecurityCode validateSecurityCode(String securityCode) {
		SecurityCode sc = securityCodes.get(securityCode);
		if (sc == null) {
			return null;
		}
		sc.resetTime();
		return sc;
	}
	
	public static TokenData getTokenData(String key) {
		TokenData td = tokenMap.get(key);
		if (td == null) {
			return null;
		}
		td.resetTime();
		return td;
	}

	private SecurityCodeManager() {

	}

	public void run(long currTime) {
		SecurityCode code = null;
		while ((code = codeQueue.peek()) != null) {
			if (code.isReorder()) {
				codeQueue.poll();
				reorderQueue.offer(code);
				continue;
			}
			if (code.isOvertime(currTime)) {
				codeQueue.poll();
				securityCodes.remove(code.getSecurityCode());
				continue;
			} else {
				break;
			}
		}

		while ((code = reorderQueue.poll()) != null) {
			code.setReorder(false);
			codeQueue.offer(code);
		}

		TokenData token = null;
		while ((token = tokenQueue.peek()) != null) {
			if (token.isReorder()) {
				tokenQueue.poll();
				tokenReorderQueue.offer(token);
				continue;
			}
			if (token.isOvertime(currTime)) {
				tokenQueue.poll();
				tokenMap.remove(token.getKey());
				continue;
			} else {
				break;
			}
		}

		while ((token = tokenReorderQueue.poll()) != null) {
			token.setReorder(false);
			tokenQueue.offer(token);
		}
	}

}
