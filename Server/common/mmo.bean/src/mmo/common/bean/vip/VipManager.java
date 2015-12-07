package mmo.common.bean.vip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.bean.xml.VipConfigXML;
import mmo.common.config.StringLib;
import mmo.tools.util.FileUtil;

/**
 * VIP管理器
 * 
 * @author fanren
 * 
 */
public class VipManager {
	/** 默认VIP最大经验值 */
	private static final int                DEFAULT_MAX_EXP        = 3200000;
	/** 配置文件子目录 */
	private static final String             SUB_DIR                = "vip";
	/** 配置文件 */
	private static final String             CONFIG_FILE            = "vips.xml";
	/** 唯一实例 */
	private static VipManager               instance               = new VipManager();
	/** VIP等级映射表<key=VIP等级,value=等级配置对象> */
	private Map<Short, VipLevel>            vipLevels              = new HashMap<Short, VipLevel>();
	/** VIP等级映射表<key=VIP序号,value=等级配置对象> */
	private Map<Short, VipLevel>            vipLevelIndexs         = new HashMap<Short, VipLevel>();
	/** VIP等级列表 */
	private List<VipLevel>                  vipLevelList           = new ArrayList<VipLevel>();
	/** VIP最高等级 */
	private short                           maxLevel               = 0;
	/** 配置文件 */
	private String                          cfgFile                = null;
	/** VIP等级与最大经验的映射 */
	private Map<Short, Integer>             levelToMaxExp          = new HashMap<Short, Integer>();
	/** VIP等级与功能集的映射 */
	private Map<Short, List<Integer>>       vipLvToFuns            = new HashMap<Short, List<Integer>>();
	/** VIP等级与所有功能的描述 */
	private Map<Short, String>              vipLvToAllDescribe     = new HashMap<Short, String>();
	/** 功能ID与功能数据的映射 */
	private final Map<Integer, VipFunction> vipFuns                = new HashMap<Integer, VipFunction>();
	/** 精力值购买次数与花费,下标为次数，值为花费 */
	private int[]                           buyJingLiCost          = null;
	/** 购买副本重置次数与花费,下标为次数，值为花费 */
	private int[]                           buyResetCountCost      = null;
	/** 购买灵石次数与花费和获得值[0]=花费，[1]=获得灵石 */
	private List<int[]>                     buyLingShiCostAndAward = new ArrayList<int[]>();
	/** 购买斗法次数与花费,下标为次数，值为花费 */
	private int[]                           buyDouFaCountCost      = null;

	private VipManager() {

	}

	/**
	 * 获得管理器实例
	 * 
	 * @return 管理器实例
	 */
	public static final VipManager getInstance() {
		if (instance == null) {
			instance = new VipManager();
		}
		return instance;
	}

	/**
	 * 增加一条等级与最大经验值的映射
	 * 
	 * @param level
	 * @param maxExp
	 */
	public final void addLevelToMaxExp(short level, int maxExp) {
		if (level < 2) {
			levelToMaxExp.put(level, maxExp);
		} else {
			int sum = 0;
			for (short lv = 1; lv < level; lv++) {
				sum += levelToMaxExp.get(lv);
			}
			levelToMaxExp.put(level, maxExp - sum);
		}
	}

	/**
	 * 通过VIP等级获取最大经验
	 * 
	 * @param level
	 * @return
	 */
	public final int getVipMaxExp(short level) {
		Integer maxExp = levelToMaxExp.get(level);
		if (maxExp == null) {
			maxExp = DEFAULT_MAX_EXP;
		}
		return maxExp;
	}

	/**
	 * 增加VIP对应的功能ID
	 * 
	 * @param vipLv
	 * @param funId
	 */
	public final void addVipLvToFuns(short vipLv, int funId) {
		List<Integer> funs = vipLvToFuns.get(vipLv);
		if (funs == null) {
			funs = new ArrayList<Integer>();
			vipLvToFuns.put(vipLv, funs);
		}
		funs.add(funId);
	}

	/**
	 * 通过VIP等级获取对应的功能数据
	 * 
	 * @param vipLv
	 * @return
	 */
	public final List<VipFunction> getVipFuntions(short vipLv) {
		List<VipFunction> result = new ArrayList<VipFunction>();
		List<Integer> funs = vipLvToFuns.get(vipLv);
		if (funs != null && !funs.isEmpty()) {
			VipFunction fun = null;
			for (int i = 0, size = funs.size(); i < size; i++) {
				fun = vipFuns.get(funs.get(i));
				if (fun != null) {
					result.add(fun);
				}
			}
		}
		return result;
	}

	/**
	 * 通过VIP等级获取所有功能描述
	 * 
	 * @param vipLv
	 * @return
	 */
	public final String getAllFunsDescribe(short vipLv) {
		String describe = vipLvToAllDescribe.get(vipLv);
		if (describe == null) {
			List<VipFunction> funs = getVipFuntions(vipLv);
			if (funs != null) {
				VipFunction vf = null;
				LimitData ld = null;
				StringBuilder sb = new StringBuilder();
				for (int i = 0, size = funs.size(); i < size; i++) {
					vf = funs.get(i);
					if (vf != null && vf.isOpen()) {
						ld = vf.getVipLimitData(vipLv);
						if (ld != null) {
							sb.append(ld.getFunDiscribe());
							if (i != size - 1) {
								sb.append("[/r]");
							}
						}
					}
				}
				describe = sb.toString();
				vipLvToAllDescribe.put(vipLv, describe);
			} else {
				describe = StringLib.CommonStr.commonNo;
			}
		}
		return describe;
	}

	/**
	 * 增加一个VIP功能数据
	 * 
	 * @param vipLv
	 * @param function
	 */
	public final void addVipFunction(VipFunction function) {
		vipFuns.put(function.getId(), function);
	}

	/**
	 * 通过vip等级和功能ID获取限制数据
	 * 
	 * @param vipLv
	 * @param funId
	 * @return
	 */
	public final LimitData getVipLimitData(short vipLv, int funId) {
		VipFunction fun = vipFuns.get(funId);
		if (fun != null) {
			LimitData data = fun.getVipLimitData(vipLv);
			if (data != null) {
				return data;
			}
		}
		return null;
	}

	/**
	 * 初始化VIP配置信息
	 * 
	 * @param cfgRoot
	 *            配置文件根目录
	 */
	public final void init(String cfgRoot) {
		StringBuilder sb = new StringBuilder();
		sb.append(cfgRoot).append(FileUtil.FILE_SEPARATOR).append(SUB_DIR).append(FileUtil.FILE_SEPARATOR).append(CONFIG_FILE);
		cfgFile = sb.toString();
		if (vipLevels != null) {
			vipLevels.clear();
		} else {
			vipLevels = new HashMap<Short, VipLevel>();
		}
		VipConfigXML.parseXML(this, cfgFile);
	}

	/**
	 * 加入一个新的等级配置对象
	 * 
	 * @param vipLevel
	 */
	public void addVipLevel(VipLevel vipLevel) {
		if (vipLevel != null) {
			vipLevels.put(vipLevel.getLevel(), vipLevel);
			vipLevelIndexs.put(vipLevel.getIndex(), vipLevel);
			vipLevelList.add(vipLevel);
			if (maxLevel < vipLevel.getLevel()) {
				maxLevel = vipLevel.getLevel();
			}
		}
	}

	public short getMaxLevel() {
		return maxLevel;
	}

	public List<VipLevel> getVipLevelList() {
		return vipLevelList;
	}

	/**
	 * 通过等级获取等级配置对象
	 * 
	 * @param vipLevel
	 * @return
	 */
	public final VipLevel getVipLevel(short vipLevel) {
		return vipLevels.get(vipLevel);
	}

	/**
	 * 通过序号获取等级配置对象
	 * 
	 * @param vipLevel
	 * @return
	 */
	public final VipLevel getVipLevelIndex(short index) {
		return vipLevelIndexs.get(index);
	}

	public final void initBuyJingLiCost(int len) {
		this.buyJingLiCost = new int[len];
	}

	public final void addBuyJingLiCost(int index, int cost) {
		if (index < 0) {
			index = 0;
		}
		if (index >= buyJingLiCost.length) {
			index = buyJingLiCost.length - 1;
		}
		buyJingLiCost[index] = cost;
	}

	public final int getBuyJingLiCost(int index) {
		if (index < 0) {
			index = 0;
		}
		if (index >= buyJingLiCost.length) {
			index = buyJingLiCost.length - 1;
		}
		return buyJingLiCost[index] > 0 ? buyJingLiCost[index] : 1000;
	}

	public final void initBuyResetCountCost(int len) {
		this.buyResetCountCost = new int[len];
	}

	public final void addBuyResetCountCost(int index, int cost) {
		if (index < 0) {
			index = 0;
		}
		if (index >= buyResetCountCost.length) {
			index = buyResetCountCost.length - 1;
		}
		buyResetCountCost[index] = cost;
	}

	public final int getBuyResetCountCost(int index) {
		if (index < 0) {
			index = 0;
		}
		if (index >= buyResetCountCost.length) {
			index = buyResetCountCost.length - 1;
		}
		return buyResetCountCost[index];
	}

	public final void addBuyLingShiCostAndAward(int cost, int award) {
		int[] value = new int[2];
		value[0] = cost;
		value[1] = award;
		this.buyLingShiCostAndAward.add(value);
	}

	public final int[] getBuyLingShiCostAndAward(int index) {
		index--;
		if (index < 0) {
			index = 0;
		}
		if (index > this.buyLingShiCostAndAward.size() - 1) {
			index = this.buyLingShiCostAndAward.size() - 1;
		}
		return this.buyLingShiCostAndAward.get(index);
	}

	public final void initBuyDouFaCountCost(int len) {
		this.buyDouFaCountCost = new int[len];
	}

	public final void addBuyDouFaCountCost(int index, int cost) {
		if (index < 0) {
			index = 0;
		}
		if (index >= buyDouFaCountCost.length) {
			index = buyDouFaCountCost.length - 1;
		}
		buyDouFaCountCost[index] = cost;
	}

	public final int getBuyDouFaCountCost(int index) {
		if (index < 0) {
			index = 0;
		}
		if (index >= buyDouFaCountCost.length) {
			index = buyDouFaCountCost.length - 1;
		}
		return buyDouFaCountCost[index];
	}
}
