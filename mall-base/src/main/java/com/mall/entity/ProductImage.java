package com.mall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_product_image")
public class ProductImage {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;// 编号

	@Column(name = "products")
	private Long products;// 产品

	@Column(name = "image")
	private String image;// 图片

	@Column(name = "title")
	private String title;// 图片标题

	@Column(name = "orders")
	private int orders;// 显示顺序

	public ProductImage() {

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

}
