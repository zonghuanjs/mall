package com.mall.dao;

import com.mall.entity.Product;
import com.mall.entity.Promotion;

public interface PromotionDao extends BaseDao<Long, Promotion>
{
	/**
	 * 获取已发放的token数
	 * @param promotion
	 * @param product
	 * @return
	 */
	public int getTokenCount(Promotion promotion, Product product);
}
