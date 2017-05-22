package com.mall.service;

import java.util.List;
import java.util.Map;

import com.mall.entity.CouponCode;
import com.mall.entity.Member;
import com.mall.entity.Product;
import com.mall.pager.Pager;
import com.mall.view.CartView;

public interface CouponCodeService extends BaseService<Long, CouponCode> {
	/**
	 * 查找会员的有效优惠券
	 * 
	 * @param member
	 *            会员对象
	 * @return 返回会员拥有的有效优惠券列表
	 */
	public List<CouponCode> findValidCodes(Member member, Pager<CouponCode> pager);

	/**
	 * 根据订单总金额查找会员有效优惠券
	 * 
	 * @param member
	 *            会员对象
	 * @param totalPrice
	 *            订单总金额
	 * @return 返回会员适用当前订单的优惠券列表
	 */
	public List<CouponCode> findValidCodes(Member member, List<Product> products, double totalPrice);

	/**
	 * 根据优惠券编码查找优惠券
	 * 
	 * @param code
	 *            优惠券编码
	 * @return 返回优惠券
	 */
	public CouponCode findByCode(String code);

	/**
	 * 检测购物车是否能使用优惠券
	 * 
	 * @param view
	 *            购物车视图
	 * @return 能使用返回true; 否则, 返回false
	 */
	public boolean enableCouponCode(CartView view);

	/**
	 * 查找会员优惠券
	 * 
	 * @param member
	 *            会员对象
	 * @param pager
	 * @param geFilter
	 *            大于等于条件
	 * @param ltFilter
	 *            小于条件
	 * @return 返回会员拥有的优惠券列表
	 */
	public List<CouponCode> findByPager(Member member, Pager<CouponCode> pager, Map<String, Object> geFilter,
			Map<String, Object> ltFilter);

	public long getCount(Member member, Map<String, Object> eqFilter, Map<String, Object> geFilter,
			Map<String, Object> ltFilter);

	/**
	 * 查找会员优惠券
	 * 
	 * @param eqFilter
	 * @param geFilter
	 *            大于等于条件
	 * @param ltFilter
	 *            小于条件
	 * @return 返回会员拥有的优惠券列表
	 */
	public List<CouponCode> getCouponCodeList(Map<String, Object> eqFilter, Map<String, Object> geFilter,
			Map<String, Object> ltFilter, String orderby);

}
