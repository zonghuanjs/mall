package com.mall.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.Ad;
import com.mall.entity.AdPosition;
import com.mall.pager.Pager;
import com.mall.service.AdPositionService;
import com.mall.service.AdService;
import com.mall.util.TimeUtils;


@Controller
@RequestMapping(value="/ad/")
public class AdController extends BaseController
{

	@Resource
	private AdService adSerivce;
	@Resource
	private AdPositionService adPositionSerivce;
	
	/**
	 * 广告管理列表页面
	 * @return
	 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, value="searchValue") String searchValue,
			@RequestParam(required=false, value="pageNumber") Integer pageNumber,
			@RequestParam(required=false, value="pageSize") Integer pageSize,
			@RequestParam(required=false, value="position") Long position)
	{
		ModelAndView mv = new ModelAndView("content/ad/list");
		Pager<Ad> pager = new Pager<>();
		pager.setOrderby("position asc,orders asc");
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
		Map<String,Object> map=new HashMap<>();	
		Map<String,Object> likes=new HashMap<>();	
		if(searchValue != null && !"".equals(searchValue))
		{	
			likes.put(searchProperty, searchValue.trim());
			
		}
		if(position != null)
		{				
			map.put("position", this.adPositionSerivce.get(position));
		}
		
		pager.setFilter(map);
		pager.setLikes(likes);
		mv.addObject("searchValue", searchValue);
		mv.addObject("position",position);
		mv.addObject("searchProperty",searchProperty);
		mv.addObject("adList", this.adSerivce.findByPager(pager));
		mv.addObject("positions",this.adPositionSerivce.getAll());
		mv.addObject("pager", pager);
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView add()
	{
		ModelAndView mv = new ModelAndView("content/ad/add");
		mv.addObject("adPositionList",this.adPositionSerivce.getAll());
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String newAd()
	{
		Ad model = new Ad();
		model.setTitle(this.getParameter("title"));
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
		if(Integer.valueOf(this.getParameter("type"))==1){
			model.setPath(null);
		}else{
			model.setPath(this.getParameter("path"));
		}
		model.setPath(this.getParameter("path"));
		model.setContent(this.getParameter("content"));
		model.setBeginDate(TimeUtils.fromDateTimeString("yyyy-MM-dd hh:mm:ss", this.getParameter("beginDate")));
		model.setEndDate(TimeUtils.fromDateTimeString("yyyy-MM-dd hh:mm:ss", this.getParameter("endDate")));
		model.setUrl(this.getParameter("url"));
		
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());
		AdPosition position=this.adPositionSerivce.get(Long.valueOf(this.getParameter("position")));
		model.setPosition(position);
		
		this.adSerivce.add(model);
		return "redirect:/ad/list.do";
	}

	
	/**
	 * 保存广告
	 * @return
	 */
	@RequestMapping(value="save.do", method=RequestMethod.POST)
	public String save(@RequestParam("id") Long id,
			@RequestParam("title") String title)
	{
		if(id != null)
		{
			Ad model = this.adSerivce.get(id);
			if(model != null)
			{						
				
				model.setTitle(title);
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
				if(Integer.valueOf(this.getParameter("type"))==1){
					model.setPath(null);
				}else{
					model.setPath(this.getParameter("path"));
				}
				model.setPath(this.getParameter("path"));
				model.setContent(this.getParameter("content"));
				model.setBeginDate(TimeUtils.fromDateTimeString("yyyy-MM-dd hh:mm:ss", this.getParameter("beginDate")));
				model.setEndDate(TimeUtils.fromDateTimeString("yyyy-MM-dd hh:mm:ss", this.getParameter("endDate")));
				model.setUrl(this.getParameter("url"));
			
				model.setModifyDate(new Date());
				AdPosition position=this.adPositionSerivce.get(Long.valueOf(this.getParameter("position")));
				model.setPosition(position);
				this.adSerivce.update(model);
			}
		}
		return "redirect:/ad/list.do";
	}
	
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") Long id)
	{
		ModelAndView mv = new ModelAndView("content/ad/add");
		if(id != null)
		{
			Ad ad = this.adSerivce.get(id);
			mv.addObject("ad",ad);
			mv.addObject("adPositionList",this.adPositionSerivce.getAll());
			
		}
		return mv;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public void delete(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		
		String[] ids = this.getParameters("ids");
		Long[] deleteIds = new Long[ids.length];
		for(int i = 0; i < ids.length; i++)
		{
			deleteIds[i] = Long.valueOf(ids[i]);
		}
		if(this.adSerivce.delete(deleteIds))
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
