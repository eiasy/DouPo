package mmo.common.service.eventcenter.module;

public class AdminMenuItem {
	private int id;
	private String itemName;
	private int menuId;
	private String url;
	private String className;
	private int orderFactor;
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getOrderFactor() {
		return orderFactor;
	}

	public void setOrderFactor(int orderFactor) {
		this.orderFactor = orderFactor;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
