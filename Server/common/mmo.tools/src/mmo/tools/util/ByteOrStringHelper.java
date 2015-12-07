package mmo.tools.util;

import java.io.UnsupportedEncodingException;

/**
 * 将Byte转换为String 或者将String转换为Byte
 * 
 * @author Administrator
 * 
 */
public class ByteOrStringHelper {

	/**
	 * 默认的字符集编码 UTF-8 一个汉字占三个字节
	 */
	private static String CHAR_ENCODE = "UTF-8";

	/**
	 * 设置全局的字符编码
	 * 
	 * @param charEncode
	 */
	public static void configCharEncode(String charEncode) {
		CHAR_ENCODE = charEncode;
	}

	/**
	 * @param str
	 *            源字符串转换成字节数组的字符串
	 * @return
	 */
	public static byte[] StringToByte(String str) {
		return StringToByte(str, CHAR_ENCODE);
	}

	/**
	 * 
	 * @param srcObj
	 *            源字节数组转换成String的字节数组
	 * @return
	 */
	public static String ByteToString(byte[] srcObj) {
		return ByteToString(srcObj, CHAR_ENCODE);
	}

	/**
	 * UTF-8 一个汉字占三个字节
	 * 
	 * @param str
	 *            源字符串 转换成字节数组的字符串
	 * @return
	 */
	public static byte[] StringToByte(String str, String charEncode) {
		byte[] destObj = null;
		try {
			if (null == str || str.equals("")) {
				destObj = new byte[0];
				return destObj;
			} else {
				destObj = str.getBytes(charEncode);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return destObj;
	}

	/**
	 * @param srcObj
	 *            源字节数组转换成String的字节数组
	 * @return
	 */
	public static String ByteToString(byte[] srcObj, String charEncode) {
		String destObj = null;
		try {
			destObj = new String(srcObj, charEncode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return destObj.replaceAll("\0", " ");
	}

}