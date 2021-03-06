package com.whoiszxl.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whoiszxl.seckill.dao.GoodsDao;
import com.whoiszxl.seckill.entities.SeckillGoods;
import com.whoiszxl.seckill.service.IGoodsService;
import com.whoiszxl.seckill.vo.GoodsVo;

@Service
public class GoodsServiceImpl implements IGoodsService {

	@Autowired
	private GoodsDao goodsDao;

	@Override
	public List<GoodsVo> listGoodsVo() {
		return goodsDao.listGoodsVo();
	}

	@Override
	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}

	@Override
	public void reduceStock(GoodsVo goods) {
		SeckillGoods g = new SeckillGoods();
		g.setGoodsId(goods.getId());
		goodsDao.reduceStock(g);
		
	}
	
	
}
