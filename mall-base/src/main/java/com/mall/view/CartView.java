package com.mall.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.mall.bean.FreightItem;
import com.mall.entity.Cart;
import com.mall.entity.CartItem;
import com.mall.entity.Product;


public class CartView
{
	private Cart cart;//购物车数据
	private Long[] selected;//选择的购物项
	
	public CartView(Cart cart)
	{
		this.cart = cart;
		this.selected = null;
	}
	
	public CartView(Cart cart, Long[] items)
	{
		this.cart = cart;
		this.selected = items;
	}

	public Cart getCart()
	{
		return cart;
	}

	public Long[] getSelected()
	{
		return selected;
	}
	
	/**
	 * 获取结算购物项
	 * @return
	 */
	public List<CartItem> getSelectedItems()
	{
		List<CartItem> items = new LinkedList<>();
		
		if(this.selected != null && this.selected.length > 0)
		{
			Set<Long> selectItems = new HashSet<>(Arrays.asList(this.selected));
			for(CartItem item : cart.getItems())
			{
				if(selectItems.contains(item.getId()))
				{
					items.add(item);
				}
			}
		}
		
		return items;
	}
	
	/**
	 * 获取选择购物项的总价
	 * @return
	 */
	public double getSelectedTotalPrice()
	{
		List<CartItem> items = this.getSelectedItems();
		double totalPrice = 0;
		
		for(CartItem item : items)
		{
			totalPrice += item.getProduct().getPrice() * item.getQuantity();
		}
		
		return totalPrice;
	}
	
	/**
	 * 获取选择购物项的总重量
	 * @return
	 */
	public double getSelectedTotalWeight()
	{
		List<CartItem> items = this.getSelectedItems();
		double totalWeight = 0;
		
		for(CartItem item : items)
		{
			totalWeight += item.getProduct().getWeight() * item.getQuantity();
		}
		
		return totalWeight;
	}
	
	/**
	 * 获取选择商品列表
	 * @return
	 */
	public List<Product> getSelectedProducts()
	{
		List<Product> list = new LinkedList<Product>();
		
		List<CartItem> items = getSelectedItems();
		for(CartItem item : items)
		{
			if(item.getProduct() != null)
			{
				list.add(item.getProduct());
			}			
		}
		
		return list;
	}
	
	/**
	 * 获取选择商品运费计算项列表
	 * @return
	 */
	public List<FreightItem> getSelectedFreightItems()
	{
		List<FreightItem> list = new LinkedList<FreightItem>();
		
		List<CartItem> items = getSelectedItems();
		for(CartItem item : items)
		{
			FreightItem fi = new FreightItem(item.getProduct(), item.getQuantity());
			list.add(fi);
		}
		
		return list;
	}
}
