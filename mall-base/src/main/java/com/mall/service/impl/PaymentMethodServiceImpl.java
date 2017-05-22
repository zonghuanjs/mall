package com.mall.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.mall.entity.PaymentMethod;
import com.mall.service.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl extends BaseServiceImpl<Long, PaymentMethod> implements PaymentMethodService {
	@Override
	public boolean add(PaymentMethod model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}

	@Override
	public boolean update(PaymentMethod model) {
		if (model != null) {
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}
}
