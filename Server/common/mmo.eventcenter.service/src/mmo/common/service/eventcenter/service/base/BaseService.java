package mmo.common.service.eventcenter.service.base;

import mmo.common.bean.bi.EventDefault;

public class BaseService {
	protected final EventService eventService = new EventService();
	protected final AdminDefaultService adminDefaultService = new AdminDefaultService();
	protected final AdminChargeService adminChargeService = new AdminChargeService();
	protected final AdminService adminService = new AdminService();
	protected final AdminMenuService adminMenuService = new AdminMenuService();
	protected final AdminMenuItemService adminMenuItemService = new AdminMenuItemService();

	public AdminMenuService getAdminMenuService() {
		return adminMenuService;
	}

	public AdminMenuItemService getAdminMenuItemService() {
		return adminMenuItemService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void addEvent(EventDefault event) {
		eventService.addEvent(event);
	}

	public void addEvent(String sql) {
		eventService.addEvent(sql);
	}

	public AdminChargeService getAdminChargeService() {
		return adminChargeService;
	}

	public AdminDefaultService getAdminDefaultService() {
		return adminDefaultService;
	}
}
