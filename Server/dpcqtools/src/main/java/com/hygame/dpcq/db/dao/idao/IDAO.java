package com.hygame.dpcq.db.dao.idao;

import java.util.List;

public interface IDAO {
	public int add(Object o);
	public int update(Object o);
	public List<Object> findbyall();
	public int del(int id);
}
