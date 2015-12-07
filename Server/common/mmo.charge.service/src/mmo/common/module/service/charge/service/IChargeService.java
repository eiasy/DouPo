package mmo.common.module.service.charge.service;

import java.util.List;

import mmo.common.bean.role.ChargeRecord;

public interface IChargeService {
	/**
	 * 添加一条充值记录
	 * 
	 * @param cr
	 *            充值记录
	 * @return true添加成功，false添加失败
	 */
	public boolean addRoleChargeRecord(ChargeRecord cr);

	public boolean addGmChargeRecord(ChargeRecord cr);

	public boolean updateChargeRecord(ChargeRecord cr);

	public List<ChargeRecord> loadCharges(int productId, int applicationId);
}
