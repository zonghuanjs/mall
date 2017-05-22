package com.mall.service;

import com.mall.entity.Member;
import com.mall.entity.OrderItem;
import com.mall.entity.Product;
import com.mall.entity.Promotion;
import com.mall.exception.PromotionException;

public interface PromotionService extends BaseService<Long, Promotion> {
	/**
	 * 检测促销活动、商品、会员资格是否符合条件
	 * 
	 * @param promotion
	 *            促销活动
	 * @param product
	 *            抢购商品
	 * @param member
	 *            会员
	 * @return 符合条件返回true, 否则返回false
	 */
	public boolean checkCondition(Promotion promotion, Product product, Member member);

	/**
	 * 检测促销活动、订单项、会员资格是否符合条件
	 * 
	 * @param promotion
	 *            促销活动
	 * @param item
	 *            订单项
	 * @param member
	 *            会员对象
	 * @return 可以购买返回true; 否则, 返回false
	 * @throws PromotionException
	 */
	public boolean checkCondition(Promotion promotion, OrderItem item, Member member) throws PromotionException;

	/**
	 * 获取已发放的token数
	 * 
	 * @param promotion
	 * @param product
	 * @return
	 */
	public int getTokenCount(Promotion promotion, Product product);

	/**
	 * 根据促销活动和购买商品处理会员资料
	 * 
	 * @param promotion
	 *            促销活动
	 * @param item
	 *            订单项
	 * @param member
	 *            会员对象
	 * @throws PromotionException
	 */
	public void process(Promotion promotion, OrderItem item, Member member) throws PromotionException;
}
