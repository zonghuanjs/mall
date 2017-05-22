package com.mall.service;

import com.mall.entity.Cart;

public interface CartService extends BaseService<Long, Cart> {
	/**
	 * 更新购物车状态
	 */
	void updateStatus(Cart model);

	/**
	 * 清理购物车
	 * 
	 * @param model
	 *            购物车实例
	 */
	void clear(Cart model, Long[] items);

	/**
	 * 从Key查找购物车
	 * 
	 * @param key
	 *            购物车Key
	 * @return 存在返回购物车对象, 否则为null
	 */
	Cart getFromKey(String key);
}
