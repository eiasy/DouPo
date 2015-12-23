package mmo.common.module.sdk.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import mmo.tools.log.LoggerError;
import mmo.tools.thread.runnable.IExecuteEntity;

public class TokenManager implements IExecuteEntity {
	public final static int              OVERTIME    = 1000 * 60 * 60;
	private final static int             MAX_HANDLE  = 100;
	private final static TokenManager    instance    = new TokenManager();

	public final static TokenManager getInstance() {
		return instance;
	}

	private Map<String, TokenData>           tokenMap   = new ConcurrentHashMap<String, TokenData>();
	/** 数据库操作事件队列 */
	private ConcurrentLinkedQueue<TokenData> tokenQueue = new ConcurrentLinkedQueue<TokenData>();

	private TokenManager() {

	}

	@Override
	public void run(long currTime) {
		int count = 0;
		TokenData data = null;
		while (count < MAX_HANDLE) {
			count++;
			if ((data = tokenQueue.peek()) == null) {
				break;
			}
			if (data.isHandled()) {
				tokenMap.remove(data.getToken());
				tokenQueue.poll();
				continue;
			}
			if (currTime > data.getOvertime()) {
				tokenMap.remove(data.getToken());
				tokenQueue.poll();
				continue;
			} else {
				break;
			}
		}
	}

	public final TokenData addToken(String token, String userid, String username, String channelSub) {
		TokenData data = tokenMap.get(token);
		if (data != null) {
			LoggerError.messageLog.error("TOKEN重复，TOKEN=" + token + ",STATUS=" + data.isHandled() + ",USERID_1=" + data.getUserid()
			        + ",CHANNEL_SUB_1=" + data.getChannelSub() + "," + ",USERID_2=" + userid + ",CHANNEL_SUB_2=" + channelSub);
			data.setHandled(true);
		}
		data = new TokenData(token, userid, username, channelSub, System.currentTimeMillis() + OVERTIME);
		tokenMap.put(token, data);
		tokenQueue.offer(data);
		return data;
	}

	public final TokenData getAndRemove(String token) {
		TokenData data = tokenMap.remove(token);
		if (data != null) {
			data.setHandled(true);
		}
		return data;
	}

	public final TokenData getTokendata(String token) {
		return tokenMap.get(token);
	}

}
