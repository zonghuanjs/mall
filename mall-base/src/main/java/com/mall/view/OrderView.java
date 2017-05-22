package com.mall.view;

import com.mall.entity.Apply;
import com.mall.entity.Order;
import com.mall.entity.Payment;
import com.mall.entity.Payment.PaymentType;
import com.mall.entity.Refund;
import com.mall.entity.Shipping;

public class OrderView {
	private Order order;// 订单数据

	public OrderView(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return this.order;
	}

	/**
	 * 显示订单状态
	 * 
	 * @return
	 */
	public String getStatus() {
		String view = "";

		switch (order.getOrderStatus()) {
		case Order.OrderStatus.CLOSED: {
			view = "交易关闭";
			break;
		}

		case Order.OrderStatus.FINSHED: {
			view = "交易完成";
			break;
		}

		case Order.OrderStatus.WAIT_FOR_PAY: {
			view = "待支付";
			break;
		}

		case Order.OrderStatus.PAID: {
			view = "已支付";
			break;
		}

		case Order.OrderStatus.WAIT_FOR_DELIVERY: {
			view = "待发货";
			break;
		}

		case Order.OrderStatus.DELIVERYED: {
			view = "已发货";
			break;
		}

		case Order.OrderStatus.PENDING: {
			view = "待处理";
			break;
		}

		case Order.OrderStatus.RETURNING: {
			view = "退货处理";
			break;
		}
		}

		return view;
	}

	/**
	 * 是否显示订单过期时间
	 * 
	 * @return
	 */
	public boolean getShowExpireTime() {
		boolean show = order.getOrderStatus() == Order.OrderStatus.WAIT_FOR_PAY;
		return show;
	}

	/**
	 * 显示付款到账确认按钮
	 * 
	 * @return
	 */
	public boolean getConfirmButton() {
		Payment payment = order.getPayment();

		boolean show = false;

		if (payment != null) {
			switch (payment.getType()) {
			case PaymentType.direct: {
				show = order.getOrderStatus() == Order.OrderStatus.PAID && payment != null
						&& payment.getStatus() == Payment.PaymentStatus.success;
				break;
			}

			case PaymentType.partner: {
				show = order.getOrderStatus() == Order.OrderStatus.PAID && payment != null
						&& payment.getStatus() == Payment.PaymentStatus.wait_for_send;
				break;
			}
			}
		}

		return show;
	}

	/**
	 * 显示商品出库按钮
	 * 
	 * @return
	 */
	public boolean getShippingPrepareButton() {
		Payment payment = order.getPayment();

		boolean show = false;

		if (payment != null) {
			switch (payment.getType()) {
			case PaymentType.direct: {
				show = order.getOrderStatus() == Order.OrderStatus.WAIT_FOR_DELIVERY && payment != null
						&& payment.getStatus() == Payment.PaymentStatus.success
						&& order.getAttributes().get(Order.Key.shippingPrepare) == null;
				break;
			}

			case PaymentType.partner: {
				show = order.getOrderStatus() == Order.OrderStatus.WAIT_FOR_DELIVERY && payment != null
						&& payment.getStatus() == Payment.PaymentStatus.wait_for_send
						&& order.getAttributes().get(Order.Key.shippingPrepare) == null;
				break;
			}
			}
		}

		return show;
	}

	/**
	 * 显示订单发货按钮
	 * 
	 * @return
	 */
	public boolean getShippingButton() {
		Payment payment = order.getPayment();

		boolean show = false;

		if (payment != null) {
			switch (payment.getType()) {
			case PaymentType.direct: {
				show = order.getOrderStatus() == Order.OrderStatus.WAIT_FOR_DELIVERY && payment != null
						&& payment.getStatus() == Payment.PaymentStatus.success;
				break;
			}

			case PaymentType.partner: {
				show = order.getOrderStatus() == Order.OrderStatus.WAIT_FOR_DELIVERY && payment != null
						&& payment.getStatus() == Payment.PaymentStatus.wait_for_send;
				break;
			}
			}
		}

		show = show && order.getAmountPaid() == payment.getAmount();

		if (show && order.getAttributes().get(Order.Key.shippingPrepare) != null) {
			show = show && order.getAttributes().get(Order.Key.shippingPrepare).equals("true");
		} else {
			show = false;
		}

		return show;
	}

	/**
	 * 显示退款按钮
	 * 
	 * @return
	 */
	public boolean getRefundButton() {
		Payment payment = order.getPayment();
		Refund refund = order.getRefund();
		Shipping shipping = order.getShipping();

		boolean show = false;
		switch (order.getOrderStatus()) {
		case Order.OrderStatus.PENDING: {
			// 订单取消
			show = payment != null && payment.getStatus() == Payment.PaymentStatus.success && shipping == null
					&& refund != null && refund.getStatus() == Refund.Status.pending
					&& payment.getAmount() >= refund.getAmount();
			break;
		}

		case Order.OrderStatus.RETURNING: {
			Apply apply = order.getReturnApply();

			// 退货处理
			show = payment != null && payment.getStatus() == Payment.PaymentStatus.success && shipping != null
					&& apply != null && refund != null && refund.getStatus() == Refund.Status.pending
					&& payment.getAmount() >= refund.getAmount();
			break;
		}
		}

		return show;
	}

	/**
	 * 显示丢单处理按钮
	 * 
	 * @return
	 */
	public boolean getLostPaymentButton() {
		boolean show = order.getOrderStatus() == Order.OrderStatus.WAIT_FOR_PAY && order.getPayment() == null;
		show = show || (order.getOrderStatus() == Order.OrderStatus.CLOSED && order.getPayment() == null);

		return show;
	}
}
