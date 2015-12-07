package mmo.common.bean.role;

public class QQChargeRecord {
	/** 新记录 */
	public final static int STATUS_0_NEW = 0;
	/** 验证通过未经确认 */
	public final static int STATUS_1_UNCONFIRM = 1;
	/** 验证通过已经确认 */
	public final static int STATUS_2_CONFIRM = 2;
	/** 充值失败 */
	public final static int STATUS_101_FAIL = 101;
	/** 验证超时 */
	public final static int STATUS_102_OVERTIME = 102;
	/** 额度小于0 */
	public final static int STATUS_103_AMOUNT_ERROR = 103;
	// 说明游戏初始化绑定service不成功
	public final static int PAYRESULT_SERVICE_BIND_FAIL = -2;
	// 支付流程失败
	public final static int PAYRESULT_ERROR = -1;
	// 支付流程成功
	public final static int PAYRESULT_SUCC = 0;
	// 用户取消
	public final static int PAYRESULT_CANCEL = 2;
	// 参数错误
	public final static int PAYRESULT_PARAMERROR = 3;

	private long id;
	private int resultCode;
	private int payChannel;
	private int payState;
	private int provideState;
	private int saveNum;
	private String extendInfo;
	private String orderId;
	private String remoteIp;
	private String openid;
	private String openkey;
	private String payToken;
	private String pf;
	private String pfkey;
	private String actionType;
	private String cdata;
	private long addTime;
	private long updateTime;
	private String imei;
	private String idfa;
	private int status;
	private int checkCount;
	private String chargeType;
	private int appId;
	private int handleAppId;
	private int zoneId;
	private String channelOrderId;
	/***********************************************************************/
	protected static int CHECK_OFFSET = 1000 * 60 * 2;
	protected static int OVERTIME = 1000 * 60 * 60;

	protected long nextTimeCheck = System.currentTimeMillis();

	public boolean isCheckable() {
		long currTime = System.currentTimeMillis();
		if (currTime > nextTimeCheck) {
			nextTimeCheck = currTime + CHECK_OFFSET;
			return true;
		}
		return false;
	}

	public void resetCheckTime() {
		nextTimeCheck = System.currentTimeMillis() + CHECK_OFFSET;
	}

	public boolean isOvertime() {
		return System.currentTimeMillis() - addTime > OVERTIME;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public int getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(int payChannel) {
		this.payChannel = payChannel;
	}

	public int getPayState() {
		return payState;
	}

	public void setPayState(int payState) {
		this.payState = payState;
	}

	public int getProvideState() {
		return provideState;
	}

	public void setProvideState(int provideState) {
		this.provideState = provideState;
	}

	public int getSaveNum() {
		return saveNum;
	}

	public void setSaveNum(int saveNum) {
		this.saveNum = saveNum;
	}

	public String getExtendInfo() {
		return extendInfo;
	}

	public void setExtendInfo(String extendInfo) {
		this.extendInfo = extendInfo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOpenkey() {
		return openkey;
	}

	public void setOpenkey(String openkey) {
		this.openkey = openkey;
	}

	public String getPayToken() {
		return payToken;
	}

	public void setPayToken(String payToken) {
		this.payToken = payToken;
	}

	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	public String getPfkey() {
		return pfkey;
	}

	public void setPfkey(String pfkey) {
		this.pfkey = pfkey;
	}

	public String getActionType() {
		if (actionType == null) {
			return "";
		}
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getCdata() {
		return cdata;
	}

	public void setCdata(String cdata) {
		this.cdata = cdata;
	}

	public long getAddTime() {
		return addTime;
	}

	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCheckCount() {
		return checkCount;
	}

	public void setCheckCount(int checkCount) {
		this.checkCount = checkCount;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getHandleAppId() {
		return handleAppId;
	}

	public void setHandleAppId(int handleAppId) {
		this.handleAppId = handleAppId;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public String getChannelOrderId() {
		return channelOrderId;
	}

	public void setChannelOrderId(String channelOrderId) {
		this.channelOrderId = channelOrderId;
	}
}
