package mmo.common.module.service.charge.service.base;

import java.util.List;

import mmo.common.bean.advertise.IdfaActive;
import mmo.common.bean.advertise.IdfaEvent;
import mmo.common.bean.role.QQChargeRecord;
import mmo.common.bean.role.RoleStoreReceipt;
import mmo.common.module.service.charge.service.IStoreService;

public class BaseService implements IStoreService {
	protected final AppStoreService storeService = new AppStoreService();
	protected final IdfaService idfaService = new IdfaService();
	protected final QQChargeService qqService = new QQChargeService();

	@Override
	public boolean addRoleStoreReceipt(RoleStoreReceipt receipt) {
		return storeService.addRoleStoreReceipt(receipt);
	}

	@Override
	public void updateRoleStoreReceipt(RoleStoreReceipt receipt) {
		storeService.updateRoleStoreReceipt(receipt);
	}

	public List<RoleStoreReceipt> loadRoleStoreReceipt(byte status) {
		return storeService.loadRoleStoreReceipt(status);
	}

	public List<RoleStoreReceipt> loadRoleStoreReceipt(int appId, byte status) {
		return storeService.loadRoleStoreReceipt(appId, status);
	}

	public List<QQChargeRecord> loadQQChargeRecord(int appId, int status) {
		return qqService.loadQQChargeRecord(appId, status);
	}

	public void addIdfaActive(IdfaActive idfa) {
		idfaService.addIdfaActive(idfa);
	}

	public boolean addQQCharge(QQChargeRecord cr) {
		return qqService.addQQCharge(cr);
	}

	public void updateQQCharge(QQChargeRecord cr) {
		qqService.updateQQCharge(cr);
	}

	public void addIdfaActive(IdfaEvent idfa) {
		idfaService.addIdfaEvent(idfa);
	}

	public void updateIdfaActive(IdfaActive idfa) {
		idfaService.updateIdfaActive(idfa);
	}

	public List<IdfaActive> loadAllIdfaActive() {
		return idfaService.loadAllIdfaActive();
	}
}
