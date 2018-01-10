package com.whoiszxl.seckill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String list(Model model, SeckillUser user) {
		model.addAttribute("user", user);

		// 查询商品列表
		List<GoodsVo> goodsList = goodsService.listGoodsVo();
		model.addAttribute("goodsList", goodsList);
		return "goods_list";
	}

	@RequestMapping("/to_detail/{goodsId}")
	public String detail(Model model, SeckillUser user, @PathVariable("goodsId") long goodsId) {
		model.addAttribute("user", user);
		//通过商品id获取到商品vo类
		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
		//添加到model中
		model.addAttribute("goods", goods);

		//获取到开始结束当前时间
		long startAt = goods.getStartDate().getTime();
		long endAt = goods.getEndDate().getTime();
		long now = System.currentTimeMillis();

		int seckillStatus = 0;
		int remainSeconds = 0;
		if (now < startAt) {// 秒杀还没开始，倒计时
			seckillStatus = 0;
			remainSeconds = (int) ((startAt - now) / 1000);
		} else if (now > endAt) {// 秒杀已经结束
			seckillStatus = 2;
			remainSeconds = -1;
		} else {// 秒杀进行中
			seckillStatus = 1;
			remainSeconds = 0;
		}
		model.addAttribute("seckillStatus", seckillStatus);
		model.addAttribute("remainSeconds", remainSeconds);
		return "goods_detail";
	}

}
