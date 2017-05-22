package com.mall.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mall.entity.Order;
import com.mall.pager.Pager;


public interface OrderDao extends BaseDao<Long, Order>
{
	/*
	 * 获取个人订单总数
	 **/
	public long getOrdersCount(Map<String, Object> filter, Map<String, Set<Object> > inSet,String orderby);
	
	/**
	 * 获取个人订单
	 * @param filter
	 * @param inSet
	 * @param orderby
	 * @return
	 */
	public List<Order> getOrderList(Map<String, Object> filter,Map<String, Set<Object>> inSet, String orderby);
	
	public List<Order> getOrderList(Map<String, Object> filter, String orderby ,String  propertyName , Object lo , Object hi);
	
	/**
	  * 查询订单页面数据
	  * @param pager			分页器
	  * @param beginDate		起始下单时间
	  * @param endDate			终止下单时间
	  * @return	返回订单分页数据
	  */
	 List<Order> findByPager(Pager<Order> pager, Date beginDate, Date endDate);
	 
	 /**
	  * 查询订单页面数据
	  * @param pager			分页器
	  * @param date				时间
	  * @param option			时间选项: -1, 之前; 0, 相等; 1, 之后
	  * @return
	  */
	 List<Order> findByPager(Pager<Order> pager, Date date, int option);

	 /**
	  * 
	  * 根据检索条件 获取订单列表
	  * 
	  * @param memberid 会员id
	  * @param pageNumber 第几页   不分页请置-1
	  * @param pageSize 页大小  不分页请置-1
	  * @param status 订单状态  不区分订单状态请置8
	  * @param days 距今多少天内的   不取时间段查询请置-1
	  * @param total 满足条件的订单数
	  * @return
	  */
	public List<Order> getFilterOrders(long memberid, int pageNumber,
			int pageSize, int status, int days, List<Long> total);
}
