package mmo.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import mmo.tools.log.LoggerError;
import mmo.tools.net.HttpsUtil;
import mmo.tools.util.MathUtil;

public class AddOrder {
	public static AtomicInteger count = new AtomicInteger();

	public static void main(String[] args) {
		test();
	}

	static String[] aray = {"http://192.168.1.102:12008/datacenter/event","http://192.168.1.102:12009/datacenter/event","http://192.168.1.102:12010/datacenter/event"};
	public static void test() {
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					long curr = System.currentTimeMillis() + 1000 * 120;
					while (System.currentTimeMillis() < curr) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("eventSource", "doupo");
						map.put("eventTag", count.incrementAndGet() + "");
						map.put("platform", "2");
						map.put("serverTag", "1");
						map.put("channelTag", "uc");
						map.put("channelSub", "com.doupo.uc");
						map.put("accountId", "10001");
						map.put("userId", "10001");
						map.put("roleId", "10001");
						map.put("roleName", "10001");
						map.put("roleLevel", "3");
						map.put("value1string", "Ê®´Î³é¿¨");
						map.put("value2string", "ÏûÑ×");
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						String result = HttpsUtil.request(aray[MathUtil.getRandom(0, 1000)%3], HttpsUtil.httpBuildQuery(map));
						// LoggerError.error(result);
					}
					System.out.println("count=" + count.get());
				}
			}.start();
		}
	}
}
