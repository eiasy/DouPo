package com.huayi.doupo.logic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.protobuf.ByteString.Output;
import com.huayi.doupo.base.util.base.FileUtil;
import com.huayi.doupo.base.util.base.SerializeUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;


/**
 * 玩家战力之管理Map,存入<instPlayerId, fightVale>
 * @author hzw
 * @date 2015-5-29上午11:12:36
 */
public class PlayerFightValueMapUtil {
	
	private PlayerFightValueMapUtil() {
		
	}
	private static String path = System.getProperty("user.dir") + "/config/";
	private static String fileName = "fightValue.txt";
	private static Map<Integer, Integer> playerFightValueMap = null;
	
	public static Map<Integer, Integer> getMap() {
		if (playerFightValueMap == null) {
			playerFightValueMap = new HashMap<Integer, Integer>();
		}
		return playerFightValueMap;
	}
	
	/**
	 * 返回map的大小
	 * @author hzw
	 * @date 2015-1-6上午11:43:44
	 * @Description
	 */
	public static int getSize() {
		return getMap().size();
	}

	/**
	 * 清空map
	 * @author hzw
	 * @date 2015-1-6上午11:43:17
	 * @Description
	 */
	public static void clearAll() {
		playerFightValueMap = null;
	}
	
	/**
	 * 根据instPlayerId，删除Player
	 * @author hzw
	 * @date 2015-1-6上午11:42:51
	 * @param msgMap
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static void removePlayer(Integer instPlayerId) {
		if (getMap().containsKey(instPlayerId)) {
			getMap().remove(instPlayerId);
		}
	}

	/**
	 * 向map中加入playerFightValue
	 * @author hzw
	 * @date 2015-1-6上午11:42:27
	 * @param msgMap
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static void add(Integer instPlayerId, int fightValue) throws Exception{
		getMap().put(instPlayerId, fightValue);
	}

	/**
	 * 判断map是否包含指定的instPlayerId
	 * @author hzw
	 * @date 2015-1-6上午11:42:09
	 * @param msgMap
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static boolean containsInstPlayer(Integer instPlayerId) {
		return getMap().containsKey(instPlayerId);
	}
	
	/**
	 * 根据指定的instPlayerId，获取他的战力值
	 * @author hzw
	 * @date 2015-1-6上午11:41:21
	 * @param msgMap
	 * @param instPlayerId
	 * @throws Exception
	 * @Description
	 */
	public static int getPlayerFightValue(Integer instPlayerId) {
		if(getMap().containsKey(instPlayerId)){
			return getMap().get(instPlayerId);
		}
		return 0;
	}
	
	
	/**
	 * 为玩家战力排序（从大到小）
	 * @author hzw
	 * @date 2015-8-1上午10:04:43
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void rank() {
		List<Map.Entry<Integer, Integer>> infoIds =
			    new ArrayList<Map.Entry<Integer, Integer>>(getMap().entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
				return (int)(b.getValue() - a.getValue()); 
			}
		}); 
		// 循环输出
//		for(Map.Entry<Integer, Integer> entity : infoIds){   
//		      System.out.println(entity.getKey() + " = " + entity.getValue());   
//		} 
	}
	
	/**
	 * 序列化玩家战力保存到本地txt
	 * @author hzw
	 * @date 2015-8-1上午10:07:13
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static void serializeFightValue() {
		FileUtil.createFile(path, fileName);
		FileUtil.writeFile(path + fileName, SerializeUtil.serialize(getMap()));
	}
	
	/**
	 * 反序列化玩家战力并加载到map中
	 * @author hzw
	 * @date 2015-8-1上午10:07:58
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static void unserializeFightValue() {
		try {
			File file = new File(path + fileName);
			if(file.exists()){
				long length = file.length();
				FileInputStream input;
				input = new FileInputStream(file);
				byte[] b = new byte[(int)length];    //设置大小，用文件长度。
				input.read(b);
				Object obj = SerializeUtil.unserialize(b);
				Map<Integer, Integer> tempMap = (Map<Integer, Integer>)obj;
				getMap().putAll(tempMap);
			}
		} catch (Exception e) {
			LogUtil.error("反序列化玩家战力并加载到map中Error", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		/*Map<Integer, Integer> map = new HashMap<Integer, Integer>();;
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		String path = System.getProperty("user.dir") + "/config/";
		String fileName = "fightValue.txt";
		FileUtil.createFile(path, fileName);
		FileUtil.writeFile(path + fileName, SerializeUtil.serialize(map));
		*/
		
		File file = new File(path + fileName);
		if(file.exists()){
	
			long length = file.length();
			FileInputStream input;
			try {
				input = new FileInputStream(file);
				byte[] b = new byte[(int)length];    //如果不知道文件有多大，最好设置大一点。
				input.read(b);
				Object obj = SerializeUtil.unserialize(b);
				Map<Integer, Integer> tempMap = (Map<Integer, Integer>)obj;
				System.out.println(tempMap.get(1));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
			}
		}
	
	}

}
