package com.mall.entity;

import java.util.Date;

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

/**
 * 
 * @author Lijun
 * 退款管理
 */
@Entity
@Table(name = "tb_refunds")
public class Refund
{
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id; //退款id
	
	@Column(name = "sn")
	private String sn; //交易流水号
	
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; // 创建时间
	
	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; //修改时间
	
	@Column(name = "account")
	private String account; //退款账户
	
	@Column(name = "bank")
	private String bank; //退款银行
	
	@Column(name = "amount")
	private double amount; //退款金额
	
	@Column(name = "memo")
	private String memo; //备注
	
	@Column(name = "operator")
	private String operator; //操作员
	
	@Column(name = "payee")
	private String payee; //收款人
	
	@ManyToOne(targetEntity=PaymentMethod.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_method")
	private PaymentMethod paymentMethod; //支付方式
	
	@Column(name = "type")
	private int type; //退款类型: 0, 即时到账; 1, 担保交易
	
	@Column(name="status")
	private int status;//退款状态: 0, 退款完成; 1, 退款处理中; -1, 退款失败
	
	@ManyToOne(targetEntity=Order.class, fetch=FetchType.LAZY)
	@JoinColumn(name = "orders")
	private Order order; //退款订单
	
	@Column(name="other_sn")
	private String otherSn;//对方交易号
	
	public Refund()
	{
		
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
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

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getBank()
	{
		return bank;
	}

	public void setBank(String bank)
	{
		this.bank = bank;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public String getPayee()
	{
		return payee;
	}

	public void setPayee(String payee)
	{
		this.payee = payee;
	}

	public PaymentMethod getPaymentMethod()
	{
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

	public String getOtherSn()
	{
		return otherSn;
	}

	public void setOtherSn(String otherSn)
	{
		this.otherSn = otherSn;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}
	
	
	/**
	 * 显示退款状态
	 * @return
	 */
	public String getStatusView()
	{
		String view = "";
		switch(this.status)
		{
			case Status.success:
				view = "退款成功";
				break;
			case Status.pending:
				view = "退款处理中";
				break;
			case Status.failure:
				view = "退款失败";
				break;
		}
		return view;
	}
	
	/**
	 * 
	 * @author ChenMingcai
	 *
	 */
	public static final class Status
	{
		public static final int success = 0;//退款成功
		public static final int pending = 1;//退款处理中
		public static final int failure = -1;//退款失败
	}
	
	/**
	 * 退款类型
	 * @author ChenMingcai
	 *
	 */
	public static final class Type
	{
		public static final int direct = 0; //即时到账
		public static final int partner = 1; //担保交易
	}
}
