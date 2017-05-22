package com.mall.service;

import com.mall.entity.Member;
import com.mall.entity.Order;
import com.mall.entity.Product;

public interface BookService
{
	/**
	 * 创建预订订单
	 * @param member		会员对象
	 * @return	创建成功, 返回订单对象; 否则, 返回null
	 */
	Order createBookOrder(Member member);
	
	/**
	 * 检测会员是否具有双11商品低价预订资格
	 * @param member		会员对象
	 * @return	返回检测结果代码
	 * 0: 检测通过; 1: 预订尚未开始; 2: 预订已结束; 3: 未绑定手机; 4: 未验证邮箱; 5: 已经预定; 6: 请支付定金
	 */
	int checkBookOrder(Member member);
	
	/**
	 * 生成双11购买订单
	 * @param member			会员对象
	 * @param product			商品对象
	 * @return	创建成功, 返回订单对象; 否则, 返回null
	 */
	Order createBuyOrder(Member member, Product product);
	
	/**
	 * 检测会员是否具有双11购买资格
	 * @param member		会员对象
	 * @return	返回检测结果代码
	 * 0: 检测通过; 1: 活动未开始; 2: 活动已结束; 3: 未绑定手机; 4: 未验证邮箱; 5: 已抢光; 6: 未预订或已经购买; 10: 参数错误
	 */
	int checkBuyOrder(Member member, Product product);
}
