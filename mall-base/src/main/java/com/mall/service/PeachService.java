package com.mall.service;

import java.util.Date;

import com.mall.entity.CouponCode;
import com.mall.entity.Member;
import com.mall.entity.Order;

public interface PeachService {
	/**
	 * 检查获取指定会员当日的砸蟠桃次数
	 * 
	 * @param member
	 *            会员对象
	 * @return 返回指定会员当日砸蟠桃次数
	 */
	int memberDayNumber(Member member, Date date);

	/**
	 * 检查指定奖品的已发放数量
	 * 
	 * @param reward
	 *            奖品商品ID
	 * @return 返回当日已发放奖品数量
	 */
	int rewardDayNumber(Long reward, Date date);

	/**
	 * 创建砸蟠桃中奖订单
	 * 
	 * @param prize
	 *            获得奖项
	 * @return 返回抽奖订单
	 */
	Order createOrder(Member member, int prize);

	/**
	 * 发放优惠券
	 * 
	 * @param member
	 *            会员对象
	 * @return 发放成功,返回优惠码; 否则, 返回null
	 */
	CouponCode createCouponCode(Member member);

	/**
	 * 给用户发放奖品
	 * 
	 * @param member
	 *            会员对象
	 * @return 返回奖品索引, 0表示未中奖
	 */
	int createPrize(Member member);

	/**
	 * 标记会员参加抽奖的次数
	 * 
	 * @param member
	 *            会员对象
	 */
	void markMember(Member member, Date date);
}
