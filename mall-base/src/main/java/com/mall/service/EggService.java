package com.mall.service;

import java.util.Date;

import com.mall.entity.CouponCode;
import com.mall.entity.Member;
import com.mall.entity.Order;



public interface EggService
{
	/**
	 * 检查获取指定会员当日的砸蛋次数
	 * @param member		会员对象
	 * @return	返回指定会员当日砸金蛋次数
	 */
	int memberDayNumber(Member member, Date date);
	
	/**
	 * 检查指定奖品在指定模式下的已发放数量
	 * @param reward		奖品商品ID
	 * @param mode			抽奖模式
	 * @return	返回指定抽奖模式下当日已发放奖品数量
	 */
	int rewardDayNumber(Long reward, int mode, Date date);
	
	/**
	 * 创建砸金蛋中奖订单
	 * @param prize			获得奖项
	 * @param mode			抽奖模式: 0, 简单模式; 1, 专家模式; 2, 疯狂模式
	 * @return	返回抽奖订单
	 */
	Order createOrder(Member member, int prize, int mode);
	
	/**
	 * 发放指定抽奖模式下的优惠券
	 * @param member		会员对象
	 * @param mode			抽奖模式
	 * @return	发放成功,返回优惠码; 否则, 返回null
	 */
	CouponCode createCouponCode(Member member, int mode);
	
	/**
	 * 以特定的百分比进行摇奖
	 * @param percent		百分比
	 * @return	如果中奖, 返回true; 否则, 返回false
	 */
	boolean isReward(int percent);
	
	/**
	 * 给特定用户在指定抽奖模式下发放奖品
	 * @param member		会员对象
	 * @param mode			抽奖模式
	 * @return	返回奖品索引, 0表示未中奖
	 */
	int createPrize(Member member, int mode);
	
	/**
	 * 创建指定抽奖模式的一个排列
	 * @param mode			抽奖模式
	 * @return	返回抽奖模式的彩蛋排列
	 */
	int[] createArrange(int mode);
	
	/**
	 * 标记会员参加抽奖的模式与次数
	 * @param member		会员对象
	 * @param mode			抽奖模式
	 */
	void markMember(Member member, int mode, Date date);
}
