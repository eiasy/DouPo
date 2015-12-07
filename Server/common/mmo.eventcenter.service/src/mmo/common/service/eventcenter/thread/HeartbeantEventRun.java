package mmo.common.service.eventcenter.thread;

import mmo.common.service.eventcenter.service.Service;
import mmo.common.service.eventcenter.thread.run.AddEventDBRun;
import mmo.tools.thread.heartbeat.AHeartbeat;

public class HeartbeantEventRun extends AHeartbeat {
	protected int count = 0;
	protected int offset = 1000 * 60;
	protected int totalCount = 0;
	protected int max = 300;
	protected long nextSave = System.currentTimeMillis() + offset;
	protected String sql = "insert into event_default (eventSource,eventTag,appTag,platform,serverTag,channelTag,channelSub,accountId,userId,roleId,roleName,roleLevel,vipLevel,value1string,value2string,value3string,value4string,value5string,value6string,value7string,value8string,key1int,value1int,key2int,value2int,key3int,value3int,key1long,value1long,key1double,value1double,timeAdd) values ";
	protected StringBuilder sb = new StringBuilder();

	public void append(String data) {
		if (count > 0) {
			sb.append(",");
		}
		count++;
		totalCount++;
		sb.append(data);
	}

	public void execute(AddEventDBRun run) {
		run.setHeartbeat(this);
		this.addEvent(run);
	}

	public void callback() {
		if (System.currentTimeMillis() > nextSave || count > max) {
			nextSave = System.currentTimeMillis() + offset;
			if (count > 0) {
				Service.getInstance().addEvent(sb.toString());
				this.count = 0;
				sb.setLength(0);
				sb.append(sql);
			}
		}
	}

}
