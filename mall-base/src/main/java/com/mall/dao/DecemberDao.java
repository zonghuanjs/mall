package com.mall.dao;

import java.util.List;

import com.mall.entity.Member;

public interface DecemberDao {
	/**
	 * 锁定并消耗用户的抢购资格
	 * 
	 * @param member
	 *            会员对象
	 * @return 操作成功, 返回预订订单编号; 否则, 返回null或者空串
	 */
	String lockMemberBook(Member member);

	/**
	 * 检测会员是否在排队队列里面
	 * 
	 * @param member
	 *            会员对象
	 * @return 如果存在队列中，则返回true; 否则, 返回false
	 */
	boolean inFilterList(Member member);

	/**
	 * 获取过滤会员ID列表
	 * 
	 * @return
	 */
	List<Long> filterList();
}
