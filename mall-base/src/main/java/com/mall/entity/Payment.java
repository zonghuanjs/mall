package com.mall.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_payment")
public class Payment {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id; // 支付id

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; // 创建时间

	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; // 修改时间

	@Column(name = "account")
	private String account; // 银行账号, 买家支付宝账号

	@Column(name = "amount")
	private double amount; // 金额

	@Column(name = "bank")
	private String bank; // 银行名称, 第三方支付名称

	@Column(name = "expire")
	private Date expire; // 失效时间

	@Column(name = "fee")
	private double fee; // 手续费

	@Column(name = "memo")
	private String memo; // 备注

	@Column(name = "operator")
	private String operator; // 操作员

	@Column(name = "payer")
	private String payer; // 付款人

	@Column(name = "payment_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate; // 支付时间

	@ManyToOne(targetEntity = PaymentMethod.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_method")
	private PaymentMethod paymentMethod; // 支付方式

	@ManyToOne(targetEntity = PluginConfig.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_plugin")
	private PluginConfig paymentPlugin; // 支付插件

	@Column(name = "status")
	private int status; // 支付状态: 0, 支付完成; 1, 等待支付; 2, 第三方平台已收款; 3, 等待发货; 4, 等待收货

	@Column(name = "type")
	private int type; // 支付类型: 0, 即时到账; 1, 担保交易

	@ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "member")
	private Member member; // 会员

	@ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "orders")
	private Order orders; // 支付订单

	@Column(name = "sn")
	private String sn; // 流水号

	public Payment() {

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PluginConfig getPaymentPlugin() {
		return paymentPlugin;
	}

	public void setPaymentPlugin(PluginConfig paymentPlugin) {
		this.paymentPlugin = paymentPlugin;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 支付类型
	 * 
	 * @author ChenMingcai
	 *
	 */
	public static final class PaymentType {
		public static final int direct = 0;// 即时到账
		public static final int partner = 1;// 担保交易
	}

	/**
	 * 支付状态
	 * 
	 * @author ChenMingcai
	 *
	 */
	public static final class PaymentStatus {
		public static final int success = 0;// 支付成功
		public static final int wait_for_pay = 1;// 等待支付
		public static final int wait_for_send = 2;// 等待发货
		public static final int wait_for_confirm = 3;// 等待收货
		public static final int failure = -1;// 支付失败
	}
}
