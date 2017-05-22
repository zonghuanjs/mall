package com.mall.service;

import com.mall.entity.Apply;

public interface ApplyService extends BaseService<Long, Apply> {

	/**
	 * 根据sn获取apply对象
	 * 
	 * @param sn
	 * @return
	 */
	Apply getFromSn(String sn);

	/**
	 * 确认收货事件通知
	 */
	void notifyRecievedEvent(Object arg);

	/**
	 * 审核通过事件通知
	 * 
	 * @param arg
	 */
	void notifyCheckedEvent(Object arg);

	/**
	 * 维修报告通知事件
	 * 
	 * @param arg
	 */
	void notifyReportEvent(Object arg);
}
