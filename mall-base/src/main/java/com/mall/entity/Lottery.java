package com.mall.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_lottery")
public class Lottery {
	public static final class Status {
		public static final int finished = 0;// 已开奖
		public static final int waiting = 1;// 等待开奖
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;// 实体标识

	@Column(name = "name")
	private String name;// 抽奖活动名称

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;// 创建时间

	@Column(name = "begin_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate;// 开始参与时间

	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;// 参与结束时间

	@ManyToOne(targetEntity = Product.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "product")
	private Product product;// 抽奖商品

	@Column(name = "number")
	private int number;// 参数份数

	@Column(name = "description")
	private String description;// 抽奖活动描述

	@Column(name = "enabled")
	private Boolean enabled;// 是否启用

	@Column(name = "price")
	private double price;// 每份参与价

	@Column(name = "status")
	private int status;// 抽奖活动状态: 0, 已开奖; 1, 等待开奖

	@OneToMany(targetEntity = OrderItem.class, mappedBy = "lottery")
	private Set<OrderItem> items;// 关联订单项

	@OneToMany(targetEntity = Prize.class, mappedBy = "lottery")
	private Set<Prize> prizes;// 关联中奖记录

	public Lottery() {
		this.items = new HashSet<>();
		this.prizes = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	public Set<Prize> getPrizes() {
		return prizes;
	}

	public void setPrizes(Set<Prize> prizes) {
		this.prizes = prizes;
	}
}
