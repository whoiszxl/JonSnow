package com.whoiszxl.seckill.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whoiszxl.seckill.dao.SeckillUserDao;
import com.whoiszxl.seckill.entities.OrderInfo;
import com.whoiszxl.seckill.entities.SeckillUser;
import com.whoiszxl.seckill.execption.GlobalException;
import com.whoiszxl.seckill.redis.RedisService;
import com.whoiszxl.seckill.redis.SeckillUserKey;
import com.whoiszxl.seckill.result.CodeMessage;
import com.whoiszxl.seckill.service.IGoodsService;
import com.whoiszxl.seckill.service.IOrderService;
import com.whoiszxl.seckill.service.ISeckillService;
import com.whoiszxl.seckill.utils.MD5Util;
import com.whoiszxl.seckill.utils.UUIDUtil;
import com.whoiszxl.seckill.vo.GoodsVo;
import com.whoiszxl.seckill.vo.LoginVo;
import com.whoiszxl.seckill.vo.RegisterVo;

@Service
public class SeckillServiceImpl implements ISeckillService {

	@Value("${my.cookie.token}")
	public String COOKIE_NAME_TOKEN;
	@Autowired
	private SeckillUserDao seckillUserDao;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IGoodsService goodsService;
	
	@Transactional
	public OrderInfo seckill(SeckillUser user, GoodsVo goods) {
		//减库存 下订单 写入秒杀订单
		goodsService.reduceStock(goods);
		//order_info
		return orderService.createOrder(user, goods);
	}
	
	
	public SeckillUser getByToken(HttpServletResponse response, String token) {
		if(StringUtils.isEmpty(token)) {
			return null;
		}	
		SeckillUser user = redisService.get(SeckillUserKey.token, token, SeckillUser.class);
		//获取之前判断是否存在,存在则续点时间
		if(user != null) {
			addCookie(response, token, user);
		}
		return user;
	}
	
	/**
	 * 将token存入redis并且还存入cookie
	 * @param response
	 * @param token
	 * @param user
	 */
	private void addCookie(HttpServletResponse response, String token, SeckillUser user) {
		redisService.set(SeckillUserKey.token, token, user);
		Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
		cookie.setMaxAge(SeckillUserKey.token.expireSeconds());
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	@Override
	public SeckillUser getById(long id) {
		//去缓存
		SeckillUser user = redisService.get(SeckillUserKey.getById, ""+id, SeckillUser.class);
		if(user != null) {
			return user;
		}
		//缓存拿不到去数据库
		user = seckillUserDao.getById(id);
		if(user != null) {
			redisService.set(SeckillUserKey.getById, ""+id, user);
		}
		return user;
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
		
		//登录后存入cookie
		String token = UUIDUtil.uuid();
		addCookie(response, token, user);
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
