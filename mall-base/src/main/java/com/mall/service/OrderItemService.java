package com.mall.service;

import com.mall.entity.OrderItem;

public interface OrderItemService extends BaseService<Long, OrderItem> {
	/**
	 * 查询属性匹配的数量
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	long findNumber(String property, Object value);
}
