package mmo.common.bean.cdkey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mmo.tools.util.FileUtil;

public class CDkeyManager {
	/** 配置文件子目录 */
	private static final String         SUB_DIR       = "CDkey";
	/** 配置文件 */
	private static final String         CONFIG_FILE_1 = "privatePwd.txt";
	private static final String         CONFIG_FILE_2 = "publicPwd.txt";
	/** 唯一实例 */
	private static CDkeyManager         instance      = new CDkeyManager();
	/** 公会入驻礼包兑换码 key=激活码，value=状态(0未使用，roleId已使用) */
	private static Map<String, Integer> gongHuiBag    = new HashMap<String, Integer>();
	/** 神秘礼包兑换码 key=激活码，value=状态(0未使用，roleId已使用) */
	private static Map<String, Integer> secretBag     = new HashMap<String, Integer>();
	/** 配置文件 */
	private String                      cfgFile       = null;

	private CDkeyManager() {

	}

	public static final CDkeyManager getInstance() {
		if (instance == null) {
			instance = new CDkeyManager();
		}
		return instance;
	}

	public final void init(String cfgRoot) {
		StringBuilder sb = new StringBuilder();
		// 解析公会入驻礼包兑换码
		sb.append(cfgRoot).append(FileUtil.FILE_SEPARATOR).append(SUB_DIR).append(FileUtil.FILE_SEPARATOR).append(CONFIG_FILE_2);
		cfgFile = sb.toString();
		parseCDkeyTxt(cfgFile, gongHuiBag);
		sb.setLength(0);
		// 解析神秘礼包兑换码
		sb.append(cfgRoot).append(FileUtil.FILE_SEPARATOR).append(SUB_DIR).append(FileUtil.FILE_SEPARATOR).append(CONFIG_FILE_1);
		cfgFile = sb.toString();
		parseCDkeyTxt(cfgFile, secretBag);

	}

	private final void parseCDkeyTxt(String fileName, Map<String, Integer> map) {
		File file = new File(fileName);
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader bf = new BufferedReader(reader);// 一行一行读
		try {
			String t1 = bf.readLine();
			while (t1 != null) {
				map.put(t1, CDkeyConfig.state.effective);
				t1 = bf.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 判断激活码是否可用，可用则消耗
	 * 
	 * @param key
	 *            激活码
	 * @param keyType
	 *            激活码类型
	 * @return boolean
	 */
	public static final boolean checkCDkey(String key, byte keyType, int roleId) {
		switch (keyType) {
			// 公会入驻礼包
			case CDkeyConfig.cate.gongHuiKey: {
				if (gongHuiBag.containsKey(key) && gongHuiBag.get(key) == 0) {
					return true;
				}
				return false;
			}
				// 神秘礼包
			case CDkeyConfig.cate.secretKey: {
				if (secretBag.containsKey(key) && secretBag.get(key) == 0) {
					return true;
				}
				return false;
			}
			default:
				return false;
		}
	}

	/**
	 * 改变使用过的激活码状态
	 * 
	 * @param key
	 *            激活码
	 * @param keyType
	 *            激活码类型
	 * @param roleId
	 *            角色ID
	 */
	public static final void changeKeyState(String key, byte keyType, int roleId) {
		switch (keyType) {
			// 公会入驻礼包
			case CDkeyConfig.cate.gongHuiKey: {
				gongHuiBag.put(key, roleId);
				break;
			}
				// 神秘礼包
			case CDkeyConfig.cate.secretKey: {
				secretBag.put(key, roleId);
				break;
			}
			default:
				break;
		}
	}

	/**
	 * 角色是否使用过该类型的CDkey
	 * 
	 * @param keyType
	 *            CDkey类型
	 * @param roleId
	 *            角色ID
	 * @return boolean true 使用过 false 未使用过
	 */
	public static final boolean isUseCDkey(byte keyType, int roleId) {
		Iterator<Integer> values = null;
		switch (keyType) {
			case CDkeyConfig.cate.gongHuiKey: {
				values = gongHuiBag.values().iterator();
				return checkValue(values, roleId);
			}
			case CDkeyConfig.cate.secretKey: {
				values = secretBag.values().iterator();
				return checkValue(values, roleId);
			}
			default:
				return true;
		}
	}

	private static boolean checkValue(Iterator<Integer> values, int roleId) {
		int value = 0;
		while (values.hasNext()) {
			value = values.next();
			if (roleId == value) {
				return true;
			}
		}
		return false;
	}
}
