package com.mall.dao;

import java.util.Date;
import java.util.List;

import com.mall.entity.BrowsingHistory;

public interface BrowsingHistoryDao extends BaseDao<Long, BrowsingHistory> {

	/**
	 * 获取用户的浏览记录
	 * 
	 * @param memberid
	 *            会员id
	 * @param ip
	 *            会员的IP
	 * @param begin
	 *            起始时间
	 * @param end
	 *            结束时间
	 * @param pagenumber
	 *            分页的 第几页
	 * @param pagesize
	 *            分页的分页大小
	 * @param count
	 *            浏览记录总数
	 * @return 浏览记录集合
	 */
	List<BrowsingHistory> getBrowsingHistories(Long memberid, String ip, Date begin, Date end, int pagenumber,
			int pagesize, List<Long> count);

	/**
	 * 判断商品是否已经存在数据库
	 * 
	 * @param memberid
	 *            会员id
	 * @param ip
	 *            会员IP
	 * @param dt
	 *            指定日期
	 * @param pid
	 *            商品id
	 * @param ids
	 *            存在的记录集合
	 * @return true:存在 false:不存在
	 */
	boolean judgeProductExists(Long memberid, String ip, Date dt, Long pid, List<Long> ids);

	List<Long> getProductsAccordingtoHistories(Long memberid, String ip, int pagenumber, int pagesize,
			List<Integer> count);
}
