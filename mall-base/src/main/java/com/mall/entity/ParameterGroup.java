package com.mall.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_parameter_group")
public class ParameterGroup {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;// 编号

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;// 创建时间

	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;// 修改时间

	@Column(name = "orders")
	private int orders;// 显示顺序

	@Column(name = "name")
	private String name;// 名称

	@ManyToOne(targetEntity = ProductCategory.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "product_category")
	private ProductCategory productCategory;// 产品类别

	@OneToMany(targetEntity = Parameter.class, mappedBy = "parameterGroup", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@OrderBy("orders")
	private List<Parameter> parameters;// 参数列表

	public ParameterGroup() {
		this.parameters = new LinkedList<>();
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

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

}
