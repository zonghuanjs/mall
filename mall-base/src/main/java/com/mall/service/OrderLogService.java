package com.mall.service;

import com.mall.entity.Order;
import com.mall.entity.OrderLog;

public interface OrderLogService extends BaseService<Long, OrderLog> {
	/**
	 * 记录订单日志
	 * 
	 * @param operator
	 *            操作员
	 * @param content
	 *            日志内容
	 */
	void log(Order order, String operator, String content, int operationType);
}
