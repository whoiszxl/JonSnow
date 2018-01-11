package com.whoiszxl.seckill.service;

import java.util.List;

import com.whoiszxl.seckill.vo.GoodsVo;

public interface IGoodsService {

	
	public List<GoodsVo> listGoodsVo();

	public GoodsVo getGoodsVoByGoodsId(long goodsId);
	
	public void reduceStock(GoodsVo goods);
}
