package mmo.common.module.service.charge.service;

import java.util.List;

import mmo.common.bean.role.RoleStoreReceipt;

public interface IStoreService {

	// public int getReceiptMaxId();

	public boolean addRoleStoreReceipt(RoleStoreReceipt receipt);

	public void updateRoleStoreReceipt(RoleStoreReceipt receipt);

	public List<RoleStoreReceipt> loadRoleStoreReceipt(byte status);

	public List<RoleStoreReceipt> loadRoleStoreReceipt(int appId, byte status);
}
