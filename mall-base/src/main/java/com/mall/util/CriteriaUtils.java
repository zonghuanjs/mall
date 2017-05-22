package com.mall.util;

import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

/**
 * Criteria工具类
 * @author zonghuan
 *
 */

public final class CriteriaUtils
{
	/**
	 * 设置等值条件
	 * @param query    查询
	 * @param filter   等值条件
	 */
	public static void setEQCondition(Criteria query, Map<String, Object> filter)
	{
		if(query == null || filter == null)
			return;
		
		if(filter.isEmpty())
			return;
		
		//设置条件
		Set<String> keys = filter.keySet();
		for(String key : keys)
		{
			if(filter.get(key) != null)
			{
				query.add(Restrictions.eq(key, filter.get(key)));
			}
			else
			{
				query.add(Restrictions.isNull(key));
			}
		}
	}
	
	/**
	 * 设置模糊查询条件
	 * @param query		查询
	 * @param filter	模糊条件
	 */
	public static void setLikeCondition(Criteria query, Map<String, Object> filter)
	{
		if(query == null || filter == null)
			return;
		
		if(filter.isEmpty())
			return;
		
		//设置模糊条件
		Set<String> keys = filter.keySet();
		for(String key : keys)
		{
			if(filter.get(key) != null)
				query.add(Restrictions.like(key, filter.get(key).toString() , MatchMode.ANYWHERE));
		}
	}
	
	/**
	 * 设置查询排序字段
	 * @param query		查询
	 * @param orderBy	排序条件
	 */
	public static void setOrderCondition(Criteria query, String orderBy)
	{
		if(query == null || StringUtils.isEmpty(orderBy))
			return;
		
		//设置排序字段
		String[] orders = orderBy.split(",");
		for(String order : orders)
		{
			Order propertyOrder = null;
			String orderStr = order.trim();
			int idx = orderStr.indexOf(' ');
			if(idx > 0)
			{
				String property = orderStr.substring(0, idx);
				String by = orderStr.substring(idx + 1);
				if(by.equals("asc"))
					propertyOrder = Order.asc(property);
				else if(by.equals("desc"))
					propertyOrder = Order.desc(property);
			}
			if(propertyOrder != null)
			{
				query.addOrder(propertyOrder);
			}
		}
	}
	
	/**
	 * 设置查询In条件集合
	 * @param query		查询
	 * @param inset		IN条件
	 */
	public static void setInCondition(Criteria query, Map<String, Set<Object> > inset)
	{
		if(query == null || inset == null)
			return;
		
		if(inset.isEmpty())
			return;
		
		//设置集合条件
		Set<String> keys = inset.keySet();
		for(String key : keys)
		{
			Set<Object> sets = inset.get(key);
			if(sets != null && !sets.isEmpty())
			{
				query.add(Restrictions.in(key, sets));
			}
		}
	}
	
	/**
	 * 设置大于查询条件
	 * @param query		查询
	 * @param gtFilter	查询条件
	 */
	public static void setGTCondition(Criteria query, Map<String, Object> gtFilter)
	{
		if(query == null || gtFilter == null)
			return;
		
		if(gtFilter.isEmpty())
			return;
		
		//设置分组条件
		Set<String> keys = gtFilter.keySet();
		for(String key : keys)
		{
			if(gtFilter.get(key) != null)
			{
				query.add(Restrictions.gt(key, gtFilter.get(key)));
			}
		}
	}
	
	/**
	 * 设置小于查询条件
	 * @param query		查询
	 * @param ltFilter	查询条件
	 */
	public static void setLTCondition(Criteria query, Map<String, Object> ltFilter)
	{
		if(query == null || ltFilter == null)
			return;
		
		if(ltFilter.isEmpty())
			return;
		
		//设置分组条件
		Set<String> keys = ltFilter.keySet();
		for(String key : keys)
		{
			if(ltFilter.get(key) != null)
			{
				query.add(Restrictions.lt(key, ltFilter.get(key)));
			}
		}
	}
	
	/**
	 * 设置大于等于查询条件
	 * @param query		查询
	 * @param geFilter	查询条件
	 */
	public static void setGECondition(Criteria query, Map<String, Object> geFilter)
	{
		if(query == null || geFilter == null)
			return;
		
		if(geFilter.isEmpty())
			return;
		
		//设置分组条件
		Set<String> keys = geFilter.keySet();
		for(String key : keys)
		{
			if(geFilter.get(key) != null)
			{
				query.add(Restrictions.ge(key, geFilter.get(key)));
			}
		}
	}
	
	/**
	 * 设置小于等于查询条件
	 * @param query		查询
	 * @param leFilter	查询条件
	 */
	public static void setLECondition(Criteria query, Map<String, Object> leFilter)
	{
		if(query == null || leFilter == null)
			return;
		
		if(leFilter.isEmpty())
			return;
		
		//设置分组条件
		Set<String> keys = leFilter.keySet();
		for(String key : keys)
		{
			if(leFilter.get(key) != null)
			{
				query.add(Restrictions.le(key, leFilter.get(key)));
			}
		}
	}
	
	/**
	 * 设置count聚合函数
	 * 
	 * @param query
	 */
	public static void setCountProjection(Criteria query)
	{
		if(query == null)
			return;
		
		//设置聚合函数
		query.setProjection(Projections.rowCount());
	}
}
