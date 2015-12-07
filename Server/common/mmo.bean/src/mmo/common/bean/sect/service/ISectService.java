package mmo.common.bean.sect.service;

import java.util.List;

import mmo.common.bean.sect.IGameSect;

public interface ISectService {
	public boolean createSect(IGameSect sect);

	public boolean openManor(IGameSect sect);


	public boolean updateSectBeast(IGameSect sect);

	public boolean updateSect(IGameSect sect);

	public boolean updateSectFull(IGameSect sect);

	public void dismiss(IGameSect sect);

	public List<IGameSect> loadSect();
}
