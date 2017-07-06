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

@Entity
@Table(name = "tb_returns")
public class Return {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id; // 退货id

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate; // 创建时间

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate; // 修改时间

	@Column(name = "address")
	private String address; // 退货地址

	@ManyToOne(targetEntity = Area.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "area")
	private Area area; // 地区

	@ManyToOne(targetEntity = DeliveryCorp.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_corp")
	private DeliveryCorp deliveryCorp; // 物流公司

	@Column(name = "freight")
	private double freight; // 运费

	@Column(name = "memo")
	private String memo; // 备注

	@Column(name = "operator")
	private String operator; // 操作员

	@Column(name = "phone")
	private String phone; // 电话

	@Column(name = "shipper")
	private String shipper; // 发货人

	@ManyToOne(targetEntity = ShippingMethod.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "shipping_method")
	private ShippingMethod shippingMethod; // 物流方式

	@Column(name = "sn")
	private String sn; // 流水号

	@Column(name = "tracking_no")
	private String trackingNo; // 运单号

	@Column(name = "zip_code")
	private String zipCode; // 邮编

	@OneToOne(targetEntity = Apply.class, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "apply")
	private Apply apply; // 申请单

	public Return() {

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public DeliveryCorp getDeliveryCorp() {
		return deliveryCorp;
	}

	public void setDeliveryCorp(DeliveryCorp deliveryCorp) {
		this.deliveryCorp = deliveryCorp;
	}

	public double getFreight() {
		return freight;
	}

	public void setFreight(double freight) {
		this.freight = freight;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public ShippingMethod getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(ShippingMethod shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Apply getApply() {
		return apply;
	}

	public void setApply(Apply apply) {
		this.apply = apply;
	}

}
