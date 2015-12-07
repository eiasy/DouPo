package com.huayi.doupo.base.util.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

/**
 * Json工具类
 * @author mp
 * @date 2014-9-23 下午2:56:21
 */
public class JsonUtil {
	
	/**
	 * 单例构造Gson对象
	 */
	private static Gson gson = new Gson() ;
	
	/**
	 * 私有构造方法
	 */
	private JsonUtil() {
		
	}
	
	/**
	 * Json编码
	 * @author mp
	 * @date 2014-9-23 下午3:01:03
	 * @param obj
	 * @return
	 * @Description
	 */
	public static String toJson( Object obj ){
		return gson.toJson( obj ) ;
	}
	
	/**
	 * Json解码
	 * @author mp
	 * @date 2014-9-23 下午3:01:32
	 * @param s
	 * @param type
	 * @return
	 * @Description
	 */
	public static < T > T fromJson( String s , Class< T > type ){
		return gson.fromJson( s , type ) ;
	}
	
	public static void main(String[] args) throws Exception{
/*		List操作
 * 		List<String> strings = new ArrayList<>();
		strings.add("miao");
		strings.add("peng");
		System.out.println(toJson(strings));
		
		List<String> abcList = fromJson(toJson(strings), ArrayList.class);
		for (int i = 0; i < abcList.size(); i++) {
			System.out.println(abcList.get(i));
		}*/
		
/*		Map操作*/
/*		Map<String, String> mpMap = new LinkedHashMap<String, String>();
		mpMap.put("one", "1");
		mpMap.put("two", "2");
		System.out.println(toJson(mpMap));
		
		Map<String, String> mpaMap = fromJson(toJson(mpMap), LinkedHashMap.class);
		for (java.util.Map.Entry<String, String> entry : mpaMap.entrySet()) {
			System.out.println("key - " + entry.getKey() + "  value - " + entry.getValue());
		}*/
		
		String aa = "1.0";
		System.out.println((int)(float)Float.valueOf(aa));
		
		
		Map<String, List<HashMap<String, Object>>> testMap = new HashMap<String, List<HashMap<String, Object>>>();
		
		HashMap<String, Object> map1 = new HashMap<>();
		map1.put("id", 1);
		map1.put("name", "苗朋");
		map1.put("state", 0);
		map1.put("ip", "192.168.1.36");
		
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("id", 2);
		map2.put("name", "苗朋2");
		map2.put("state", 0);
		map2.put("ip", "192.168.1.362");
		
		List<HashMap<String, Object>> strList = new ArrayList<>();
		strList.add(map1);
		strList.add(map2);
		
		testMap.put("str", strList);
		
		String mapJsonStr = toJson(testMap);
		System.out.println(mapJsonStr);
		
		Map<String, List<LinkedTreeMap<String, Object>>> retMap = fromJson(mapJsonStr, Map.class);
		List<LinkedTreeMap<String, Object>> retList = retMap.get("str");
		for (LinkedTreeMap<String, Object> obj : retList) {
			int id = (int)(double)(Double)(obj.get("id"));
			String name = obj.get("name") + "";
			System.out.println(id + "  " + name);
		}
		
//		System.out.println(retList);
		
//		for (HashMap<String, Object> objMap : retMap.get("str")) {
//			System.out.println(objMap.get("id"));
//		}
//		System.out.println(retMap.get("str"));
	}
	
}
