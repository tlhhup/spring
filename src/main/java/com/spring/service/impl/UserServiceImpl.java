package com.spring.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.dao.impl.UserDao;
import com.spring.entity.User;
import com.spring.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Override
	public int saveEntity(User t) {
		return userDao.saveEntity(t);
	}

	@Override
	public int deleteEntity(User t) {
		return userDao.deleteEntity(t);
	}

	@Override
	public List<User> queryAll() {
		return userDao.queryAll();
	}

	@Override
	public List<Map<String, Object>> queryMap() {
		return userDao.queryMap();
	}

	@Override
	public User queryEntity(String sql, Object... objects) {
		return userDao.quertyEntity(sql, objects);
	}

}
