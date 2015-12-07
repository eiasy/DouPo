package mmo.common.service.eventcenter.service;

import mmo.common.service.eventcenter.service.base.BaseService;

/**
 * @功能：业务逻辑类，完成与游戏相关的业务处理
 * @日期：2009-10-15
 * @作者：李天喜
 */
public class Service extends BaseService {
	private static Service instance = new Service();

	private Service() {

	}

	public static final Service getInstance() {
		if (instance == null) {
			instance = new Service();
		}
		return instance;
	}

}
