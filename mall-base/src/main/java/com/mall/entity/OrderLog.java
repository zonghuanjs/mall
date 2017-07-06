package com.mall.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_order_log")
public class OrderLog {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; // 创建时间

	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; // 修改时间

	@Column(name = "content")
	private String content; // 内容

	@Column(name = "operator")
	private String operator; // 操作员

	@Column(name = "type")
	private int type; // 类型 : 0, 订单完成;1，创建订单; 2，取消订单;3，付款 ; 4,订单审核;
						// 5，申请退款;6，订单发货;-1,订单关闭;7,申请售后;8，售后审核及处理;9,

	@ManyToOne(targetEntity = Order.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "orders")
	private Order orders; // 订单

	public OrderLog() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}

	/**
	 * 订单操作类型
	 * 
	 * @author ChenMingcai
	 *
	 */
	public static final class OrderOperationType {
		// 类型 : 0, 订单完成;1，创建订单; 2，取消订单;3，付款 ; 4,订单审核;
		// 5，申请退款;6，订单发货;-1,订单关闭;7,申请售后;8，售后审核及处理;9,
		public static final int success = 0;// 订单完成
		public static final int createOrder = 1;// 创建订单
		public static final int cancelOrder = 2;// 取消订单
		public static final int payOrder = 3;// 订单付款
		public static final int auditOrder = 4;// 订单审核
		public static final int refundOrder = 5;// 订单退款
		public static final int shippingOrder = 6;// 订单发货
		public static final int closeOrder = -1;// 订单关闭
		public static final int applyService = 7;// 申请售后
		public static final int afterSales = 8;// 售后审核及处理

	}
}
