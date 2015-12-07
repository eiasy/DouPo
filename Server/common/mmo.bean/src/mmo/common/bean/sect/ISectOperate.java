package mmo.common.bean.sect;

import mmo.common.bean.role.Role;

public interface ISectOperate {
	public void sectList(Role userRole, int connectId);
	public void sectListOpenWindow(Role userRole, int connectId);
	/** 刷新宗门列表里面宗门申请的状态*/
	public void updateSectListState(Role userRole, long sectId, byte state);
	
	public void getManor(Role userRole, int connectId);

	public void enterSect(Role userRole, int connectId);

	public void enterEnemySect(Role userRole, int connectId);

	//public void getBeast(Role userRole, int connectId);

	public void openBurse(Role userRole, int connectId);

	public void createSect(Role userRole, int connectId);

	public void openBeastPark(Role userRole, int connectId);

	public void openCollege(Role userRole, int connectId);

	public void openMansion(Role userRole, int connectId);

	public void openStoreHouse(Role userRole, int connectId);

	public void openWarMinistry(Role userRole, int connectId);

	public void openSectUpgrade(Role userRole, int connectId);

	public void openSectQuit(Role userRole, int connectId);

	public void battleAward(Role userRole, int connectId);

	public void sectBattleList(Role userRole, int connectId);

	public void manorEnter(Role userRole, int connectId);
	
	public void checkBeastPark(Role userRole, int connectId);
	
	public void checkStoreHouse(Role userRole, int connectId);
	
	public void setSectPageNumber(Role role, int curPage);
}
