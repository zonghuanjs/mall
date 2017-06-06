package com.mall.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.mall.entity.Product;

public class ProductAdminUtils
{	
	/**
	 * 创建新的包含默认值商品
	 * @return	返回默认空白商品
	 */
 	public static Product createNewProduct()
	{
		Product model = new Product();
		
		model.setHits(0L);
		model.setScore(0.0F);
		model.setScoreCount(0L);
		model.setWeekHits(0L);
		model.setMonthHits(0L);
		model.setSales(0L);
		model.setWeekSales(0L);
		model.setMonthSales(0L);
		model.setWeekHitsdate(new Date());
		model.setMonthHitsdate(new Date());
		model.setWeekSalesdate(new Date());
		model.setMonthSalesdate(new Date());
		
		return model;
	}

	/**
	 * 将请求参数填充到商品对象
	 * @param product		商品对象
	 * @param request		Http请求
	 */
	public static void fillProduct(Product product, HttpServletRequest request)
	{
		if(product == null)
		{
			return;
		}
		
		
		//设置基本参数
		String type = request.getParameter("type");
		if(type != null && !"".equals(type))
		{
			product.setType(RequestUtil.getIntParameter(request, "type"));
		}
		
		//名称
		String name = request.getParameter("name");
		if(name != null && !"".equals("name"))
		{
			product.setName(name.trim());
		}
		
		//价格
		String price = request.getParameter("price");
		if(price != null && !"".equals("price"))
		{
			product.setPrice(RequestUtil.getDoubleParameter(request, "price"));
		}
		
		String appPrice = request.getParameter("app_price");
		if(appPrice != null && !"".equals(appPrice))
		{
			product.setAppPrice(RequestUtil.getDoubleParameter(request, "app_price"));
		}
		
		//成本价
		String cost = request.getParameter("cost");
		if(cost != null && !"".equals(cost))
		{
			product.setCost(RequestUtil.getDoubleParameter(request, "cost"));
		}
		
		//市场价
		String marketPrice = request.getParameter("marketPrice");
		if(marketPrice != null && !"".equals(marketPrice))
		{
			product.setMarketPrice(RequestUtil.getDoubleParameter(request, "marketPrice"));
		}
		
		//重量
		String weight = request.getParameter("weight");
		if(weight != null && !"".equals(weight))
		{
			product.setWeight(RequestUtil.getDoubleParameter(request, "weight"));
		}
		
		//库存
		String stock = request.getParameter("stock");
		if(stock != null && !"".equals("stock"))
		{
			product.setStock(RequestUtil.getIntParameter(request, "stock"));
		}
		
		//积分
		String point = request.getParameter("point");
		if(point != null && !"".equals(point))
		{
			product.setPoint(RequestUtil.getLongParameter(request, "point"));
		}
		
		String warehouseStr = request.getParameter("warehouse");
		if(warehouseStr != null)
		{
			try
			{
				//Warehouse warehouse = Warehouse.valueOf(warehouseStr);
				//product.setWarehouse(warehouse.getValue());
			}
			catch(IllegalArgumentException ex)
			{
				ex.printStackTrace();
			}
			
		}
		
		product.setImage(request.getParameter("images"));
		product.setMediumImage(request.getParameter("mediumImage"));
		product.setUnit(request.getParameter("unit"));
		
		product.setMarketable(RequestUtil.getBooleanParameter(request, "isMarketable"));
		product.setList(RequestUtil.getBooleanParameter(request, "isList"));
		product.setTop(RequestUtil.getBooleanParameter(request, "isTop"));
		product.setGift(RequestUtil.getBooleanParameter(request, "isGift"));
		product.setFreePost(RequestUtil.getBooleanParameter(request, "freePost"));
		
		product.setMemo(request.getParameter("memo"));
		product.setKeyword(request.getParameter("keyword"));
		product.setSeoDescription(request.getParameter("seoDescription"));
		product.setSeoTitle(request.getParameter("seoTitle"));
		product.setSeoKeywords(request.getParameter("seoKeywords"));
		product.setDescription(request.getParameter("description"));
		product.setThumbnail(request.getParameter("thumbnail"));				
	}
	
	/**
	 * 克隆目标商品数据
	 * @param product		目标商品
	 * @return	返回克隆了目标商品的新商品对象
	 */
	public static Product clone(Product product)
	{
		Product model = new Product();
		
		//克隆基本信息
		model.setGoods(product.getGoods());
		model.setProductCategory(product.getProductCategory());
		model.setName(product.getName());
		model.setType(product.getType());
		model.setStock(product.getStock());
		model.setPrice(product.getPrice());
		model.setAppPrice(product.getAppPrice());
		model.setCost(product.getCost());
		model.setMarketPrice(product.getMarketPrice());
		model.setImage(product.getImage());
		model.setMediumImage(product.getMediumImage());
		model.setThumbnail(product.getThumbnail());
		model.setPoint(product.getPoint());
		model.setBrand(product.getBrand());
		model.setWarehouse(product.getWarehouse());
		
		//克隆标签
		model.getTags().addAll(product.getTags());
		
		//克隆SEO
		model.setSeoTitle(product.getSeoTitle());
		model.setSeoKeywords(product.getSeoKeywords());
		model.setSeoDescription(product.getSeoDescription());
		model.setMemo(product.getMemo());
		
		//克隆详情页
		model.setDescription(product.getDescription());
		
		
		return model;
	}
}
