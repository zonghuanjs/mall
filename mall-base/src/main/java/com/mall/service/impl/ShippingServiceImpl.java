package com.mall.service.impl;

import java.util.Date;
import org.springframework.stereotype.Repository;

import com.mall.entity.Shipping;
import com.mall.service.ShippingService;
import com.mall.util.CommonUtil;

@Repository
public class ShippingServiceImpl extends BaseServiceImpl<Long, Shipping> implements ShippingService {
	@Override
	public boolean add(Shipping model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
			model.setSn(CommonUtil.createShippingSN());
		}
		return super.add(model);
	}

	@Override
	public boolean update(Shipping model) {
		if (model != null) {
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}

}
