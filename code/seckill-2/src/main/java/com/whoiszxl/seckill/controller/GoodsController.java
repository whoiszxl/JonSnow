package com.whoiszxl.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whoiszxl.seckill.entities.SeckillUser;
import com.whoiszxl.seckill.redis.RedisService;
import com.whoiszxl.seckill.service.IUserService;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	IUserService userService;
	
	@Autowired
	RedisService redisService;
	
    @RequestMapping("/to_list")
    public String list(Model model,SeckillUser user) {
    	model.addAttribute("user", user);
        return "goods_list";
    }
    
}
