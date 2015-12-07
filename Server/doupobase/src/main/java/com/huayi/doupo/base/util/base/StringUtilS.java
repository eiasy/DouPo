package com.huayi.doupo.base.util.base;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * 字符串操作工具类
 * @author mp
 * @date 2014-3-28 上午11:49:42
 */
public class StringUtilS {
	
	/**
	 * 判断字符串是否为空
	 * null 或者  "" 返回 true
	 * @author mp
	 * @date 2014-3-28 下午12:05:43
	 * @param str
	 * @return
	 * @Description
	 */
	public static boolean isEmpty (String str) {
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * 判断字符串是否不为空
	 * @author mp
	 * @date 2014-3-28 下午12:07:23
	 * @param str
	 * @return
	 * @Description
	 */
	public static boolean isNotEmpty (String str){
		return StringUtils.isNotEmpty(str);
	}
	
	/**
	 * 去除字符串两端空格
	 * @author mp
	 * @date 2014-3-28 下午1:50:47
	 * @param str
	 * @return
	 * @Description
	 */
	public static String trim (String str) {
		return StringUtils.trim(str);
	}

	/**
	 * 用省略号缩写字符串
	 * 当字符串个数大于4个时,会maxWidth=最小4的时候，会显示4个字符,其中共3个省略号 
	 * e.g. abbreviate("abcdef",4) = a...
	 * @author mp
	 * @date 2014-3-28 下午1:54:06
	 * @param str
	 * @param maxWidth 此参数最小为4
	 * @return
	 * @Description
	 */
	public static String abbreviate (String str, int maxWidth) {
		return StringUtils.abbreviate(str, maxWidth);
	}
	
	/**
	 * 分隔字符串
	 * e.g. split("abc,de.f", ",.") = [abc de f ]
	 * @author mp
	 * @date 2014-3-28 下午2:06:19
	 * @param str
	 * @param ch
	 * @return
	 * @Description
	 */
	public static String [] split (String str, String ch) {
		return StringUtils.split(str, ch);
	}
	
	/**
	 * 重复字符串
	 * e.g.  repeat("你好 ", 3) = 你好 你好 你好
	 * @author mp
	 * @date 2014-3-28 下午2:09:36
	 * @param str
	 * @param repeat 重复次数
	 * @return
	 * @Description
	 */
	public static String repeat (String str, int repeat) {
		return StringUtils.repeat(str, repeat);
	}
	
	/**
	 * 添加指定字符串,使原字符串居中
	 * e.g. center("ab", "#", 4) = #ab#
	 * @author mp
	 * @date 2014-3-28 下午2:18:30
	 * @param str
	 * @param padStr
	 * @param size
	 * @return
	 * @Description
	 */
	public static String center (String str, String padStr, int size) {
		return StringUtils.center(str, size, padStr);
	}
	
	/**
	 * 倒转字符串顺序
	 * @author mp
	 * @date 2014-3-28 下午2:21:23
	 * @param str
	 * @return
	 * @Description
	 */
	public static String reverse (String str) {
		return StringUtils.reverse(str);
	}
	
	/**
	 * 判断字符串是否只包含字母/汉字,存在数字和符号也会返回false
	 * @author mp
	 * @date 2014-3-28 下午2:35:59
	 * @param str
	 * @return
	 * @Description
	 */
	public static boolean isAlpha(String str){
		return StringUtils.isAlpha(str);
	}
	
	/**
	 * 判断字符串是否只包含数字
	 * @author mp
	 * @date 2014-3-28 下午2:40:16
	 * @param str
	 * @return
	 * @Description
	 */
	public static boolean isNumeric (String str) {
		return StringUtils.isNumeric(str);
	}
	
	/**
	 * 判断字符串是否只包含字母/汉字/数字
	 * @author mp
	 * @date 2014-3-28 下午2:50:50
	 * @param str
	 * @return
	 * @Description
	 */
	public static boolean isAlphanumeric (String str) {
		return StringUtils.isAlphanumeric(str);
	}
	
	/**
	 * 统计后一个字符串在前一个字符串中出现了几次
	 * @author mp
	 * @date 2014-3-28 下午2:56:24
	 * @param str
	 * @param sub
	 * @return
	 * @Description
	 */
	public static int countMatches (String str, String sub) {
		return StringUtils.countMatches(str, sub);
	}
	
	/**
	 * 设置首字母大写
	 * @author mp
	 * @date 2014-3-28 下午2:58:44
	 * @param str
	 * @return
	 * @Description
	 */
	public static String capitalize (String str) {
		return StringUtils.capitalize(str);
	}
	
	/**
	 * 设置首字母小写
	 * @author mp
	 * @date 2014-3-28 下午2:59:17
	 * @param str
	 * @return
	 * @Description
	 */
	public static String uncapitalize (String str) {
		return StringUtils.uncapitalize(str);
	}
	
	/**
	 * 大小写字母互转
	 * @author mp
	 * @date 2014-3-28 下午3:02:06
	 * @param str
	 * @return
	 * @Description
	 */
	public static String swapCase (String str) {
		return StringUtils.swapCase(str);
	}
	
	/**
	 * 将字符串数组中的每一项用指定字符连接并返回
	 * @author mp
	 * @date 2014-3-28 下午3:10:57
	 * @param strArray
	 * @param conChar
	 * @return
	 * @Description
	 */
	public static String convStr (String [] strArray, String conChar) {
		StringBuffer sb = new StringBuffer("");
		for(String str : strArray){
			sb.append(str).append(conChar);
		}
		return sb.toString();
	}
	
	/**
	 * 将原字符串中的某个字符替换成另外一个字符
	 * @author mp
	 * @date 2014-3-28 下午3:14:45
	 * @param str
	 * @param repl
	 * @param with
	 * @return
	 * @Description
	 */
	public static String replace (String str, String repl, String with) {
		return StringUtils.replace(str, repl, with);
	}
	
	/**
	 * 从头至尾的顺序查找某个字符在字符串中的位置,从0开始; 没有返回-1
	 * @author mp
	 * @date 2014-3-28 下午3:19:06
	 * @param str
	 * @param searchChar
	 * @return
	 * @Description
	 */
	public static int indexOf (String str, String searchChar) {
		return StringUtils.indexOf(str, searchChar);
	}
	
	/**
	 * 从尾至头的顺序查找某个字符在字符串中的位置,从0开始; 没有返回-1
	 * @author mp
	 * @date 2014-3-28 下午3:20:45
	 * @param str
	 * @param searchChar
	 * @return
	 * @Description
	 */
	public static int lastIndexOf (String str, String searchChar) {
		return StringUtils.lastIndexOf(str, searchChar);
	}
	
	/**
	 * 字符串中是否包含指定字符
	 * @author mp
	 * @date 2014-3-28 下午3:22:31
	 * @param str
	 * @param searchChar
	 * @return
	 * @Description
	 */
	public static boolean contains (String str, String searchChar) {
		return StringUtils.contains(str, searchChar);
	}
	
	/**
	 * 判断两个字符串是否内容相等,区分大小写
	 * @author mp
	 * @date 2014-3-28 下午3:24:48
	 * @param str1
	 * @param str2
	 * @return
	 * @Description
	 */
	public static boolean equals (String str1, String str2) {
		return StringUtils.equals(str1, str2);
	}
	
	/**
	 * 判断两个字符串是否内容相等,不区分大小写
	 * @author mp
	 * @date 2014-3-28 下午3:25:52
	 * @param str1
	 * @param str2
	 * @return
	 * @Description
	 */
	public static boolean equalsIgnoreCase (String str1, String str2) {
		return StringUtils.equalsIgnoreCase(str1, str2);
	}
	
	
	@Test
	public void test() {
		System.out.println(indexOf("bad", "a"));
	}
}
