package com.mall.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;

import com.mall.pager.Pager;


/**
 * 统计数据接口
 * @author ChenMingcai
 *
 */
public interface StatisticDao
{
	/**
	 * 已绑定手机会员数
	 * @return
	 */
	public long mobileBindCount();
	
	/**
	 * 已验证邮箱会员数
	 * @return
	 */
	public long mailValidateCount();
	
	/**
	 * 一段时间内有登录会员数
	 * @param days	天数
	 * @return
	 */
	public long loginInDaysCount(int days);
	
	
	/**
	 * 订单金额统计
	 * @param orderStatus
	 * @return
	 */
	public double orderSalseCount(String propertyName , Object value);
	
	/**
	 * 退款金额统计
	 * @param orderStatus
	 * @return
	 */
	public double refundSalseCount(String propertyName , Object value);
			
	/**
	 * 时间段金额统计
	 * @param date
	 * @param minDay
	 * @param maxDay
	 * @return
	 */
	public Map<String, Object> orderTimeSalseCount(Date date , int minDay ,int maxDay);
	
	/**
	 * 注册会员统计
	 * @return
	 */
	public Map<String, Object> registerMemberCount(Date startDate , Date endDate);

	/**
	 * 获取评论的总数和平均分数
	 * @param productId
	 * @return  key: "totalcount"  / "averagescore"
	 */
	public Map<String, Object> getCommentsInfo(long productId);
	
	/**
	 * 分页获取评论记录信息
	 * 
	 * @param productId product的id
	 * @param pageNumber 当前第几页
	 * @param size 分页大小
	 * @param order 排序
	 * @return comment的id
	 */
	public Long[] getCommentsInfo(long productId, int pageNumber, int pageSize, String order);

	/**
	 * 商品交易记录总数
	 * @param productId 
	 */
	public long getTradeRecodesCount(long productId);
	
	
	/**
	 * 分页获取交易记录信息
	 * 
	 * @param productId product的id
	 * @param itemIds 返回的itemIds 
	 * @param pageNumber 当前第几页
	 * @param size 分页大小
	 * @param order 排序
	 * @return 数据库中总数 
	 */
	public Long[] getTradeRecodesInfo(long productId, int pageNumber, int pageSize, String order);

	/**
	 * 获取该商品销售的总数 剔除订单取消数量
	 * @param productId
	 * @return
	 */
	public long getSalesCount(long productId);

	/**
	 * 获取当日从0点开始到现在的登录人数
	 * @return	返回当日登录人数
	 */
	public long getLoginCountAfter(Date date);
	
	/**
	 *  根据id， 获取评论的相关信息
	 *  
	 * @param productid
	 * @param info 
	 * @return list  顺序：评论总数、平均数、 好评数、中评数、差评数、晒图数
	 */
	List<Long> getCommentsInfoById(long productid, List<Integer> info);

	public List<Long> getCommentsInfoByPage(long productid, String type,
			int pageNumber, int pageSize);
	/**
	 * 销售排行
	 * @param pager
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String , Object>> salesRanking(Pager<T> pager, Date startDate , Date endDate);
	
	/**
	 * 查询指定时间之后未登录系统的会员总数
	 * @param lastLoginDate	时间
	 * @return	返回数量
	 */
	public long  memberCountLoginBeforeTime(Date lastLoginDate);	
}
