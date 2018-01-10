package com.whoiszxl.seckill.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.whoiszxl.seckill.entities.OrderInfo;
import com.whoiszxl.seckill.entities.SeckillOrder;

/**
 * 订单数据库操作
 * 
 * @author whoiszxl
 *
 */
@Mapper
public interface OrderDao {

	/**
	 * 通过用户id和商品id获取到这个秒杀的订单
	 * 
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	@Select("select * from seckill_order where user_id=#{userId} and goods_id=#{goodsId}")
	public SeckillOrder getSeckillOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

	/**
	 * 将订单信息存储到数据库
	 * 
	 * @param orderInfo
	 * @return
	 */
	@Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
			+ "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
	@SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
	public long insert(OrderInfo orderInfo);

	/**
	 * 将秒杀订单信息储存到数据库
	 * 
	 * @param seckillOrder
	 * @return
	 */
	@Insert("insert into seckill_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
	public int insertSeckillOrder(SeckillOrder seckillOrder);
}
