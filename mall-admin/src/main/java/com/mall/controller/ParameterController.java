package com.mall.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import com.mall.entity.Parameter;
import com.mall.entity.ParameterGroup;
import com.mall.pager.Pager;
import com.mall.service.ParameterGroupService;
import com.mall.service.ParameterService;
import com.mall.service.ProductCategoryService;


@Controller
@RequestMapping(value = "/parameter/")
public class ParameterController extends BaseController
{
	@Autowired
	private ParameterService service;
	@Autowired
	private ParameterGroupService groupService;
	@Autowired
	private ProductCategoryService cateService;
	
	/**
	 * 商品参数管理列表页
	 * @param pageNumber		当前页数
	 * @param pageSize			当前页大小
	 * @param searchValue		搜索值
	 * @param searchProperty	搜索字段
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(required=false, value="pageNumber") Integer pageNumber,
			@RequestParam(required=false, value="pageSize") Integer pageSize,
			@RequestParam(required=false, value="searchValue") String searchValue,
			@RequestParam(required=false, value="searchProperty") String searchProperty,
			HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("product/parameter/list");		
		Pager<ParameterGroup> pager = new Pager<>();
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
		if(StringUtils.isNotEmpty(searchValue) && StringUtils.isNotEmpty(searchProperty))
		{
			Map<String,Object> map=new HashMap<>();		
			map.put(searchProperty, searchValue.trim());	
			pager.setLikes(map);
		}	
		mv.addObject("searchValue", searchValue);
		mv.addObject("searchProperty",searchProperty);		
		mv.addObject("groupList", this.groupService.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}
	
	//跳转添加参数页面
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("product/parameter/add");
		mv.addObject("paramList", this.service.getAll("orders asc"));
		mv.addObject("categoryList", this.cateService.getAll("orders asc"));
		return  mv;
	}
	
	/**
	 * 添加参数
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String newParam()
	{
		ParameterGroup model = new ParameterGroup();
		Long categoryId = this.getLongParameter("productCategoryId");
		model.setProductCategory(cateService.get(categoryId));
		
		model.setName(this.getParameter("name"));
		String order = this.getParameter("order");
		if(StringUtils.isNotEmpty(order)){
			model.setOrders(Integer.valueOf(order));
		}
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());
		this.groupService.add(model);
		
		//参数组
		String[] names = this.getParameters("parameterName");
		String[] orders = this.getParameters("parameterOrder");
		if(names != null){
			for(int i=0; i<names.length; i++){
				if(orders != null){
					Parameter p = new Parameter();
					p.setName(names[i]);
					if (StringUtils.isNotEmpty(orders[i]))
					{
						p.setOrders(Integer.valueOf(orders[i]));
					}
					p.setParameterGroup(model.getId());
					this.service.add(p);
				}
			}
		}
		
		return "redirect:/parameter/list.do";
	}
	
	//保存修改参数
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String saveParam(@RequestParam("groupId") Long groupId)
	{
		if(groupId != null)
		{
			ParameterGroup model = this.groupService.get(groupId);
			if(model != null){
				Long categoryId = this.getLongParameter("productCategoryId");
				model.setProductCategory(cateService.get(categoryId));
				model.setName(this.getParameter("name"));
				String order = this.getParameter("order");
				if(StringUtils.isNotEmpty(order)){
					model.setOrders(Integer.valueOf(order));
				}
				model.setModifyDate(new Date());
				this.groupService.update(model);
				
				//更新参数组
				Long[] parameterIds = this.getLongParameters("parameterId");
				List<Parameter> parameters = model.getParameters();
				for(Long parameterId : parameterIds)
				{
					Parameter parameter = this.service.get(parameterId);
					if(parameter != null && parameters != null)
					{
						parameters.remove(parameter);
						parameter.setName(this.getParameter("parameterName_" + parameter.getId()));
						if(StringUtils.isNotEmpty(this.getParameter("parameterOrder_" + parameter.getId())))
						{
							parameter.setOrders(this.getIntParameter("parameterOrder_" + parameter.getId()));
						}
						this.service.update(parameter);
					}
				}
				//删除已删除的参数
				if(parameters != null)
				{
					Iterator<Parameter> it = parameters.iterator();
					while(it.hasNext())
					{
						Parameter parameter = it.next();
						it.remove();
						this.service.delete(parameter.getId());
					}
				}
				//增加新参数
				String[] names = this.getParameters("parameterName");
				String[] orders = this.getParameters("parameterOrder");
				if(names != null && names.length > 0)
				{
					for (int i = 0; i < names.length; i++)
					{
						Parameter parameter = new Parameter();
						parameter.setName(names[i]);
						if(StringUtils.isNotEmpty(orders[i]))
							parameter.setOrders(Integer.valueOf(orders[i]));
						parameter.setParameterGroup(groupId);
						this.service.add(parameter);
					}
				}
			}
		}
		return "redirect:/parameter/list.do";
	}
	
	/**
	 * 编辑参数 
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editParam(@RequestParam("groupId") Long groupId)
	{
		ModelAndView mv = new ModelAndView("product/parameter/add");
		if(groupId != null)
		{
			ParameterGroup model = this.groupService.get(groupId);
			if(model != null){
				mv.addObject("group", model);
			}
		}
		mv.addObject("categoryList", this.cateService.getAll("orders asc"));
		mv.addObject("paramList", this.service.getAll("orders asc"));
		return mv;
	}
	
	//删除参数
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public void deleteParam(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		
		String[] ids = this.getParameters("ids");
		Long[] deleteIds = new Long[ids.length];
		for(int i = 0; i < ids.length; i++)
		{
			deleteIds[i] = Long.valueOf(ids[i]);
		}
		if(this.groupService.delete(deleteIds))
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//验证名称是否存在
	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public void validateParam(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		response.reset();
		response.setContentType("text/html; charset=UTF-8");

		List<ParameterGroup> list = this.groupService.getAll();
		String name = this.getParameter("name");
		for (ParameterGroup pg : list)
		{
			if (name.equals(pg.getName()))
			{
				response.getWriter().print(false);
				return;

			}
		}
		response.getWriter().print(true);
	}
		
}
