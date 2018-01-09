package com.whoiszxl.seckill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whoiszxl.seckill.entities.SeckillUser;
import com.whoiszxl.seckill.redis.RedisService;
import com.whoiszxl.seckill.service.IGoodsService;
import com.whoiszxl.seckill.service.IUserService;
import com.whoiszxl.seckill.vo.GoodsVo;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private IGoodsService goodsService;
	
    @RequestMapping("/to_list")
    public String list(Model model,SeckillUser user) {
    	model.addAttribute("user", user);
    	
    	//查询商品列表
    	List<GoodsVo> goodsList = goodsService.listGoodsVo();
    	model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }
    
}
