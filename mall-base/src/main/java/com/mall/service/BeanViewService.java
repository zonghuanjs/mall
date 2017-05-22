package com.mall.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mall.entity.BeanView;
import com.mall.pager.Pager;

public interface BeanViewService extends BaseService<Date, BeanView> {
	/**
	 * 查找芯豆
	 * 
	 * @param geFilter
	 *            大于等于条件
	 * @param ltFilter
	 *            小于等于条件
	 * @return 返回会员芯豆列表
	 */
	public List<BeanView> findByPager(Pager<BeanView> pager, Map<String, Object> geFilter,
			Map<String, Object> leFilter);

}
