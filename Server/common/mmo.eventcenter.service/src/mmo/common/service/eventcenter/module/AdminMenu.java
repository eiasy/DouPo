package mmo.common.service.eventcenter.module;

public class AdminMenu {
	private int id;
	private String menuName;
	private int orderFactor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getOrderFactor() {
		return orderFactor;
	}

	public void setOrderFactor(int orderFactor) {
		this.orderFactor = orderFactor;
	}
}
