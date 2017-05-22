package com.mall.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.mall.entity.Prize;
import com.mall.service.PrizeService;

@Service
public class PrizeServiceImpl extends BaseServiceImpl<Long, Prize> implements PrizeService {
	@Override
	public boolean add(Prize model) {
		if (model != null) {
			model.setCreateDate(new Date());
		}
		return super.add(model);
	}
}
