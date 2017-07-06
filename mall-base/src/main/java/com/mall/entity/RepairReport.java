package com.mall.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_repair_report")
public class RepairReport {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id; // id

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate; // 创建日期

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate; // 修改日期

	@OneToOne(targetEntity = Repair.class, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "repair")
	private Repair repair; // 关联维修单

	@Column(name = "operator")
	private String operator; // 操作员

	@Column(name = "facade")
	private boolean facade; // 外观是否有问题

	@Column(name = "facade_description")
	private String facadeDescription; // 外观问题描述

	@Column(name = "performance")
	private boolean performance; // 是否有问题

	@Column(name = "performance_desc")
	private String performanceDesc; // 性能问题描述

	@Column(name = "free")
	private boolean free; // 是否免费

	@Column(name = "amount")
	private double amount; // 付费金额

	public RepairReport() {

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

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public boolean getFacade() {
		return facade;
	}

	public void setFacade(boolean facade) {
		this.facade = facade;
	}

	public String getFacadeDescription() {
		return facadeDescription;
	}

	public void setFacadeDescription(String facadeDescription) {
		this.facadeDescription = facadeDescription;
	}

	public boolean getPerformance() {
		return performance;
	}

	public void setPerformance(boolean performance) {
		this.performance = performance;
	}

	public String getPerformanceDesc() {
		return performanceDesc;
	}

	public void setPerformanceDesc(String performanceDesc) {
		this.performanceDesc = performanceDesc;
	}

	public boolean getFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
