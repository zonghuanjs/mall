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
import com.mall.entity.Tag;
import com.mall.pager.Pager;
import com.mall.service.TagService;

@Controller
@RequestMapping(value="/tag/")
public class TagController extends BaseController
{

	@Resource
	private TagService tagService;
	
	/**
	 * 标签列表
	 * @param pageNumber
	 * @param pageSize
	 * @param searchValue
	 * @return
	 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, value="pageNumber") Integer pageNumber,
			@RequestParam(required=false, value="pageSize") Integer pageSize,
			@RequestParam(required = false, value="searchValue") String searchValue)
	{
		ModelAndView mv = new ModelAndView("content/tag/list");		
		Pager<Tag> pager = new Pager<>();
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
		String type=this.getParameter("type");
		Map<String,Object> map=new HashMap<>();	
		Map<String,Object> likes=new HashMap<>();
		if(searchValue != null && !searchValue.equals(""))
		{
			
			likes.put(searchProperty, searchValue.trim());						
		}
		if(type != null && !type.equals(""))
		{
			
			map.put("type", Integer.valueOf(type));						
		}
		pager.setLikes(likes);
		pager.setFilter(map);
		mv.addObject("searchValue",searchValue);
		mv.addObject("searchProperty",searchProperty);
		mv.addObject("type",type);
		mv.addObject("tagList", this.tagService.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}
		
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("content/tag/add");
		return mv;
	}
	
	//添加标签
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String newTag()
	{
		Tag model = new Tag();
		model.setName(this.getParameter("name"));
		model.setMemo(this.getParameter("memo"));
		model.setIcon(this.getParameter("icon"));
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
		
		this.tagService.add(model);
		return "redirect:/tag/list.do";
	}
	
	
	//保存标签
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String saveTag(@RequestParam("id") Long tagId)
	{
		Tag model = this.tagService.get(tagId);
		if(tagId != null)
		{
			model.setName(this.getParameter("name"));
			model.setMemo(this.getParameter("memo"));
			model.setIcon(this.getParameter("icon"));
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
			this.tagService.update(model);
		}
		return "redirect:/tag/list.do";
	}
	
	//编辑标签
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editTag(@RequestParam("id") Long tagId)
	{
		ModelAndView mv = new ModelAndView("/content/tag/add");
		if(tagId != null)
		{
			Tag tag = this.tagService.get(tagId);
			if(tag != null)
				mv.addObject("tag", tag);
		}
		return mv;
	}
	
	//删除标签
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
		if(this.tagService.delete(deleteIds))
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
