package com.mall.util;

import java.util.List;

import com.mall.bean.FreightItem;
import com.mall.bean.SystemConfig;
import com.mall.entity.Area;
import com.mall.entity.FreightRule;
import com.mall.entity.Product;
import com.mall.entity.ShippingMethod;



public class FreightUtils
{	
	/**
	 * 根据特定运输方式计算部分商品计算到目标地区的运费（请使用此方法计算运费）
	 * @param products		商品列表
	 * @param method		运输方式
	 * @param area			目标地区
	 * @return	返回运费
	 */
	public static double computeFreight(List<FreightItem> items, ShippingMethod method, Area area)
	{
		double freight = 0;
		
		if(!free(items))
		{
			double weight = weight(items);
			freight = computeFreight(method, area, weight / 1000);
		}
		
		return freight;
	}
	
	/**
	 * 根据特定运输方式计算到目标地区商品重量的运费
	 * @param method		配送方式
	 * @param area			目标地区
	 * @param weight		商品重量(单位: kg)
	 * @return
	 */
	public static double computeFreight(ShippingMethod method, Area area, double weight)
	{
		double freight = 0;
		if(method != null && area != null)
		{
			FreightRule rule = FreightUtils.findRule(method, area);
			if(rule != null)
			{
				freight = FreightUtils.computeFreight(rule, weight);
			}
		}
		return freight;
	}
	
	/**
	 * 根据指定规则计算到目标地区, 指定重量的运费
	 * @param rule		运费规则
	 * @param weight	商品重量(单位：kg)
	 * @return	返回计算后的运费
	 */
	public static double computeFreight(FreightRule rule, double weight)
	{
		double freight = 0;
		
		if(rule == null)
		{
			return freight;
		}
		
		double firstWeight = rule.getFirstWeight();
		double firstPrice = rule.getFirstPrice();
		double continueWeight = rule.getContinueWeight();
		double continuePrice = rule.getContinuePrice();
		
		if(weight <= firstWeight)
		{
			freight = firstPrice;
		}
		else
		{
			double count = (weight - firstWeight) / continueWeight;
			freight = firstPrice + Math.ceil(count) * continuePrice;
		}
		
		return freight;
	}
	
	/**
	 * 根据目标地区查找运费计算规则
	 * @param method		配送方式
	 * @param area			目标地区
	 * @return	返回运费计算规则
	 */
	public static FreightRule findRule(ShippingMethod method, Area area)
	{
		FreightRule rule = null;
		if(method != null && area != null)
		{
			for(FreightRule rl : method.getRules())
			{
				if(rl.getAreas().contains(area))
				{
					rule = rl;
					break;
				}
			}
		}
		return rule;
	}
	
	/**
	 * 判断是否需要收运费
	 * @param items		商品列表
	 * @return	需要收运费, 返回true; 否则返回false
	 */
	protected static boolean free(List<FreightItem> items)
	{
		boolean free = false;
		double totalPrice = 0.0;
		
		SystemConfig config = SystemConfigUtil.getSystemConfig();
		double limit = config.getFreightLimitation();
		
		for(FreightItem item : items)
		{
			Product product = item.getProduct();
			if(product != null)
			{
				totalPrice += item.getOrderPrice();				
				if(product.isFreePost() || totalPrice >= limit)
				{
					free = true;
					break;
				}
			}
			
		}
		
		return free;
	}

	/**
	 * 计算商品列表总重量(单位：g)
	 * @param items		商品列表
	 * @return
	 */
	protected static double weight(List<FreightItem> items)
	{
		double wght = 0.0;
		
		for(FreightItem item : items)
		{
			Product product = item.getProduct();
			if(product != null)
			{
				wght += product.getWeight() * item.getQuantity();
			}
			
		}
		
		return wght;
	}
}
