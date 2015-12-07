package mmo.tools.test;

import java.io.UnsupportedEncodingException;

public class CutString {

	/**
	 * 判断是否是一个中文汉字
	 * 
	 * @param c
	 *            字符
	 * @return true表示是中文汉字，false表示是英文字母
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static boolean isChineseChar(char c) throws UnsupportedEncodingException {
		// 如果字节数大于1，是汉字
		// 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了
		return String.valueOf(c).getBytes("GBK").length > 1;
	}

	/**
	 * 按字节截取字符串
	 * 
	 * @param orignal
	 *            原始字符串
	 * @param count
	 *            截取位数
	 * @return 截取后的字符串
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static String substring(String orignal, int count) throws UnsupportedEncodingException {
		// 原始字符不为null，也不是空字符串
		if (orignal != null && !"".equals(orignal)) {
			System.out.println("orignal  1  " + orignal);
			// 将原始字符串转换为GBK编码格式
			orignal = new String(orignal.getBytes(), "GBK");
			System.out.println("orignal  2  " + orignal);
			// 要截取的字节数大于0，且小于原始字符串的字节数
			if (count > 0 && count < orignal.getBytes("GBK").length) {
				StringBuffer buff = new StringBuffer();
				char c;
				for (int i = 0; i < count; i++) {
					c = orignal.charAt(i);
					System.out.println("c + " + c);
					buff.append(c);
					if (CutString.isChineseChar(c)) {
						// 遇到中文汉字，截取字节总数减1
						--count;
					}
				}
				return buff.toString();
			}
		}
		return orignal;
	}

	public static void main(String[] args) {
		// 原始字符串
		String s = "我ZWR爱JAVA";
		System.out.println("原始字符串：" + s);
		try {
			System.out.println("截取前1位：" + CutString.substring(s, 1));
//			System.out.println("截取前2位：" + CutString.substring(s, 2));
//			System.out.println("截取前4位：" + CutString.substring(s, 4));
//			System.out.println("截取前6位：" + CutString.substring(s, 6));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}