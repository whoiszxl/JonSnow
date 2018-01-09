package com.whoiszxl.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.whoiszxl.seckill.vo.GoodsVo;

@Mapper
public interface GoodsDao {

	
	@Select("select g.*,sg.stock_count, sg.start_date, sg.end_date,sg.seckill_price from seckill_goods sg left join goods g on sg.goods_id = g.id")
	public List<GoodsVo> listGoodsVo();
}
