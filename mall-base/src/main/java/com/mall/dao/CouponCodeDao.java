package com.mall.dao;

import java.util.List;
import java.util.Map;

import com.mall.entity.CouponCode;
import com.mall.entity.Member;
import com.mall.pager.Pager;

public interface CouponCodeDao extends BaseDao<Long, CouponCode>
{
	/**
	 * 查找会员的有效优惠券
	 * @param member		会员对象
	 * @return	返回会员拥有的有效优惠券列表
	 */
	public List<CouponCode> findValidCodes(Member member , Pager<CouponCode> pager);
	
	/**
	 * 查找会员优惠券
	 * @param member		会员对象
	 * @param pager         
	 * @param geFilter      大于等于条件
	 * @param ltFilter      小于条件
	 * @return	返回会员拥有的优惠券列表
	 */	
	public List<CouponCode> findByPager(Member member , Pager<CouponCode> pager , Map<String, Object> geFilter , Map<String, Object> ltFilter);
	
	public long getCount(Member member , Map<String, Object> eqFilter , Map<String, Object> geFilter , Map<String, Object> ltFilter);
	
	/**
	 * 查找会员优惠券
	 * @param eqFilter         
	 * @param geFilter      大于等于条件
	 * @param ltFilter      小于条件
	 * @return	返回会员拥有的优惠券列表
	 */	
	public List<CouponCode> getCouponCodeList(Map<String, Object> eqFilter , Map<String, Object> geFilter , Map<String, Object> ltFilter, String orderby);
	
}
