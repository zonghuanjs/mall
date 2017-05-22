package com.mall.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.mall.bean.SystemConfig;
import com.mall.entity.Order;
import com.mall.entity.OrderItem;
import com.mall.entity.Product;
import com.mall.entity.Promotion;
import com.mall.util.CommonUtil;
import com.mall.util.SystemConfigUtil;
import com.mall.util.TimeUtils;



public class ProductView
{
	private Product product;//商品对象
	private SystemConfig config;//系统配置
	
	public ProductView(Product product)
	{
		this.product = product;
		config = SystemConfigUtil.getSystemConfig();
	}

	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}
	
	/**
	 * 是否显示市场价
	 * @return
	 */
	public boolean getShowMarketPrice()
	{
		boolean show = config.isShowMarketPrice();
		
		return show;
	}
	
	/**
	 * 显示市场价
	 * @return
	 */
	public String getMarketPrice()
	{
		String price = "";
		if(product != null && product.getMarketPrice() != 0.0)
		{
			StringBuffer builder = new StringBuffer(config.getCurrencySign());
			//builder.append(CommonUtil.formatCurrency(product.getMarketPrice()));
			price = builder.toString();
		}
		return price;
	}
	
	/**
	 * 显示价格
	 * @return
	 */
	public String getPrice()
	{
		String price = "";
		if(product != null)
		{
			//price = config.getCurrencySign() + CommonUtil.formatCurrency(product.getPrice());
		}
		return price;
	}
	
	
	/**
	 * 显示当前商品交易成功数量
	 * 
	 * @return 交易成功数量
	 */
	public int getTransCount(){
		
		List<OrderItem> itemsTotal = new LinkedList<>(product.getOrderItems());
		List<OrderItem> items = new ArrayList<OrderItem>();
		for(OrderItem item : itemsTotal)
		{
			//去掉已取消的订单
			if(item.getOrders().getOrderStatus() != Order.OrderStatus.CLOSED)
			{
				items.add(item);
			}
		}
		return items.size();
	}
	
	/**
	 * 获取当前有效促销活动列表
	 * @return
	 */
	public List<Promotion> getPromotions()
	{
		List<Promotion> promotions = new LinkedList<>();
		Iterator<Promotion> iter = product.getPromotions().iterator();
		Date now = new Date();
		while(iter.hasNext())
		{
			Promotion promotion = iter.next();
			
			String enabled = promotion.getAttributes().get(Promotion.Keys.isEnabled);
			if(enabled == null || !enabled.equals("true"))
			{
				continue;
			}
			
			Date showDate = TimeUtils.delayTime(promotion.getBeginDate(), -360);
			if(now.getTime() >= showDate.getTime() && now.getTime() <= promotion.getEndDate().getTime())
			{
				promotions.add(promotion);
			}
		}
		return promotions;
	}
	
	/**
	 * 获取当前抢购活动
	 * @return
	 */
	public Promotion getCurrentPromotion()
	{
		Promotion currentPromotion = null;
		Iterator<Promotion> iter = product.getPromotions().iterator();
		Date now = new Date();
		while(iter.hasNext())
		{
			Promotion promotion = iter.next();
			
			String enabled = promotion.getAttributes().get(Promotion.Keys.isEnabled);
			if(enabled == null || !enabled.equals("true"))
			{
				continue;
			}
			Date showDate = TimeUtils.delayTime(promotion.getBeginDate(), -360);
			if(now.getTime() >= showDate.getTime() && now.getTime() <= promotion.getEndDate().getTime())
			{
				currentPromotion = promotion;
				break;
			}
		}
		
		return currentPromotion;
	}
}
