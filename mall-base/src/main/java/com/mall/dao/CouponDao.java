package com.mall.dao;

import java.util.Date;

import com.mall.entity.Coupon;

public interface CouponDao extends BaseDao<Long, Coupon> {
	/**
	 * 计算某日发放的优惠券数目
	 * 
	 * @param coupon
	 *            优惠信息
	 * @param day
	 *            日期(仅日期部分有效)
	 * @return 返回优惠券发放的数量
	 */
	long issueNumber(Coupon coupon, Date day);
}
