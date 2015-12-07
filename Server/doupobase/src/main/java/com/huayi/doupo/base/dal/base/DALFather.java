package com.huayi.doupo.base.dal.base;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.player.PlayerMemObj;
import com.huayi.doupo.base.model.player.PlayerMemObjMapUtil;

/**
 * Dao的父类
 * @author mp
 * @date 2014-5-6 上午10:53:58
 */
public class DALFather extends JdbcDaoSupport {
	
	/**
	 * 是否使用缓存
	 * @author mp
	 * @date 2014-5-8 下午4:24:39
	 * @return
	 * @Description
	 */
	public static boolean isUseCach() {
//		boolean isUseCach = false;
//		try {
//			isUseCach = SysConfigUtil.getValue("cache.use").equals("1") ? true : false;
//		} catch (Exception e) {
//			LogUtil.error(e);
//		}
		return ParamConfig.isUseCache;
	}
	
	/**
	 * 根据玩家实例ID获取玩家缓存对象
	 * @author mp
	 * @date 2014-5-8 下午5:05:02
	 * @param instPlayerId
	 * @return
	 * @Description
	 */
	public static PlayerMemObj getPlayerMemObjByPlayerId (int instPlayerId) {
		return PlayerMemObjMapUtil.getPlayerMemObjByPlayerId(instPlayerId);
	}
	
	
}
