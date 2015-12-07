package mmo.common.bean.buf;

import mmo.common.config.skill.BufRepeat;

public class SubjoinBuf {
	/** 添加该BUF的技能ID */
	private int   skillId;
	/** buffId */
	private int   subBufId;
	/** buff 层数 */
	private short subBufNumber;
	/** buff叠加上限 */
	private short subBufMaxNumber;
	/** BUFF是否重置时间 (即添加类型) */
	protected int addFlag;
	/** 添加BUF几率(百分比) */
	protected int addBuffPercent;

	public SubjoinBuf() {

	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getSubBufId() {
		return subBufId;
	}

	public void setSubBufId(int subBufId) {
		this.subBufId = subBufId;
	}

	public short getSubBufNumber() {
		return subBufNumber;
	}

	public void setSubBufNumber(short subBufNumber) {
		this.subBufNumber = subBufNumber;
	}

	public short getSubBufMaxNumber() {
		return subBufMaxNumber;
	}

	public void setSubBufMaxNumber(short subBufMaxNumber) {
		this.subBufMaxNumber = subBufMaxNumber;
	}

	public int getAddFlag() {
		return addFlag;
	}

	public void setBuffUpdateTime(boolean buffUpdateTime) {
		if (buffUpdateTime) {
			this.addFlag = BufRepeat.REPEAT_RESET;
		} else {
			this.addFlag = BufRepeat.REPEAT_PILE_UP;
		}
	}

	public int getAddBuffPercent() {
		return addBuffPercent;
	}

	public void setAddBuffPercent(int addBuffPercent) {
		this.addBuffPercent = addBuffPercent;
	}

}
