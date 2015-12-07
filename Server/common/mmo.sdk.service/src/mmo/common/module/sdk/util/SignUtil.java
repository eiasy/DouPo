package mmo.common.module.sdk.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mmo.tools.util.MD5;

public class SignUtil {

	public static String getSign(String key, String reqParams) {

		HashMap<String, String> map = getMd5Map(reqParams);
		String req = sortConstructVars(map);
		String str = req + "&key=" + key;
		String sign = getMD5(str);

		return sign;
	}

	private static HashMap<String, String> getMd5Map(String params) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] s_map = params.split("&");
		for (int i = 0; i < s_map.length; i++) {
			int index = s_map[i].indexOf("=");
			if (index > 0 && index < s_map[i].length() - 1) {
				map.put(s_map[i].substring(0, index), UriUtil.decode(s_map[i].substring(index + 1)));
			}
		}
		return map;
	}

	private static String sortConstructVars(HashMap<String, String> orig_map) {
		StringBuilder s = new StringBuilder();
		Map.Entry<String, String> entry;
		Iterator<Map.Entry> itr;
		boolean first_param;
		List<Map.Entry> list = new LinkedList<Map.Entry>(orig_map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue().toString()).compareTo(((Map.Entry) (o2)).getValue().toString());
			}
		});

		first_param = true;

		for (itr = list.iterator(); itr.hasNext();) {
			entry = (Map.Entry) itr.next();
			if ((entry.getKey().toString().length() > 0) && (entry.getValue().toString().length() > 0)) {
				if (first_param) {
					s.append(entry.getKey().toString());
					s.append("=");
					s.append(entry.getValue().toString());
					first_param = false;
				} else {
					s.append("&");
					s.append(entry.getKey().toString());
					s.append("=");
					s.append(entry.getValue().toString());
				}
			}

		}
		return s.toString();
	}

	private static String getMD5(String string) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(string.getBytes("utf-8"));
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);
		} catch (Exception e) {
		}
		return s;
	}

	
}