package com.huayi.doupo.base.util.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.huayi.doupo.base.util.logic.system.LogUtil;

/**
 * 字符串操作工具类
 * @author mp
 * @date 2014-3-28 上午11:49:42
 */
public class StringUtil {
	private static Random                      _random        = new Random();                   // 随机数类
	/**
	 * 初始化拼音代码
	 */
    private static final int[] AREA_CODE = new int[]{
        45217, 45253, 45761, 46318, 46826, 47010, 47297, 47614, 48119, 48119, 49062,
        49324, 49896, 50371, 50614, 50622, 50906, 51387, 51446, 52218, 52698, 52698,
        52698, 52980, 53689, 54481
    };
    
    /**
     * 获取Alpha
     * @author mp
     * @date 2015-3-4 下午2:13:49
     * @param character
     * @return
     * @Description
     */
    private static String getAlpha(String character) {
        byte[] bytes;
        try {
            bytes = character.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return character;
        }
        if (bytes.length < 2) {
            return character;
        }

        int area = (short) bytes[0];
        int pos = (short) bytes[1];
        int code = (area << 8 & 0xff00) + (pos & 0xff);

        for (int i = 0; i < 26; i++) {
            int max = 55290;
            if (i != 25) {
                max = AREA_CODE[i + 1];
            }
            if (AREA_CODE[i] <= code && code < max) {
                return new String(new byte[]{(byte) (65 + i)});
            }
        }
        return character;
    }
    
    /**
     * 获取输入参数的首字母[大写]
     * @author mp
     * @date 2015-3-4 下午2:14:05
     * @param text
     * @return
     * @Description
     */
    public static String getFirstAlpha(String text) {
        if (text == null || "".equals(text)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            sb.append(getAlpha(text.substring(i, i + 1)));
        }
        return sb.toString();
    }
	
    /**
     * 获取首字母
     * @author mp
     * @date 2015-3-4 下午2:23:20
     * @param str
     * @return
     * @Description
     */
    public static String getFirstString (String str) {
    	return str.substring(0,1);
    }

    /**
     * 获取尾字母
     * @author mp
     * @date 2015-3-4 下午2:24:52
     * @param str
     * @return
     * @Description
     */
    public static String getLastString (String str) {
    	return str.substring(str.length() - 1, str.length());
    }
    
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
	
	/**
	 * 获取不包含最后一个字符的字符串
	 * @author mp
	 * @date 2014-8-16 上午10:17:18
	 * @param str
	 * @return
	 * @Description
	 */
	public static String noContainLastString (String str) {
		return str.substring(0, str.length() - 1);
	}
	
	/**
	 * 获取不包含最第一个字符的字符串
	 * @author mp
	 * @date 2014-8-20 下午4:38:51
	 * @param str
	 * @return
	 * @Description
	 */
	public static String noContainFirstString (String str) {
		return str.substring(1, str.length());
	}
	
	/**
	 * 给字符串前后加入指定字符
	 * @author hzw
	 * @date 2014-8-21下午2:23:01
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static String firstLastAddChar (String str , String inserChar) {
		return inserChar + str + inserChar;
	}
	
	/**
	 * 加上自定字符后,是否包含
	 * @author hzw
	 * @date 2014-8-28 下午4:40:14
	 * @param str
	 * @param searchChar
	 * @param inserChar 
	 * @return
	 * @Description
	 */
	public static boolean contains (String str , String searchChar, String inserChar) {
		return StringUtils.contains(StringUtil.firstLastAddChar(str, inserChar), StringUtil.firstLastAddChar(searchChar, inserChar));
	}
	
	/**
	 * 将Map中的值以固定的连接符连接起来
	 * @author mp
	 * @date 2015-3-23 下午4:16:59
	 * @param params
	 * @param connector
	 * @return
	 * @Description
	 */
	@SuppressWarnings({ "rawtypes"})
	public static String toParams (Map<String, String> params, String connector) {
		String ret = "";
        if (params !=null && !params.isEmpty()) {
            Iterator iter = params.entrySet().iterator();
            StringBuilder buffer = new StringBuilder(128);
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                buffer.append((String)entry.getKey()).append("=").append((String)entry.getValue()).append(connector);
            }
            ret = buffer.toString();
        }
        return noContainLastString(ret);
	}
	
	/**
	 * 将参数进行ENCODE编码
	 * @author mp
	 * @date 2015-3-24 上午11:07:13
	 * @param params
	 * @param connector
	 * @return
	 * @Description
	 */
	@SuppressWarnings({ "rawtypes"})
	public static String toParamsEncode (Map<String, String> params, String connector) {
		String ret = "";
        if (params !=null && !params.isEmpty()) {
            Iterator iter = params.entrySet().iterator();
            StringBuilder buffer = new StringBuilder(128);
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                buffer.append((String)entry.getKey()).append("=").append(encode((String)entry.getValue())).append(connector);
            }
            ret = buffer.toString();
        }
        return noContainLastString(ret);
	}
	
	/**
	 * 将某个值进行编码
	 * @author mp
	 * @date 2015-3-24 上午11:51:55
	 * @param input
	 * @return
	 * @Description
	 */
    public static String encode(String input) {
    	try {
    		return URLEncoder.encode(input, "UTF-8").replace("+", "%20").replace("*", "%2A");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }

    /**
     * 获得指定字符后边的值, 如果输入的seg参数不包含在str里, value返回0
     * eg: str = ";1_2;12_636;2_5;3_3456;";   输入";12_"得到636
     * @author mp
     * @date 2015-6-4 下午3:23:06
     * @param str
     * @param seg
     * @return
     * @Description
     */
    public static int getSegValue (String str, String seg) {
    	if (str.contains(seg)) {
    		int index = str.indexOf(seg) + seg.length();
			return ConvertUtil.toInt(str.substring(index, str.indexOf(";", index)));
		} else {
			return 0;
		}
    }
    
    /**
     * 返回指定字符后边加上1后的字符串, 如果输入的seg参数不包含在str里, 返回追加后的字符串(数量为1)
     * eg: str = ";1_2;12_636;2_5;3_3456;";   输入";12_"得到  ";1_2;12_637;2_5;3_3456;"
     * @author mp
     * @date 2015-6-4 下午3:29:52
     * @param str
     * @param seg
     * @return
     * @Description
     */
    public static String segValueAddOne (String str, String seg) {
    	int value = getSegValue (str, seg);
    	if (value == 0) {
    		if (str.equals("")) {
    			seg = seg + 1 + ";";
			} else {
				seg = noContainFirstString(seg) + 1 + ";";
			}
    		return str + seg;
		} else {
			return str.replace((seg + value + ";"), (seg + (value + 1) + ";"));
		}
    }
	
	@Test
	public void test() {
//		String str = ";1_2;12_636;2_5;3_3456;";
//		System.out.println(segValueAddOne(str, ";11_"));
		
		String openId = "733856328@360";
		System.out.println(openId.substring(0,1));
		
	}
	
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
			LogUtil.error(src + " 拆分为数字数组是有误", e);
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
			LogUtil.error(src + " 拆分为数字数组是有误", e);
			return new int[0];
		}
		return _ints;
	}
	
	/**
	 * 得到两个非负数范围内的随机值(包括这两个数,第二个数大于等于第一个数)
	 * 
	 * @param 较小的非负数
	 * @param 较大的非负数
	 * @return 两数范围内的一个数,包括边界
	 */
	public final static int getRandom(int _int0, int _int1) {
		if (_int0 > _int1) {
			int t = _int0;
			_int0 = _int1;
			_int1 = t;
		}
		return _int0 + Math.abs(_random.nextInt() % (_int1 - _int0 + 1));
	}
}
