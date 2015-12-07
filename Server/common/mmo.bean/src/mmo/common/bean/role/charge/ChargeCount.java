package mmo.common.bean.role.charge;

public class ChargeCount {
	private int roleId;
	private int accountId;
	private int chargeTotal;
	private int patchCount;
	private int numberSucc;
	private int numberFail;
	private int numberPatch;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getChargeTotal() {
		return chargeTotal;
	}

	public void setChargeTotal(int chargeTotal) {
		this.chargeTotal = chargeTotal;
	}

	public void addChargeTotal(int count) {
		this.chargeTotal += count;
	}

	public void addPatchCount(int count) {
		this.patchCount += count;
	}

	public int getPatchCount() {
		return patchCount;
	}

	public void setPatchCount(int patchCount) {
		this.patchCount = patchCount;
	}

	public int getNumberSucc() {
		return numberSucc;
	}

	public void addNumgerSucc() {
		this.numberSucc++;
	}

	public void addNumgerFail() {
		this.numberFail++;
	}

	public void addNumgerPatch() {
		this.numberPatch++;
	}

	public void setNumberSucc(int numberSucc) {
		this.numberSucc = numberSucc;
	}

	public int getNumberFail() {
		return numberFail;
	}

	public void setNumberFail(int numberFail) {
		this.numberFail = numberFail;
	}

	public int getNumberPatch() {
		return numberPatch;
	}

	public void setNumberPatch(int numberPatch) {
		this.numberPatch = numberPatch;
	}

}
