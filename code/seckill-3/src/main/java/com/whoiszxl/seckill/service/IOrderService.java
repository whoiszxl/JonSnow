package com.whoiszxl.seckill.service;

import com.whoiszxl.seckill.entities.OrderInfo;
import com.whoiszxl.seckill.entities.SeckillOrder;
import com.whoiszxl.seckill.entities.SeckillUser;
import com.whoiszxl.seckill.vo.GoodsVo;

public interface IOrderService {

	/**
	 * 通过用户和商品id获取这个秒杀订单详细
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	public SeckillOrder getSeckillOrderByUserIdGoodsId(long userId, long goodsId);
	
	/**
	 * 通过用户和订单vo创建订单到数据库
	 * @param user
	 * @param goods
	 * @return
	 */
	public OrderInfo createOrder(SeckillUser user, GoodsVo goods);
}
