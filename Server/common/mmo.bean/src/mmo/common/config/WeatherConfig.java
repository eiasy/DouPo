package mmo.common.config;

import java.util.HashMap;
import java.util.Map;

public class WeatherConfig {
	/** 存放地形类别与名称的键值对 */
	private final static Map<String, Byte> WEATHER              = new HashMap<String, Byte>();
	/** 晴朗 */
	public static final byte               sunny                = 0;
	/** 黑暗 */
	public static final byte               dark                 = 1;
	/** 小雪 */
	public final static byte               WEATHER_SMALL_SNOW   = 2;
	/** 大雪 */
	public final static byte               WEATHER_BIG_SNOW     = 3;
	/** 小雨 */
	public final static byte               WEATHER_SMALL_RAIN   = 4;
	/** 大雨 */
	public final static byte               WEATHER_BIG_RAIN     = 5;
	/** 雷雨 */
	public final static byte               WEATHER_THUNDER_RAIN = 6;
	static {
		WEATHER.put("晴天", sunny);
		WEATHER.put("黑暗", dark);
		WEATHER.put("小雪", WEATHER_SMALL_SNOW);
		WEATHER.put("大雪", WEATHER_BIG_SNOW);
		WEATHER.put("小雨", WEATHER_SMALL_RAIN);
		WEATHER.put("大雨", WEATHER_BIG_RAIN);
		WEATHER.put("雷雨", WEATHER_THUNDER_RAIN);
	}

	public final static byte getWeatherValue(String name) {
		Byte weather = WEATHER.get(name);
		if (weather == null) {
			return 0;
		}
		return weather;
	}
}
