package com.mall.service;

import com.mall.entity.Refund;

public interface RefundService extends BaseService<Long, Refund> {
	/**
	 * 通过退款编号获取退款记录
	 * 
	 * @param sn
	 *            退款编号
	 * @return 退款记录
	 */
	Refund getFromSn(String sn);
}
