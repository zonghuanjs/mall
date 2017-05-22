package com.mall.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import com.mall.entity.Log;
import com.mall.pager.Pager;
import com.mall.service.LogService;

/**
 * 
 * @author huan.zong
 *
 */
@Controller
@RequestMapping(value = "/log/")
public class LogController extends BaseController
{

	@Autowired
	private LogService logService;
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, value="pageNumber") Integer pageNumber,
			@RequestParam(required=false, value="pageSize") Integer pageSize,
			@RequestParam(required = false, value="searchValue") String searchValue)
	{
		ModelAndView mv = new ModelAndView("system/log/list");			
		Pager<Log> pager = new Pager<>();
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
		if(StringUtils.isNotEmpty(searchValue) && StringUtils.isNotEmpty(searchProperty))
		{	
			Map<String,Object> likeFilter =new HashMap<>();		
			likeFilter.put(searchProperty, searchValue.trim());
			pager.setLikes(likeFilter);  
		}
		mv.addObject("searchValue",searchValue);
		mv.addObject("searchProperty",searchProperty);
		mv.addObject("logList", this.logService.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}
				
	//查看日志
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editLog(@RequestParam("id") Long logId)
	{
		ModelAndView mv = new ModelAndView("system/log/view");
		if(logId != null)
		{
			Log log = this.logService.get(logId);
			if(log != null)
				mv.addObject("log", log);
		}
		return mv;
	}	
	
	//删除日志
	@RequestMapping(value="delete", method=RequestMethod.POST)
	//@AdminOperationMethod(operationType=AdminOperationType.deleteLog)
	public void deleteLog(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		
		String[] ids = this.getParameters("ids");
		Long[] deleteIds = new Long[ids.length];
		for(int i = 0; i < ids.length; i++)
		{
			deleteIds[i] = Long.valueOf(ids[i]);
		}
		if(this.logService.delete(deleteIds))
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
	
	//清空日志
	@RequestMapping(value="deleteall", method=RequestMethod.POST)
	//@AdminOperationMethod(operationType=AdminOperationType.clearLog)
	public void deleteAllLog(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		
		Long[] deleteIds = new Long[this.logService.getAll().size()];
		for(int i = 0; i < this.logService.getAll().size(); i++)
		{
			deleteIds[i] = this.logService.getAll().get(i).getId();
		}
		if(this.logService.delete(deleteIds))
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
