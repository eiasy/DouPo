package mmo.module.cache.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleEmail {
	private final static byte              STATE_0_NEW     = 0;
	private final static byte              STATE_1_READ    = 1;
	private final static byte              STATE_101_DEL   = 101;

	private final static byte              PICKUP_0_NEW    = 0;
	private final static byte              PICKUP_1_GET    = 1;
	private final static byte              PICKUP_2_CANCEL = 2;
	private final static byte              PICKUP_3_REFUSE = 3;

	private final static byte              TYPE_0_SYSTEM   = 0;
	private final static byte              TYPE_1_USER     = 1;
	private final static Map<Byte, String> STATE_NAME      = new HashMap<Byte, String>();
	private final static Map<Byte, String> PICKUP_NAME     = new HashMap<Byte, String>();
	private final static Map<Byte, String> TYPE_NAME       = new HashMap<Byte, String>();
	static {
		STATE_NAME.put(STATE_0_NEW, "未读");
		STATE_NAME.put(STATE_1_READ, "已读");
		STATE_NAME.put(STATE_101_DEL, "已删除");

		PICKUP_NAME.put(PICKUP_0_NEW, "未提取");
		PICKUP_NAME.put(PICKUP_1_GET, "已提取");
		PICKUP_NAME.put(PICKUP_2_CANCEL, "退件");
		PICKUP_NAME.put(PICKUP_3_REFUSE, "拒收");

		TYPE_NAME.put(TYPE_0_SYSTEM, "系统邮件");
		TYPE_NAME.put(TYPE_1_USER, "用户邮件");
	}
	private int                            gameId;
	private int                            serverId;
	protected long                         id;
	/** 发件人 */
	protected int                          fromid;
	/** 发件人的名称 */
	protected String                       fromName;
	/** 收件人 */
	protected int                          targetid;
	/** 目标角色的名称 */
	protected String                       targetName;
	/** 标题 */
	protected String                       title;
	/** 内容 */
	protected String                       content;
	/** 阅读状态：0未读，1已读 ，101已经删除 */
	protected byte                         emailState;
	/** 提取状态：0未提取，1，已提取，2发出方取消，3接受方拒收 */
	protected byte                         pickup;
	/** 信件类型：0系统邮件, 1玩家间交互邮件 */
	protected byte                         etype;
	/** 发件时间 */
	protected long                         ctime;
	/** 操作时间（提取、取消、拒绝） */
	protected long                         ptime;

	private List<EmailGoods>               goodsList       = new ArrayList<EmailGoods>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFromid() {
		return fromid;
	}

	public void setFromid(int fromid) {
		this.fromid = fromid;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public int getTargetid() {
		return targetid;
	}

	public void setTargetid(int targetid) {
		this.targetid = targetid;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public byte getEmailState() {
		return emailState;
	}

	public void setEmailState(byte emailState) {
		this.emailState = emailState;
	}

	public byte getPickup() {
		return pickup;
	}

	public void setPickup(byte pickup) {
		this.pickup = pickup;
	}

	public byte getEtype() {
		return etype;
	}

	public void setEtype(byte etype) {
		this.etype = etype;
	}

	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

	public long getPtime() {
		return ptime;
	}

	public void setPtime(long ptime) {
		this.ptime = ptime;
	}

	public List<EmailGoods> getGoodsList() {
		return goodsList;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public void setGoodsList(List<EmailGoods> goodsList) {
		this.goodsList = goodsList;
	}

	public String getStateName() {
		return STATE_NAME.get(emailState);
	}

	public String getPicekupName() {
		return PICKUP_NAME.get(pickup);
	}

	public String getTypeName() {
		return TYPE_NAME.get(etype);
	}
}
