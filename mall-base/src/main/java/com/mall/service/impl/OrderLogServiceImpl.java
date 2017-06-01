package com.mall.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.mall.entity.Order;
import com.mall.entity.OrderLog;
import com.mall.service.OrderLogService;

@Service
public class OrderLogServiceImpl extends BaseServiceImpl<Long, OrderLog> implements OrderLogService {

	@Override
	public boolean add(OrderLog model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}

	@Override
	public void log(Order order, String operator, String content, int operationType) {
		Assert.notNull(order);
		if (StringUtils.isNotEmpty(operator) && StringUtils.isNotEmpty(content)) {
			OrderLog log = new OrderLog();
			log.setOperator(operator);
			log.setContent(content);
			log.setType(operationType);
			log.setOrders(order);
			this.add(log);
		}
	}

}
