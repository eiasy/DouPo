package mmo.tools.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathUtil {
	private static Random                      _random        = new Random();                   // 随机数类

	private final static int                   BASE_NUMBER    = 100;                            // 基数

	private final static Map<Class<?>, String> TYPE_ZONE_INFO = new HashMap<Class<?>, String>();
	static {
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		sb.append("取值区间为 (").append(Byte.MIN_VALUE).append("~").append(Byte.MAX_VALUE).append(") ！");
		TYPE_ZONE_INFO.put(Byte.class, sb.toString());
		sb.setLength(0);
		sb.append("取值区间为 (").append(Short.MIN_VALUE).append("~").append(Short.MAX_VALUE).append(") ！");
		TYPE_ZONE_INFO.put(Short.class, sb.toString());
		sb.setLength(0);
		sb.append("取值区间为 (").append(Integer.MIN_VALUE).append("~").append(Integer.MAX_VALUE).append(") ！");
		TYPE_ZONE_INFO.put(Integer.class, sb.toString());
		sb.setLength(0);
		sb.append("取值区间为 (").append(Long.MIN_VALUE).append("~").append(Long.MAX_VALUE).append(") ！");
		TYPE_ZONE_INFO.put(Long.class, sb.toString());
		sb.setLength(0);
		sb = null;
	}

	public static String                       NUMBER         = "^-?[1-9]\\d*$";

	public static boolean matchNumber(String src) {
		Pattern pattern = null;
		Matcher matcher = null;
		pattern = Pattern.compile("^[0-9]");
		matcher = pattern.matcher(src);
		return matcher.find();
	}

	public static String getValueZoneInfo(@SuppressWarnings("rawtypes") Class type) {
		return TYPE_ZONE_INFO.get(type);
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

	public final static long random(long _int0, long _int1) {
		if (_int0 > _int1) {
			long t = _int0;
			_int0 = _int1;
			_int1 = t;
		}
		return _int0 + Math.abs(_random.nextLong() % (_int1 - _int0 + 1));
	}

	/**
	 * 判断是否在一个几率范围内
	 * 
	 * 参数1:分母 参数2:分子
	 */
	public final static boolean isProbability(int denominator, int molecular) {
		return Math.abs(_random.nextInt()) % denominator < molecular;
	}

	public final static boolean probability(int rate) {
		return _random.nextInt(BASE_NUMBER) < rate;
	}

	public final static boolean probability(int base, int rate) {
		return _random.nextInt(base) < rate;
	}

	public static final float point2ellips(int pointX, int pointY, int centerX, int centerY, int a, int b) {
		if (a == 0 || b == 0) {
			return 2;
		}
		float x = pointX - centerX;
		float y = pointY - centerY;
		return (x * x) / (a * a) + (y * y) / (b * b);
	}

	/**
	 * 获取count个不重复的数，范围在0-size之间
	 * 
	 * @param count
	 * @param size
	 * @return
	 */
	public static int[] getNotRepRandom(int count, int size) {
		int[] values = new int[count];
		int[] all = new int[size];
		int mark = 0;
		int value = 0;
		int temp = 0;
		for (int i = 0; i < size; i++) {
			all[i] = i;
		}
		for (int i = size - 1; i >= 0; i--) {
			value = ((int) (Math.random() * 100 * size)) % (i + 1);
			temp = all[value];
			all[value] = all[i];
			all[i] = temp;
			values[mark++] = all[i];
			if (mark == count) {
				break;
			}
		}
		return values;
	}
	
	/**
	 * 获得指定范围，指定个数的不重复的随机数,从1开始；
	 * 
	 * @param limitNum
	 * @param sum
	 * @return
	 */
	public static List<Integer> getRandomNoRepeat(int num, int maxNum) {
		List<Integer> list = new ArrayList<Integer>();
		if (maxNum < 1) {
			return list;
		}
		if (num > 0) {
			int offset = maxNum / num;
			if (offset > 1) {
				for (int n = 0; n < num; n++) {
					if(n==num-1){
						list.add(getRandom(offset * n + 1, maxNum));
					}else{
						list.add(getRandom(offset * n + 1, offset * (n + 1)));
					}
				}
			} else {
				int[] array = new int[maxNum];
				int a1 = 0;
				int a2 = 0;
				for (int n = 0; n < maxNum; n++) {
					array[n] = n + 1;
				}
				int temp = 0;
				for (int n = 0; n < maxNum; n++) {
					a1 = getRandom(0,maxNum-1);
					a2 = getRandom(0,maxNum-1);
					temp = array[a1];
					array[a1]=array[a2];
					array[a2]=temp;
				}
				for (int n = 0; n < num; n++) {
					list.add(array[n]);
				}
			}
		}
		return list;
	}

}
