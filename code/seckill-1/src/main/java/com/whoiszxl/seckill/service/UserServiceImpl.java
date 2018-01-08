package com.whoiszxl.seckill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whoiszxl.seckill.dao.UserDao;
import com.whoiszxl.seckill.entities.User;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User getByUsername(String username) {
		return userDao.getByUsername(username);
	}

	@Transactional
	@Override
	public boolean tx() {
		User u1 = new User();
		u1.setUsername("wj");
		u1.setPassword("wang");
		
		User u2 = new User();
		u2.setUsername("zxl");
		u2.setPassword("wang");
		userDao.insert(u1);
		userDao.insert(u2);
		return true;
	}
	
}
