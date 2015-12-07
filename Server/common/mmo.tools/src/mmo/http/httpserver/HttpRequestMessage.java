package mmo.http.httpserver;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 使用Mina解析出的HTTP请求对象
 * 
 * @author Ajita
 * 
 */
public class HttpRequestMessage {
	/**
	 * HTTP请求的主要属性及内容
	 */
	private Map<String, String[]> headers        = null;
	private List<String>          parameterNames = null;

	public Map<String, String[]> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String[]> headers, List<String> parameterNames) {
		this.headers = headers;
		this.parameterNames = parameterNames;
	}

	public List<String> getParameterNames() {
		return parameterNames;
	}

	/**
	 * 获取HTTP请求的Context信息
	 */
	public String getContext() {
		String[] context = headers.get("Context");
		return context == null || context.length < 1 ? "" : context[0];
	}

	/**
	 * 获取HTTP请求的Host信息
	 */
	public String getHost() {
		String[] context = headers.get("Host");
		return context == null || context.length < 1 ? "" : context[0];
	}

	/**
	 * 根据属性名称获得属性值数组第一个值，用于在url中传递的参数
	 */
	public String getParameter(String name) {
		String[] param = headers.get("@".concat(name));
		return param == null || param.length < 1 ? "" : param[0];
	}

	/**
	 * 根据属性名称获得属性值，用于在url中传递的参数
	 */
	public String[] getParameters(String name) {
		String[] param = headers.get("@".concat(name));
		return param == null ? new String[] {} : param;
	}

	/**
	 * 根据属性名称获得属性值数组第一个值，用于在url中传递的参数
	 */
	public String getHeaderValue(String name) {
		String[] param = headers.get(name);
		if (param != null) {
			return arrayToString(param, ',');
		}
		return "";
	}

	/**
	 * 根据属性名称获得属性值，用于请求的特征参数
	 */
	public String[] getHeader(String name) {
		return headers.get(name);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		for (Entry<String, String[]> e : headers.entrySet()) {
			str.append(e.getKey() + " : " + arrayToString(e.getValue(), ',') + "\n");
		}
		return str.toString();
	}

	/**
	 * 静态方法，用来把一个字符串数组拼接成一个字符串
	 * 
	 * @param s要拼接的字符串数组
	 * @param sep数据元素之间的烦恼歌负
	 * @return 拼接成的字符串
	 */
	public static String arrayToString(String[] s, char sep) {
		if (s == null || s.length == 0) {
			return "";
		}
		StringBuffer buf = new StringBuffer();
		if (s != null) {
			for (int i = 0; i < s.length; i++) {
				if (i > 0) {
					buf.append(sep);
				}
				buf.append(s[i]);
			}
		}
		return buf.toString();
	}


	public int getIntParameter( String name) {
		try {
			return Integer.parseInt(getParameter(name));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public long getLongParameter( String name) {
		try {
			return Long.parseLong(getParameter(name));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public double getDoubleParameter( String name) {
		try {
			return Double.parseDouble(getParameter(name));
		} catch (Exception e) {
			return 0;
		}
	}
}
