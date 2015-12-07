package mmo.common.service.eventcenter.thread;

public class HeartbeatEventCharge extends HeartbeantEventRun {
	private final static HeartbeatEventCharge instance = new HeartbeatEventCharge();

	public final static HeartbeatEventCharge getInstance() {
		return instance;
	}

	private HeartbeatEventCharge() {
		sql = "insert into event_charge (eventSource,eventTag,appTag,platform,serverTag,channelTag,channelSub,accountId,userId,roleId,roleName,roleLevel,vipLevel,value1string,value2string,value3string,value4string,value5string,value6string,value7string,value8string,key1int,value1int,key2int,value2int,key3int,value3int,key1long,value1long,key1double,value1double,timeAdd) values ";
		sb.append(sql);
	}
}
