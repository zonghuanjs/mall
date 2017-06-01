package com.mall.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.Brand;
import com.mall.pager.Pager;
import com.mall.service.BrandService;

/**
 * 商品品牌控制器
 * @author zonghuan
 *
 */
@Controller
@RequestMapping(value="/brand/")
public class BrandController extends BaseController
{

	@Resource
	private BrandService brandService;
	
	/**
	 * 品牌管理列表
	 * @param searchValue
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, value="searchValue") String searchValue,
			@RequestParam(required=false, value="pageSize") Integer pageSize,
			@RequestParam(required=false, value="pageNumber") Integer pageNumber)
	{
		ModelAndView mv = new ModelAndView("product/brand/list");		
		Pager<Brand> pager = new Pager<>();
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
		String searchProperty=this.getParameter("searchProperty");
		if(searchValue != null && !"".equals(searchValue))
		{
			Map<String,Object> map=new HashMap<>();		
			map.put(searchProperty, searchValue.trim());
			pager.setFilter(map);
		}
		mv.addObject("searchValue", searchValue);
		mv.addObject("searchProperty",searchProperty);		
		mv.addObject("brandList", this.brandService.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}
		
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("product/brand/add");
		return mv;
	}
	
	//添加品牌
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String newBrand()
	{
		Brand model = new Brand();
		model.setName(this.getParameter("name"));
		model.setUrl(this.getParameter("url"));
		
		model.setDescription(this.getParameter("description"));	
		
		model.setLogo(this.getParameter("logo"));
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
		
		this.brandService.add(model);
		return "redirect:/brand/list.do";
	}
	
	
	//保存品牌
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String saveBrand(@RequestParam("id") Long brandId)
	{
		Brand model = this.brandService.get(brandId);
		if(brandId != null)
		{
			model.setName(this.getParameter("name"));
			model.setUrl(this.getParameter("url"));
			model.setDescription(this.getParameter("description"));
			model.setLogo(this.getParameter("logo"));
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
			model.setModifyDate(new Date());
			this.brandService.update(model);
		}
		return "redirect:/brand/list.do";
	}
	
	//编辑品牌
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editBrand(@RequestParam("id") Long brandId)
	{
		ModelAndView mv = new ModelAndView("product/brand/add");
		if(brandId != null)
		{
			Brand brand = this.brandService.get(brandId);
			if(brand != null)
				mv.addObject("brand", brand);
		}
		return mv;
	}
	
	//删除品牌
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public void deleteBrand(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		
		String[] ids = this.getParameters("ids");
		Long[] deleteIds = new Long[ids.length];
		for(int i = 0; i < ids.length; i++)
		{
			deleteIds[i] = Long.valueOf(ids[i]);
		}
		if(this.brandService.delete(deleteIds))
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
}
