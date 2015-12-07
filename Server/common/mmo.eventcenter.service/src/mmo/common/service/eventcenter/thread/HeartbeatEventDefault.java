package mmo.common.service.eventcenter.thread;


public class HeartbeatEventDefault extends HeartbeantEventRun {

	private final static HeartbeatEventDefault instance = new HeartbeatEventDefault();

	public final static HeartbeatEventDefault getInstance() {
		return instance;
	}

	private HeartbeatEventDefault() {
		sql = "insert into event_default (eventSource,eventTag,appTag,platform,serverTag,channelTag,channelSub,accountId,userId,roleId,roleName,roleLevel,vipLevel,value1string,value2string,value3string,value4string,value5string,value6string,value7string,value8string,key1int,value1int,key2int,value2int,key3int,value3int,key1long,value1long,key1double,value1double,timeAdd) values ";
		sb.append(sql);
	}

	
}
