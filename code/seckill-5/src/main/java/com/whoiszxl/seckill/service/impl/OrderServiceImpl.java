package com.whoiszxl.seckill.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whoiszxl.seckill.dao.OrderDao;
import com.whoiszxl.seckill.entities.OrderInfo;
import com.whoiszxl.seckill.entities.SeckillOrder;
import com.whoiszxl.seckill.entities.SeckillUser;
import com.whoiszxl.seckill.service.IOrderService;
import com.whoiszxl.seckill.vo.GoodsVo;

/**
 * 订单服务
 * @author whoiszxl
 *
 */
@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	OrderDao orderDao;
	
	/**
	 * 通过用户id和商品id获取到秒杀的订单
	 */
	@Override
	public SeckillOrder getSeckillOrderByUserIdGoodsId(long userId, long goodsId) {
		return orderDao.getSeckillOrderByUserIdGoodsId(userId, goodsId);
	}

	/**
	 * 创建订单
	 */
	@Override
	public OrderInfo createOrder(SeckillUser user, GoodsVo goods) {
		//创建订单信息
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getSeckillPrice());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);
		orderInfo.setUserId(user.getId());
		long orderId = orderDao.insert(orderInfo);
		//创建秒杀订单信息
		SeckillOrder seckillOrder = new SeckillOrder();
		seckillOrder.setGoodsId(goods.getId());
		seckillOrder.setOrderId(orderId);
		seckillOrder.setUserId(user.getId());
		orderDao.insertSeckillOrder(seckillOrder);
		//再次返回这个订单的信息
		return orderInfo;
	}

}
