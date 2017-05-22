package com.mall.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.mall.entity.CartItem;
import com.mall.service.CartItemService;

@Service
public class CartItemServiceImpl extends BaseServiceImpl<Long, CartItem> implements CartItemService {

	@Override
	public boolean add(CartItem model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}

	@Override
	public boolean update(CartItem model) {
		if (model != null) {
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}

}
