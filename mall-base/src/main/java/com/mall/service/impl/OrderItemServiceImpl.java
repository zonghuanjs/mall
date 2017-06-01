package com.mall.service.impl;

import java.util.Date;
import org.springframework.stereotype.Service;

import com.mall.dao.OrderItemDao;
import com.mall.entity.OrderItem;
import com.mall.service.OrderItemService;

@Service
public class OrderItemServiceImpl extends BaseServiceImpl<Long, OrderItem> implements OrderItemService {
	@Override
	public boolean add(OrderItem model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}

	@Override
	public boolean update(OrderItem model) {
		if (model != null) {
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}

	@Override
	public long findNumber(String property, Object value) {
		OrderItemDao dao = (OrderItemDao) getDao();
		return dao.findNumber(property, value);
	}

}
