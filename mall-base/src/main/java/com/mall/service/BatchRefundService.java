package com.mall.service;

import com.mall.entity.BatchRefund;

public interface BatchRefundService extends BaseService<Long, BatchRefund>
{
	/**
	 * 通过sn查找批量退款交易
	 * @param sn		批量退款交易流水号
	 * @return
	 */
	BatchRefund findBySn(String sn);
}
