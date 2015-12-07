package mmo.common.service.eventcenter.thread;

public class HeartbeatEventAccount extends HeartbeantEventRun {
	private final static HeartbeatEventAccount instance = new HeartbeatEventAccount();

	public final static HeartbeatEventAccount getInstance() {
		return instance;
	}

	private HeartbeatEventAccount() {
		sql = "insert into event_account (eventSource,eventTag,appTag,platform,serverTag,channelTag,channelSub,accountId,userId,roleId,roleName,roleLevel,vipLevel,value1string,value2string,value3string,value4string,value5string,value6string,value7string,value8string,key1int,value1int,key2int,value2int,key3int,value3int,key1long,value1long,key1double,value1double,timeAdd) values ";
		sb.append(sql);
	}

}
