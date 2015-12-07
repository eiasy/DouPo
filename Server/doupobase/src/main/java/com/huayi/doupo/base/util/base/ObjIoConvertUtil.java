package com.huayi.doupo.base.util.base;   
  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
  
  
/**  
 * @blog http://sjsky.iteye.com  
 * @author Michael  
 */  
public class ObjIoConvertUtil {   
  
    /**  
     * 文件转化为Object  
     * @param fileName  
     * @return byte[]  
     */  
    public static Object file2Object(String fileName) {   
  
        FileInputStream fis = null;   
        ObjectInputStream ois = null;   
        try {   
            fis = new FileInputStream(fileName);   
            ois = new ObjectInputStream(fis);   
            Object object = ois.readObject();   
            return object;   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            if (fis != null) {   
                try {   
                    fis.close();   
                } catch (IOException e1) {   
                    e1.printStackTrace();   
                }   
            }   
            if (ois != null) {   
                try {   
                    ois.close();   
                } catch (IOException e2) {   
                    e2.printStackTrace();   
                }   
            }   
        }   
        return null;   
    }   
  
    /**  
     * 把Object输出到文件  
     * @param obj  
     * @param outputFile  
     */  
    public static void object2File(Object obj, String outputFile) {   
        ObjectOutputStream oos = null;   
        FileOutputStream fos = null;   
        try {   
            fos = new FileOutputStream(new File(outputFile));   
            oos = new ObjectOutputStream(fos);   
            oos.writeObject(obj);   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            if (oos != null) {   
                try {   
                    oos.close();   
                } catch (IOException e1) {   
                    e1.printStackTrace();   
                }   
            }   
            if (fos != null) {   
                try {   
                    fos.close();   
                } catch (IOException e2) {   
                    e2.printStackTrace();   
                }   
            }   
        }   
    }   
  
    /**  
     * @param args  
     */  
    @SuppressWarnings("unchecked")   
    public static void main(String[] args) {   
        String fileName = "d:/test/object.txt";   
        List<String> list = new ArrayList<String>();   
        list.add("michael");   
        list.add("大大");   
  
        ObjIoConvertUtil.object2File(list, fileName);   
        System.out.println("success write List<String> to file.");   
  
        List<String> tmpList = (List<String>) ObjIoConvertUtil   
                .file2Object(fileName);   
        for (String tmp : tmpList) {   
            System.out.println(tmp);   
        }   
  
//        System.out.println("--------------------------------");   
//  
//        fileName = "d:/test/uservo.txt";   
//        UserVo vo = new UserVo("michael", "大大", 18, new Date());   
//  
//        ObjectFileConvert.object2File(vo, fileName);   
//        System.out.println("success write bean:UserVo to file.");   
//  
//        UserVo tmpvo = (UserVo) ObjectFileConvert.file2Object(fileName);   
//        System.out.println("read bean:UserVo from file get info : " + tmpvo);   
  
    }   
  
}  