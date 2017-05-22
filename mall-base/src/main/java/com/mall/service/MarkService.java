package com.mall.service;

import com.mall.entity.Member;
import com.mall.entity.Product;
import com.mall.entity.Promotion;

public interface MarkService {
	/**
	 * 记录会员参加促销活动购买过商品
	 * 
	 * @param member
	 *            会员对象
	 * @param promotion
	 *            促销活动
	 * @param product
	 *            商品对象
	 */
	void markMember(Member member, Promotion promotion, Product product, int number);

	/**
	 * 取消会员参加促销活动购买商品的标记
	 * 
	 * @param member
	 *            会员对象
	 * @param promotion
	 *            促销活动
	 * @param product
	 *            商品对象
	 */
	void revokeMark(Member member, Promotion promotion, Product product, int number);

	/**
	 * 查询会员是否有参加促销活动购买商品的标记
	 * 
	 * @param member
	 *            会员对象
	 * @param promotion
	 *            促销活动
	 * @param product
	 *            商品对象
	 * @return 如果有标记, 返回true; 否则, 返回false
	 */
	boolean isMark(Member member, Promotion promotion, Product product);

	/**
	 * 查询会员参与促销活动购买的商品次数
	 * 
	 * @param member
	 * @param promotion
	 * @param product
	 * @return
	 */
	int markNumber(Member member, Promotion promotion, Product product);

	/**
	 * 标记会员参加促销活动
	 * 
	 * @param member
	 *            会员对象
	 * @param promotion
	 *            促销活动
	 */
	void markMember(Member member, Promotion promotion, int number);

	/**
	 * 重置会员标记
	 * 
	 * @param member
	 *            会员对象
	 * @param promotion
	 *            促销活动
	 */
	void resetMember(Member member, Promotion promotion);

	/**
	 * 检测会员参与促销活动的次数
	 * 
	 * @param member
	 *            会员对象
	 * @param promotion
	 *            促销活动
	 * @return 返回会员被标记促销活动的次数
	 */
	int markNumber(Member member, Promotion promotion);
}
