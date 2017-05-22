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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Lijun
 *
 */
@Entity
@Table(name = "tb_shipping")
public class Shipping
{
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id; //物流id
	
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; //创建时间
	
	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; //修改时间
	
	@Column(name = "address")
	private String address; //配送地址
	
	@Column(name = "area")
	private String area; //配送区域
	
	@Column(name = "consignee")
	private String consignee; //收件人
	
	@Column(name = "delivery_corp")
	private String deliveryCorp; //物流公司
	
	@Column(name = "delivery_corp_code")
	private String deliveryCorpCode; //物流公司代码
	
	@Column(name = "delivery_corp_url")
	private String deliveryCorpUrl; //物流公司网址
	
	@Column(name = "freight")
	private double freight; //运费
	
	@Column(name = "memo")
	private String memo; //备注
	
	@Column(name = "operator")
	private String operator; //操作员
	
	@Column(name = "phone")
	private String phone; //电话
	
	@ManyToOne(targetEntity=ShippingMethod.class, cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinColumn(name = "shipping_method")
	private ShippingMethod shippingMethod; //配送方式
	
	@Column(name = "sn")
	private String sn; //流水号
	
	@Column(name = "tracking_no")
	private String trackingNo; //运单号
	
	@Column(name = "zip_code")
	private String zipCode; //邮政编号
	
	@OneToOne(targetEntity=Order.class, cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinColumn(name="orders")
	private Order orders; //配送订单

	public Shipping()
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

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getConsignee()
	{
		return consignee;
	}

	public void setConsignee(String consignee)
	{
		this.consignee = consignee;
	}

	public String getDeliveryCorp()
	{
		return deliveryCorp;
	}

	public void setDeliveryCorp(String deliveryCorp)
	{
		this.deliveryCorp = deliveryCorp;
	}

	public String getDeliveryCorpCode()
	{
		return deliveryCorpCode;
	}

	public void setDeliveryCorpCode(String deliveryCorpCode)
	{
		this.deliveryCorpCode = deliveryCorpCode;
	}

	public String getDeliveryCorpUrl()
	{
		return deliveryCorpUrl;
	}

	public void setDeliveryCorpUrl(String deliveryCorpUrl)
	{
		this.deliveryCorpUrl = deliveryCorpUrl;
	}

	public double getFreight()
	{
		return freight;
	}

	public void setFreight(double freight)
	{
		this.freight = freight;
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

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public ShippingMethod getShippingMethod()
	{
		return shippingMethod;
	}

	public void setShippingMethod(ShippingMethod shippingMethod)
	{
		this.shippingMethod = shippingMethod;
	}

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public String getTrackingNo()
	{
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo)
	{
		this.trackingNo = trackingNo;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public Order getOrders()
	{
		return orders;
	}

	public void setOrders(Order orders)
	{
		this.orders = orders;
	}
	
}
