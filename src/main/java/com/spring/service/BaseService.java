package com.spring.service;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
	
	public int saveEntity(T t);

	public int deleteEntity(T t);
	
	public List<T> queryAll();
	
	public T queryEntity(String sql,Object...objects);
	
	public List<Map<String,Object>> queryMap();
	
}
