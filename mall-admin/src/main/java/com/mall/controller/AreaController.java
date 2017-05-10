package com.mall.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.Area;
import com.mall.service.AreaService;

/**
 * 
 * @author zonghuan
 *
 */
@Controller
@RequestMapping(value="/area/")
public class AreaController extends BaseController
{

	@Autowired
	private AreaService areaSerivce;
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView listAreas(@RequestParam(required=false,value="id") Long areaId)
	{
		ModelAndView mv = new ModelAndView("system/area/list");		
		
		if(areaId != null){
			Area parent=this.areaSerivce.get(areaId);	
			mv.addObject("parent",parent);
			mv.addObject("areaList", this.areaSerivce.getList("parent", parent, "orders"));
		}
		else{
			mv.addObject("areaList", this.areaSerivce.getList("parent", null , "orders"));
		}
		return mv;
	}
	
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView addArea(@RequestParam(required=false,value="id") Long parentId)
	{
		ModelAndView mv = new ModelAndView("system/area/add");
		if(parentId != null){
			mv.addObject("parent",this.areaSerivce.get(parentId));
		}
		return mv;
	}
	
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String newArea()
	{
		Area model = new Area();
		model.setName(this.getParameter("name"));
		model.setOrders(Integer.valueOf(this.getParameter("orders")));
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());
		if(this.getParameter("parent")==null||this.getParameter("parent").equals("")){
			model.setParent(null);
			model.setFullName(this.getParameter("name"));
			model.setTreePath(",");
			this.areaSerivce.add(model);
			return "redirect:/area/list.do";
		}else{
			Area parent=this.areaSerivce.get(Long.valueOf(this.getParameter("parent")));
			model.setParent(parent);
			model.setFullName(parent.getFullName()+this.getParameter("name"));
			if(this.areaSerivce.get(Long.valueOf(this.getParameter("parent"))).getTreePath()==null){
				model.setTreePath(this.getParameter("parent")+",");
			}else{
				model.setTreePath(parent.getTreePath()+this.getParameter("parent")+",");
			}
			this.areaSerivce.add(model);
			return "redirect:/area/list.do?id="+Long.valueOf(this.getParameter("parent"));			
		}
				
	}
		
	//保存地区
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String saveArea(@RequestParam("id") Long areaId)
	{
		if(areaId != null)
		{
			Area model = this.areaSerivce.get(areaId);
			if(model != null)
			{				
				model.setName(this.getParameter("name"));
				model.setOrders(Integer.valueOf(this.getParameter("orders")));
				model.setModifyDate(new Date());
				if(this.getParameter("parent")==null||this.getParameter("parent").equals("")){
					model.setParent(null);
					model.setFullName(this.getParameter("name"));
					this.areaSerivce.update(model);
					return "redirect:/area/list.do";
				}else{
					Area parent=this.areaSerivce.get(Long.valueOf(this.getParameter("parent")));
					model.setParent(parent);
					model.setFullName(parent.getFullName()+this.getParameter("name"));
					this.areaSerivce.update(model);
					return "redirect:/area/list.do?id="+Long.valueOf(this.getParameter("parent"));
				}							
			}
		}
		return "redirect:/area/list.do";
	}
	
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editArea(@RequestParam("id") Long areaId)
	{
		ModelAndView mv = new ModelAndView("system/area/add");
		if(areaId != null)
		{
			Area area = this.areaSerivce.get(areaId);
			if(area != null)
			{
				if(area.getParent()!=null){
					mv.addObject("parent",this.areaSerivce.get(area.getParent().getId()));
				}
				
				mv.addObject("area", area);		
			}
		}
		return mv;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public void deleteArea(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		
		String[] ids = this.getParameters("ids");
		Long[] deleteIds = new Long[ids.length];
		for(int i = 0; i < ids.length; i++)
		{
			deleteIds[i] = Long.valueOf(ids[i]);
		}
		if(this.areaSerivce.delete(deleteIds))
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
