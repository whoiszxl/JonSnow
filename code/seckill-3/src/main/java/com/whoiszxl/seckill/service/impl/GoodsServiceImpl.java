package com.whoiszxl.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whoiszxl.seckill.dao.GoodsDao;
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
	
	
}
