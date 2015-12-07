package com.hygame.dpcq.tools;

import com.google.gson.Gson;

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
	
	public static void main(String[] args) {
/*		List操作
 * 		List<String> strings = new ArrayList<>();
		strings.add("miao");
		strings.add("peng");
		System.out.println(toJson(strings));
		
		List<String> abcList = fromJson(toJson(strings), ArrayList.class);
		for (int i = 0; i < abcList.size(); i++) {
			System.out.println(abcList.get(i));
		}*/
		
/*		Map操作
 * 		Map<String, String> mpMap = new LinkedHashMap<String, String>();
		mpMap.put("one", "1");
		mpMap.put("two", "2");
		System.out.println(toJson(mpMap));
		
		Map<String, String> mpaMap = fromJson(toJson(mpMap), LinkedHashMap.class);
		for (java.util.Map.Entry<String, String> entry : mpaMap.entrySet()) {
			System.out.println("key - " + entry.getKey() + "  value - " + entry.getValue());
		}*/
		
	}
	
}
