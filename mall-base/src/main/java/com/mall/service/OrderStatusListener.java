package com.mall.service;

import com.mall.entity.Order;

public interface OrderStatusListener {
	/**
	 * 订单创建时触发
	 * 
	 * @param order
	 *            订单对象
	 * @param operator
	 *            操作员
	 */
	void onCreate(Order order, String operator);

	/**
	 * 订单被支付时触发
	 * 
	 * @param order
	 *            订单对象
	 * @param operator
	 *            操作员
	 */
	void onPaid(Order order, String operator);

	/**
	 * 订单付款到时时触发
	 * 
	 * @param order
	 *            订单对象
	 * @param operator
	 *            操作员
	 */
	void onPaidChecked(Order order, String operator);

	/**
	 * 订单备货后触发
	 * 
	 * @param order
	 *            订单对象
	 * @param operator
	 *            操作员
	 */
	void onPrepared(Order order, String operator);

	/**
	 * 订单发货后触发
	 * 
	 * @param order
	 *            订单对象
	 * @param operator
	 *            操作员
	 */
	void onShipping(Order order, String operator);

	/**
	 * 订单确认收货时触发
	 * 
	 * @param order
	 *            订单对象
	 * @param operator
	 *            操作员
	 */
	void onConfirm(Order order, String operator);

	/**
	 * 订单过期关闭时触发
	 * 
	 * @param order
	 *            订单对象
	 */
	void onExpired(Order order);

	/**
	 * 订单被取消时触发
	 * 
	 * @param order
	 *            订单对象
	 * @param operator
	 *            操作员
	 * @param reason
	 *            取消原因
	 */
	void onCancel(Order order, String operator, String reason);
}
