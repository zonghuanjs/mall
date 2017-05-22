package com.mall.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mall.entity.Apply;
import com.mall.entity.IDCard;
import com.mall.entity.Member;
import com.mall.entity.Order;
import com.mall.entity.OrderItem;
import com.mall.entity.PaymentMethod;
import com.mall.entity.Product;
import com.mall.entity.Promotion;
import com.mall.entity.ShippingMethod;
import com.mall.entity.UmsNumber;
import com.mall.exception.OrderException;
import com.mall.pager.Pager;
import com.mall.view.CartView;

public interface OrderService extends BaseService<Long, Order> {
	/**
	 * 从购物车创建订单项
	 * 
	 * @param cart
	 *            购物车对象
	 * @return 订单项列表
	 */
	Set<OrderItem> createFromCart(CartView cartView);

	/**
	 * 从购物车创建订单项
	 * 
	 * @param cartView
	 *            购物车选择项
	 * @param isApp
	 *            是否为App客户端
	 * @return 订单项列表
	 */
	Set<OrderItem> createItemsByCart(CartView cartView, boolean isApp);

	/**
	 * 根据商品创建订单项
	 * 
	 * @param product
	 * @param count
	 * @return
	 */
	OrderItem createOrderItemByProduct(Product product, int count);

	/**
	 * 创建普通订单
	 * 
	 * @param items
	 *            订单项列表
	 * @param member
	 *            会员
	 * @param paymentMethod
	 *            支付方式
	 * @param shippingMethod
	 *            配送方式
	 * @param areaName
	 *            地区名称
	 * @param address
	 *            详细地址
	 * @param consignee
	 *            收货人
	 * @param phone
	 *            联系电话
	 * @param zipCode
	 *            邮编
	 * @param couponCode
	 *            优惠券号
	 * @param memo
	 *            订单备注
	 * @return 创建成功,返回订单对象; 创建失败返回null
	 * @throws OrderException
	 */
	Order createCommonOrder(Set<OrderItem> items, Member member, PaymentMethod paymentMethod,
			ShippingMethod shippingMethod, String areaName, String address, String consignee, String phone,
			String zipCode, String couponCode, String memo) throws OrderException;

	/**
	 * 创建商品抢购订单
	 * 
	 * @param product
	 *            抢购商品
	 * @param member
	 *            抢购会员
	 * @param paymentMethod
	 *            支付方式
	 * @param shippingMethod
	 *            物流配送方式
	 * @param memo
	 *            订单备注
	 * @return 创建成功, 返回订单对象; 否则返回null
	 */
	Order createPromotionOrder(Product product, Member member, PaymentMethod paymentMethod,
			ShippingMethod shippingMethod, String areaName, String address, String consignee, String phone,
			String zipCode, Promotion promotion, String memo);

	/**
	 * 创建联通卡(上网卡, 手机卡)订单
	 * 
	 * @param product
	 *            联通卡
	 * @param member
	 *            会员
	 * @param paymentMethod
	 *            支付方式
	 * @param shippingMethod
	 *            物流配送方式
	 * @param memo
	 *            订单备注
	 * @return 创建成功, 返回订单对象; 否则返回null
	 */
	Order createCardOrder(Product product, Member member, PaymentMethod paymentMethod, ShippingMethod shippingMethod,
			String areaName, String address, String consignee, String phone, String zipCode, UmsNumber number,
			IDCard card, String memo);

	/**
	 * 创建售后订单
	 * 
	 * @param apply
	 * @return
	 */
	Order createApplyOrder(Apply apply);

	/**
	 * 取消订单
	 * 
	 * @param order
	 *            订单对象
	 * @param memo
	 *            取消原因
	 * @return true, 订单取消成功; false, 订单取消失败
	 */
	boolean cancelOrder(Order order, String memo);

	/**
	 * 确认收货
	 * 
	 * @param order
	 *            订单对象
	 */
	void confirmOrder(Order order);

	/**
	 * 通过SN号获取订单
	 * 
	 * @param sn
	 *            订单编号
	 * @return 如果存在,返回订单对象; 否则, 返回null
	 */
	Order getFromSn(String sn);

	/**
	 * 订单过期
	 * 
	 * @param order
	 */
	void expiredOrder(Order order);

	/**
	 * 确认订单付款已到账
	 * 
	 * @param order
	 *            订单对象
	 * @param operator
	 *            操作员
	 */
	void confirmOrderPaid(Order order, String operator);

	/**
	 * 获取个人订单总数
	 **/
	long getOrdersCount(Map<String, Object> filter, Map<String, Set<Object>> inSet, String orderby);

	/**
	 * 获取个人订单列表
	 * 
	 * @param filter
	 * @param inSet
	 * @param orderby
	 * @return
	 */
	List<Order> getOrderList(Map<String, Object> filter, Map<String, Set<Object>> inSet, String orderby);

	List<Order> getOrderList(Map<String, Object> filter, String orderby, String propertyName, Object lo, Object hi);

	/**
	 * 查询订单页面数据
	 * 
	 * @param pager
	 *            分页器
	 * @param beginDate
	 *            起始下单时间
	 * @param endDate
	 *            终止下单时间
	 * @return 返回订单分页数据
	 */
	List<Order> findByPager(Pager<Order> pager, Date beginDate, Date endDate);

	/**
	 * 
	 * 根据检索条件 获取订单列表
	 * 
	 * @param memberid
	 *            会员id
	 * @param pageNumber
	 *            第几页 不分页请置-1
	 * @param pageSize
	 *            页大小 不分页请置-1
	 * @param status
	 *            订单状态 不区分订单状态请置8
	 * @param days
	 *            距今多少天内的 不取时间段查询请置-1
	 * @param total
	 *            满足条件的订单数
	 * @return
	 */
	List<Order> getFilterOrders(long memberid, int pageNumber, int pageSize, int status, int days, List<Long> total);
}
