package mmo.tools.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;

import mmo.tools.log.LoggerError;

public class SecurityUtil {
	/** 组成密匙的字符数组 */
	private final static char[] CHARKEY  = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
	        'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	/** 存放固定密匙 */
	private char[]              KeyChars = null;

	public SecurityUtil(String SecurityFilePath) {
		KeyChars = getKeyChars(SecurityFilePath);
	}

	/**
	 * @根据玩家的数据生成校验码
	 * @参数1:玩家
	 * @返回:生成好的校验码
	 */
	public byte[] getVerify(String userID, String userName, short sex) throws Exception {
		// 生成校验码
		ByteArrayOutputStream tempBos = new ByteArrayOutputStream();
		DataOutputStream tempDos = new DataOutputStream(tempBos);

		tempDos.writeUTF(userID);
		tempDos.writeUTF(userName);
		tempDos.writeByte(sex);

		byte[] securityData = formatDatas(tempBos.toByteArray());
		tempDos.close();
		tempBos.close();

		return securityData;
	}

	/**
	 * 判断两个字节数组是否相等 参数1:字节数组1 参数2:字节数组2 返回:比较的结果 true 相等 false 不相等
	 */
	public boolean isSameDatas(byte[] datas1, byte[] datas2) {
		if (datas1.length != datas2.length) {
			return false;
		}
		for (int i = 0; i < datas1.length; i++) {
			if (datas1[i] != datas2[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @得到指定个数的字符串密匙
	 * @参数1:密匙的个数
	 * @返回:对应个数的随机字串
	 */
	public String getKeyString(int num) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++) {
			int tempIndex = getRand(0, CHARKEY.length - 1);
			char tempChar = CHARKEY[tempIndex];
			sb.append(tempChar);
		}
		return sb.toString();
	}

	/**
	 * 将数据流进行加密 或 把数据流进行解密 参数1:字节数组 参数2:字符串密匙 返回:加过密的字节数组
	 */
	public byte[] formatDatas(byte[] datas, String key) {
		char[] _keyChar = key.toCharArray();
		for (int i = 0; i < datas.length; i++) {
			datas[i] ^= (byte) _keyChar[i % _keyChar.length];
		}
		return datas;
	}

	/**
	 * 用本地固定的密匙文件里的密匙 对 数据流进行加密 或 把数据流进行解密 参数1:字节数组 返回:加过密的字节数组
	 */
	private byte[] formatDatas(byte[] datas) {

		for (int i = 0; i < datas.length; i++) {
			datas[i] ^= (byte) KeyChars[i % KeyChars.length];
		}
		return datas;
	}

	/**
	 * 得到两个非负数范围内的随机值(包括这两个数,第二个数大于等于第一个数) 参数1:较小的非负数 参数2:较大的非负数 返回:两数范围内的一个数
	 */
	private int getRand(int _int, int _int1) {
		Random _random = new Random(); // 随机数类
		return _int + Math.abs(_random.nextInt() % (_int1 - _int + 1));
	}

	/**
	 * 将文件里的内容读出 参数1:文件目录名 返回:读出的内容 (string)
	 */
	private char[] getKeyChars(String fileName) {
		try {
			InputStreamReader _filereader = new InputStreamReader(new FileInputStream(fileName));
			BufferedReader _bufferedreader = new BufferedReader(_filereader);
			String note = _bufferedreader.readLine();
			_bufferedreader.close();
			_filereader.close();
			return note.toCharArray();
		} catch (Exception e) {
			LoggerError.error(fileName + "文件读取失败", e);
			return null;
		}
	}
}