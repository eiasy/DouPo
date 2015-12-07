package mmo.tools.filter.impl;

import mmo.tools.filter.IFilter;

/**
 * Tea算法 每次操作可以处理8个字节数据 KEY为16字节,应为包含4个int型数的int[]，一个int为4个字节 加密解密轮数应为8的倍数，推荐加密轮数为64轮
 */
public class DefaultTeaFilter implements IFilter {
	public final static int[] DEFAULT_TEA_KEY = new int[] { 0x789f5645, 0xf68bd5a4, 0x81963ffa, 0x458fac58, 0x645f5789, 0xd5a4f68b, 0x3ffa8196,
	        0xac58458f                       };

	public DefaultTeaFilter(int i) {

	}

	public byte[] onReceiveData(byte[] data) {
		return decrypt(data, 0, DEFAULT_TEA_KEY, 32);
	}

	public byte[] onSendData(byte[] data) {
		return encrypt(data, 0, DEFAULT_TEA_KEY, 32);
	}

	public byte[] onReceiveData(byte[] data, int[] key) {
		if (key == null) {
			return decrypt(data, 0, DEFAULT_TEA_KEY, 32);
		}

		return decrypt(data, 0, key, 32);
	}

	public byte[] onSendData(byte[] data, int[] key) {
		if (key == null) {
			return encrypt(data, 0, DEFAULT_TEA_KEY, 32);
		}
		return encrypt(data, 0, key, 32);
	}

	/**
	 * 加密
	 */
	public static byte[] encrypt(byte[] content, int offset, int[] key, int times) {// times为加密轮数
		int n = 8 - content.length % 8;// 若temp的位数不足8的倍数,需要填充的位数

		byte[] tempEnStr = new byte[content.length + n];
		tempEnStr[0] = (byte) n;
		System.arraycopy(content, 0, tempEnStr, n, content.length);
		int[] tempInt = byteToInt(tempEnStr, offset);

		int y = tempInt[0], z = tempInt[1], y1 = 0, z1 = 0, sum = 0, i;
		boolean flag = tempInt.length >= 4;
		if (flag) {
			y1 = tempInt[2];
			z1 = tempInt[3];
		}
		int delta = 0x9e3779b9; // 这是算法标准给的值
		int a = key[0], b = key[1], c = key[2], d = key[3], e = key[4], f = key[5], g = key[6], h = key[7];

		for (i = 0; i < times; i++) {
			sum += delta;
			y += ((z << 4) + a) ^ (z + sum) ^ ((z >> 5) + b);
			z += ((y << 4) + c) ^ (y + sum) ^ ((y >> 5) + d);
			if (flag) {
				y1 += ((z1 << 4) + e) ^ (z1 + sum) ^ ((z1 >> 5) + f);
				z1 += ((y1 << 4) + g) ^ (y1 + sum) ^ ((y1 >> 5) + h);
			}
		}
		tempInt[0] = y;
		tempInt[1] = z;
		if (flag) {
			tempInt[2] = y1;
			tempInt[3] = z1;
		}
		return intToByte(tempInt, offset);
	}

	/**
	 * 解密
	 */
	public static byte[] decrypt(byte[] encryptContent, int offset, int[] key, int times) {
		int[] tempInt = byteToInt(encryptContent, offset);
		int y = tempInt[0], z = tempInt[1], y1 = 0, z1 = 0, sum = 0, i;

		boolean flag = tempInt.length >= 4;
		if (flag) {
			y1 = tempInt[2];
			z1 = tempInt[3];
		}

		int delta = 0x9e3779b9; // 这是算法标准给的值
		int a = key[0], b = key[1], c = key[2], d = key[3], e = key[4], f = key[5], g = key[6], h = key[7];
		if (times == 32)
			sum = 0xC6EF3720; /**//* delta << 5 */
		else if (times == 16)
			sum = 0xE3779B90; /**//* delta << 4 */
		else
			sum = delta * times;

		for (i = 0; i < times; i++) {
			z -= ((y << 4) + c) ^ (y + sum) ^ ((y >> 5) + d);
			y -= ((z << 4) + a) ^ (z + sum) ^ ((z >> 5) + b);
			if (flag) {
				z1 -= ((y1 << 4) + g) ^ (y1 + sum) ^ ((y1 >> 5) + h);
				y1 -= ((z1 << 4) + e) ^ (z1 + sum) ^ ((z1 >> 5) + f);
			}
			sum -= delta;
		}
		tempInt[0] = y;
		tempInt[1] = z;
		if (flag) {
			tempInt[2] = y1;
			tempInt[3] = z1;
		}

		byte[] tempDecrypt = intToByte(tempInt, offset);

		int n = tempDecrypt[0];
		byte[] dec = new byte[tempDecrypt.length - n];
		System.arraycopy(tempDecrypt, n, dec, 0, tempDecrypt.length - n);
		return dec;
	}

	/**
	 * byte[]型数据转成int[]型数据
	 */
	private static int[] byteToInt(byte[] content, int offset) {
		// 除以2的n次方 == 右移n位 即 content.length / 4 == content.length >> 2
		int[] result = new int[content.length >> 2];
		for (int i = 0, j = offset; j < content.length; i++, j += 4) {
			result[i] = transform(content[j + 3]) | transform(content[j + 2]) << 8 | transform(content[j + 1]) << 16 | (int) content[j] << 24;
		}
		return result;
	}

	/**
	 * int[]型数据转成byte[]型数据
	 */
	private static byte[] intToByte(int[] content, int offset) {
		// 乘以2的n次方 == 左移n位 即 content.length * 4 == content.length << 2
		byte[] result = new byte[content.length << 2];
		for (int i = 0, j = offset; j < result.length; i++, j += 4) {
			result[j + 3] = (byte) (content[i] & 0xff);
			result[j + 2] = (byte) ((content[i] >> 8) & 0xff);
			result[j + 1] = (byte) ((content[i] >> 16) & 0xff);
			result[j] = (byte) ((content[i] >> 24) & 0xff);
		}
		return result;
	}

	/**
	 * 若某字节为负数则需将其转成无符号正数
	 */
	private static int transform(byte temp) {
		int tempInt = (int) temp;
		if (tempInt < 0) {
			tempInt += 256;
		}
		return tempInt;
	}

	public int filter(int value, byte[] key, int count) {
		// TODO Auto-generated method stub
		return 0;
	}

	public byte[] onReceiveData(byte[] data, byte[] key, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] onSendData(byte[] data, byte[] key, int count) {
		return null;
	}
	
	public void setFilter(boolean isFilter){
		
	}
	
	public boolean isFilter(){
		return false;
	}
}
