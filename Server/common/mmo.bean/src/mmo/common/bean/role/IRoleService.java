package mmo.common.bean.role;

import java.util.List;

public interface IRoleService {

	/**
	 * 通过角色ID获取角色信息
	 * @param id 角色ID
	 * @return 角色对象
	 */
	Role getOffLineUserRole(int id);
	
	/**
	 * 初始化角色相关的表格
	 */
	void initRoleTable();
	/**
	 * 加载角色MINI
	 * @return
	 */
	List<RoleMini> getRoleDIdByState();
}
