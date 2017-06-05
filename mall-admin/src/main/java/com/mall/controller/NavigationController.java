package com.mall.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.Navigation;
import com.mall.service.NavigationService;


@Controller
@RequestMapping(value="/navigation/")
public class NavigationController extends BaseController
{

	@Resource
	private NavigationService navigationService;
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView listNavigations()
	{
		ModelAndView mv = new ModelAndView("content/navigation/list");
        mv.addObject("topnavigationList",this.navigationService.getList("position", 1, "orders asc"));
        mv.addObject("middlenavigationList",this.navigationService.getList("position", 2, "orders asc"));
        mv.addObject("bottomnavigationList",this.navigationService.getList("position", 3, "orders asc"));
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView addNavigation()
	{
		ModelAndView mv = new ModelAndView("content/navigation/add");
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String newNavigation()
	{
		Navigation model = new Navigation();
		model.setName(this.getParameter("name"));
		model.setUrl(this.getParameter("url"));		
		if(this.getParameter("orders")==null||this.getParameter("orders").equals("")){
			model.setOrders(0);
		}else{
			
			model.setOrders(Integer.valueOf(this.getParameter("orders")));
		}		
		model.setPosition(Integer.valueOf(this.getParameter("position")));
		model.setBlankTarget((this.getParameter("blankTarget").equals("true")));
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());		
		this.navigationService.add(model);
		return "redirect:/navigation/list.do";
	}
	
	
	//保存导航
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String saveNavigation(@RequestParam("id") Long navigationId)
	{
		Navigation model = this.navigationService.get(navigationId);
		if(navigationId != null)
		{
			model.setName(this.getParameter("name"));
			model.setUrl(this.getParameter("url"));			
			if(this.getParameter("orders")==null||this.getParameter("orders").equals("")){
				model.setOrders(0);
			}else{
				
				model.setOrders(Integer.valueOf(this.getParameter("orders")));
			}
			model.setPosition(Integer.valueOf(this.getParameter("position")));
			model.setBlankTarget((this.getParameter("blankTarget").equals("true")));
			model.setModifyDate(new Date());             
			this.navigationService.update(model);
		}
		return "redirect:/navigation/list.do";
	}
	
	//编辑导航
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editNavigation(@PathVariable Long navigationId)
	{
		ModelAndView mv = new ModelAndView("content/navigation/add");
		if(navigationId != null)
		{
			Navigation navigation = this.navigationService.get(navigationId);
			if(navigation != null)
				mv.addObject("navigation", navigation);
		}
		return mv;
	}
	
	//删除导航
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public void deleteNavigation(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		
		String[] ids = this.getParameters("ids");
		Long[] deleteIds = new Long[ids.length];
		for(int i = 0; i < ids.length; i++)
		{
			deleteIds[i] = Long.valueOf(ids[i]);
		}
		if(this.navigationService.delete(deleteIds))
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
