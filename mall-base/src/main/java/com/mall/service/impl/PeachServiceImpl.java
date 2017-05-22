package com.mall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.bean.PeachConfig;
import com.mall.bean.SystemConfig;

import com.mall.dao.PeachDao;
import com.mall.entity.Address;
import com.mall.entity.Coupon;
import com.mall.entity.CouponCode;
import com.mall.entity.Member;
import com.mall.entity.Order;
import com.mall.entity.OrderItem;
import com.mall.entity.PaymentMethod;
import com.mall.entity.Product;
import com.mall.entity.ShippingMethod;
import com.mall.entity.SpecificationValue;
import com.mall.service.AreaService;
import com.mall.service.CouponService;
import com.mall.service.MemberService;
import com.mall.service.OrderItemService;
import com.mall.service.OrderService;
import com.mall.service.PaymentMethodService;
import com.mall.service.PeachService;
import com.mall.service.ProductService;
import com.mall.service.ShippingMethodService;
import com.mall.util.FreightUtils;
import com.mall.util.SystemConfigUtil;
import com.mall.util.TimeUtils;

@Service
public class PeachServiceImpl implements PeachService {
	@Resource
	private OrderService oService;// 订单服务

	@Resource
	private OrderItemService itemService;// 订单项服务

	@Resource
	private ProductService prdctService;// 商品服务

	@Resource
	private ShippingMethodService smService;// 物流方式服务

	@Resource
	private PaymentMethodService pmService;// 支付方式服务

	@Resource
	private AreaService areaService;// 地区服务

	@Resource
	private MemberService memberService;// 会员服务

	@Resource
	private CouponService cpService;// 优惠服务

	@Resource
	private PeachDao dao;// 砸金蛋活动数据访问接口

	@Override
	public int memberDayNumber(Member member, Date date) {
		int count = 0;
		if (member != null && date != null) {

			count = dao.memberDayNumber(member, date);
			;
		}
		return count;
	}

	@Override
	public int rewardDayNumber(Long reward, Date date) {
		int count = 0;
		count = dao.rewardDayNumber(reward, date);
		return count;
	}

	@Override
	public Order createOrder(Member member, int prize) {
		if (member == null || prize == 0) {
			return null;
		}

		Long productId = new Long(PeachConfig.rewards[prize][0]);
		Product product = prdctService.get(productId);
		if (product == null) {
			return null;
		}
		if (product.getStock() <= 0) {
			return null;
		}
		Address addr = member.defaultAddress();
		if (addr == null) {
			return null;
		}

		PaymentMethod pm = pmService.getAll().iterator().next();
		ShippingMethod sm = smService.getAll().iterator().next();

		Order order = new Order();
		order.setMember(member);
		order.setAddress(addr.getAddress());
		order.setAreaName(addr.getAreaName().getFullName());
		order.setConsignee(addr.getConsignee());
		order.setPhone(addr.getPhone());
		order.setZipCode(addr.getZipCode());
		order.setPaymentMethod(pm);
		order.setShippingMethod(sm);
		order.setMemo("打蟠桃活动");
		order.setIsInvoice(false);
		order.setType(Order.Type.commonOrder);
		order.setOrderStatus(Order.OrderStatus.WAIT_FOR_PAY);

		SystemConfig config = SystemConfigUtil.getSystemConfig();
		order.setExpire(TimeUtils.delayTime(new Date(), config.getOrderExpiredTime()));

		double total = PeachConfig.prices[prize];

		// 计算折扣
		double discount = product.getPrice() - PeachConfig.prices[prize];
		order.setDiscount(discount);

		// 计算运费
		if (total < config.getFreightLimitation()) {
			double freight = FreightUtils.computeFreight(sm, areaService.findTopArea(addr.getAreaName()),
					product.getWeight() / 1000);
			order.setFreight(freight);
			total += freight;
		} else {
			order.setFreight(0);
		}

		order.setAmountPaid(total);

		if (oService.add(order)) {
			OrderItem item = new OrderItem();
			item.setFullName(product.getName());
			item.setName(product.getName());
			item.setProduct(product);
			item.setGift(false);
			item.setPrice(product.getPrice());
			item.setOrders(order);
			item.setThumbnail(product.getThumbnail());
			item.setQuantity(1);
			item.setWeight(product.getWeight());

			// 保存规格值
			if (!product.getSpecificationValues().isEmpty()) {
				StringBuilder builder = new StringBuilder();
				for (SpecificationValue value : product.getSpecificationValues())
					builder.append("," + value.getName());
				builder.deleteCharAt(0);
				item.setSpecification(builder.toString());
			}

			if (!itemService.add(item)) {
				oService.delete(order.getId());
				order = null;
			} else {
				// 订单标记
				StringBuffer keyBuilder = new StringBuffer(PeachConfig.prefix);
				keyBuilder.append(productId).append("_").append(TimeUtils.formatDate("yyyyMMdd", new Date()));
				order.getAttributes().put(keyBuilder.toString(), member.getId() + "");
				oService.update(order);
				oService.refreshObject(order);
			}
		}

		return order;
	}

	@Override
	public int createPrize(Member member) {
		int prize = 0;

		ArrayList<Integer> array = findPrizes();
		if (array.size() > 0) {
			Random random = new Random();
			int idx = random.nextInt(array.size());
			prize = array.get(idx);
		}

		return prize;
	}

	/**
	 * 查找可以抽取的奖品
	 * 
	 * @return
	 */
	protected ArrayList<Integer> findPrizes() {
		ArrayList<Integer> prizes = new ArrayList<>();

		int[] allPrizes = PeachConfig.peachRewards;

		// 检测奖品当日是否可用
		for (int i = 0; i < allPrizes.length; i++) {
			int idx = allPrizes[i];
			int num = rewardDayNumber(new Long(PeachConfig.rewards[idx][0]), new Date());
			int limit = PeachConfig.rewards[idx][1];

			if (limit > num) {
				prizes.add(idx);
			}
		}

		return prizes;
	}

	@Override
	public synchronized void markMember(Member member, Date date) {
		int number = this.memberDayNumber(member, date);
		String key = "PEACH_" + TimeUtils.formatDate("yyyyMMdd", date);
		if (number + 1 <= PeachConfig.dayLimitMember) {
			int newNumber = dao.memberDayNumber(member, date) + 1;
			member.getAttributes().put(key, newNumber + "");
			memberService.update(member);
		}
	}

	@Override
	public CouponCode createCouponCode(Member member) {
		Long couponId = null;

		Random random = new Random();

		int idx = random.nextInt(PeachConfig.peachCoupons.length);
		couponId = new Long(PeachConfig.peachCoupons[idx]);

		CouponCode code = null;
		Coupon coupon = cpService.get(couponId);
		if (coupon != null) {
			code = cpService.issueCode(coupon, member);
		}

		return code;
	}
}
