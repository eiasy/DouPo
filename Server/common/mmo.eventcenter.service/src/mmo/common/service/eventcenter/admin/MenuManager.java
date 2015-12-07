package mmo.common.service.eventcenter.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.service.eventcenter.comparator.ComparatorMenu;
import mmo.common.service.eventcenter.comparator.ComparatorMenuItem;
import mmo.common.service.eventcenter.module.AdminMenu;
import mmo.common.service.eventcenter.module.AdminMenuItem;
import mmo.common.service.eventcenter.service.Service;

public class MenuManager {
	private final static MenuManager instance = new MenuManager();

	public final static MenuManager getInstance() {
		return instance;
	}

	private MenuManager() {

	}

	private List<AdminMenu> menuList = new ArrayList<AdminMenu>();
	private Map<Integer, List<AdminMenuItem>> adminMenuItemLib = new HashMap<Integer, List<AdminMenuItem>>();
	private Map<Integer, AdminMenuItem> adminMenuItemMap = new HashMap<Integer, AdminMenuItem>();

	public List<AdminMenu> getMenuList() {
		return menuList;
	}

	public Map<Integer, AdminMenuItem> getAdminMenuItemMap() {
		return adminMenuItemMap;
	}

	public void init() {
		menuList = Service.getInstance().getAdminMenuService().getAllAdminMenu();
		List<AdminMenuItem> itemList = Service.getInstance().getAdminMenuItemService().getAllAdminMenu();
		AdminMenuItem item = null;
		List<AdminMenuItem> menuItems = null;
		for (int ii = 0; ii < itemList.size(); ii++) {
			item = itemList.get(ii);
			menuItems = adminMenuItemLib.get(item.getMenuId());
			if (menuItems == null) {
				menuItems = new ArrayList<AdminMenuItem>();
				adminMenuItemLib.put(item.getMenuId(), menuItems);
			}
			adminMenuItemMap.put(item.getId(), item);
			menuItems.add(item);
		}
		Collections.sort(menuList, new ComparatorMenu());
		Collection<List<AdminMenuItem>> values = adminMenuItemLib.values();
		for (List<AdminMenuItem> v : values) {
			Collections.sort(v, new ComparatorMenuItem());
		}
	}
}
