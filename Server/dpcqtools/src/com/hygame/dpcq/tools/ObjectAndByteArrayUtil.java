package com.hygame.dpcq.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象和字节数组转换的帮助类
 * 现有方法：
 * 1.将对象转换成字节数组：byte[] ObjectToByte(java.lang.Object obj) 
 * 2.将字节数组转换成对象：java.lang.Object ByteToObject(byte[] bytes)
 * @author Administrator
 */
public class ObjectAndByteArrayUtil {
	
	public static void main(String[] args) {
		TestObjAndByte testObjAndByte = new TestObjAndByte();
		byte[] bytes = ObjectToByte(testObjAndByte);
		TestObjAndByte testObjAndByte2 = (TestObjAndByte)ByteToObject(bytes);
		System.out.println(testObjAndByte2.name);
	}

	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static java.lang.Object ByteToObject(byte[] bytes){ 
        java.lang.Object obj = null; 
        try { 
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes); 
        ObjectInputStream oi = new ObjectInputStream(bi); 

        obj = oi.readObject(); 

        bi.close(); 
        oi.close(); 
        } 
        catch(Exception e) { 
            System.out.println("translation"+e.getMessage()); 
            e.printStackTrace(); 
        } 
        return obj; 
    } 
  
    public static byte[] ObjectToByte(java.lang.Object obj) 
    { 
        byte[] bytes = null; 
        try { 
            //object to bytearray 
            ByteArrayOutputStream bo = new ByteArrayOutputStream(); 
            ObjectOutputStream oo = new ObjectOutputStream(bo); 
            oo.writeObject(obj); 

            bytes = bo.toByteArray(); 

            bo.close(); 
            oo.close();     
        } 
        catch(Exception e){ 
            System.out.println("translation"+e.getMessage()); 
            e.printStackTrace(); 
        } 
        return(bytes); 
    } 
}

class TestObjAndByte implements Serializable{
	
	private static final long serialVersionUID = -4291881947609123374L;
	public String name = "name";
}
