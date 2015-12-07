package com.huayi.doupo.base.config.rmi.interfaces;

import java.rmi.Remote;


public interface IRmiJavaHandler extends Remote{
  public int getOnlinePlayerCount()throws Exception;
  public void refreshDictList()throws Exception;
	public void setAccountState(String playerName,int accountState)throws Exception;
	public void updateQuartzJob(String jobName)throws Exception;
}
