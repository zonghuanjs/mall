package com.mall.dao;

import java.util.Date;

import com.mall.entity.Member;


public interface PeachDao
{
	/**
	 * 检查获取指定会员当日的砸蟠桃次数
	 * @param member		会员对象
	 * @param mode			抽奖模式
	 * @return	返回指定会员当日砸金蛋次数
	 */
	int memberDayNumber(Member member, Date date);
	
	/**
	 * 检查指定奖品在指定模式下的已发放数量
	 * @param reward		奖品商品ID
	 * @return	返回当日已发放奖品数量
	 */
	int rewardDayNumber(Long reward , Date date);
	
}
