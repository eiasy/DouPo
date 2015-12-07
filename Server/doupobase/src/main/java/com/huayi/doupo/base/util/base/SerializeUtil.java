package com.huayi.doupo.base.util.base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 对象序列化反序列化工具类
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
	public static byte[] serialize(Object obj) {
		if (obj == null) {
			return new byte[0];
		} else {
			byte[] bytes = null;
			try {
				ByteArrayOutputStream bo = new ByteArrayOutputStream();
				ObjectOutputStream oo = new ObjectOutputStream(bo);
				oo.writeObject(obj);
				
				bytes = bo.toByteArray();
				
				bo.close();
				oo.close();
			} catch (Exception e) {
				System.out.println("translation" + e.getMessage());
				e.printStackTrace();
			}
			return bytes;
		}
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
		try (ByteArrayInputStream bai = new ByteArrayInputStream(bytes); ObjectInputStream ois = new ObjectInputStream(bai)) {
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
