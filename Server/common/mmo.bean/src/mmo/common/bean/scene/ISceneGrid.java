package mmo.common.bean.scene;

import java.util.List;

import mmo.common.bean.role.Role;

public interface ISceneGrid {
	public List<Role> getRoles();

	public List<Role> getMonsters();

	public void monsterEnterGrid(Role monster);
	public void monsterExitGrid(Role role) ;
	public void updateData();

	public void roleEnterGrid(Role session);

	public void roleExitGrid(Role session);

	public void releasee();
	
}
