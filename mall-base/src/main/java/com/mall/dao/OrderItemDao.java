package com.mall.dao;

import com.mall.entity.OrderItem;

public interface OrderItemDao extends BaseDao<Long, OrderItem> {
	/**
	 * 查询属性匹配的数量
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	long findNumber(String property, Object value);
}
