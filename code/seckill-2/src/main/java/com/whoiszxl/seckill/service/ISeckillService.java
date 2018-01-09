package com.whoiszxl.seckill.service;

import javax.servlet.http.HttpServletResponse;

import com.whoiszxl.seckill.entities.SeckillUser;
import com.whoiszxl.seckill.vo.LoginVo;
import com.whoiszxl.seckill.vo.RegisterVo;

public interface ISeckillService {
	
	/**
	 * 通过ID获取
	 * @param id
	 * @return
	 */
	public SeckillUser getById(long id);

	/**
	 * 执行登录操作
	 * @param response
	 * @param loginVo
	 * @return 
	 */
	public boolean login(HttpServletResponse response, LoginVo loginVo);
	
	
	/**
	 * 执行注册操作
	 * @param response
	 * @param registerVo
	 * @return
	 */
	public int register(HttpServletResponse response, RegisterVo registerVo);
}
