package com.mall.service;

import java.util.Date;

import com.mall.entity.Coupon;
import com.mall.entity.CouponCode;
import com.mall.entity.Member;


public interface CouponService extends BaseService<Long, Coupon>
{
	/**
	 * 检测会员的优惠券领取条件
	 * @param coupon		优惠信息
	 * @param member		会员对象
	 * @return	满足条件, 返回true; 否则, 返回false
	 */
	boolean checkCondition(Coupon coupon, Member member);
	/**
	 * 检测会员的优惠券使用条件
	 * @param coupon		优惠信息
	 * @param member		会员对象
	 * @return	满足条件, 返回true; 否则, 返回false
	 */
	boolean checkUseCondition(Coupon coupon, Member member);
	
	/**
	 * 发放优惠券给会员
	 * @param coupon		优惠信息
	 * @param member		会员对象
	 * @return	发放成功, 返回优惠券; 否则, 返回null
	 */
	CouponCode issueCode(Coupon coupon, Member member);
	
	/**
	 * 计算某日发放的优惠券数目
	 * @param coupon		优惠信息
	 * @param day			日期(仅日期部分有效)
	 * @return	返回优惠券发放的数量
	 */
	long issueNumber(Coupon coupon, Date day);
}
