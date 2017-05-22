package com.mall.service;

import java.util.Date;

import com.mall.entity.CouponCode;
import com.mall.entity.Member;


public interface RedPacketsService
{

	/**
	 * 检查获取指定会员当日的次数
	 * @param member		会员对象
	 * @return	返回指定会员当日可用次数
	 */
	int memberDayNumber(Member member, Date date);
	
	/**
	 * 标记会员参加抽奖的模式与次数
	 * @param member		会员对象
	 */
	void markMember(Member member, Date date);
	
	/**
	 * 发放指定抽奖模式下的优惠券
	 * @param member		会员对象
	 * @return	发放成功,返回优惠码; 否则, 返回null
	 */
	CouponCode createCouponCode(Member member);
}
