package com.whoiszxl.seckill.service;

import com.whoiszxl.seckill.entities.User;

/**
 * 测试用户service服务层
 * @author zxlvoid
 *
 */
public interface IUserService {

	public User getByUsername(String username);
	
	public boolean tx();
	
}
