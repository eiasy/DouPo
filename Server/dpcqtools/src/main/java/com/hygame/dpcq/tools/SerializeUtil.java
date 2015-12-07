package com.hygame.dpcq.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 对象流工具类
 * @author mp
 * @date 2014-4-21 下午2:41:20
 */
public class SerializeUtil {
	
	/**
	 * 序列化
	 * @author mp
	 * @date 2014-4-21 下午2:44:47
	 * @param object
	 * @return
	 * @Description
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 反序列化
	 * @author mp
	 * @date 2014-4-21 下午2:45:01
	 * @param bytes
	 * @return
	 * @Description
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
