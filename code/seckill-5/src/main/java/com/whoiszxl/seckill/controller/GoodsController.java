package com.whoiszxl.seckill.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import com.whoiszxl.seckill.entities.SeckillUser;
import com.whoiszxl.seckill.redis.GoodsKey;
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
	
	@Autowired
	ThymeleafViewResolver thymeleafViewResolver;
	
	@Autowired
	ApplicationContext applicationContext;

	@RequestMapping(value = "/to_list", produces="text/html")
	@ResponseBody
	public String list(HttpServletRequest request, HttpServletResponse response,Model model, SeckillUser user) {
		model.addAttribute("user", user);

		//先取缓存中是否存在list页面数据
		String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
		if(!StringUtils.isEmpty(html)) {
			return html;
		}
		
		// 查询商品列表
		List<GoodsVo> goodsList = goodsService.listGoodsVo();
		model.addAttribute("goodsList", goodsList);
		
		SpringWebContext swc = new SpringWebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap(), applicationContext);
		
		//手动渲染
    	html = thymeleafViewResolver.getTemplateEngine().process("goods_list", swc);
    	if(!StringUtils.isEmpty(html)) {
    		redisService.set(GoodsKey.getGoodsList, "", html);
    	}
    	return html;
	}

	@RequestMapping(value="/to_detail/{goodsId}",produces="text/html")
	@ResponseBody
	public String detail(HttpServletRequest request, HttpServletResponse response,
			Model model, SeckillUser user, @PathVariable("goodsId") long goodsId) {
		model.addAttribute("user", user);
		
		//先从缓存中取
		String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);
		if(!StringUtils.isEmpty(html)) {
			return html;
		}
		
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
		
		SpringWebContext swc = new SpringWebContext(request,response,
    			request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
    	html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", swc);
    	if(!StringUtils.isEmpty(html)) {
    		redisService.set(GoodsKey.getGoodsDetail, ""+goodsId, html);
    	}
		return html;
	}

}
