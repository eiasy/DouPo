package mmo.common.bean.mission;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.bean.mission.extension.Award;
import mmo.common.config.MissionConfig;
import mmo.tools.util.string.StringSplit;

public class GameMission implements java.io.Serializable {
	protected Map<Integer, String> interest         = new HashMap<Integer, String>(); // 完成任务需要满足的因素(alter
	                                                                                  // by xiaoqiong
	/************ 持久化属性 **************/
	private static final long      serialVersionUID = 1L;
	/** 任务类别，预留 */
	protected short                cate;
	/** 任务编号 */
	protected int                  id;
	/** 前置任务，该任务不影响任务链表 */
	protected int                  premission;
	/** 前置关卡 */
	protected int                  preGateId;
	/** 开启任务条件 */
	protected List<Integer>        premissions;
	/** 可接该任务的最低等级 */
	protected short                minLevel;
	/** 等级上限 */
	protected short                maxLevel;
	/** 任务标题 */
	protected String               title;
	/** 内容描述 */
	protected String               describe;
	/** 目标描述-接任务 */
	protected String               targetDescAccept;
	/** 发任务NPC */
	protected int                  dispatch;
	/** 接任务对白 */
	protected String               dialogAccept;
	/** 接任务选项文字 */
	protected String               itemAccept;
	/** 目标描述（进行） */
	protected String               targetDescDo;

	/** 目标描述（完成） */
	protected String               targetDescComplete;

	/** 收任务NPC */
	protected int                  receive;
	/** 交任务对白 */
	protected String               dialogCommit;
	/** 交任务选项文字 */
	protected String               itemCommit;
	/** 任务奖励 */
	protected List<Award>          awards           = new ArrayList<Award>();
	/** 星级 */
	protected short                starLevel;
	/** 是否为不可重复做 */
	protected boolean              isUnrepeat;
	/** 循环接受任务的数量限制值 */
	protected short                countLimit;
	/** 背包内有该物品才可以接受任务 */
	protected int                  goodsLimit;
	/** 达到指定的境界才可以接受该任务 */
	protected byte                 realmLimit;
	/** 宗门等级限制 */
	protected short                sectLevelLimit;
	/**************************************************************/
	/** 提交任务的等级 */
	protected short                commitLevel;
	/** 发放副本任务的NPC */
	protected int                  duplicate;
	/** 加入时间 */
	protected Timestamp            atime;
	/** 能否放弃标识：0可以放弃，1不能放弃 */
	protected byte                 flag;
	/** 任务完成时导航文字 */
	protected String               navigateText;
	/** 每天可以重复做到次数 */
	protected int                  repeatCount;
	private String                 levelLimit       = "不限";

	/** 触发事件：100装备 ,101开礼包 */
	protected short                eventOnMissionCommit;
	/** 接任务音效 */
	protected String               musicAccept      = "";
	/** 交任务音效 */
	protected String               musicCommit      = "";

	protected int                  gateId;

	public String getMusicAccept() {
		return musicAccept;
	}

	public void setMusicAccept(String musicAccept) {
		if (musicAccept == null) {
			return;
		}
		this.musicAccept = musicAccept;
	}

	public String getMusicCommit() {
		return musicCommit;
	}

	public void setMusicCommit(String musicCommit) {
		if (musicCommit == null) {
			return;
		}
		this.musicCommit = musicCommit;
	}

	public short getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(short maxLevel) {
		this.maxLevel = maxLevel;
	}

	public short getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(short starLevel) {
		this.starLevel = starLevel;
	}

	public String getTargetDescDo() {
		return targetDescDo;
	}

	public void setTargetDescDo(String targetDescDo) {
		this.targetDescDo = StringSplit.transformString(targetDescDo);
	}

	public String getItemAccept() {
		return itemAccept;
	}

	public void setItemAccept(String itemAccept) {
		this.itemAccept = itemAccept;
	}

	public String getTargetDescComplete() {
		return targetDescComplete;
	}

	public void setTargetDescComplete(String targetDescComplete) {
		this.targetDescComplete = StringSplit.transformString(targetDescComplete);
	}

	public String getDialogCommit() {
		return dialogCommit;
	}

	public void setDialogCommit(String dialogCommit) {
		this.dialogCommit = StringSplit.transformString(dialogCommit);
	}

	public String getItemCommit() {
		return itemCommit;
	}

	public void setItemCommit(String itemCommit) {
		this.itemCommit = itemCommit;
	}

	public String getDialogAccept() {
		return dialogAccept;
	}

	public String getTargetDescAccept() {
		return targetDescAccept;
	}

	public void setTargetDescAccept(String targetDescAccept) {
		this.targetDescAccept = StringSplit.transformString(targetDescAccept);
	}

	public short getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(short minLevel) {
		this.minLevel = minLevel;
	}

	public String getLevelLimit() {
		return levelLimit;
	}

	public int getPremission() {
		return premission;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public void setPremission(int premission) {
		this.premission = premission;
	}

	public short getCommitLevel() {
		return commitLevel;
	}

	public void setCommitLevel(short commitLevel) {
		this.commitLevel = commitLevel;
	}

	public void setLevelLimit(int minLevel, int maxLevel) {
		StringBuilder sb = new StringBuilder();
		if (minLevel > 0) {
			sb.append(minLevel).append("级");
		}
		levelLimit = sb.toString();
	}

	public String getNavigateText() {
		return navigateText;
	}

	public void setNavigateText(String navigateText) {
		this.navigateText = navigateText;
	}

	/** default constructor */
	public GameMission() {
	}

	public Timestamp getAtime() {
		return this.atime;
	}

	public int getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(int duplicate) {
		this.duplicate = duplicate;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public short getCate() {
		return this.cate;
	}

	public String getDialog() {
		return this.dialogAccept;
	}

	public byte getFlag() {
		return flag;
	}

	public int getId() {
		return this.id;
	}

	public Map<Integer, String> getInterest() {
		return interest;
	}

	public String getDescribe() {
		return this.describe;
	}

	public String getTitle() {
		return this.title;
	}

	public void setAtime(Timestamp atime) {
		this.atime = atime;
	}

	public void setCate(short cate) {
		this.cate = cate;
	}

	/**
	 * 是否为主线
	 * 
	 * @return true为主线
	 */
	public boolean isTrunk() {
		return this.cate == MissionConfig.Category.TRUNK;
	}

	/**
	 * 是否为支线
	 * 
	 * @return true支线
	 */
	public boolean isBranch() {
		return this.cate == MissionConfig.Category.BRANCH;
	}

	/**
	 * 是否为每日任务
	 * 
	 * @return
	 */
	public boolean isEveryDay() {
		return this.cate == MissionConfig.Category.DAY_COLLECT || this.cate == MissionConfig.Category.DAY_LEVEL
		        || this.cate == MissionConfig.Category.DAY_MUTUAL || this.cate == MissionConfig.Category.DAY_PLAY;
	}

	public boolean isLiveness() {
		return this.cate == MissionConfig.Category.LIVENESS;
	}

	public void setDialogAccept(String dialog) {
		this.dialogAccept = StringSplit.transformString(dialog);
	}

	public void setDispatch(int dispatch) {
		this.dispatch = dispatch;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescribe(String note) {
		this.describe = note;
	}

	public void setReceive(int receive) {
		this.receive = receive;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addAward(Award award) {
		this.awards.add(award);
	}

	@Override
	public String toString() {
		return "GameMission [id=" + id + ", title=" + title + ", dialog=" + dialogAccept + ", note=" + describe + ", navigateText=" + navigateText
		        + "]";
	}

	public boolean isUnrepeat() {
		return isUnrepeat;
	}

	public void setUnrepeat(boolean isUnrepeat) {
		this.isUnrepeat = isUnrepeat;
	}

	public short getCountLimit() {
		return countLimit;
	}

	public void setCountLimit(short countLimit) {
		this.countLimit = countLimit;
	}

	public int getGoodsLimit() {
		return goodsLimit;
	}

	public void setGoodsLimit(int goodsLimit) {
		this.goodsLimit = goodsLimit;
	}

	public byte getRealmLimit() {
		return realmLimit;
	}

	public void setRealmLimit(byte realmLimit) {
		this.realmLimit = realmLimit;
	}

	public short getSectLevelLimit() {
		return sectLevelLimit;
	}

	public void setSectLevelLimit(short sectLevelLimit) {
		this.sectLevelLimit = sectLevelLimit;
	}

	public short getEventOnMissionCommit() {
		return eventOnMissionCommit;
	}

	public void setEventOnMissionCommit(short eventOnMissionCommit) {
		this.eventOnMissionCommit = eventOnMissionCommit;
	}

	public int getDupGateId() {
		return gateId;
	}

	public void setDupGateId(int dupGateId) {
		this.gateId = dupGateId;
	}

	public List<Integer> getPremissions() {
		return premissions;
	}

	public void setPremissions(List<Integer> premissions) {
		this.premissions = premissions;
	}

	public int getPreGateId() {
		return preGateId;
	}

	public void setPreGateId(int preGateId) {
		this.preGateId = preGateId;
	}

}