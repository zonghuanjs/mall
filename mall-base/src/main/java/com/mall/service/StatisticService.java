package com.mall.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mall.entity.Order;
import com.mall.pager.Pager;

public interface StatisticService {
	/**
	 * 已绑定手机会员数
	 * 
	 * @return
	 */
	public long mobileBindCount();

	/**
	 * 已验证邮箱会员数
	 * 
	 * @return
	 */
	public long mailValidateCount();

	/**
	 * 一段时间内有登录会员数
	 * 
	 * @param days
	 *            天数
	 * @return
	 */
	public long loginInDaysCount(int days);

	/**
	 * 订单统计
	 * 
	 * @param orderStatus
	 * @return
	 */
	public long orderCount(String propertyName, Object value);

	/**
	 * 订单金额统计
	 * 
	 * @param orderStatus
	 * @return
	 */
	public double orderSalseCount(String propertyName, Object value);

	/**
	 * 退款金额统计
	 * 
	 * @param orderStatus
	 * @return
	 */
	public double refundSalseCount(String propertyName, Object value);

	/**
	 * 时间段金额统计
	 * 
	 * @param orderStatus
	 * @return
	 */
	public Map<String, Object> orderTimeSalsesCount(Date date, int minDay, int maxDay);

	/**
	 * 打印订单
	 * 
	 * @param orders
	 * @return
	 */
	public HSSFWorkbook exportOrder(List<Order> orders);

	/**
	 * 注册会员统计
	 * 
	 * @return
	 */
	public Map<String, Object> registerMemberCount(Date startDate, Date endDate);

	/**
	 * 获取评论的总数和平均分数
	 * 
	 * @param productId
	 * @return key: "totalcount" / "averagescore"
	 */
	public Map<String, Object> getCommentsInfo(long productId);

	/**
	 * 分页获取评论记录信息
	 * 
	 * @param productId
	 *            product的id
	 * @param pageNumber
	 *            当前第几页
	 * @param size
	 *            分页大小
	 * @param order
	 *            排序
	 * @return comment的id
	 */
	public Long[] getCommentsInfo(long productId, int pageNumber, int pageSize, String order);

	/**
	 * 商品交易记录总数
	 */
	public long getTradeRecodesCount(long productId);

	/**
	 * 获取该商品销售的总数 剔除订单取消数量
	 * 
	 * @param productId
	 * @return
	 */
	public long getSalesCount(long productId);

	/**
	 * 分页获取交易记录信息
	 * 
	 * @param productId
	 *            product的id
	 * @param pageNumber
	 *            当前第几页
	 * @param size
	 *            分页大小
	 * @param order
	 *            排序
	 * @return item的id
	 */
	public Long[] getTradeRecodesInfo(long productId, int pageNumber, int pageSize, String order);

	/**
	 * 获取当日从0点开始到现在的登录人数
	 * 
	 * @return 返回当日登录人数
	 */
	public long getTodayLoginCount();

	/**
	 * 根据product id， 获取评论的相关信息
	 * 
	 * @param productid
	 * @return list 顺序：评论总数、平均数、 好评数、中评数、差评数、晒图数
	 */
	List<Long> getCommentsInfoById(long productid, List<Integer> infos);

	/**
	 * 
	 * 分页获取评论信息
	 * 
	 * @param productid
	 * @param type
	 *            'a':所有评价 'p':晒图的评价 'g':好评 'm':中评 'b'：差评
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<Long> getCommentsInfoByPage(long productid, String type, int pageNumber, int pageSize);

	public List<Map<String, Object>> salesRanking(Pager<T> pager, Date startDate, Date endDate);

	/**
	 * 查询指定天数未登录会员总数
	 * 
	 * @param days
	 *            天数
	 * @return 返回会员数量
	 */
	public long memberLogoutDays(int days);
}
