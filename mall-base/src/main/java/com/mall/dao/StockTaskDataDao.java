package com.mall.dao;

import java.util.Date;

import com.mall.entity.StockTaskData;


public interface StockTaskDataDao extends BaseDao<Long, StockTaskData>
{
	/**
	 * 根据当前时间查找任务数据
	 * @param date		时间
	 * @return	返回任务数据, 如果存在; 否则, 返回null
	 */
	public StockTaskData findTaskData(Date date);
}
