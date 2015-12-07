package mmo.tools.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import mmo.tools.util.ByteOrStringHelper;
import mmo.tools.util.DateUtil;
import mmo.tools.util.MD5;
import mmo.tools.util.Point;
import net.sf.json.JSONObject;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class TestClass {
	private final static String APP_ID_VALUE = "116051";
	private String              APP_KEY      = "9af85a8f0a38f248d0c4a513025b37993750b0f917d07660";
	// 91的服务器地址
	private String              goUrl        = "http://service.sj.91.com/usercenter/ap.aspx?";
	private final static int    POS          = 1 << 30;

	public static int task(int n, int a) {
		int sum = 0;
		int temp = a;
		// 循环迭代
		for (int i = 0; i < n; i++) {
			sum += a;
			temp *= 10;
			a += temp;
		}
		return sum;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(MD5.getHashString("0,0,0,0,10,0,f7a0f01c467127b46652fa43b34365d2_2_3,C65BF074A15305568D97C26C00159045,6FED3116D035FF079B12E6D66D881848,6FED3116D035FF079B12E6D66D881848,desktop_m_qq-73213123-android-73213123-qq-1104824416-C65BF074A15305568D97C26C00159045,3334d29b441998fc9b23d9933e5fe3dc,qq"));
		// Map<String, String> map = new ConcurrentHashMap<String, String>();
		// map.put("a", "a");
		// map.put("b", "b");
		// map.put("c", "c");
		// map.put("d", "d");
		// map.put("f", "f");
		// map.put("g", "g");
		//
		// for(String s:map.values()){
		// System.out.println(s);
		// map.remove(s);
		// }
		// System.out.println(map);

		// String strTime = "2014-03-08 17:02:32,843 +0800 ";
		// Date date = DateUtil.stringToDate(strTime);
		// System.out.println("date = " + date);
		// System.out.println(date.getTime());
		// System.out.println(date.toLocaleString());
		// System.out.println("DateUtil = " +DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss.SS"));

		// int[] ar = new int[3];
		// ar[0] = 1;
		// ar[1] = 2;
		// ar[2] = 3;
		//
		// int[] b = ar.clone();
		// for (int v : b) {
		// System.out.println("b = " + v);
		// }
		//
		// ar[0] = 11;
		// ar[1] = 21;
		// ar[2] = 31;
		//
		// for (int v : b) {
		// System.out.println("b + " + v);
		// }
		// Map<Integer, Object> rolesPushed = new ConcurrentHashMap<Integer, Object>();
		// rolesPushed.put(1, "a");
		// rolesPushed.put(2, "b");
		// rolesPushed.put(3, "c");
		// Set<Integer> keys = rolesPushed.keySet();
		// for (int k : keys) {
		// rolesPushed.remove(k);
		// System.out.println(rolesPushed);
		// }

		// testMap();
		// System.out.println(System.currentTimeMillis());

		// StringBuilder sb = new StringBuilder();
		// sb.append("hy_fanren").append("_z").append(1).append("_charge_result_").append(DateUtil.formatDate(new
		// Date(), "yyyyMMdd-HH"));
		// System.out.println(sb.toString());
		// int id = 66;
		// System.out.println(String.format("%-7s", "aa"));

		// int[][] a = new int[3][2];
		// System.out.println(a.length);
		// System.out.println(a[0].length);

		// int x=4;
		// System.out.println("value  is  "+ ((x>4) ? 99.9 :9));

		// int i, j;
		// int a[] = { 5, 9, 6, 8, 7 };
		// for (i = 0; i < a.length - 1; i++) {
		// int k = i;
		// for (j = i; j < a.length; j++) {
		// if (a[j] < a[k]) {
		// int temp = a[j];
		// a[j] = a[k];
		// a[k] = temp;
		// k = j;
		// }
		// }
		// }
		// for (i = 0; i < a.length; i++)
		// System.out.print(a[i] + " ");
		// System.out.println();

		// Calendar date = Calendar.getInstance();
		// System.out.println(date.get(Calendar.DAY_OF_WEEK));
		// System.out.println(DateUtil.formatDate(new Date(), "MMddHHmmss"));
		// System.out.println(Integer.MAX_VALUE);

		// List<Integer> roles = new ArrayList<Integer>();
		//
		// roles.add(1);
		// roles.add(3);
		// roles.add(2);
		// System.out.println("1 roles = " + roles);
		// roles.remove(2);
		// System.out.println("2 roles = " + roles);
		//
		// List<Integer> roles2 = new ArrayList<Integer>();
		// roles2.add(1);
		// roles2.add(3);
		// roles2.add(2);
		// Integer i2 = 3;
		//
		// System.out.println("1 roles2 = " + roles2);
		// roles2.remove(i2);
		// System.out.println("2 roles2 = " + roles2);

		// Date d = DateUtil.stringToDate("2014-10-10 24:00:00");
		// System.out.println(DateUtil.formatDate(d));
		// Set<Integer> s = new HashSet<Integer>();
		// s.add(1);
		// s.add(2);
		// System.out.println(s);
		// s.add(1);
		// s.add(3);
		// System.out.println(s);

		// List<Integer> l = new ArrayList<Integer>();
		// l.add(1);
		// l.add(2);
		// l.add(3);
		// System.out.println(l);
		// l.set(0, 11);
		// l.set(1, 22);
		// l.set(2, 33);
		// System.out.println(l);
		// String s = "Tier 3/¥18.00";
		// System.out.println(s.substring(s.indexOf('/') + 2));

		// long ct = System.currentTimeMillis();
		// System.out.println((ct / 10) & 0x7fffffff);
		// System.out.println((ct & 0x7fffffff) / 10);
		// System.out.println(Short.MAX_VALUE);
		// String a = "dsdf@1";
		// System.out.println(a.substring(a.indexOf("@") + 1));
		// System.out.println(MD5.getHashString(a));

		// StringBuilder sb = new StringBuilder();
		// sb.append(1).append('^');
		// sb.append(2).append('#');
		// sb.append(1).append('@');
		// sb.append(2);
		// System.out.println(MD5.getHashString(sb.toString()));
		// byte[] sign = HMACSHA1
		// .getHmacSHA1(
		// "GET&%2Fmpay%2Fbuy_goods_m&amt%3D6%26appid%3D1104244259%26appmode%3D1%26format%3Djson%26goodsmeta%3D60%E5%85%83%E5%AE%9D%2A60%E5%85%83%E5%AE%9D%26goodsurl%3Dhttp%3A%2F%2Fwww.huayigame.com%2FfrRes%2Fyuanbao.png%26openid%3D948ED0BB54B8EC4EAAF4108E7D779D3C%26openkey%3D3FD2B23C32EB04C40D1F2560D1F273C4%26pay_token%3DA2D5E8C4E2DF037E1A423FDBB19015F8%26payitem%3D914006%2A6%2A1%26pf%3Ddesktop_m_qq-73213123-android-73213123-qq-1104244259-948ED0BB54B8EC4EAAF4108E7D779D3C%26pfkey%3D50ec72e40643b2b44cd17ba04330f3ec%26ts%3D1426236980%26zoneid%3D1",
		// "X6U6ZZch0ZEXHt8r&");
		// System.out.println(new BASE64Encoder().encode(sign));
		// System.out.println("2IHbtUcTUUuMocAUXX3fUE29Fdk=");
		// System.out.println((new sun.misc.BASE64Encoder()).encode("道具*金币".getBytes()));
		// System.out.println(URLEncoder.encode("60元宝*60元宝").replaceAll("\\*", "%" +
		// Integer.toHexString('*')).toUpperCase());
		// System.out.println("60%E5%85%83%E5%AE%9D%2A60%E5%85%83%E5%AE%9D");
		// System.out
		// .println("GET&"
		// + URLEncoder.encode("/mpay/buy_goods_m")
		// + "&"
		// + URLEncoder
		// .encode("amt=6&appid=1104244259&appmode=1&format=json&goodsmeta=60元宝*60元宝&goodsurl=http://www.huayigame.com/frRes/yuanbao.png&openid=948ED0BB54B8EC4EAAF4108E7D779D3C&openkey=3FD2B23C32EB04C40D1F2560D1F273C4&pay_token=A2D5E8C4E2DF037E1A423FDBB19015F8&payitem=914006*6*1&pf=desktop_m_qq-73213123-android-73213123-qq-1104244259-948ED0BB54B8EC4EAAF4108E7D779D3C&pfkey=50ec72e40643b2b44cd17ba04330f3ec&ts=1426238721&zoneid=1")
		// .replace("*", "%" + Integer.toHexString('*')).toUpperCase());
		// System.out
		// .println("GET&%2Fmpay%2Fbuy_goods_m&amt%3D6%26appid%3D1104244259%26appmode%3D1%26format%3Djson%26goodsmeta%3D60%E5%85%83%E5%AE%9D%2A60%E5%85%83%E5%AE%9D%26goodsurl%3Dhttp%3A%2F%2Fwww.huayigame.com%2FfrRes%2Fyuanbao.png%26openid%3D948ED0BB54B8EC4EAAF4108E7D779D3C%26openkey%3D3FD2B23C32EB04C40D1F2560D1F273C4%26pay_token%3DA2D5E8C4E2DF037E1A423FDBB19015F8%26payitem%3D914006%2A6%2A1%26pf%3Ddesktop_m_qq-73213123-android-73213123-qq-1104244259-948ED0BB54B8EC4EAAF4108E7D779D3C%26pfkey%3D50ec72e40643b2b44cd17ba04330f3ec%26ts%3D1426236980%26zoneid%3D1");
		//
		// System.out
		// .println("http://msdktest.qq.com/mpay/buy_goods_m?amt=6&appid=1104244259&appmode=1&format=json&goodsmeta=60元宝*60元宝&goodsurl=http://www.huayigame.com/frRes/yuanbao.png&openid=948ED0BB54B8EC4EAAF4108E7D779D3C&openkey=3FD2B23C32EB04C40D1F2560D1F273C4&pay_token=A2D5E8C4E2DF037E1A423FDBB19015F8&payitem=914006*6*1&pf=desktop_m_qq-73213123-android-73213123-qq-1104244259-948ED0BB54B8EC4EAAF4108E7D779D3C&pfkey=50ec72e40643b2b44cd17ba04330f3ec&ts=1426240198&zoneid=1&sig=L9vAMYKAe4Ovg565taQb7MtEYHA=");
		// System.out.println(URLEncoder.encode("60%E5%85%83%E5%AE%9D%2A60%E5%85%83%E5%AE%9D"));

		// System.out
		// .println(MD5
		// .getHashString("363/15b458a8f4568d961de014e3ca1634fb@mk177/1/1/670000/1002045/100024/葛开山/6/1/176864059/948ED0BB54B8EC4EAAF4108E7D779D3C/TENCENT/914006"));

		float[] yzz = new float[] { 2450, 50, 110, 1200, 1990, 920, 2000, 3305, 7000, 300, 5000, 2000, 3500, 492, 2600, 2400, 14000, 6000, 10000,
		        5000, 2989, 10000, 2000, 14914, 10000, 9000, 10, 8000, 5000, 4000, 5000, 5000, 3900, 100, 10000, 1000.79f, 9000, 7100, 9000, 500,
		        1000, 10000, 4000, 3000, 9201, 190, 12000, 1000 };
		float[] zzy = new float[] { -300, -500, -500, -250, -200, -580, -128, -200, -9000, -2000, -700, -100, -634, -174, -2600, -100, -600, -1650,
		        -200, -2000, -2000, -1500, -5000, -5000, -8355, -330, -3000, -200, -500, -110, -1000, -1200, -19000, -4700, -200, -6000, -200, -5,
		        -700, -8000, -150, -100, -1000, -400, -500, -5000, -100, -1500, -5, -1000, -12800, -320, -1000, -10000, -9000, -124, -4000, -121,
		        -500, -188, -20, -500, -2500, -232, -5500, -8000, -3896, -2500, -303, -5797, -1000, -11759, -7198.85f, -8399 };
		float[] sum = new float[] { 2450, 50, 110, 1200, -300, 1990, -500, -500, -250, -200, -580, 920, 2000, 3305, -128, -200, 7000, 300, -9000,
		        5000, -2000, 2000, -700, -100, -634, -174, 3500, 492, 2600, -2600, 2400, -100, -600, -1650, -200, 14000, -2000, -2000, -1500, -5000,
		        -5000, 6000, -8355, 10000, 5000, -330, -3000, 2989, -200, -500, 10000, 2000, -110, -1000, 14914, -1200, -19000, 10000, -4700, 9000,
		        -200, -6000, -200, 10, -5, -700, -8000, 8000, 5000, 4000, -150, -100, -1000, -400, -500, -5000, 5000, -100, 5000, 3900, -1500, -5,
		        -1000, 100, -12800, -320, -1000, 10000, 1000.79f, 9000, -10000, -9000, 7100, -124, -4000, -121, -500, -188, -20, 9000, -500, -2500,
		        -232, -5500, 500, 1000, -8000, -3896, 10000, 4000, 3000, 9201, -2500, 190, -303, -5797, -1000, 12000, 1000, -11759, -7198.85f, -8399 };
		float[] zqmr = new float[] { 1270, 1239, 1210, 1080, 1088, 975, 1130, 3135, 1010, 1682, 2090, 3680, 2546, 2300, 2452, 945, 2997, 1940, 3040,
		        3936, 2760, 951, 1936, 2444, 2164, 3364, 2164, 4700, 5556, 4065, 4096, 5405, 4725, 4075, 4555, 3687, 3960, 4855, 4851, 4510, 5195,
		        4530, 8703, 8338, 8024, 734, 7655, 8397, 3284, 1646, 7770, 2424, 3920, 7875, 2919, 7170, 7080, 6366, 7788, 6930, 5480, 5680, 5630,
		        6588, 5934, 6825, 7200, 6181, 5940, 7445, 4912, 7660, 11716, 5970, 5440, 7000, 4728, 3930, 3444, 3990, 7322, 3630, 5456, 4722, 3805,
		        6315, 6225, 4165, 5840, 7784, 6195, 5538, 5925, 5454, 6990, 7420, 11832, 12429, 11600, 5650, 4125, 4290, 4245, 7200, 8020, 7470,
		        1815, 5050, 5380, 5450, 4437, 4860, 2265, 3805, 4656, 3645, 3360, 2230, 5680, 5300, 5140, 3081, 2460, 5175, 3765, 3726, 4149, 3225,
		        3942, 4930, 4689, 5115, 4704, 6270, 2482, 8090, 7158, 5040, 5310, 8360, 3150, 5330, 5240, 5200, 3525, 3840, 3532, 4008, 3680, 3680,
		        3620, 5742, 5721, 4855, 8307, 1836, 6450, 8110, 8070, 4176, 3860, 3894, 6175, 4136, 5590, 8504, 7996, 7420, 6246, 4803, 4620, 1719,
		        9276, 9702, 6250, 4975, 3736, 9430, 6552, 9000, 7998, 8799, 7908, 8280, 3864, 3825, 8390, 7416, 7505, 7650, 7014, 8073, 6923, 5550,
		        2725, 5400, 8001, 4430, 8985, 5750, 5565, 5070, 5650, 4525, 7450, 3600, 5745, 2282, 4434, 4533, 3915, 4140, 4128, 6010, 5400, 4012,
		        4000, 3712, 4299, 3903, 6195, 4880, 2782, 5076, 4995, 4926, 4635, 7360, 5280, 4196, 3138, 6840, 5640, 5628, 8701, 3935, 6608, 5432,
		        2949, 974, 3592, 6285, 7560, 5180, 7795, 6216, 4720, 8091, 5070, 8320, 5990, 7605, 8772, 5887, 2076, 5982.64f, 8136, 8994, 2750,
		        4119, 9500, 5135, 5125, 7715, 6100, 3000, 5960, 5960, 14976, 33600, 16020, 15408, 14260, 10710, 6950, 10410, 28520, 13810, 17206,
		        12680, 6061, 13302, 4972, 12150, 5844, 9792, 6064, 8490, 8240, 9570, 5886, 6070, 4065, 6975, 4440, 4700, 7130, 4990, 7779, 8128,
		        11187, 6758, 8748, 9600, 5796, 7227, 5469, 8939, 6416, 8877, 9016, 9891, 8750, 4655, 4640, 1840, 8136, 19200, 5860, 11980, 6444,
		        11128, 7100, 4760, 6600, 6664, 6468, 10255, 6096, 6210, 10591, 8659, 7448, 7091, 3780, 3435, 3135, 5655, 7288, 3498, 4660, 3845,
		        3815, 6780, 8840, 8716, 6879, 9310, 7452, 8224, 9006, 7278, 8514, 7900, 8110, 6744, 7172, 7335, 7269, 8445, 9852, 5030, 5700, 8180,
		        13328, 3200, 6188, 3480, 5301, 1735, 6888, 5185, 2778, 2099, 2460, 3514, 3484, 3783, 712, 2756, 861, 1963, 1969, 3564, 2169, 1164,
		        2343, 2886, 1730, 3206, 2971, 1872, 2317, 2310, 1703, 1697, 2718, 2102, 3930, 2140, 2058, 2304, 2306, 4146, 5040, 2432, 4090, 2222,
		        4872, 3042, 2154, 3180, 2154, 3592, 2276, 2396, 2510, 4013, 2502, 2528, 2718, 2700, 2093, 1832, 2930, 2388, 3130, 1804, 2872, 2511,
		        3398, 2890, 2805, 583, 2950, 4705, 2930, 2930, 1892, 5490, 668, 2710, 3496, 2418, 5621, 2534, 3795, 3675, 2650, 2719, 4479, 9784,
		        12348, 6336, 3720, 3678, 6772, 7200, 5420, 3504, 5984, 2940, 5666, 2420, 3820, 3055, 3065, 7228, 2678, 2918, 1908, 7515, 4422, 4371,
		        4780, 2736, 3652, 3735, 4209, 5510, 6756, 1106, 2204, 3330, 5970, 6080, 7120, 6236, 6145, 2026, 4832, 5094, 5760, 5705, 5115, 3095,
		        4389, 3420, 5454, 4407, 906, 6734, 3141, 2824, 1415, 7090, 5235, 8970, -1275, -1244, -1215, -1085, -1093, -980, -1135.06f, -3141.27f,
		        -1015, -1687.12f, -2095, -3687.6f, -2551.09f, -2305.12f, -2457, -950, -3002.99f, -1945, -3046.08f, -3944.11f, -2765.64f, -956,
		        -1941.12f, -2449, -2169.24f, -3370.97f, -2169.24f, -4709.4f, -5567.11f, -4073.13f, -4104.19f, -5415.81f, -4734.45f, -4083.15f,
		        -4564.11f, -3694.55f, -3967.92f, -4865.01f, -4860.7f, -4519.02f, -5205.39f, -4539.36f, -8720.41f, -8354.68f, -8040.05f, -739.06f,
		        -7670.31f, -8414.33f, -3290.81f, -1651.12f, -7785.54f, -2429.18f, -3928.26f, -7891.29f, -2925.02f, -7184.7f, -7094.52f, -6378.73f,
		        -7803.94f, -6943.86f, -5490.96f, -5691.36f, -5641.26f, -6601.54f, -5945.87f, -6838.65f, -7215f, -6193.36f, -5951.88f, -7459.89f,
		        -4921.82f, -7675.32f, -11739.9f, -5981.94f, -5450.88f, -7014.42f, -4737.7f, -3938.04f, -3450.89f, -3997.98f, -7336.76f, -3637.32f,
		        -5466.91f, -4731.44f, -3812.91f, -6327.63f, -6237.45f, -4173.33f, -5851.68f, -7800.05f, -6207.69f, -5549.08f, -5937.15f, -5464.91f,
		        -7003.98f, -7434.84f, -11855.7f, -12453.9f, -11623.2f, -5661.6f, -4133.55f, -4298.58f, -4253.49f, -7214.4f, -8036.34f, -7484.94f,
		        -1820f, -5060.1f, -5391.06f, -5460.9f, -4445.87f, -4869.72f, -2270f, -3812.61f, -4665.79f, -3652.59f, -3366.72f, -2235, -5691.66f,
		        -5310.6f, -5150.28f, -3087.34f, -2465f, -5185.35f, -3772.53f, -3733.45f, -4157.3f, -3231.45f, -3949.88f, -4939.86f, -4698.38f,
		        -5125.23f, -4713.41f, -6282.84f, -2487.12f, -8106.18f, -7172.5f, -5050.08f, -5320.62f, -8376.72f, -3156.36f, -5340.96f, -5250.72f,
		        -5210.4f, -3532.05f, -3847.86f, -3539.06f, -4016.2f, -3687.36f, -3687.36f, -3627.24f, -5753.48f, -5732.44f, -4864.71f, -8323.61f,
		        -1841f, -6463.5f, -8126.82f, -8086.74f, -4184.59f, -3867.96f, -3901.97f, -6187.65f, -4144.51f, -5601.48f, -8521.49f, -8012.23f,
		        -7435.68f, -6258.85f, -4812.79f, -4629.6f, -1724.18f, -9295.27f, -9721.82f, -6262.8f, -4985.25f, -3743.71f, -9449.46f, -6565.52f,
		        -9018.54f, -8014.18f, -8817.02f, -7924.54f, -8297.16f, -3871.91f, -3832.83f, -8407.38f, -7431.19f, -7520.31f, -7665.66f, -7028.45f,
		        -8089.69f, -6937.27f, -5561.7f, -2730.75f, -5411.4f, -8017.54f, -4439.16f, -9003.27f, -5761.5f, -5576.55f, -5080.44f, -5661.6f,
		        -4534.35f, -7464.9f, -3607.5f, -5756.79f, -2287.12f, -4443.05f, -4542.07f, -3922.83f, -4148.46f, -4136.44f, -6022.32f, -5411.16f,
		        -4020.26f, -4008.24f, -3719.66f, -4307.78f, -3910.81f, -6207.39f, -4889.76f, -2787.68f, -5086.15f, -5004.99f, -4935.85f, -4644.45f,
		        -7375.02f, -5290.86f, -4204.63f, -3144.46f, -6854.04f, -5651.4f, -5639.38f, -8719.06f, -3943.17f, -6621.46f, -5443.28f, -2955.08f,
		        -979.06f, -3599.42f, -6297.87f, -7575.48f, -5190.36f, -7810.89f, -6228.67f, -4729.74f, -8107.18f, -5080.14f, -8337.24f, -6002.58f,
		        -7620.51f, -8792.12f, -5900.51f, -2081.12f, -5995.01f, -8152.81f, -9012.11f, -2755.62f, -4127.42f, -9519.3f, -5145.57f, -5135.55f,
		        -7730.73f, -6112.2f, -3006, -5971.92f, -5971.92f, -15006, -33673.2f, -16052, -15438.8f, -14289.7f, -10732, -6964.5f, -10431.7f,
		        -28578.4f, -13837.6f, -17240.4f, -12707.8f, -6074.26f, -13329.7f, -4981.94f, -12174.8f, -5855.69f, -9812.12f, -6076.61f, -8507.58f,
		        -8257.08f, -9589.74f, -5897.77f, -6082.14f, -4073.43f, -6988.95f, -4449.18f, -4709.7f, -7144.26f, -5000.28f, -7794.74f, -8144.74f,
		        -11209.6f, -6771.64f, -8766.04f, -9619.8f, -5807.77f, -7242.11f, -5479.94f, -8957.3f, -6428.83f, -8894.75f, -9034.27f, -9910.96f,
		        -8768.1f, -4664.61f, -4649.58f, -1845.12f, -8152.81f, -19238.4f, -5871.72f, -12004, -6457.61f, -11150.7f, -7114.5f, -4770.12f,
		        -6613.92f, -6678.17f, -6481.36f, -10275.9f, -6108.43f, -6222.42f, -10612.2f, -8676.32f, -7462.9f, -7105.6f, -3787.56f, -3441.87f,
		        -3141.27f, -5666.31f, -7302.58f, -3505f, -4669.32f, -3852.99f, -3822.93f, -6793.86f, -8857.68f, -8733.43f, -6892.76f, -9328.62f,
		        -7466.9f, -8240.69f, -9024.37f, -7292.92f, -8531.39f, -7915.8f, -8126.22f, -6757.49f, -7186.46f, -7349.67f, -7283.54f, -8461.89f,
		        -9871.7f, -5040.36f, -5711.76f, -8196.36f, -13354.7f, -3206.7f, -6200.38f, -3486.96f, -5311.6f, -1740f, -6901.78f, -5195.67f,
		        -2783.74f, -2104.06f, -2465f, -3521.15f, -3491.09f, -3790.63f, -717.12f, -2761.51f, -866f, -1968.06f, -1974.06f, -3571.13f, -2174f,
		        -1169f, -2348.06f, -2891.83f, -1735.06f, -3212.41f, -2977f, -1877.06f, -2322f, -2315f, -1708.06f, -1702.06f, -2723.56f, -2107.12f,
		        -3938.04f, -2145.12f, -2063f, -2309.24f, -2311.12f, -4154.47f, -5050.08f, -2437.12f, -4098.18f, -2227f, -4881.74f, -3048.26f, -2159f,
		        -3186.36f, -2159.18f, -3599.3f, -2281f, -2401f, -2515.02f, -4021.09f, -2507.12f, -2533.12f, -2723.56f, -2705.52f, -2098f, -1837.12f,
		        -2936.16f, -2393.24f, -3136.26f, -1809.12f, -2877.86f, -2516.02f, -3404.92f, -2896.08f, -2810.79f, -588.06f, -2956.02f, -4714.71f,
		        -2935.98f, -2935.98f, -1897.12f, -5501.28f, -673.12f, -2715.42f, -3502.99f, -2423.18f, -5632.66f, -2539.07f, -3802.59f, -3682.77f,
		        -2655.36f, -2724.5f, -4488.14f, -9803.57f, -12372.7f, -6348.91f, -3727.44f, -3685.36f, -6785.78f, -7214.4f, -5430.84f, -3511.37f,
		        -5996.03f, -2945.94f, -5677.33f, -2425f, -3827.88f, -3061.41f, -3071.43f, -7242.46f, -2683.48f, -2923.96f, -1913.18f, -7530.33f,
		        -4430.84f, -4379.92f, -4789.86f, -2741.65f, -3659.3f, -3742.47f, -4217.42f, -5521.14f, -6769.63f, -1111f, -2209f, -3336.66f,
		        -5981.94f, -6092.4f, -7134.36f, -6248.71f, -6157.29f, -2031.12f, -4841.66f, -5104.19f, -5771.82f, -5716.71f, -5125.41f, -3101.49f,
		        -4397.78f, -3426.9f, -5465.27f, -4415.99f, -911.06f, -6747.47f, -3147.46f, -2829.65f, -1420f, -7104.48f, -5245.47f, -8987.94f };
		float[] zqmc_1 = new float[] { 3408, 3210, 1145, 4012, 1736, 2170, 3416, 2610, 2294, 940, 2346, 3010, 3824, 2690, 1934, 5670, 2440, 3316,
		        4112, 4080, 5240, 5305, 4615, 4620, 4055, 3760, 4500, 3564, 4028, 4900, 4896, 5190, 4360, 4700, 8730, 8239, 8568, 882, 7810, 8451,
		        2433, 1708, 3432, 7920, 7641, 2871, 4046, 6972, 6360, 7656, 7644, 5372, 5644, 7392, 5710, 6576, 7134, 6526, 6970, 6139, 5838, 7345,
		        6828, 4800, 11864, 5940, 5496, 4436, 6468, 3882, 3200, 4193, 11124, 4620, 3735, 5340, 12880, 4300, 5904, 6000, 7664, 5712, 5910,
		        5310, 6350, 11685, 11799, 7580, 11500, 4010, 5430, 4320, 4285, 7160, 7995, 7461, 1750, 4770, 5500, 4670, 4347, 5360, 4408, 5864,
		        3465, 5540, 5360, 5200, 5272, 3165, 2502, 5075, 3654, 3650, 3978, 4059, 5050, 4683, 5045, 7932, 8631, 8165, 4960, 5226, 7185, 3040,
		        8156, 5350, 5184, 5450, 3861, 3490, 7290, 4023, 4840, 11196, 7140, 9779, 4040, 3748, 6590, 3885, 6175, 7970, 7970, 7792, 6414, 5645,
		        6300, 4590, 4668, 5590, 3913, 6636, 8964, 9618, 6140, 9180, 10065, 6944, 7941, 6846, 9142, 7656, 8080, 8190, 6972, 7566, 7578, 7300,
		        13510, 7857, 5170, 7725, 12250, 6000, 5418, 9680, 5455, 3535, 2605.98f, 2461.59f, 8015, 4194, 4840, 7500, 7476, 3906, 5940, 4940,
		        2952, 5532, 3656, 10323, 3777, 15092, 14616, 7575, 6264, 6234, 6858, 11248, 4848, 6532, 14820, 3128, 3848, 13662, 3632, 5295, 13770,
		        4575, 5085, 7995, 7950, 5920, 7345, 14328, 2010, 1430.08f, 12236, 6810, 9146, 7620, 9900, 9475, 35636, 16150, 1615, 14535, 15600,
		        15660, 10300, 1346, 28767, 30590, 13730, 18060, 5985, 12560, 13086, 4792, 11952, 5940, 10890, 6736, 16660, 6180, 7400, 4060, 9970,
		        6990, 9510, 7350, 5185, 7776, 7779, 15200, 6033, 6952, 5493, 991, 7944, 9980, 8729, 9144, 9435, 7960, 6360, 8770, 18690, 18432, 6000,
		        11920, 6384, 11640, 6480, 7080, 10992, 6377, 9996, 6056, 9450, 7560, 7216, 7126, 8596, 6864, 3537, 5580, 7384, 7945, 7540, 6480,
		        9224, 9124, 6699, 16479, 8760, 8228, 7584, 7080, 8095, 7256, 8145, 7178, 2466, 12010, 8360, 10002, 4710, 7096, 5175, 6584, 6578,
		        3125, 6034, 15610, 4795, 6856, 2105, 3708, 3876, 656, 851, 2728, 2485, 3880, 4496, 2102, 2934, 1750, 2404, 3185, 2936, 1857, 4600,
		        3438, 2088, 2134, 2730, 4110, 2074, 2254, 4092, 2216, 5170, 2290, 4080, 4836, 2270, 2955, 3124, 2094, 1965, 3582, 2378, 2236, 3871,
		        2509, 2688, 2524, 2069, 1824, 2706, 3050, 2746, 3340, 3194, 3300, 4655, 3480, 2500, 4500, 4482, 4715, 5540, 1990, 676, 2804, 3500,
		        7830, 6335, 5540, 4431, 3500, 4904, 5301, 6296, 8897, 10800, 6694, 7050, 5424, 5780, 3468, 2732, 5300, 3792, 6000, 2582, 7332, 2700,
		        1905, 3002, 4502, 2718, 4590, 4212, 4050, 3402, 3710, 8105, 2822, 2808, 6570, 7190, 5829, 5756, 7190, 6124, 6040, 2292, 5326, 4995,
		        5665, 5650, 4245, 5018, 3285, 3324, 6825, 4737, 7777, 4401, 3486, 7175, 5574, 8430 };

		float[] zqmc_2 = new float[] { 3397.77f, 3200.37f, 1138.79f, 3999.97f, 1729.14f, 2162.83f, 3405.51f, 2602.17f, 2286.58f, 934.06f, 2338.65f,
		        3000.97f, 3812.29f, 2681.81f, 1926.95f, 5652.99f, 2432.56f, 3305.81f, 4099.19f, 4067.76f, 5224.28f, 5289.08f, 4601.15f, 4606.14f,
		        4042.83f, 3748.72f, 4486.5f, 3553.13f, 4015.91f, 4885f, 4881.31f, 5174.43f, 4346.62f, 4685.9f, 8703.81f, 8214.28f, 8542.29f, 876.06f,
		        7786.57f, 8425.11f, 2425.39f, 1701.17f, 3421.47f, 7896.24f, 7617.54f, 2862.21f, 4033.44f, 6950.73f, 6340.92f, 7632.67f, 7620.71f,
		        5355.89f, 5627.07f, 7369.83f, 5692.87f, 6555.91f, 7112.6f, 6506.42f, 6948.49f, 6120.58f, 5820.48f, 7322.97f, 6807.51f, 4785.6f,
		        11827.93f, 5922.18f, 5479.51f, 4422.45f, 6448.18f, 3870.18f, 3190.4f, 4180.42f, 11090.45f, 4606.14f, 3723.49f, 5323.98f, 12841.36f,
		        4287.1f, 5886.29f, 5981.7f, 7640.53f, 5694.87f, 5891.97f, 5294.07f, 6330.94f, 11649.94f, 11763.6f, 7557.26f, 11465.5f, 3997.67f,
		        5413.41f, 4307.04f, 4272.14f, 7138.52f, 7970.71f, 7438.62f, 1743.25f, 4755.69f, 5483.5f, 4655.99f, 4333.96f, 5343.62f, 4394.29f,
		        5846.41f, 3454.31f, 5523.08f, 5343.92f, 5184.4f, 5256.19f, 3155.32f, 2494.5f, 5059.77f, 3643.04f, 3639.05f, 3966.06f, 4046.82f,
		        5034.85f, 4668.95f, 5029.86f, 7908.21f, 8604.69f, 8140.5f, 4945.12f, 5210.32f, 7163.26f, 3030.82f, 8131.53f, 5333.65f, 5168.21f,
		        5433.65f, 3849.24f, 3479.53f, 7268.13f, 4010.75f, 4825.48f, 11162.41f, 7118.58f, 9749.66f, 4027.64f, 3736.51f, 6569.63f, 3873.16f,
		        6156.17f, 7945.49f, 7945.49f, 7768.39f, 6394.4f, 5627.76f, 6280.74f, 4575.87f, 4653.81f, 5572.63f, 3900.84f, 6615.73f, 8936.38f,
		        9588.72f, 6121.28f, 9151.86f, 10034.14f, 6922.75f, 7917f, 6825.04f, 9114.16f, 7632.31f, 8055.16f, 8164.83f, 6950.73f, 7542.94f,
		        7554.9f, 7277.8f, 13468.62f, 7832.89f, 5153.89f, 7700.92f, 12212.41f, 5982f, 5401.32f, 9650.66f, 5438.33f, 3524.09f, 2598.01f,
		        2453.98f, 7990.53f, 4181.24f, 4825.18f, 7477.49f, 7453.57f, 3894.28f, 5921.88f, 4924.88f, 2942.97f, 5515.05f, 3644.79f, 10292.03f,
		        3765.67f, 15046.07f, 14572.15f, 7551.97f, 6244.84f, 6214.94f, 6837.06f, 11214.01f, 4833.27f, 6512.17f, 14774.4f, 3118.37f, 3836.21f,
		        13620.36f, 3620.87f, 5279.11f, 13728.15f, 4560.97f, 5069.74f, 7971.01f, 7925.55f, 5901.64f, 7322.66f, 14280.69f, 2002.87f, 1423.55f,
		        12198.45f, 6789.27f, 9118.44f, 7596.84f, 9869.7f, 9446.27f, 35529.09f, 16098.55f, 1608.08f, 14488.69f, 15553.2f, 15613.02f, 10268.5f,
		        1339.53f, 28678.12f, 30496.85f, 13688.81f, 18005.82f, 5965.9f, 12519.92f, 13045.66f, 4777.63f, 11915.61f, 5922.18f, 10856.79f,
		        6715.31f, 16608.82f, 6161.46f, 7377.8f, 4047.52f, 9939.49f, 6969.03f, 9480.87f, 7327.95f, 5169.14f, 7752.19f, 7755.48f, 15154.1f,
		        6014.72f, 6930.49f, 5476.52f, 984.95f, 7919.69f, 9949.46f, 8702.39f, 9116.57f, 9406.51f, 7935.88f, 6340.92f, 8743.09f, 18632.67f,
		        18376.71f, 5982f, 11884.24f, 6364.13f, 11604.59f, 6459.84f, 7058.46f, 10957.59f, 6357.45f, 9965.59f, 6037.59f, 9421.65f, 7537.32f,
		        7194.36f, 7104.2f, 8570.21f, 6843.41f, 3526.39f, 5563.26f, 7361.85f, 7921.16f, 7516.78f, 6460.26f, 9196.33f, 9096.63f, 6678.9f,
		        16429.56f, 8733.36f, 8203.07f, 7560.89f, 7058.4f, 8070.71f, 7234.11f, 8120.56f, 7156.46f, 2458.53f, 11973.97f, 8334.92f, 9972f,
		        4695.57f, 7074.71f, 5159.17f, 6564.25f, 6558.26f, 3115.32f, 6015.9f, 15563.17f, 4780.31f, 6835.19f, 2097.83f, 3696.81f, 3864.13f,
		        650.22f, 845.15f, 2719.81f, 2477.51f, 3868.24f, 4482.51f, 2094.9f, 2925.14f, 1743.19f, 2396.54f, 3175.44f, 2927.13f, 1850.08f,
		        4586.2f, 3427.56f, 2080.79f, 2126.75f, 2721.69f, 4097.49f, 2066.93f, 2246.63f, 4079.55f, 2208.54f, 5154.49f, 2282.59f, 4067.76f,
		        4821.49f, 2262.73f, 2945.95f, 3114.63f, 2086.91f, 1957.85f, 3571.14f, 2370.62f, 2228.76f, 3859.33f, 2501.47f, 2679.81f, 2516.37f,
		        2061.93f, 1817.06f, 2697.76f, 3040.55f, 2737.64f, 3329.86f, 3184.42f, 3289.98f, 4640.73f, 3469.2f, 2492.26f, 4486.32f, 4468.38f,
		        4700.55f, 5523.08f, 1982.89f, 670.2f, 2795.59f, 3489.5f, 7805.91f, 6315.99f, 5523.26f, 4417.53f, 3489.08f, 4889.29f, 5285.1f,
		        6276.87f, 8870.31f, 10767.6f, 6673.67f, 7028.85f, 5407.73f, 5762.6f, 3457.23f, 2723.75f, 5284.1f, 3780.39f, 5981.4f, 2574.26f,
		        7310.01f, 2691.78f, 1897.91f, 2992.88f, 4488.5f, 2709.66f, 4575.93f, 4199.19f, 4037.85f, 3391.8f, 3698.87f, 8080.38f, 2813.48f,
		        2799.51f, 6550.29f, 7168.31f, 5811.51f, 5738.49f, 7168.31f, 6105.39f, 6021.88f, 2284.59f, 5310.02f, 4979.83f, 5647.7f, 5632.75f,
		        4232.26f, 5002.94f, 3274.84f, 3313.97f, 6804.1f, 4722.61f, 7753.67f, 4387.8f, 3475.36f, 7153.17f, 5557.28f, 8404.71f };
		float s1 = 0;
		float s2 = 0;
		for (float f : yzz) {
			s1 += f;
		}
		for (float f : zzy) {
			s2 += f;
		}
		float s = 0;
		for (float f : sum) {
			s += f;
		}
		System.out.println("s1=" + s1);
		System.out.println("s2=" + s2);
		System.out.println(s1 + s2);
		System.out.println(s);
		float mr = 0;
		for (float f : zqmr) {
			mr += f;
		}
		System.out.println("zqmr：" + mr);
		float mc_1 = 0;
		for (float f : zqmc_1) {
			mc_1 += f;
		}
		float mc_2 = 0;
		for (float f : zqmc_2) {
			mc_2 += f;
		}
		System.out.println("zqmc：" + mc_1 + ", " + mc_2 + ", " + (mc_1 - mc_2));
		System.out.println("(mc_1 - mc_2)--" + ((mc_1 - mc_2) - mr));

		System.out.println(DateUtil.formatDate(new Date(DateUtil.getCurrentDateSec())));
	}

	/**
	 * 接收支付购买结果
	 * 
	 * @param appid
	 * @param act
	 * @param productName
	 * @param consumeStreamId
	 * @param cooOrderSerial
	 * @param uin
	 * @param goodsId
	 * @param goodsInfo
	 * @param goodsCount
	 * @param originalMoney
	 * @param orderMoney
	 * @param note
	 * @param payStatus
	 * @param createTime
	 * @param fromSign
	 * @return 支付结果
	 * @throws UnsupportedEncodingException
	 */
	public int payResultNotify(String appid, String act, String productName, String consumeStreamId, String cooOrderSerial, String uin,
	        String goodsId, String goodsInfo, String goodsCount, String originalMoney, String orderMoney, String note, String payStatus,
	        String createTime, String fromSign) throws UnsupportedEncodingException {

		StringBuilder strSign = new StringBuilder();
		strSign.append(appid);
		strSign.append(act);
		strSign.append(productName);
		strSign.append(consumeStreamId);
		strSign.append(cooOrderSerial);
		strSign.append(uin);
		strSign.append(goodsId);
		strSign.append(goodsInfo);
		strSign.append(goodsCount);
		strSign.append(originalMoney);
		strSign.append(orderMoney);
		strSign.append(note);
		strSign.append(payStatus);
		strSign.append(createTime);
		strSign.append(APP_KEY);
		String sign = md5(strSign.toString());

		if (!this.APP_ID_VALUE.equals(appid)) {
			return 2; // appid无效
		}
		if (!"1".equals(act)) {
			return 3; // Act无效
		}
		if (!sign.toLowerCase().equals(fromSign.toLowerCase())) {
			return 5; // sign无效
		}
		int payResult = -1;
		if ("1".equals(payStatus)) {
			try {
				if (queryPayResult(cooOrderSerial) == 1) {
					payResult = 1; // 有订单
				} else {
					payResult = 11; // 无订单
				}
			} catch (Exception e) {
				payResult = 6; // 自定义：网络问题
			}
			return payResult;
		}
		return 0; // 错误
	}

	/**
	 * 查询支付购买结果的API调用
	 * 
	 * @param cooOrderSerial
	 *            商户订单号
	 * @return ERRORCODE的值
	 * @throws Exception
	 *             API调用失败
	 */
	public int queryPayResult(String cooOrderSerial) throws Exception {
		String act = "1";
		StringBuilder strSign = new StringBuilder();
		strSign.append(APP_ID_VALUE);
		strSign.append(act);
		strSign.append(cooOrderSerial);
		strSign.append(APP_KEY);
		String sign = md5(strSign.toString());
		StringBuilder getUrl = new StringBuilder();
		getUrl.append("Appid=");
		getUrl.append(APP_ID_VALUE);
		getUrl.append("&Act=");
		getUrl.append(act);
		getUrl.append("&CooOrderSerial=");
		getUrl.append(cooOrderSerial);
		getUrl.append("&Sign=");
		getUrl.append(sign);
		return GetResult(HttpGetGo(getUrl.toString()));
	}

	/**
	 * 获取91服务器返回的结果
	 * 
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	private int GetResult(String jsonStr) throws Exception {
		// Pattern p = Pattern.compile("(?<=\"ErrorCode\":\")\\d{1,3}(?=\")");
		// Matcher m = p.matcher(jsonStr);
		// m.find();
		// return Integer.parseInt(m.group());

		// 这里需要引入JSON-LIB包内的JAR
		JSONObject jo = JSONObject.fromObject(jsonStr);
		return Integer.parseInt(jo.getString("ErrorCode"));
	}

	/**
	 * 发送GET请求并获取结果
	 * 
	 * @param getUrl
	 * @return
	 * @throws Exception
	 */
	private String HttpGetGo(String getUrl) throws Exception {
		StringBuffer readOneLineBuff = new StringBuffer();
		String content = "";
		URL url = new URL(goUrl + getUrl);
		URLConnection conn = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			readOneLineBuff.append(line);
		}
		content = readOneLineBuff.toString();
		reader.close();
		return content;
	}

	/**
	 * 对字符串进行MD5并返回结果
	 * 
	 * @param sourceStr
	 * @return
	 */
	private String md5(String sourceStr) {
		String signStr = "";
		try {
			byte[] bytes = sourceStr.getBytes("utf-8");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(bytes);
			byte[] md5Byte = md5.digest();
			if (md5Byte != null) {
				signStr = HexBin.encode(md5Byte);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signStr;
	}


	static int[] a = new int[1];

	static public int t() {
		return a[0]++;
	}

	public static void testInteger(Integer src) {
		src = 9;
	}

	/** ID前4位存在非零的则是角色ID */
	public final static int NOT_ROLE = 0xF0000000;
	/** ID左侧第3为为1则是怪物（下标从0开始【0x1*******】） */
	public final static int MONSTER  = 0x10000000;
	/** ID左侧第2为为1则是npc（下标从0开始【0x2*******】） */
	public final static int NPC      = 0x20000000;
	/** ID左侧第为0则是goods（下标从0开始【0x3*******】） */
	public final static int GOODS    = 0x30000000;
	/** ID左侧第为4则是系统生成的灵兽（下标从0开始【0x4*******】） */
	public final static int PET      = 0x40000000;
	/** 由技能创建的灵兽编号生成因子 */
	public final static int bbPet    = 0x50000000;

	static void testNum() {
		AtomicInteger ai = new AtomicInteger(bbPet);
		int v = ai.incrementAndGet();
		System.out.println("v = " + v);
		System.out.println((v & bbPet) == bbPet);
	}

	public static void testMap() {
		System.out.println("test-map");
		Map<Integer, String> map = new HashMap<Integer, String>();
		System.out.println("空值：" + map);
		map.put(1, "1");
		System.out.println("放入<k:1, v:abc> -- " + map);
		map.put(2, "2");
		System.out.println("放入<k:2, v:abc> -- " + map);
		map.put(3, "3");
		System.out.println("放入<k:1, v:abc> -- " + map);
		map.put(4, "4");
		System.out.println("放入<k:3, v:def> -- " + map);

		System.out.println("map 1= " + map);

		Map<Integer, String> clone = new HashMap<Integer, String>();
		clone.putAll(map);
		map.put(1, "11");
		map.put(2, "22");
		System.out.println("map 2= " + map);
		System.out.println("clone 3= " + clone);
	}

	public static void testAtomicInteger() {
		final AtomicInteger ato = new AtomicInteger(1);

		int pre = 0, cur = 0;
		int i = 0;
		while (i < 999999999) {
			cur = ato.getAndIncrement();
			pre = cur;
			i++;
		}
	}

	public static final void testPacketBuffer() {
		// PacketBuffer pac = PacketBufferPool.getPacketBuffer();
		int count = 0;
		long endTime = System.currentTimeMillis() + 1000;
		IoBuffer pb = null;
		while (System.currentTimeMillis() < endTime) {
			pb = PacketBufferPool.getPacketBuffer();
			// pb.setProperty((short) 1, (byte) 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, "阿里等级分类");
			// pb.setProperty((short) 1, "阿伦多夫家乐福");
			// pb.setProperty((short) 1, (byte) 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, "阿里等级分类");
			// pb.setProperty((short) 1, "阿伦多夫家乐福");
			// pb.setProperty((short) 1, (byte) 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			pb.setProperty((short) 1, "阿里等级分类");
			pb.setProperty((short) 1, "阿伦多夫家乐福");
			// pb.setProperty((short) 1, (byte) 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, 2);
			// pb.setProperty((short) 1, StringSplit.transformString( "阿伦多夫家乐福"));
			// pb.setProperty((short) 1, StringSplit.transformString( "阿伦多夫家乐福"));
			PacketBufferPool.freePacketBuffer(pb);
			count++;
		}
		System.out.println(count);
	}

	final static AtomicInteger         ato    = new AtomicInteger(1);
	final static Map<Integer, Integer> values = new HashMap<Integer, Integer>();

	public static void testAtomicIntegerMap() {
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					while (true) {
						int value = ato.getAndIncrement();
						if (values.containsKey(value)) {
						} else {
							values.put(value, value);
						}
						System.out.println(value);
						if (value > 888888888) {
							return;
						}
						try {
							Thread.sleep(3);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
	}

	public static void testPacket() {
		IoBuffer packet = PacketBufferPool.getPacketBuffer();
		packet.putInt(1);
		packet.put((byte) 2);
		packet.putShort((short) 3);
		// packet.putString("阿道夫");
		packet.putInt(4);
		packet.flip();
		byte[] array = new byte[packet.limit()];
		System.arraycopy(packet.array(), 0, array, 0, packet.limit());
		System.out.println("----------------------------------");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
		System.out.println("----------------------------------");
		String string = ByteOrStringHelper.ByteToString(array);
		System.out.println("tostring =" + string);

		PacketBufferPool.freePacketBuffer(packet);
		packet = PacketBufferPool.getPacketBuffer();

		packet.putString(string);
		packet.flip();
		string = packet.getString();
		array = string.getBytes();// ByteOrStringHelper.StringToByte(string);
		System.out.println("----------------------------------");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
		System.out.println("----------------------------------");
		System.out.println("getstring=" + string);

		PacketBufferPool.freePacketBuffer(packet);
		packet = PacketBufferPool.getPacketBuffer();
		System.arraycopy(array, 0, packet.array(), 0, array.length);
		packet.position(array.length);
		packet.flip();
		System.out.println("int 1 ==  " + packet.getInt());
		System.out.println("byte 2 ==  " + packet.get());
		System.out.println("short 3 ==  " + packet.getShort());
		// System.out.println("String 阿道夫 ==  " + packet.getString());
		System.out.println("int 4 ==  " + packet.getInt());
		System.out.println("000000000000000000000000000000");
		packet = PacketBufferPool.getPacketBuffer();
		packet.put((byte) 1);
		packet.putShort((short) 2);
		packet.putInt(3);
		packet.putLong(4);
		packet.putString("1234地点");
		packet.putBoolean(true);
		packet.flip();
		IoBuffer tmp = packet.duplicate();
		System.out.println(packet.get());
		System.out.println(packet.getShort());
		System.out.println(packet.getInt());
		System.out.println(packet.getLong());
		System.out.println(packet.getString());
		System.out.println(packet.getBoolean());
		System.out.println(packet.limit());
		System.out.println("1111111111111111111111111111111");
		System.out.println(tmp.get());
		System.out.println(tmp.getShort());
		System.out.println(tmp.getInt());
		System.out.println(tmp.getLong());
		System.out.println(tmp.getString());
		System.out.println(tmp.getBoolean());
		System.out.println(tmp.limit());

	}

	public static void testIoBuffer() {
		IoBuffer ib = IoBuffer.allocate(8);
		ib.setAutoExpand(true);
		ib.putInt(4);
		ib.put((byte) 1);
		ib.putInt(5);
		ib.put((byte) 7);
		putString(ib, "阿里士大夫就啦的");
		// try {
		// ib.putString("阿里士大夫就啦的", Charset.forName("UTF-8").newEncoder());
		// } catch (CharacterCodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		ib.flip();
		DataInputStream di = new DataInputStream(new ByteArrayInputStream(ib.array(), 0, ib.limit()));

		try {
			System.out.println(di.readInt());
			System.out.println(di.readByte());
			System.out.println(di.readInt());
			System.out.println(di.readByte());
			System.out.println(di.readUTF());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("--------------------");

		IoBuffer packet = PacketBufferPool.getPacketBuffer();
		packet.put(ib.array(), 0, ib.limit());
		packet.flip();
		System.out.println(packet.getInt());
		System.out.println(packet.get());
		System.out.println(packet.getInt());
		System.out.println(packet.get());
		System.out.println(packet.getString());

		System.out.println(ib.getInt());
		System.out.println(ib.get());
		System.out.println(ib.getInt());
		System.out.println(ib.get());
		System.out.println(getString(ib));
		// try {
		// System.out.println(ib.getString(Charset.forName("UTF-8").newDecoder()));
		// } catch (CharacterCodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public static final int putString(IoBuffer buffer, String str) {
		if (str == null) {
			try {
				throw new IOException("发送到字符串为 NULL");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int strlen = str.length();
		int utflen = 0;
		int c, count = 0;

		for (int i = 0; i < strlen; i++) {
			c = str.charAt(i);
			if ((c >= 0x0001) && (c <= 0x007F)) {
				utflen++;
			} else if (c > 0x07FF) {
				utflen += 3;
			} else {
				utflen += 2;
			}
		}
		if (utflen > 65535) {
			try {
				throw new UTFDataFormatException("encoded string too long: " + utflen + " bytes");
			} catch (UTFDataFormatException e) {
			}
		}
		byte[] bytearr = new byte[(utflen * 2) + 2];

		bytearr[count++] = (byte) ((utflen >>> 8) & 0xFF);
		bytearr[count++] = (byte) ((utflen >>> 0) & 0xFF);

		int i = 0;
		for (i = 0; i < strlen; i++) {
			c = str.charAt(i);
			if (!((c >= 0x0001) && (c <= 0x007F)))
				break;
			bytearr[count++] = (byte) c;
		}

		for (; i < strlen; i++) {
			c = str.charAt(i);
			if ((c >= 0x0001) && (c <= 0x007F)) {
				bytearr[count++] = (byte) c;

			} else if (c > 0x07FF) {
				bytearr[count++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
				bytearr[count++] = (byte) (0x80 | ((c >> 6) & 0x3F));
				bytearr[count++] = (byte) (0x80 | ((c >> 0) & 0x3F));
			} else {
				bytearr[count++] = (byte) (0xC0 | ((c >> 6) & 0x1F));
				bytearr[count++] = (byte) (0x80 | ((c >> 0) & 0x3F));
			}
		}
		buffer.put(bytearr, 0, utflen + 2);
		return utflen + 2;
	}

	public static final String getString(IoBuffer buffer) {
		try {
			int utflen = buffer.getShort();
			byte[] bytearr = null;
			char[] chararr = null;
			bytearr = new byte[utflen * 2];
			chararr = new char[utflen * 2];

			int c, char2, char3;
			int count = 0;
			int chararr_count = 0;

			buffer.get(bytearr, 0, utflen);

			while (count < utflen) {
				c = (int) bytearr[count] & 0xff;
				if (c > 127)
					break;
				count++;
				chararr[chararr_count++] = (char) c;
			}

			while (count < utflen) {
				c = (int) bytearr[count] & 0xff;
				switch (c >> 4) {
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
					case 7: {
						count++;
						chararr[chararr_count++] = (char) c;
						break;
					}
					case 12: {

					}
					case 13: {
						count += 2;
						if (count > utflen)
							throw new UTFDataFormatException("malformed input: partial character at end");
						char2 = (int) bytearr[count - 1];
						if ((char2 & 0xC0) != 0x80)
							throw new UTFDataFormatException("malformed input around byte " + count);
						chararr[chararr_count++] = (char) (((c & 0x1F) << 6) | (char2 & 0x3F));
						break;
					}
					case 14: {
						count += 3;
						if (count > utflen)
							throw new UTFDataFormatException("malformed input: partial character at end");
						char2 = (int) bytearr[count - 2];
						char3 = (int) bytearr[count - 1];
						if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
							throw new UTFDataFormatException("malformed input around byte " + (count - 1));
						chararr[chararr_count++] = (char) (((c & 0x0F) << 12) | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
						break;
					}
					default: {
						throw new UTFDataFormatException("malformed input around byte " + count);
					}
				}
			}
			return new String(chararr, 0, chararr_count);
		} catch (Exception e) {
			return null;
		}
	}

	private static ThreadLocal<Point> points = new ThreadLocal<Point>();

	private static void testThreadLocal() {
		Point p = new Point();
		p.setX(1);
		p.setY(1);
		points.set(p);
		p = new Point();
		p.setX(2);
		p.setY(2);
		points.set(p);
		p = new Point();
		p.setX(3);
		p.setY(3);
		points.set(p);
		p = new Point();
		p.setX(4);
		p.setY(4);
		points.set(p);
		p = new Point();
		p.setX(5);
		p.setY(5);
		points.set(p);
		points.remove();
		System.out.println("1 = " + points.get());
		System.out.println("2 = " + points.get());
		System.out.println("3 = " + points.get());
		System.out.println("4 = " + points.get());
		System.out.println("5 = " + points.get());
	}

	private static void readfile(String file) {
		try {
			Map<String, StringBuilder> mlist = new HashMap<String, StringBuilder>();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			// StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.length() > 11) {
					line = line.substring(2);
					String head = line.substring(0, 11);
					StringBuilder list = mlist.get(head);
					if (list == null) {
						list = new StringBuilder();
						mlist.put(head, list);
					}
					list.append(line);
					list.append(System.getProperty("line.separator"));
				}

			}
			if (br != null) {
				br.close();
			}
			System.out.println(mlist);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
