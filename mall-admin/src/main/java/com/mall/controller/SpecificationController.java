package com.mall.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
import com.mall.entity.Specification;
import com.mall.entity.SpecificationValue;
import com.mall.pager.Pager;
import com.mall.service.SpecificationService;
import com.mall.service.SpecificationValueService;


@Controller
@RequestMapping(value="/specification/")
public class SpecificationController extends BaseController
{

	@Resource
	private SpecificationService specificationSerivce;

	@Resource
	private SpecificationValueService specificationValueSerivce;

	/**
	 * 商品规格管理列表页面
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
			@RequestParam(required = false, value="searchProperty") String searchProperty)
	{
		ModelAndView mv = new ModelAndView("product/specification/list");
		
		Pager<Specification> pager = new Pager<>();
		pager.setOrderby("orders asc");
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
			Map<String,Object> map=new HashMap<>();		
			map.put(searchProperty, searchValue.trim());
			pager.setLikes(map);
		}
		mv.addObject("searchValue", searchValue);
		mv.addObject("searchProperty",searchProperty);		
		mv.addObject("specificationList", this.specificationSerivce.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("product/specification/add");

		return mv;
	}

	// 添加规格
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String newSpecification()
	{
		Specification model = new Specification();
		model.setName(this.getParameter("name"));
		model.setMemo(this.getParameter("memo"));
		if(this.getParameter("type")==null||this.getParameter("type").equals("")){
			model.setType(1);
		}else{			
			model.setType(Integer.valueOf(this.getParameter("type")));
		}
		if(this.getParameter("orders")==null||this.getParameter("orders").equals("")){
			model.setOrders(0);
		}else{
			
			model.setOrders(Integer.valueOf(this.getParameter("orders")));
		}
		
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());

		this.specificationSerivce.add(model);

		// 添加规格值

		String[] valuename = this.getParameters("valuename");
		String[] valueimage = this.getParameters("valueimage");
		String[] valueorders = this.getParameters("valueorders");
		for (int i = 0; i < valuename.length; i++)
		{
			SpecificationValue value = new SpecificationValue();
			value.setName(valuename[i]);

			if (Integer.valueOf(this.getParameter("type")) == 2)
			{
				value.setImage(valueimage[i]);
			}
			else
			{
				value.setImage(null);
			}
			value.setOrders(Integer.valueOf(valueorders[i]));
			value.setSpecification(model);
			value.setCreateDate(new Date());
			value.setModifyDate(new Date());

			this.specificationValueSerivce.add(value);
		}

		return "redirect:/specification/list.do";
	}

	// 保存规格
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveSpecification(@RequestParam("id") Long specificationId)
	{
		if (specificationId != null)
		{
			Specification model = this.specificationSerivce.get(specificationId);
			if (model != null)
			{

				model.setName(this.getParameter("name"));
				model.setMemo(this.getParameter("memo"));
				if(this.getParameter("type") == null || this.getParameter("type").equals("")){
					model.setType(1);
				}else{			
					model.setType(this.getIntParameter("type"));
				}
				if(this.getParameter("orders") == null || this.getParameter("orders").equals("")){
					model.setOrders(0);
				}else{
					
					model.setOrders(this.getIntParameter("orders"));
				}
				model.setModifyDate(new Date());

				this.specificationSerivce.update(model);

				String[] valuename = this.getParameters("valuename");
				String[] valueimage = this.getParameters("valueimage");
				Integer[] valueorders = this.getIntegerParameters("valueorders");
				Long[] ids = this.getLongParameters("ids");
				
				List<String> names = new LinkedList<>(Arrays.asList(valuename));
				List<String> images = null;
				if(model.getType() == 2)
					images = new LinkedList<>(Arrays.asList(valueimage));
				List<Integer> orders = new LinkedList<>(Arrays.asList(valueorders));
				
				List<SpecificationValue> oldValues = this.specificationValueSerivce.get(ids);
				
				List<SpecificationValue> values = new LinkedList<>(model.getValues());
				
				Iterator<SpecificationValue> iter = oldValues.iterator();
				Iterator<String> name = names.iterator();
				Iterator<String> image = null;
				if(model.getType() == 2)
					image = images.iterator();
				Iterator<Integer> order = orders.iterator();
				//保存修改规格值
				while(iter.hasNext())
				{
					SpecificationValue value = iter.next();
					value.setName(name.next());
					if(model.getType() == 2)
					{
						value.setImage(image.next());
						image.remove();
					}
					value.setOrders(order.next());
					//更新
					this.specificationValueSerivce.update(value);
					//移除使用过的值
					name.remove();
					order.remove();
					values.remove(value);
				}
				//删除
				if(!values.isEmpty())
				{
					for(SpecificationValue value : values)
					{
						this.specificationValueSerivce.delete(value.getId());
					}
				}
				//新增规格值
				while(name.hasNext() && order.hasNext())
				{
					SpecificationValue value = new SpecificationValue();
					value.setName(name.next());
					value.setOrders(order.next());
					if(model.getType() == 2)
						value.setImage(image.next());
					value.setSpecification(model);
					this.specificationValueSerivce.add(value);
				}
			}

		}
		return "redirect:/specification/list.do";
	}

	// 编辑规格
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editSpecification(@RequestParam("id") Long specificationId)
	{
		ModelAndView mv = new ModelAndView("product/specification/add");
		if (specificationId != null)
		{
			Specification specification = this.specificationSerivce.get(specificationId);
			if (specification != null)
			{
				mv.addObject("specification", specification);
			}
		}
		return mv;
	}

	// 删除规格
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void deleteSpecification(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		Long[] ids = this.getLongParameters("ids");
		if (this.specificationSerivce.delete(ids))
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
