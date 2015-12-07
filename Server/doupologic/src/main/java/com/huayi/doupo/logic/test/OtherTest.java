package com.huayi.doupo.logic.test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import model.proto.Message;

import com.google.common.collect.Maps;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.FileUtil;
import com.huayi.doupo.base.util.base.SerializeUtil;
import com.huayi.doupo.logic.util.MessageData;

public class OtherTest extends DALFactory{
	
	public static String content = "阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费阿凯萨琳等交房了可接受的楼看风景昆仑山交罚款了交水电费困了就睡代理费了交水电费困了就睡代理费";
	
	public static void main(String[] args) throws Exception{
		
		/*System.out.println("===输入选项===");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String input = bf.readLine();
		while (true) {
			if (input.equals("quit")) {
				break;
			} else {
				System.out.println("input is " + input);
				System.out.println("sssssssssssssssssssssssssss");
				System.out.println("===再输入选项====");
				bf = new BufferedReader(new InputStreamReader(System.in));
				input = bf.readLine();
			}
		}
		System.out.println("exit");*/
		
		InstPlayer instPlayer = getInstPlayerDAL().getModel(9, 0);
		getInstPlayerDAL().update(instPlayer, 0);
		System.out.println("aaaaaaaaa");
		
//		List<String> nameList = FileUtil.readFileToList("src\\main\\resources\\name\\", "name_first.txt", "utf-8");
//		System.out.println(nameList.size());
//		for (String str : nameList) {
//			System.out.println(str);
//		}
//		
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\work\\Sync\\workspace\\java\\doupologic\\src\\main\\resources\\name\\name_first.txt")));
//		String content = "";
//		while ((content = bufferedReader.readLine()) != null) {
//			System.out.println(content);
//		}
		
		
//		map();
		
//		toFile();
		
//		objIo();
		
//		protoIo();
		
	}

	private static void map() {
		Map<String, String> testMap = Maps.newHashMap();
		
		Map<String, String> copyMap = Maps.newHashMap();
		
		testMap.put("1", "我操");
		
		copyMap = testMap;
		
		//序列化
		byte[] resByte = SerializeUtil.serialize(testMap);
		
		//修改
		testMap.put("1", "我日");
		System.out.println("now " + testMap.get("1"));
		
		//反序列化
		Map<String, String> afterMap = (Map<String, String>)SerializeUtil.unserialize(resByte);
		System.out.println("old " + afterMap.get("1"));
		
		//引用的Map
		System.out.println("引用 " + copyMap.get("1"));
	}

	private static void toFile() {
		String conString = "";
		
		for (int i = 0; i < 100; i++) {
			conString += content;
		}
		
		System.out.println(conString);
		
		FileUtil.writeContentToFile("E:/", "aaa.txt" ,conString);
	}

	private static void protoIo() {
		
		MessageData messageData = new MessageData();
		for (int i = 0; i < 10000; i++) {
			messageData.putStringItem(i + "", content);
		}
		
		long start = DateUtil.getCurrMill();
		System.out.println("--- start ---");
		
		for (int i = 0; i < 10000; i++) {
			Message.Msg.Builder msg = Message.Msg.newBuilder();
			msg.setMsgdata(messageData.getMsgData());
			Message.Msg newMsg =  msg.build();
			newMsg.toByteArray();
		}
		
		System.out.println("--- end---" + (DateUtil.getCurrMill() - start));
		
	}

	private static void objIo() throws Exception{
		
		Map<String, String> strMap = Maps.newHashMap();
		
		for (int i = 0; i < 100; i++) {
			strMap.put(i+"", content);
		}
		
		long start = DateUtil.getCurrMill();
		System.out.println("--- start ---");
		
		//序列化
		for (int i = 0; i < 10000; i++) {
			byte [] aa = SerializeUtil.serialize(strMap);
			SerializeUtil.unserialize(aa);
			TimeUnit.MILLISECONDS.sleep(1);
		}
		
		System.out.println("--- end---" + (DateUtil.getCurrMill() - start));
	}
}
