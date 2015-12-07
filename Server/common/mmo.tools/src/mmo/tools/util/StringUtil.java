package mmo.tools.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mmo.tools.log.LoggerError;
import mmo.tools.util.string.StringSplit;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class StringUtil {
	public final static String   BR              = System.getProperty("line.separator");
	private static String        stringPattern   = null;

	private static String        symbols         = "~|!|#| |:|@|\\$|%|&|\\(|\\)|\\[" + "|\\]|\\{|\\}|\\*|_|\\+|-|\\||=|'|;|εzε|,|/|\\\"|\\?|<|>|\\\\";
	private static final String  MATCH_USERID    = "^[a-zA-Z0-9][a-zA-Z0-9]{4,49}$";
	public static String         MATCH_NUMBER    = "^[0-9]\\d*$";

	private static final String  MATCH_USERNAME  = "^[\u4e00-\u9fa5_a-zA-Z0-9][\u4e00-\u9fa5_a-zA-Z0-9]{1,9}$";
	private static final Pattern patternNumber   = Pattern.compile(MATCH_NUMBER);
	private static final Pattern patternUserid   = Pattern.compile(MATCH_USERID);
	private static final Pattern patternUsername = Pattern.compile(MATCH_USERNAME);
	private static final Pattern patternLength   = Pattern.compile("[\u4e00-\u9fa5]");
	private static Pattern       patternKeyword  = null;

	public final static boolean isNumberString(String string) {
		Matcher matcherNumber = patternNumber.matcher(string);
		if (matcherNumber.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 长度为5到50位字母和数字的组合
	 * 
	 * @param string
	 * @return
	 */
	public static final boolean validateUserid(String string) {
		Matcher matcherNumber = patternUserid.matcher(string);
		if (matcherNumber.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 长度为2到5位中文、字母和数字的组合
	 * 
	 * @param string
	 * @return
	 */
	public static final boolean validateUsername(String string) {
		int length = stringLength(string);
		if (length < 2 || length > 10) {
			return false;
		}
		Matcher matcherNumber = patternUsername.matcher(string);
		if (matcherNumber.find()) {
			return true;
		}
		return false;
	}

	public static final boolean validateKeyword(String string) {
		if (patternKeyword == null) {
			patternKeyword = Pattern.compile(stringPattern);
		}
		Matcher matcherNumber = patternKeyword.matcher(string);
		if (matcherNumber.find()) {
			return true;
		}
		return false;
	}

	public final static String showText(int main, int sub, int serial, int key) {
		StringBuilder sb = new StringBuilder();
		sb.append("　[info_font:1,").append(main).append("/").append(sub).append("/").append(serial).append("/").append(key).append("]");
		return StringSplit.transformString(sb.toString());
	}

	public final static void init(String filePath) {
		SAXBuilder builder = new SAXBuilder();
		InputStream file;
		try {
			file = new FileInputStream(filePath);
			Document document = builder.build(file);// 获得文档对象
			Element root = document.getRootElement();// 获得根节点
			List<Element> list = root.getChildren();
			String localName = null;
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (Element attributes : list) {
				localName = attributes.getName();
				String value = null;
				if ("text".equalsIgnoreCase(localName)) {
					value = attributes.getAttributeValue("value");
					if ((value.trim()).length() != 0) {
						if (i == 0) {
							sb.append(value.trim());
						} else {
							sb.append("|");
							sb.append(value.trim());
						}
						i++;
					}
				}
			}
			sb.append("|");
			sb.append(symbols);
			stringPattern = sb.toString();
			patternKeyword = Pattern.compile(stringPattern);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public final static String[] insertSortStringArray(String[] S_string_array) {
		int in, out;

		for (out = 1; out < S_string_array.length; out++) {
			String Temp = S_string_array[out]; // 先出对

			in = out;

			while ((in > 0) && S_string_array[in - 1].compareTo(Temp) >= 0) // 如果比出队的大则交互
			{
				S_string_array[in] = S_string_array[in - 1];
				--in;
			}
			S_string_array[in] = Temp;
		}

		return S_string_array;
	}

	/** 验证通过 */
	public static final int OK       = 10;
	/** 字符长度小于最低字符 */
	public static final int LESS_MIN = 1;
	/** 字符以数字开头 */
	public static final int FST_NUM  = 2;
	/** 包含非法字符 */
	public static final int LESS_LAW = 3;
	/** 字符长度大于最大字符 */
	public static final int MORE_MAX = 4;
	/** 字符串为空 */
	public static final int STR_NULL = 5;

	/**
	 * 根据指定的字符拆分字符串 参数1：要拆分的字符串 参数2：根据该字符拆分字符串 返回：拆分后的字符串数组
	 */
	public final static String[] splitString(String source, char _char) {
		if (source == null) {
			return new String[0];
		}
		char[] _chars = source.toCharArray();
		StringBuilder _stringbuffer = new StringBuilder();
		List<String> _vector = new ArrayList<String>();

		for (int i = 0; i < _chars.length; i++) {
			if (_chars[i] == _char) {
				_vector.add(_stringbuffer.toString());
				_stringbuffer.setLength(0);

			} else {
				_stringbuffer.append(_chars[i]);
			}
		}
		if (_stringbuffer.length() > 0) {
			_vector.add(_stringbuffer.toString());
		}

		String[] result = new String[_vector.size()];
		_vector.toArray(result);
		return result;
	}

	/**
	 * 根据指定的字符截取字符串 参数1：被截取的字符串 参数2：根据该字符截取字符串 返回：截取后的字符串
	 */
	public final static String subString(String source, char _char) {
		if (source == null) {
			return null;
		}
		char[] _chars = source.toCharArray();
		StringBuilder _stringbuffer = new StringBuilder();
		for (int i = 0; i < _chars.length; i++) {
			_stringbuffer.append(_chars[i]);
			if (_chars[i] == _char) {
				break;
			}
		}
		return _stringbuffer.toString();
	}

	/**
	 * @获取图片的名称
	 * 
	 * @参数：字符串形式的图片名
	 * @返回：整数型的图片名称
	 */
	public final static int[] string2IntArray(String src) {
		String[] _strings = splitString(src, ',');
		int[] _ints = new int[_strings.length];
		try {
			for (int i = 0; i < _strings.length; i++) {
				if (_strings[i].trim().length() > 0) {
					_ints[i] = Integer.parseInt(_strings[i].trim());
				}
			}
		} catch (NumberFormatException e) {
			LoggerError.error(src + " 拆分为数字数组是有误", e);
			return new int[0];
		}
		return _ints;
	}

	/**
	 * @获取图片的名称
	 * 
	 * @参数：字符串形式的图片名
	 * @返回：整数型的图片名称
	 */
	public final static int[] string2IntArray(String src, char c) {
		String[] _strings = splitString(src, c);
		int[] _ints = new int[_strings.length];
		try {
			for (int i = 0; i < _strings.length; i++) {
				_ints[i] = Integer.parseInt(_strings[i].trim());
			}
		} catch (NumberFormatException e) {
			LoggerError.error(src + " 拆分为数字数组是有误", e);
			return new int[0];
		}
		return _ints;
	}

	/**
	 * @获取图片的名称
	 * 
	 * @参数：字符串形式的图片名
	 * @返回：整数型的图片名称
	 */
	public final static int[] string2float2IntArray(String src, char c) {
		String[] _strings = splitString(src, c);
		int[] _ints = new int[_strings.length];
		try {
			for (int i = 0; i < _strings.length; i++) {
				if (_strings[i].contains(".")) {
					_ints[i] = (int) Double.parseDouble(_strings[i].trim());
				} else {
					_ints[i] = Integer.parseInt(_strings[i].trim());
				}
			}
		} catch (NumberFormatException e) {
			LoggerError.error(src + " 拆分为数字数组是有误", e);
			return new int[0];
		}
		return _ints;
	}

	/**
	 * @获取图片的名称
	 * 
	 * @参数：字符串形式的图片名
	 * @返回：整数型的图片名称
	 */
	public final static short[] string2ShortArray(String src) {
		return string2ShortArray(src, ',');
	}

	/**
	 * @获取图片的名称
	 * 
	 * @参数：字符串 * @返回：short型数组
	 */
	public final static short[] string2ShortArray(String src, char c) {
		String[] _strings = splitString(src, c);
		short[] _shorts = new short[_strings.length];
		try {
			for (int i = 0; i < _strings.length; i++) {
				_shorts[i] = Short.parseShort(_strings[i].trim());
			}
		} catch (NumberFormatException e) {
			LoggerError.error(src + " 拆分为数字数组是有误", e);
			return new short[0];
		}
		return _shorts;
	}

	public final static byte[] string2ByteArray(String src, char ch) {
		String[] _strings = splitString(src, ch);
		byte[] _shorts = new byte[_strings.length];
		try {
			for (int i = 0; i < _strings.length; i++) {
				_shorts[i] = Byte.parseByte(_strings[i].trim());
			}
		} catch (NumberFormatException e) {
			LoggerError.error(src + " 拆分为数字数组是有误", e);
			return new byte[0];
		}
		return _shorts;
	}

	/**
	 * 得到串码(17位 13位 unix 4位 随机
	 */
	public final static String getChuanMa() {
		long _long = System.currentTimeMillis();
		int _int = MathUtil.getRandom(1000, 9999);
		StringBuilder _stringbuffer = new StringBuilder();
		_stringbuffer.append(_long).append(_int);
		return _stringbuffer.toString();
	}

	/**
	 * 处理字符串中的单引号和双引号
	 * 
	 * 参数：待处理的字符串 返回：处理后的结果
	 */
	public final static String dealQuoteMark(String source) {
		if (source == null) {
			return "";
		}
		char _chars[] = source.toCharArray();
		StringBuilder _stringbuffer = new StringBuilder();
		for (int i = 0; i < _chars.length; i++) {
			if (_chars[i] == '"') {
				_stringbuffer.append('\\');
				_stringbuffer.append('"');
			} else if (_chars[i] == '\'') {
				_stringbuffer.append('\\');
				_stringbuffer.append('\'');
			} else {
				_stringbuffer.append(_chars[i]);
			}
		}
		return _stringbuffer.toString();
	}

	/**
	 * 以换行符为分界线进行截取字符串
	 * 
	 * 参数：源字符串 返回：处理结果
	 */
	public final static Vector<String> dealEnterSymbol(String source) {
		int index = 0;
		int start = 0;
		Vector<String> result = new Vector<String>();
		do {
			do {
				index = source.indexOf("\r\n", start);
				if (index <= 0) {
					break;
				}
				result.addElement(source.substring(start, index));
				if (index + 1 == source.length() - 1) {
					return result;
				}
				start = index + 2;
			} while (true);
		} while (start < 0 || start >= source.length() - 1);
		result.addElement(source.substring(start));
		return result;
	}

	/**
	 * 获取拼成地图的地砖的图片序列 参数：地图数据 返回：地砖图片名称序列
	 */
	public final static String getCeilPicOrder(String datas) {
		String[] ceils = datas.split("\\|");

		StringBuilder _stringbuffer = new StringBuilder();
		for (int i = 0; i < ceils.length; i++) {
			_stringbuffer.append(ceils[i].substring(0, ceils[i].indexOf(',') + 1));
		}
		return _stringbuffer.substring(0, _stringbuffer.length() - 1);
	}

	/**
	 * 获取地图中能否通过的序列 参数：地图数据 返回：数字序列
	 */
	public final static String getPassOrder(String datas) {
		String[] ceils = datas.split("\\|");

		StringBuilder _stringbuffer = new StringBuilder();
		for (int i = 0; i < ceils.length; i++) {
			_stringbuffer.append(ceils[i].substring(ceils[i].indexOf(',')));
		}
		return _stringbuffer.substring(1);
	}

	/**
	 * 获取地图中所使用到的地砖图片的名称 参数：地砖图片序列 返回：地砖图片名称列表
	 */
	public final static String getCeilPics(String picsOrder) {
		String[] ceils = picsOrder.split(",");
		Hashtable<String, String> names = new Hashtable<String, String>();
		StringBuilder _stringbuffer = new StringBuilder();
		for (int i = 0; i < ceils.length; i++) {
			if (names.get(ceils[i]) == null) {
				_stringbuffer.append(ceils[i]).append(",");
				names.put(ceils[i], ceils[i]);
			}
		}
		return _stringbuffer.toString();
	}

	/**
	 * 获取转换后的地图地砖序列 参数1：地砖图片名称 参数2：地砖图片名称序列 返回：转换后的数据
	 */
	public final static String convertCeilOrders(String ceilPics, String ceilPicsOrder) {
		String[] picsName = ceilPics.split(",");
		String[] picsOrder = ceilPicsOrder.split(",");
		StringBuilder _stringbuffer = new StringBuilder();
		for (int i = 0; i < picsOrder.length; i++) {
			for (int j = 0; j < picsName.length; j++) {
				if (picsName[j].equals(picsOrder[i])) {
					if (i < picsOrder.length - 1) {
						_stringbuffer.append(j).append(",");
					} else {
						_stringbuffer.append(j);
					}
				}
			}
		}
		return _stringbuffer.toString();
	}

	public final static String subString(String src, int length) {
		String value = "";
		if (src != null) {
			if (src.length() > length) {
				value = src.substring(0, length) + "...";
			} else {
				value = src;
			}
		}
		return value;
	}

	/**
	 * Convert the integer to an unsigned number.
	 */
	public final static String toUnsignedString(long i, int shift) {
		char[] buf = new char[64];
		int charPos = 64;
		int radix = 1 << shift;
		long mask = radix - 1;
		do {
			buf[--charPos] = digits[(int) (i & mask)];
			i >>>= shift;
		} while (i != 0);
		return new String(buf, charPos, (64 - charPos));
	}

	public static final String mathProperty(short p1, short p2) {
		StringBuilder sb = new StringBuilder();
		sb.append("[math:").append("P").append(p1).append("*").append("P").append(p2).append("]");
		return StringSplit.transformString(sb.toString());
	}

	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
	        'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z', };

	/**
	 * MD5 加密
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			LoggerError.error("NoSuchAlgorithmException caught!", e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

	private static Pattern phonePattern = Pattern.compile("1[3|5|7|8|][0-9]{9}");

	public static void setPhonePattern(Pattern phonePattern) {
		StringUtil.phonePattern = phonePattern;
	}

	public static boolean isMobileNO(String mobiles) {
		Matcher m = phonePattern.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 验证手机号是否合法
	 * 
	 * @param phoneCode
	 *            待验证的手机号
	 * @return true合法，false不合法
	 */
	public static boolean validatePhoneCode(String phoneCode) {
		if (phoneCode == null || phoneCode.trim().length() != 11) {
			return false;
		}
		return true;
	}

	public final static int stringLength(String value) {
		if (value == null) {
			return 0;
		}
		int count = 0;
		Matcher m = patternLength.matcher(value);
		while (m.find()) {
			count++;
		}
		return value.length() + count;
	}

	public final static String formatIp(String source) {
		if (source != null) {
			if (source.contains("/") && source.contains(":")) {
				source = source.substring(source.indexOf('/') + 1, source.indexOf(':'));
			}
		}
		return source;
	}
}
