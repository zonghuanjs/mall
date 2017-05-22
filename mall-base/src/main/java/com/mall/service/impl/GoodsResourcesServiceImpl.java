package com.mall.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.mall.entity.GoodsResources;
import com.mall.service.GoodsResourcesService;

@Service
public class GoodsResourcesServiceImpl extends BaseServiceImpl<Long, GoodsResources> implements GoodsResourcesService {
	@Override
	public boolean add(GoodsResources model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}

	@Override
	public boolean update(GoodsResources model) {
		if (model != null) {
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}
}
