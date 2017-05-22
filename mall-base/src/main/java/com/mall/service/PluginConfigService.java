package com.mall.service;

import java.util.List;

import com.mall.entity.Order;
import com.mall.entity.PluginConfig;

public interface PluginConfigService extends BaseService<Long, PluginConfig> {
	/**
	 * 通过插件名称获取插件配置
	 * 
	 * @param name
	 *            插件名称
	 * @return 插件配置
	 */
	PluginConfig getFromName(String name);

	/**
	 * 为指定订单查询支持插件
	 * 
	 * @param order
	 *            订单对象
	 * @return 返回适合订单支付的支付方式列表
	 */
	List<PluginConfig> findPlugins(Order order);
}
