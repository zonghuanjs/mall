package com.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.mall.util.SpringUtil;

/**
 * 商城订单实体类
 * @author ChenMingcai
 * 2016-01-25
 *
 */
@Entity
@Table(name = "tb_order")
public class Order
{	
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id; //订单id
	
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; //创建时间
	
	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; //修改时间
	
	@Column(name = "consignee")
	private String consignee; //收货人
	
	@Column(name = "address")
	private String address; //收货地址
	
	@Column(name = "amount_paid")
	private double amountPaid; //实付款
	
	@Column(name = "area_name")
	private String areaName; //地区名称
	
	@Column(name = "discount")
	private double discount; //折扣
	
	@Column(name = "fee")
	private double fee; //手续费
	
	@Column(name = "memo")
	private String memo; //备注
	
	@Column(name = "freight")
	private double freight; //运费
	
	@Column(name = "invoice_title")
	private String invoiceTitle; //发票抬头
	
	@Column(name = "is_invoice")
	private boolean isInvoice; //是否开发票
	
	@Column(name = "expire")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expire; //过期时间
	
	@Column(name = "order_status")
	private int orderStatus; //订单状态: 1,未支付; 2, 已支付; 3, 待发货; 4, 已发货; 0, 交易完成; 5,待处理; 6, 退货处理; -1,交易关闭
	
	@ManyToOne(targetEntity = PaymentMethod.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="payment_method")
	private PaymentMethod paymentMethod;
	
	@OneToOne(targetEntity = Payment.class, mappedBy="orders", fetch = FetchType.LAZY)
	private Payment payment; //支付记录
	
	@OneToOne(targetEntity = Refund.class, mappedBy = "order", fetch = FetchType.LAZY)
	private Refund refund;//退款记录
	
	@OneToMany(targetEntity = Apply.class, mappedBy = "orders", fetch = FetchType.LAZY)
	private List<Apply> applys;//售后服务申请
	
	@Column(name = "phone")
	private String phone; //电话号码
	
	@Column(name = "point")
	private int point; //赠送积分
	
	@ManyToOne(targetEntity = Promotion.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "promotion")
	private Promotion promotion; //促销活动
	
	@ManyToOne(targetEntity=ShippingMethod.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "shipping_method")
	private ShippingMethod shippingMethod; //配送方式
	
	@Column(name = "in_erp")
	private boolean inERP; //是否已入ERP系统
	
	@Column(name = "zip_code")
	private String zipCode; //邮政编码
	
	@Column(name = "coupon_code")
	private String couponCode; //优惠券码
	
	@ManyToOne(targetEntity = Member.class, fetch=FetchType.LAZY)
	@JoinColumn(name = "member")
	private Member member; //会员
	
	@Column(name="deleted")
	private boolean deleted;//是否删除

	@Column(name = "sn")
	private String sn; //订单号
	
	@Column(name="type")
	private int type;//订单类型: 1, 普通订单; 2, 售后订单 ; 3,抽奖订单 ; 4,策略订单; 5, 预订订单
	
	@OneToMany(targetEntity=OrderItem.class, mappedBy="orders", cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy("createDate desc")
	private List<OrderItem> items;//订单项列表
	
	@OneToOne(targetEntity=Invoice.class, mappedBy="orders", cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Invoice invoice;//关联发票
	
	@OneToOne(targetEntity=Shipping.class, mappedBy="orders", fetch=FetchType.LAZY)
	private Shipping shipping; //物流记录
	
	@OneToMany(targetEntity=OrderLog.class, mappedBy="orders", fetch=FetchType.LAZY)
	@OrderBy("createDate desc")
	private List<OrderLog> logs;//关联订单日志
	
	@ElementCollection(targetClass=String.class)
	@JoinTable(name="tb_order_attributes", joinColumns=@JoinColumn(name="orders"))
	private Map<String, String> attributes;//附加属性
	
	@OneToOne(targetEntity = IDCard.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_orders_id", joinColumns=@JoinColumn(name="orders"), inverseJoinColumns=@JoinColumn(name="ids"))
	private IDCard ids;//关联身份证
	
	@Transient
	private boolean commented;//是否评价
	
	@Transient
	private Date commentTime;//评论时间
	
	public Order()
	{
		this.items = new LinkedList<>();
		this.logs = new LinkedList<>();
		this.applys = new ArrayList<Apply>();
		this.attributes = new HashMap<>();
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Date getModifyDate()
	{
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate)
	{
		this.modifyDate = modifyDate;
	}

	public String getConsignee()
	{
		return consignee;
	}

	public void setConsignee(String consignee)
	{
		this.consignee = consignee;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public double getAmountPaid()
	{
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid)
	{
		this.amountPaid = amountPaid;
	}

	public String getAreaName()
	{
		return areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	public double getDiscount()
	{
		return discount;
	}

	public void setDiscount(double discount)
	{
		this.discount = discount;
	}

	public double getFee()
	{
		return fee;
	}

	public void setFee(double fee)
	{
		this.fee = fee;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

	public double getFreight()
	{
		return freight;
	}

	public void setFreight(double freight)
	{
		this.freight = freight;
	}

	public String getInvoiceTitle()
	{
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle)
	{
		this.invoiceTitle = invoiceTitle;
	}

	/*
	public boolean isInvoice()
	{
		return isInvoice;
	}

	public void setInvoice(boolean isInvoice)
	{
		this.isInvoice = isInvoice;
	}
	 */
	public Date getExpire()
	{
		return expire;
	}

	public void setExpire(Date expire)
	{
		this.expire = expire;
	}

	public int getOrderStatus()
	{
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus)
	{
		this.orderStatus = orderStatus;
	}

	public PaymentMethod getPaymentMethod()
	{
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	public Payment getPayment()
	{
		return payment;
	}

	public void setPayment(Payment payment)
	{
		this.payment = payment;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public int getPoint()
	{
		return point;
	}

	public void setPoint(int point)
	{
		this.point = point;
	}

	public Promotion getPromotion()
	{
		return promotion;
	}

	public void setPromotion(Promotion promotion)
	{
		this.promotion = promotion;
	}

	public ShippingMethod getShippingMethod()
	{
		return shippingMethod;
	}

	public void setShippingMethod(ShippingMethod shippingMethod)
	{
		this.shippingMethod = shippingMethod;
	}

	public Boolean getInERP()
	{
		return inERP;
	}

	public void setInERP(Boolean inERP)
	{
		this.inERP = inERP;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public String getCouponCode()
	{
		return couponCode;
	}

	public void setCouponCode(String couponCode)
	{
		this.couponCode = couponCode;
	}

	public Member getMember()
	{
		return member;
	}

	public void setMember(Member member)
	{
		this.member = member;
	}

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Invoice getInvoice()
	{
		return invoice;
	}

	public boolean getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(boolean isInvoice) {
		this.isInvoice = isInvoice;
	}

	public void setInvoice(Invoice invoice)
	{
		this.invoice = invoice;
	}
	
	/**
	 * 获取订单主题/名称
	 * @return
	 */
	public String getSubject()
	{
		if(type == Type.salesOrder)
		{
			return "售后服务";
		}
		else
		{
			if(this.items != null && this.items.size() > 0)
				return this.items.iterator().next().getFullName();
			else
				return "";
		}
	}

	public boolean getDeleted()
	{
		return deleted;
	}

	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}

	public Refund getRefund()
	{
		return refund;
	}

	public void setRefund(Refund refund)
	{
		this.refund = refund;
	}
	
	public Shipping getShipping()
	{
		return shipping;
	}

	public void setShipping(Shipping shipping)
	{
		this.shipping = shipping;
	}
	
	public List<OrderLog> getLogs()
	{
		return logs;
	}

	public void setLogs(List<OrderLog> logs)
	{
		this.logs = logs;
	}

	public void setInvoice(boolean isInvoice)
	{
		this.isInvoice = isInvoice;
	}

	public List<Apply> getApplys() {
		return applys;
	}

	public void setApplys(List<Apply> applys) {
		this.applys = applys;
	}
	
	public Map<String, String> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes)
	{
		this.attributes = attributes;
	}

	public IDCard getIds()
	{
		return ids;
	}

	public void setIds(IDCard ids)
	{
		this.ids = ids;
	}

	public boolean getCommented()
	{
		commented = false;
		for(OrderItem item : items)
		{
			if(item.getComment() != null)
			{
				commented = true;
				break;
			}
		}
		return commented;
	}

	public Date getCommentTime()
	{
		commentTime = null;
		if(this.getCommented())
		{
			for(OrderItem item : items)
			{
				if(item.getComment() != null)
				{
					commentTime = item.getComment().getModifyDate();
					break;
				}
			}
		}
		return commentTime;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	@Transient
	public Apply getReturnApply()
	{
		Apply apply = null;
		for(Apply aply : applys)
		{
			if(aply.getType() == Apply.Type.RETURN)
			{
				apply = aply;
				break;
			}
		}
		return apply;
	}
	
	/**
	 * 支付的售后申请单
	 * @return
	 */
	public Apply getRepairPaidApply()
	{
		Apply apply = null;
		if(this.attributes.get(Key.repairApply) != null)
		{
			try
			{
				/*ApplyService service = (ApplyService)SpringUtil.getBean("applyServiceImpl");
				Long applyId = Long.valueOf(this.attributes.get(Key.repairApply));
				apply = service.get(applyId);*/
			}
			catch(Exception ex)
			{
				
			}
			
		}
		return apply;
	}
	
	/**
	 * 订单类型
	 * @author ChenMingcai
	 *
	 */
	public static final class Type
	{
		public static final int commonOrder = 1;//普通订单
		public static final int salesOrder = 2;//售后支付费用订单
		public static final int lotteryOrder = 3;//抽奖订单
		public static final int otherOrder = 4;//策略订单
		public static final int bookOrder = 5;//预订订单
	}
	
	/**
	 * 订单状态
	 * @author ChenMingcai
	 *
	 */
	public static final class OrderStatus
	{
		public static final int CLOSED = -1;//关闭状态
		public static final int FINSHED = 0;//交易完成
		public static final int WAIT_FOR_PAY = 1;//等支付
		public static final int PAID = 2;//支付完成
		public static final int WAIT_FOR_DELIVERY = 3;//等待发货
		public static final int DELIVERYED = 4;//已发货
		public static final int PENDING = 5;//待处理
		public static final int RETURNING = 6;//退货处理
	}
	
	/**
	 * 订单属性Key
	 * @author ChenMingcai
	 *
	 */
	public static final class Key
	{
		public static final String repairApply = "repair_apply";//维修申请单ID
		public static final String shippingPrepare = "shipping_prepare";//商品出库,订单不能修改
		public static final String refundConfirm = "refund_confirm";//财务人员确认退款
	}
	
	/**
	 * 获取订单的状态的描述
	 * @return
	 */
	public String getOrderDesc(){
		
		String desc = "";
		
		switch(this.orderStatus){
		case OrderStatus.CLOSED:
			desc = "交易取消";
			break;
		case OrderStatus.FINSHED:
			desc = "交易完成";
			break;
		case OrderStatus.WAIT_FOR_PAY:
			desc = "待支付";
			break;
		case OrderStatus.PAID:
			desc = "已支付";
			break;
		case OrderStatus.WAIT_FOR_DELIVERY:
			desc = "待发货";
			break;
		case OrderStatus.DELIVERYED:
			desc = "已发货";
			break;
		case OrderStatus.PENDING:
			desc = "待处理";
			break;
		case OrderStatus.RETURNING:
			desc = "退货处理";
			break;
		}
		
		return desc;
	}
	
}
