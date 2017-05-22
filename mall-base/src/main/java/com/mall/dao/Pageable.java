package com.mall.dao;

import java.util.List;

import com.mall.pager.Pager;


public interface Pageable<T>
{
	/**
	 * 获取分页数据
	 * @param pager
	 * @return
	 */
	List<T> findByPager(Pager<T> pager);
}
