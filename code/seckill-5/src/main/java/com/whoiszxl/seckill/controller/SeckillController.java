package com.whoiszxl.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.whoiszxl.seckill.entities.OrderInfo;
import com.whoiszxl.seckill.entities.SeckillOrder;
import com.whoiszxl.seckill.entities.SeckillUser;
import com.whoiszxl.seckill.redis.RedisService;
import com.whoiszxl.seckill.result.CodeMessage;
import com.whoiszxl.seckill.service.IGoodsService;
import com.whoiszxl.seckill.service.IOrderService;
import com.whoiszxl.seckill.service.ISeckillService;
import com.whoiszxl.seckill.vo.GoodsVo;

/**
 * 秒杀controller
 * 
 * @author whoiszxl
 *
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

	@Autowired
	ISeckillService seckillService;

	@Autowired
	RedisService redisService;

	@Autowired
	IGoodsService goodsService;

	@Autowired
	IOrderService orderService;

	@RequestMapping("/do_seckill")
	public String list(Model model, SeckillUser user, @RequestParam("goodsId") long goodsId) {
		model.addAttribute("user", user);
		if (user == null) {
			return "login";
		}
		// 判断库存
		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
		int stock = goods.getStockCount();
		if (stock <= 0) {
			model.addAttribute("errmsg", CodeMessage.SECKILL_OVER.getMsg());
			return "seckill_fail";
		}
		// 判断是否已经秒杀到了
		SeckillOrder order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
		if (order != null) {
			model.addAttribute("errmsg", CodeMessage.REPEATE_SECKILL.getMsg());
			return "seckill_fail";
		}
		// 减库存 下订单 写入秒杀订单
		OrderInfo orderInfo = seckillService.seckill(user, goods);
		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("goods", goods);
		return "order_detail";
	}
}
