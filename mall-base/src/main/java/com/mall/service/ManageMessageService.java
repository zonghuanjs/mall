package com.mall.service;

import java.util.Date;
import java.util.List;

import com.mall.entity.MallMessage;

public interface ManageMessageService extends BaseService<Long, MallMessage> {

	/**
	 * 获取商城推送的一些消息
	 * 
	 * @param memberId
	 *            不用请置-1
	 * @param pageNumber
	 *            不用请置-1
	 * @param pageSize
	 *            不用请置-1
	 * @param msgType
	 *            消息类型: 1,系统消息; 2,优惠券消息; 3,促销消息
	 * @param lastTime
	 *            上次访问时间 不用请置null
	 * @param total
	 *            index=0，所有数量 ； index=1，未曾浏览的个数
	 * @return msgType=1或者3， 返回MallMessage的id序列；msgType=2， 返回Coupon的id序列；
	 */
	List<Long> getMallMessages(long memberId, int pageNumber, int pageSize, int msgType, Date lastTime,
			List<Long> total);
}
