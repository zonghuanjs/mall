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
import com.mall.entity.AdPosition;
import com.mall.pager.Pager;
import com.mall.service.AdPositionService;


@Controller
@RequestMapping(value="/ad_position/")
public class AdPositionController extends BaseController
{

	@Resource
	private AdPositionService adPositionSerivce;
	
	/**
	 * 管理位列表管理页面
	 * @param searchValue
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, value="searchValue") String searchValue,
			@RequestParam(required=false, value="pageNumber") Integer pageNumber,
			@RequestParam(required=false, value="pageSize") Integer pageSize)
	{
		ModelAndView mv = new ModelAndView("content/ad_position/list");
		Pager<AdPosition> pager = new Pager<>();
		pager.setOrderby("createDate desc");
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
			Map<String,Object> likes = new HashMap<>();		
			likes.put(searchProperty, searchValue.trim());
			pager.setLikes(likes);
		}
		mv.addObject("searchValue", searchValue);
		mv.addObject("searchProperty",searchProperty);
		mv.addObject("adPositionList", this.adPositionSerivce.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView add()
	{
		ModelAndView mv = new ModelAndView("content/ad_position/add");
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String newAdPosition()
	{
		AdPosition model = new AdPosition();
		model.setName(this.getParameter("name"));
		model.setDescription(this.getParameter("description"));
		model.setHeight(Integer.valueOf(this.getParameter("height")));
		model.setWidth(Integer.valueOf(this.getParameter("width")));
		model.setTemplate(this.getParameter("template"));
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());
		
		this.adPositionSerivce.add(model);
		return "redirect:/ad_position/list.do";
	}
	
	/**
	 * 保存广告位
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String save(@RequestParam("id") Long adPositionId)
	{
		if(adPositionId != null)
		{
			AdPosition model = this.adPositionSerivce.get(adPositionId);
			if(model != null)
			{
				
				model.setName(this.getParameter("name"));
				model.setDescription(this.getParameter("description"));
				model.setHeight(Integer.valueOf(this.getParameter("height")));
				model.setWidth(Integer.valueOf(this.getParameter("width")));
				model.setTemplate(this.getParameter("template"));				
				model.setModifyDate(new Date());
				this.adPositionSerivce.update(model);
			}
		}
		return "redirect:/ad_position/list.do";
	}
	
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") Long adPositionId)
	{
		ModelAndView mv = new ModelAndView("admin/content/ad_position/add");
		if(adPositionId != null)
		{
			AdPosition adPosition = this.adPositionSerivce.get(adPositionId);
			mv.addObject("adPosition",adPosition);
			
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
		if(this.adPositionSerivce.delete(deleteIds))
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
