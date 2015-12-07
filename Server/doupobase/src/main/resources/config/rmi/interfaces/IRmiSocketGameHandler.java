package com.huayi.doupo.base.config.rmi.interfaces;

public interface IRmiSocketGameHandler{
  public int getOnlinePlayerCount()throws Exception;//获取所有在线人数
  public int getOnlineSessionCount()throws Exception;//获取所有在线session
  public int getNowManageSessionNum()throws Exception;//获取mina正在管理的session数量
  public int getOnlinePlayerCountByTownId(long townId)throws Exception;//获得指定城镇里的所有在线人数
  public boolean kickPlayerByPlayerName(String  playerName)throws Exception;//踢掉指定的playerName
  public boolean kickPlayerByUserName(String  userName)throws Exception;//踢掉指定的playerName
  public boolean kickAll()throws Exception;//踢掉所有人
  public String getMapSize()throws Exception;//获得map集合的大小
  public boolean isOnlineByPlayerName(String playerName)throws Exception;//检查指定playerName玩家是否在线
  public boolean isOnlineByUserName(String userName)throws Exception;//检查指定userName玩家是否在线
  public boolean cleanMap()throws Exception;//清理所有map集合，核正sessionMap和playerMap几个对象
}
