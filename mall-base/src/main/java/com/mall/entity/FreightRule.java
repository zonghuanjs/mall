package com.mall.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "tb_freight_rule")
public class FreightRule {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "first_weight")
	private double firstWeight;// 首重重量

	@Column(name = "first_price")
	private double firstPrice;// 首重价格

	@Column(name = "continue_price")
	private double continuePrice;// 续重价格

	@Column(name = "continue_weight")
	private double continueWeight;// 续重重量

	@ManyToOne(targetEntity = ShippingMethod.class, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_shipping_method_rules", joinColumns = @JoinColumn(name = "rules"), inverseJoinColumns = @JoinColumn(name = "methods"))
	private ShippingMethod method;// 配送方式

	@ManyToMany(targetEntity = Area.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "tb_rules_areas", joinColumns = @JoinColumn(name = "rules"), inverseJoinColumns = @JoinColumn(name = "areas"))
	private Set<Area> areas;// 目标地区

	public FreightRule() {
		this.areas = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getFirstWeight() {
		return firstWeight;
	}

	public void setFirstWeight(double firstWeight) {
		this.firstWeight = firstWeight;
	}

	public double getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(double firstPrice) {
		this.firstPrice = firstPrice;
	}

	public double getContinuePrice() {
		return continuePrice;
	}

	public void setContinuePrice(double continuePrice) {
		this.continuePrice = continuePrice;
	}

	public double getContinueWeight() {
		return continueWeight;
	}

	public void setContinueWeight(double continueWeight) {
		this.continueWeight = continueWeight;
	}

	public ShippingMethod getMethod() {
		return method;
	}

	public void setMethod(ShippingMethod method) {
		this.method = method;
	}

	public Set<Area> getAreas() {
		return areas;
	}

	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

}
