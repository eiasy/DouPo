package mmo.common.service.eventcenter.service.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mmo.common.service.eventcenter.module.AdminMenu;
import mmo.tools.db.DBConnection;

public class AdminMenuService {
	private static String getAllAdminMenu = "select id,menuName,orderFactor from admin_menu";

	public List<AdminMenu> getAllAdminMenu() {
		List<AdminMenu> menuList = new ArrayList<AdminMenu>();
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AdminMenu menu = null;
		try {
			stmt = conn.prepareStatement(getAllAdminMenu);
			rs = stmt.executeQuery();
			while (rs.next()) {
				menu = new AdminMenu();
				menu.setId(rs.getInt(1));
				menu.setMenuName(rs.getString(2));
				menu.setOrderFactor(rs.getInt(3));
				menuList.add(menu);
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
		return menuList;
	}

}
