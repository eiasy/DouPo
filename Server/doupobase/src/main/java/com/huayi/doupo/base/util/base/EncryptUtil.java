package com.huayi.doupo.base.util.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具类
 * 记住一个名字吧，她叫王小云
 * 不能将字符串当做中间媒介,因为字节数组异或后转字符串再反转的时候会改变原始字节。以文件做为媒介是可以的,因为是直接写入文件
 * @author mp
 * @date 2014-3-18 下午5:18:02
 */
public class EncryptUtil {
	
	 private static final char last2byte = (char) Integer.parseInt("00000011", 2);  
	 private static final char last4byte = (char) Integer.parseInt("00001111", 2);  
	 private static final char last6byte = (char) Integer.parseInt("00111111", 2);  
	 private static final char lead6byte = (char) Integer.parseInt("11111100", 2);  
	 private static final char lead4byte = (char) Integer.parseInt("11110000", 2);  
	 private static final char lead2byte = (char) Integer.parseInt("11000000", 2);  
	 private static final char[] encodeTable = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};  
	
	/**
	 * 私有构造方法
	 */
	private EncryptUtil(){
		
	}
	
	/**
	 * BASE64Encoder加密
	 * @author mp
	 * @date 2015-3-23 上午11:32:05
	 * @param from
	 * @return
	 * @Description
	 */
	 public String BASE64Encoder (byte[] from) {  
	        StringBuffer to = new StringBuffer((int) (from.length * 1.34) + 3);  
	        int num = 0;  
	        char currentByte = 0;  
	        for (int i = 0; i < from.length; i++) {  
	            num = num % 8;  
	            while (num < 8) {  
	                switch (num) {  
	                    case 0:  
	                        currentByte = (char) (from[i] & lead6byte);  
	                        currentByte = (char) (currentByte >>> 2);  
	                        break;  
	                    case 2:  
	                        currentByte = (char) (from[i] & last6byte);  
	                        break;  
	                    case 4:  
	                        currentByte = (char) (from[i] & last4byte);  
	                        currentByte = (char) (currentByte << 2);  
	                        if ((i + 1) < from.length) {  
	                            currentByte |= (from[i + 1] & lead2byte) >>> 6;  
	                        }  
	                        break;  
	                    case 6:  
	                        currentByte = (char) (from[i] & last2byte);  
	                        currentByte = (char) (currentByte << 4);  
	                        if ((i + 1) < from.length) {  
	                            currentByte |= (from[i + 1] & lead4byte) >>> 4;  
	                        }  
	                        break;  
	                }  
	                to.append(encodeTable[currentByte]);  
	                num += 6;  
	            }  
	        }  
	        if (to.length() % 4 != 0) {  
	            for (int i = 4 - to.length() % 4; i > 0; i--) {  
	                to.append("=");  
	            }  
	        }  
	        return to.toString();  
	    }  
	
	 /**
	  * 每次从IO中读取512个字节
	  */
	 private static int bufferLen = 512; 
	 private static final byte[] keyByte = "6^)(9-p35@%3#4S!4S0)$Y%%^&5(j.&^&o(*0)$Y%!#O@*GpG@=+@j.&6^)(0-=+".getBytes();
    
	/**
     * 将字符串通过md5加密算法,加密成32位的16进制字符串,不可逆
     * @author mp
     * @date 2014-3-18 下午5:02:57
     * @param srcString
     * @return
     * @Description
     */
    public static String md5Hex(String source){
    	return DigestUtils.md5Hex(source);
    }
    
    /**
     * 将字符串通过sha加密算法,加密成40位的16进制字符串,不可逆
     * @author mp
     * @date 2014-3-18 下午5:04:48
     * @param srcString
     * @return
     * @Description
     */
    @SuppressWarnings("deprecation")
	public static String shaHex(String source){
    	return DigestUtils.shaHex(source);
    }
    
    /**
     * 异或方式加密/解密文件
     * @author mp
     * @date 2014-3-19 下午3:10:07
     * @param oldFilePath
     * @param newFilePath
     * @Description
     */
    public static void xorEnDecryptFile(String oldFilePath, String newFilePath){
		try {
			int c = 0;
			int pos = 0;
			int keyLen = keyByte.length;
			File newFile = new File(newFilePath);
			if(!newFile.exists()){
				newFile.createNewFile();
			}
			FileInputStream fis = new FileInputStream(oldFilePath);
			FileOutputStream fos = new FileOutputStream(newFile);
			byte[] buffer = new byte[bufferLen];
			while((c = fis.read(buffer)) != -1){
				for(int i = 0; i < c; i++){
					buffer[i] ^= keyByte[pos];
					fos.write(buffer[i]);
					pos++;
					if(pos == keyLen){
						pos = 0;
					}
				}
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 用异或的方式将字符串加密并写入文件
     * @author mp
     * @date 2014-3-19 下午2:11:28
     * @param string
     * @return
     * @Description
     */
    public static void xorEncryptString(String sourStr, String newFilePath){
		try {
			int pos = 0;
			File newFile = new File(newFilePath);
			if(!newFile.exists()){
				newFile.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(newFile);
			byte[] buffer = sourStr.getBytes();
				for(int i = 0; i < buffer.length; i++){
					buffer[i] ^= keyByte[pos];
					fos.write(buffer[i]);
					pos++;
					if(pos == keyByte.length){
						pos = 0;
					}
				}
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 用异或的方式将字符串解密文件并返回解密后的内容
     * @author mp
     * @date 2014-3-19 下午2:51:30
     * @param encryptFilePath
     * @return
     * @Description
     */
    public static String xorDecryptString(String encryptFilePath){
		try {
			File encryptFile = new File(encryptFilePath);
			byte[] deByte = new byte[(int)encryptFile.length()];
			FileInputStream fileInputStream = new FileInputStream(encryptFile);
			fileInputStream.read(deByte);
			int pos = 0;
			for(int i = 0; i < deByte.length; i++){
				deByte[i] ^= keyByte[pos];
				pos ++;
				if(pos == keyByte.length){
					pos = 0;
				}
			}
			fileInputStream.close();
			return new String(deByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public static void main(String[] args) throws Exception{
    	
//    	String oldFilePath = "D:/xor/oldIo.txt";
//    	String newFilePath = "D:/xor/newIo.txt";
//    	xorEnDecrypt(oldFilePath, newFilePath);
    	
//    	String string = "hello world !!! 你好， 世界 ！！！哈哈哈%%@@@@@@@@@@@@@！！！！！*******abc1234";
//    	xorEncryptString(string, "D:/xor/new.txt");
//    	System.out.println("解密后字符串为------" + xorDecryptString("D:/xor/new.txt"));	
    	
    	String str = "aaaaaa";
    	System.out.println(md5Hex(str));
    	
	}
}
