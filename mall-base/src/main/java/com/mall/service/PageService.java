package com.mall.service;

import java.util.List;

import com.mall.pager.Pager;

public interface PageService<T> {
	/**
	 * 获取分页数据
	 * 
	 * @param pager
	 * @return
	 */
	List<T> findByPager(Pager<T> pager);
}
