package com.mall.bean;

import com.mall.entity.Product;

public class FreightItem {
	/**
	 * 商品项
	 */
	private Product product;

	/**
	 * 数量
	 */
	private int quantity;

	/**
	 * 是否是移动端
	 */
	private boolean mobile = false;

	public FreightItem() {

	}

	public FreightItem(Product product, int quantity) {
		this(product, quantity, false);
	}

	public FreightItem(Product product, int quantity, boolean isMobile) {
		this.product = product;
		this.quantity = quantity;
		this.mobile = isMobile;
	}

	/**
	 * 获取运费项原始订单价格
	 * 
	 * @return 返回原始订单项价格
	 */
	public double getOrderPrice() {
		double price = 0.0;
		if (product != null && quantity > 0) {
			if (mobile) {
				price = product.getAppPrice() * quantity;
			} else {
				price = product.getPrice() * quantity;
			}
		}
		return price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}
}
