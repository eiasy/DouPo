package mmo.common.bean.role;

/**
 * 角色最基本的三大属性
 * 
 * @author 肖琼
 * 
 */
public class RoleProBean {
	private int hp;
	private int attack;
	private int defence;

	public RoleProBean() {

	}

	public RoleProBean(int hp, int attack, int defence) {
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}
}
