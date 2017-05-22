package com.mall.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.DeliveryCorp;
import com.mall.pager.Pager;
import com.mall.service.DeliveryCorpService;

/**
 * 后台管理物流公司管理模块
 * @author zonghuan
 *
 */
@Controller
@RequestMapping(value = "/delivery_corp/")
public class DeliveryController extends BaseController
{

	@Autowired
	private DeliveryCorpService service;
	
	/**
	 * 后台管理模块物流公司管理列表页
	 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, value="pageNumber") Integer pageNumber,
			@RequestParam(required=false, value="pageSize") Integer pageSize,
			@RequestParam(required = false, value="searchValue") String searchValue)
	{
		ModelAndView mv = new ModelAndView("system/delivery_corp/list");
		
		Pager<DeliveryCorp> pager = new Pager<>();
		pager.setOrderby("orders asc");
		
		if(pageNumber != null)
		{
			pager.setCurrentIdx(pageNumber);
		}
		if(pageSize != null)
		{
			pager.setPageSize(pageSize);
		}
		
		//查询条件
		String searchProperty = this.getParameter("searchProperty");
		if(StringUtils.isNotEmpty(searchValue) && StringUtils.isNotEmpty(searchProperty))
		{
	
			Map<String,Object> likeFilter =new HashMap<>();		
			likeFilter.put(searchProperty, searchValue.trim());
			pager.setLikes(likeFilter);    
		}
		
		mv.addObject("searchValue",searchValue);
		mv.addObject("searchProperty",searchProperty);
		mv.addObject("deliveryCorps", this.service.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("system/delivery_corp/add");
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String newDelivery()
	{
		DeliveryCorp model = new DeliveryCorp();
		model.setName(this.getParameter("name"));
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());
		model.setUrl(this.getParameter("url"));
		model.setCode(this.getParameter("code"));
		if(StringUtils.isNotEmpty(this.getParameter("order")))
			model.setOrders(Integer.valueOf(this.getParameter("order")));
		this.service.add(model);
		
		return "redirect:/delivery_corp/list.do";
	}
	
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editDelivery(@RequestParam("id") Long id)
	{
		ModelAndView mv = new ModelAndView("system/delivery_corp/add");
		if(id != null)
		{
			DeliveryCorp deliveryCorp = this.service.get(id);
			if(deliveryCorp != null)
			{
				mv.addObject("deliveryCorp", deliveryCorp);
			}
		}
		return mv;
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String saveDelivery(@RequestParam("id") Long id)
	{
		if(id != null)
		{
			DeliveryCorp model = this.service.get(id);
			if(model != null)
			{
				model.setName(this.getParameter("name"));
				model.setModifyDate(new Date());
				model.setUrl(this.getParameter("url"));
				model.setCode(this.getParameter("code"));
				if(StringUtils.isNotEmpty(this.getParameter("order")))
					model.setOrders(Integer.valueOf(this.getParameter("order")));
				this.service.update(model);
			}
		}
		return "redirect:/delivery_corp/list.do";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void deleteAttribute(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		String[] ids = this.getParameters("ids");
		Long[] deleteIds = new Long[ids.length];

		for (int i = 0; i < ids.length; i++)
		{
			deleteIds[i] = Long.valueOf(ids[i]);
		}
		if (this.service.delete(deleteIds))
		{
			rootNode.put("errCode", 0);
		}
		else
		{
			rootNode.put("errCode", -1);
		}
		try
		{
			mapper.writeValue(response.getOutputStream(), rootNode);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
