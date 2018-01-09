package com.whoiszxl.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whoiszxl.seckill.dao.UserDao;
import com.whoiszxl.seckill.entities.User;
import com.whoiszxl.seckill.redis.RedisService;
import com.whoiszxl.seckill.redis.UserKey;
import com.whoiszxl.seckill.result.CodeMessage;
import com.whoiszxl.seckill.result.Result;
import com.whoiszxl.seckill.service.IUserService;

/**
 * 测试thymeleaf和result类的控制器
 * @author zxlvoid
 *
 */
@Controller
@RequestMapping(value = "/my")
public class MyController {
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserDao userDao;

	@RequestMapping("/result")
	@ResponseBody
	public Result<String> result(){
		return Result.success("请求成功");
	}
	
	@RequestMapping("/result2")
	@ResponseBody
	public Result<String> result2(){
		return Result.build(CodeMessage.SERVER_ERROR);
	}
	
	@RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
 		model.addAttribute("name", "wwwwj");
 		return "index";
    }
	
	@RequestMapping("/db/get")
	@ResponseBody
	public Result<User> dbGet(){
		User user = userService.getByUsername("zxl");
		return Result.success(user);
	}
	
	@RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
    	userService.tx();
        return Result.success(true);
    }
	
	@RequestMapping("/redis/get")
	@ResponseBody
	public Result<User> redisGet(){
		User user = redisService.get(UserKey.USER_INFO, "1", User.class);
		return Result.success(user);
	}
	
	@RequestMapping("/redis/set")
	@ResponseBody
	public Result<String> redisSet(){
		User user = new User();
		user.setUsername("wangfei");
		user.setPassword("wangfeifei");
		redisService.set(UserKey.USER_INFO, "1", user);
		return Result.success("redis key设置成功");
	}
}
