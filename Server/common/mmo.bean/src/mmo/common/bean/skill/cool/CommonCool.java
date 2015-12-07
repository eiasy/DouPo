package mmo.common.bean.skill.cool;

/**
 * 公共冷去
 * 
 * @author fanren
 * 
 */
public class CommonCool implements Cloneable {
	/** 公共冷去ID */
	private int  id;
	/** 冷去时间 */
	private int  coolTime;
	/** 公共冷去结束时间 */
	private long endCool;

	public final void startCool() {
		endCool = System.currentTimeMillis() + coolTime;
	}

	public final boolean isEndCool() {
		return System.currentTimeMillis() > endCool;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCoolTime() {
		return coolTime;
	}

	public long getEndCool() {
    	return endCool;
    }

	public void setCoolTime(int coolTime) {
		this.coolTime = coolTime;
	}

	public CommonCool clone() {
		Object cool = null;
		try {
			cool = super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (CommonCool) cool;
	}

	@Override
	public String toString() {
		return "CommonCool [id=" + id + ", coolTime=" + coolTime + ", endCool=" + endCool + "]";
	}
}
