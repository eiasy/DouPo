package com.hygame.dpcq.tools;

import java.lang.reflect.Method;
/**
 * 反射类
 * @version 2012-4-25 下午07:45:08
 */
public class RequestReflectUtil {

	
	@SuppressWarnings("rawtypes")
	public static Object invokeMethod(String clazz, String methodName,
			Object[] params) throws Exception {

		try {
			Class<?> ownerClass = Class.forName(clazz);
			Class[] paramsclass = new Class[params.length];
			for (int i = 0, j = paramsclass.length; i < j; i++) {
				paramsclass[i] = params[i].getClass();
			}
			Method method = ownerClass.getMethod(methodName, paramsclass);
			return method.invoke(ownerClass.newInstance(), params);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	public static void main(String[] args){
		try {
			
			
			invokeMethod("awo.socket.gameServer.handler.PlayerHandler","login",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
