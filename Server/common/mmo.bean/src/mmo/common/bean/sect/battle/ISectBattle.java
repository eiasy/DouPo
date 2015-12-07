package mmo.common.bean.sect.battle;

import mmo.common.bean.role.Role;
import mmo.common.bean.sect.IGameSect;
import mmo.common.bean.sect.spell.SpellLevel;

public interface ISectBattle {

	public abstract boolean isCampApply(long sectId);

	public abstract boolean isCampResponse(long sectId);

	public abstract int getId();

	public abstract void setId(int id);

	public abstract byte getState();

	public void toStart();

	public abstract IGameSect getCampApply();

	public abstract void setCampApply(IGameSect campApply);

	public abstract IGameSect getCampResponse();

	public abstract void setCampResponse(IGameSect campResponse);

	public abstract boolean isFinish();

	public abstract void killBeast();

	public SpellLevel getSpellLevel();

	public void changeApplyRoles(int count);

	public void changeResponseRoles(int count);

	public void updateBattleInfo(Role userRole);
	
	public void updateBattleInfoTime(Role userRole);
	
	public void clearBattleInfo(Role userRole);
	
	public boolean isTimeOver();
}