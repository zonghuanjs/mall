package com.mall.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_attribute")
public class Attribute {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;// 属性编号

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;// 创建时间

	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;// 修改时间

	@Column(name = "orders")
	private int orders;// 显示顺序

	@Column(name = "name")
	private String name;// 属性名称

	@Column(name = "propoerty_index")
	private int propoertyIndex;//

	@Column(name = "product_category")
	private Long productCategory;//

	public Attribute() {

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

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPropoertyIndex() {
		return propoertyIndex;
	}

	public void setPropoertyIndex(int propoertyIndex) {
		this.propoertyIndex = propoertyIndex;
	}

	public Long getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Long productCategory) {
		this.productCategory = productCategory;
	}

}
