package com.mall.dao;

import java.util.Date;

import com.mall.entity.Member;


public interface RedPacketsDao
{

	/**
	 * 检查获取指定会员当日次数
	 * @param member		会员对象
	 * @param mode			抽奖模式
	 * @return	返回指定会员当日次数
	 */
	int memberDayNumber(Member member , Date date);
}
