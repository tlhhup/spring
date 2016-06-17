package com.spring.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

	public int saveEntity(T t);

	public int deleteEntity(T t);

	public List<T> queryAll();
	
	public T quertyEntity(String sql,Object...objects);
	
	public List<Map<String,Object>> queryMap();

}
