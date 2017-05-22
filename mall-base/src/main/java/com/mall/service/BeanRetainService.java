package com.mall.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mall.entity.BeanRetain;
import com.mall.entity.Comment;
import com.mall.entity.Member;
import com.mall.entity.Order;
import com.mall.pager.Pager;



public interface BeanRetainService extends BaseService<Long, BeanRetain>
{
	/**
	 * 获取特定会员当前有效芯豆总量
	 * @param member		关联会员
	 * @return	返回会员持有的有效芯豆总数
	 */
	int queryTotal(Member member);
	
	/**
	 * 查找芯豆    
	 * @param geFilter      大于等于条件
	 * @param ltFilter      小于等于条件
	 * @return	返回会员芯豆列表
	 */	
	public List<BeanRetain> findByPager(Pager<BeanRetain> pager , Map<String, Object> geFilter , Map<String, Object> leFilter);
	
	/**
	 * 芯豆过期定时执行任务
	 * 
	 * @param count 每次执行数据库update的记录个数，假如有的话
	 */
	void beanExpiredTaskService(int count);
	
	/**
	 * 订单结算时，处理芯豆的业务逻辑
	 * 
	 * @param memberid
	 * @param orderid
	 * @param beans 芯豆的待抵扣个数
	 */
	boolean consumeBeansAccordingtoMember(long memberid, long orderid, int beans);

	/**
	 * 获取特定会员将过期芯豆总量
	 * @param member		关联会员
	 * @return	返回会员持有的有效芯豆总数
	 */
	int queryExpiredTotal(Member member, Date date);
	
	/**
	 * 发放芯豆
	 * @param member	会员对象
	 * @param order	订单对象
	 * @return	返回发放结果
	 */
	boolean issueBean(Order order);
	
	/**
	 * 发放芯豆
	 * @param member	会员对象
	 * @param comment	评论对象
	 * @return	返回发放结果
	 */
	boolean issueBean(Comment comment);

}
