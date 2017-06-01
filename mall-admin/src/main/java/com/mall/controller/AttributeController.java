package com.mall.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.Attribute;
import com.mall.entity.AttributeOptions;
import com.mall.pager.Pager;
import com.mall.service.AttributeOptionsService;
import com.mall.service.AttributeService;
import com.mall.service.ProductCategoryService;


@Controller
@RequestMapping(value = "/attribute/")
public class AttributeController extends BaseController
{
	@Resource
	private AttributeService service;
	@Resource
	private AttributeOptionsService optionsService;
	@Resource
	private ProductCategoryService cateService;

	/**
	 * 管理列表
	 * @param pageNumber     当前页
	 * @param pageSize       页面大小
	 * @param searchValue    查询值
	 * @param searchProperty 查询字段
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, value="pageNumber") Integer pageNumber,
			@RequestParam(required=false, value="pageSize") Integer pageSize,
			@RequestParam(required=false, value="searchValue") String searchValue,
			@RequestParam(required=false, value="searchProperty") String searchProperty)
	{
		ModelAndView mv = new ModelAndView("product/attribute/list");
		mv.addObject("optionsList", this.optionsService.getAll());
		mv.addObject("categoryList", this.cateService.getAll());
		
		Pager<Attribute> pager = new Pager<>();
		pager.setOrderby("orders asc");
		if(pageNumber != null)
		{
			pager.setCurrentIdx(this.getIntParameter("pageNumber"));
		}
		if(pageSize != null)
		{
			pager.setPageSize(this.getIntParameter("pageSize"));
		}
		
		/**	
		 * 查询
		 */
		if(StringUtils.isNotEmpty(searchValue) && StringUtils.isNotEmpty(searchProperty))
		{
			Map<String,Object> likes = new HashMap<String, Object>();
			likes.put(searchProperty, searchValue.trim());
			pager.setLikes(likes);
		}
		mv.addObject("searchValue", searchValue);
		mv.addObject("searchProperty", searchProperty);		
		mv.addObject("attributeList", this.service.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}

	// 跳转商品添加页面
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("product/attribute/add");
		mv.addObject("categoryList", this.cateService.getAll());
		return mv;
	}

	// 添加商品属性
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String newAttribute()
	{
		Attribute model = new Attribute();
		model.setName(this.getParameter("name"));
		String order = this.getParameter("order");
		if (StringUtils.isNotEmpty(order))
		{
			model.setOrders(Integer.valueOf(order));
		}
		String productCategoryId = this.getParameter("productCategoryId");
		if (StringUtils.isNotEmpty(productCategoryId))
		{
			model.setProductCategory(Long.valueOf(productCategoryId));
		}

		model.setCreateDate(new Date());
		model.setModifyDate(new Date());
		this.service.add(model);

		// 可选项
		String[] options = this.getParameters("options");
		if (options != null)
		{
			for (String option : options)
			{
				AttributeOptions ao = new AttributeOptions();
				ao.setOptions(option);
				ao.setAttribute(model);
				this.optionsService.add(ao);
			}
		}
		
		return "redirect:/attribute/list.do";
	}

	// 保存商品属性
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveAttribute(@RequestParam("id") Long attributeId)
	{
		if (attributeId != null)
		{
			Attribute model = this.service.get(attributeId);
			if (model != null)
			{
				model.setName(this.getParameter("name"));
				String order = this.getParameter("order");
				if (StringUtils.isNotEmpty(order))
				{
					model.setOrders(Integer.valueOf(order));
				}

				String productCategoryId = this.getParameter("productCategoryId");
				if (StringUtils.isNotEmpty(productCategoryId))
				{
					model.setProductCategory(Long.valueOf(productCategoryId));
				}

				model.setModifyDate(new Date());
				this.service.update(model);
				
				//更新可选项
				Long[] optionIds = this.getLongParameters("optionId");
				List<AttributeOptions> options = this.optionsService.getListFromProperty("attribute", model);
				for(Long optionId : optionIds)
				{
					AttributeOptions option = this.optionsService.get(optionId);
					if(option != null && options != null)
					{
						options.remove(option);
						option.setOptions(this.getParameter("options_" + option.getId()));
						this.optionsService.update(option);
					}
				}
				//删除可选项
				if(options != null)
				{
					Iterator<AttributeOptions> it = options.iterator();
					while(it.hasNext())
					{
						AttributeOptions option = it.next();
						it.remove();
						this.optionsService.delete(option.getId());
					}
				}
				//增加新可选项
				String[] newOptions = this.getParameters("options");
				if(newOptions != null && newOptions.length > 0)
				{
					for (int i = 0; i < newOptions.length; i++)
					{
						AttributeOptions option = new AttributeOptions();
						option.setOptions(newOptions[i]);
						option.setAttribute(model);
						this.optionsService.add(option);
					}
				}
			}
		}
		return "redirect:/attribute/list.do";
	}

	// 编辑商品属性
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editAttribute(@RequestParam("id") Long attributeId)
	{
		ModelAndView mv = new ModelAndView("product/attribute/add");
		if (attributeId != null)
		{
			Attribute attribute = this.service.get(attributeId);
			if (attribute != null){
				mv.addObject("attribute", attribute);
			}
		}
		mv.addObject("optionsList", this.optionsService.getAll());
		mv.addObject("categoryList", this.cateService.getAll());
		return mv;
	}

	// 删除商品属性
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

	// 验证名称是否存在
	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public void validateAttribute(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		response.reset();
		response.setContentType("text/html; charset=UTF-8");

		List<Attribute> list = this.service.getAll();
		String name = this.getParameter("name");
		for (Attribute a : list)
		{
			if (name.equals(a.getName()))
			{
				response.getWriter().print(false);
				return;

			}
		}
		response.getWriter().print(true);
	}
}
