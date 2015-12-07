package com.hygame.dpcq.coon;  
  
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
  
public class ByteObjConverter {  
    public static Object ByteToObject(byte[] bytes) throws Exception{  
        Object obj = null;  
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);  
        ObjectInputStream oi = null;  
        try {  
            oi = new ObjectInputStream(bi);  
            obj = oi.readObject();  
        }  finally {  
            bi.close();  
            oi.close();  
        }  
        return obj;  
    }  
  
    public static byte[] ObjectToByte(Object obj) throws Exception{  
        byte[] bytes = null;  
        ByteArrayOutputStream bo = new ByteArrayOutputStream();  
        ObjectOutputStream oo = null;  
        try {  
            oo = new ObjectOutputStream(bo);  
            oo.writeObject(obj);  
            bytes = bo.toByteArray();  
        } finally {  
            bo.close();  
            oo.close();  
        }  
        return (bytes);  
    }  
}  