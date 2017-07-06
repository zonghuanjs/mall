package com.mall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_product_parameter_value")
public class ProductParameterValue {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id; // 商品参数id

	@Column(name = "products")
	private Long products; // 商品id

	@Column(name = "parameters")
	private Long parameters; // 参数id

	@Column(name = "value")
	private String value; // 参数值

	public ProductParameterValue() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProducts() {
		return products;
	}

	public void setProducts(Long products) {
		this.products = products;
	}

	public Long getParameters() {
		return parameters;
	}

	public void setParameters(Long parameters) {
		this.parameters = parameters;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
