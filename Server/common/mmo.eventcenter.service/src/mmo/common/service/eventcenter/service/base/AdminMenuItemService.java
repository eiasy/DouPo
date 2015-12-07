package mmo.common.service.eventcenter.service.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mmo.common.service.eventcenter.module.AdminMenuItem;
import mmo.tools.db.DBConnection;

public class AdminMenuItemService {
	private static String getAllAdminMenuItem = "select id,itemName,menuId,url,className,orderFactor,status from admin_menu_item";

	public List<AdminMenuItem> getAllAdminMenu() {
		List<AdminMenuItem> itemList = new ArrayList<AdminMenuItem>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AdminMenuItem item = null;
		try {
			stmt = conn.prepareStatement(getAllAdminMenuItem);
			rs = stmt.executeQuery();
			while (rs.next()) {
				item = new AdminMenuItem();
				item.setId(rs.getInt(1));
				item.setItemName(rs.getString(2));
				item.setMenuId(rs.getInt(3));
				item.setUrl(rs.getString(4));
				item.setClassName(rs.getString(5));
				item.setOrderFactor(rs.getInt(6));
				item.setStatus(rs.getInt(7));
				itemList.add(item);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnection.freeConnection(conn);
			conn = null;
		}
		return itemList;
	}

}
