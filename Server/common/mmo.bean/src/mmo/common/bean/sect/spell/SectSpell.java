package mmo.common.bean.sect.spell;

public class SectSpell {

	/** 阵法等级 */
	private int    id;
	/** 阵法名称 */
	private String name;
	/** 描述 */
	private String note;
	/** 最高等级 */
	private short  maxLevel;

	public short getMaxLevel() {
    	return maxLevel;
    }

	public void setMaxLevel(short maxLevel) {
    	this.maxLevel = maxLevel;
    }

	public SectSpell() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "SectSpell [id=" + id + ", name=" + name + "]";
	}
}
