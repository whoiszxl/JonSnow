package com.whoiszxl.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.whoiszxl.seckill.entities.SeckillGoods;
import com.whoiszxl.seckill.vo.GoodsVo;

@Mapper
public interface GoodsDao {

	/**
	 * 获取秒杀商品列表
	 * @return
	 */
	@Select("select g.*,sg.stock_count, sg.start_date, sg.end_date,sg.seckill_price from seckill_goods sg left join goods g on sg.goods_id = g.id")
	public List<GoodsVo> listGoodsVo();

	/**
	 * 通过id获取到这个商品的详细信息
	 * @param goodsId
	 * @return
	 */
	@Select("select g.*,sg.stock_count, sg.start_date, sg.end_date,sg.seckill_price from seckill_goods sg left join goods g on sg.goods_id = g.id where g.id = #{goodsId}")
	public GoodsVo getGoodsVoByGoodsId(long goodsId);
	
	/**
	 * 将当前商品的库存减去1
	 * @param g
	 * @return
	 */
	@Update("update seckill_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
	public int reduceStock(SeckillGoods g);
}
