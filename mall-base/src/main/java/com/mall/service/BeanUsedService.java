package com.mall.service;

import java.util.List;
import java.util.Map;

import com.mall.entity.BeanUsed;
import com.mall.pager.Pager;

public interface BeanUsedService extends BaseService<Long, BeanUsed> {

	/**
	 * 查找芯豆
	 * 
	 * @param geFilter
	 *            大于等于条件
	 * @param ltFilter
	 *            小于等于条件
	 * @return 返回会员芯豆列表
	 */
	public List<BeanUsed> findByPager(Pager<BeanUsed> pager, Map<String, Object> geFilter,
			Map<String, Object> leFilter);

}
