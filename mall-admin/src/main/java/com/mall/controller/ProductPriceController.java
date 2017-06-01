package com.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.Admin;
import com.mall.entity.Product;
import com.mall.pager.Pager;
import com.mall.service.ProductService;

@Controller
@RequestMapping("/price/")
public class ProductPriceController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(ProductPriceController.class);
	
	@Resource
	private ProductService productService;//商品服务
	
	/**
	 * 商品价格管理列表页
	 * @param pageNumber     当前页
	 * @param pageSize       页面大小
	 * @param searchValue    查询值
	 * @param searchProperty 查询字段
	 * @param orderProperty  排序字段
	 * @param orderDirection 排序
	 * @return
	 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView priceList(@RequestParam(required=false, value="pageNumber") Integer pageNumber,
			@RequestParam(required=false, value="pageSize") Integer pageSize,
			@RequestParam(required = false, value="searchValue") String searchValue,
			@RequestParam(required = false, value="searchProperty") String searchProperty,
			@RequestParam(required = false, value="orderProperty") String orderProperty,
			@RequestParam(required = false, value="orderDirection") String orderDirection)
	{
		ModelAndView mv = new ModelAndView("product/price/list");
		Pager<Product> pager = new Pager<>();
		pager.setOrderby("createDate desc");
		if(pageNumber != null)
		{
			pager.setCurrentIdx(pageNumber);
		}
		if(pageSize != null)
		{
			pager.setPageSize(pageSize);
		}
		/**
		 * 查询
		 */
		if(StringUtils.isNotEmpty(searchValue) && StringUtils.isNotEmpty(searchProperty))
		{			
			Map<String, Object> likeFilter = new HashMap<>();
			likeFilter.put(searchProperty, searchValue.trim());
			pager.setLikes(likeFilter);	
		}		
		/**
		 * 排序
		 */
		if(StringUtils.isNotEmpty(orderProperty) && StringUtils.isNotEmpty(orderDirection))
		{
			pager.setOrderby(orderProperty+" "+orderDirection);
		}

		List<Product> products = productService.findByPager(pager);
		
		mv.addObject("pager", pager);
		mv.addObject("products", products);
		mv.addObject("searchProperty", searchProperty);
		mv.addObject("searchValue", searchValue);
		mv.addObject("orderProperty", orderProperty);
		mv.addObject("orderDirection", orderDirection);
		return mv;
	}
	
	/**
	 * 商品价格编辑页面
	 * @param productId	商品ID号
	 * @return
	 */
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView priceEdit(@RequestParam("id") Long productId)
	{
		ModelAndView mv = new ModelAndView("product/price/edit");
		
		Product product = productService.get(productId);
		if(product != null)
		{
			mv.addObject("product", product);
		}
		else
		{
			Admin admin = this.getAdmin();
			logger.warn("管理员({})打开了错误的商品({})进行价格编辑", admin.getUsername(), productId);
			mv.setViewName("error/404");
		}
		
		return mv;
	}
	
	/**
	 * 编辑保存操作
	 * @param productId	商品ID号
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String priceSave(@RequestParam("id") Long productId)
	{
		Product product = productService.get(productId);
		
		String price = this.getParameter("price");
		
		String cost = this.getParameter("cost");
		
		String marketPrice = this.getParameter("marketPrice");
				
		if(product != null)
		{
			if(StringUtils.isNotEmpty(price))
				product.setPrice(new Double(price));
			
			if(StringUtils.isNotEmpty(cost))
				product.setCost(new Double(cost));
			
			if(StringUtils.isNotEmpty(marketPrice))
				product.setMarketPrice(new Double(marketPrice));
			
			product.setAppPrice(this.getDoubleParameter("app_price"));
			
			this.productService.update(product);
		}
		
		return "redirect:list.do";
	}
}
