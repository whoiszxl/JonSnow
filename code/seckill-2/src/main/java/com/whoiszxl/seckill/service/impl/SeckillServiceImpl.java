package com.whoiszxl.seckill.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whoiszxl.seckill.dao.SeckillUserDao;
import com.whoiszxl.seckill.entities.SeckillUser;
import com.whoiszxl.seckill.execption.GlobalException;
import com.whoiszxl.seckill.result.CodeMessage;
import com.whoiszxl.seckill.service.ISeckillService;
import com.whoiszxl.seckill.utils.MD5Util;
import com.whoiszxl.seckill.utils.UUIDUtil;
import com.whoiszxl.seckill.vo.LoginVo;
import com.whoiszxl.seckill.vo.RegisterVo;

@Service
public class SeckillServiceImpl implements ISeckillService {

	@Autowired
	private SeckillUserDao seckillUserDao;

	@Override
	public SeckillUser getById(long id) {
		return seckillUserDao.getById(id);
	}

	@Override
	public boolean login(HttpServletResponse response, LoginVo loginVo) {
		if (loginVo == null) {
			throw new GlobalException(CodeMessage.SERVER_ERROR);
		}
		String mobile = loginVo.getMobile();
		String formPass = loginVo.getPassword();

		// 判断手机号码是否存在
		SeckillUser user = getById(Long.parseLong(mobile));
		if (user == null) {
			throw new GlobalException(CodeMessage.MOBILE_NOT_EXIST);
		}

		// 验证密码
		String dbPass = user.getPassword();// 获取到数据库中的加密串
		String saltDB = user.getSalt();// 获取到数据库中的盐
		String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);// 通过工具加密form提交过来的密码
		if (!calcPass.equals(dbPass)) {
			// 不相等抛出异常
			throw new GlobalException(CodeMessage.PASSWORD_ERROR);
		}

		return true;
	}

	@Override
	public int register(HttpServletResponse response, RegisterVo registerVo) {
		if (registerVo == null) {
			throw new GlobalException(CodeMessage.SERVER_ERROR);
		}
		String mobile = registerVo.getMobile();
		String formPass = registerVo.getPassword();
		String nickname = registerVo.getNickname();
		// 判断手机号码是否存在
		SeckillUser user = getById(Long.parseLong(mobile));
		if (user != null) {
			throw new GlobalException(CodeMessage.MOBILE_EXIST);
		}

		String saltDB = UUIDUtil.uuid();
		// 插入数据库
		String insertPass = MD5Util.formPassToDBPass(formPass, saltDB);
		return seckillUserDao.insertUser(Long.parseLong(mobile), insertPass, saltDB, nickname);
	}

}
