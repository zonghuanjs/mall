package com.mall.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_specification")
public class Specification {
	/**
	 * 规格类别
	 * 
	 * @author ChenMingcai
	 *
	 */
	public static class Type {
		public static final int TEXT = 1;// 文本
		public static final int IMAGE = 2;// 图片
		public static final int SELECT = 3;// 复选框
	}

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;// 规格编号

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;// 创建时间

	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;// 修改时间

	@Column(name = "memo")
	private String memo;//

	@Column(name = "orders")
	private int orders;// 显示顺序

	@Column(name = "name")
	private String name;// 规格名称

	@Column(name = "type")
	private int type;// 规格类别

	@OneToMany(targetEntity = SpecificationValue.class, mappedBy = "specification", fetch = FetchType.LAZY)
	@OrderBy("orders")
	private List<SpecificationValue> values;// 规格值列表

	public Specification() {
		this.values = new LinkedList<>();
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<SpecificationValue> getValues() {
		return values;
	}

	public void setValues(List<SpecificationValue> values) {
		this.values = values;
	}
}
