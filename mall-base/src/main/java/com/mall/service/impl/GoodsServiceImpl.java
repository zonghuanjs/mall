package com.mall.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.mall.entity.Goods;
import com.mall.service.GoodsService;

@Service
public class GoodsServiceImpl extends BaseServiceImpl<Long, Goods> implements GoodsService {
	@Override
	public boolean add(Goods model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}
}
