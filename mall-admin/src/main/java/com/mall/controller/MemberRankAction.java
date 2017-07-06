package com.mall.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
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
import com.mall.entity.MemberRank;
import com.mall.pager.Pager;
import com.mall.service.MemberRankService;


@Controller
@RequestMapping(value="/member_rank/")
public class MemberRankAction extends BaseController
{
	
	@Resource
	private MemberRankService rankService;
	
	/**
	 * 会员等级列表页
	 * @return
	 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, value="searchValue") String searchValue)
	{
		ModelAndView mv = new ModelAndView("member/rank/list");
		Pager<MemberRank> pager = new Pager<>();
		pager.setOrderby("scale asc");
		if(this.getIntParameter("pageNumber") != null)
			pager.setCurrentIdx(this.getIntParameter("pageNumber"));
		if(this.getIntParameter("pageSize") != null)
			pager.setPageSize(this.getIntParameter("pageSize"));
		
		//查询条件
		String searchProperty=this.getParameter("searchProperty");
		if(searchValue != null && !"".equals(searchValue.trim()))
		{
			Map<String,Object> likes = new HashMap<>();		
			likes.put(searchProperty, searchValue.trim());
			pager.setLikes(likes);
		}
		mv.addObject("searchValue", searchValue);
		mv.addObject("searchProperty", searchProperty);
		mv.addObject("ranks", this.rankService.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("member/rank/add");
		return mv;
	}
	
	/**
	 * 添加会员等级
	 * @return
	 */
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String newMemberRank()
	{
		MemberRank model = new MemberRank();
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());
		model.setName(this.getParameter("name"));
		model.setScale(Double.valueOf(this.getParameter("scale")));
		model.setAmount(Double.valueOf(this.getParameter("amount")));
		model.setLevel(this.getIntParameter("level"));
		
		if(StringUtils.isNotEmpty(this.getParameter("isDefault")))
			model.setIsDefault(Boolean.valueOf(this.getParameter("isDefault")));
		else
			model.setIsDefault(false);
		if(StringUtils.isNotEmpty(this.getParameter("isSpecial")))
			model.setSpecial(Boolean.valueOf(this.getParameter("isSpecial")));
		else
			model.setSpecial(false);
		this.rankService.add(model);
		return "redirect:/member_rank/list.do";
	}
	
	/**
	 * 编辑会员等级
	 * @param id
	 * @return
	 */
	@RequestMapping(value="edit", method=RequestMethod.GET)	
	public ModelAndView edit(@RequestParam("id") Long id)
	{
		ModelAndView mv = new ModelAndView("member/rank/add");
		MemberRank model = this.rankService.get(id);
		if(model != null)
			mv.addObject("rank", model);
		return mv;
	}
	
	/**
	 * 保存会员等级
	 * @param id
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String save(@RequestParam("id") Long id)
	{
		MemberRank model = this.rankService.get(id);
		if(model != null)
		{
			model.setModifyDate(new Date());
			model.setName(this.getParameter("name"));
			model.setScale(Double.valueOf(this.getParameter("scale")));
			model.setAmount(Double.valueOf(this.getParameter("amount")));
			model.setLevel(this.getIntParameter("level"));
			
			if(StringUtils.isNotEmpty(this.getParameter("isDefault")))
				model.setIsDefault(Boolean.valueOf(this.getParameter("isDefault")));
			else
				model.setIsDefault(false);
			if(StringUtils.isNotEmpty(this.getParameter("isSpecial")))
				model.setSpecial(Boolean.valueOf(this.getParameter("isSpecial")));
			else
				model.setSpecial(false);
			this.rankService.update(model);
		}
		return "redirect:/member_rank/list.do";
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public void delete(OutputStream out)
	{
		int errCode = 0;
		String[] ids = this.getParameters("ids");
		Long[] delIds = new Long[ids.length];
		for(int i = 0; i < ids.length; i++)
		{
			delIds[i] = Long.valueOf(ids[i]);
		}
		if(!this.rankService.delete(delIds))
			errCode = 1222;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		rootNode.put("errCode", errCode);
		try
		{
			mapper.writeValue(out, rootNode);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 检测等级名称
	 * @param response
	 */
	@RequestMapping(value="check_name.do")
	public void checkName(PrintWriter out)
	{
		String preName = this.getUTF8Parameter("prevName");
		String name = this.getUTF8Parameter("name");
		if(StringUtils.isNotEmpty(preName) && StringUtils.equals(preName, name))
			out.print("true");
		else
		{
			List<MemberRank> list = this.rankService.getListFromProperty("name", name);
			if (list.size() > 0)
				out.print("false");
			else
				out.print("true");
		}
		out.flush();
	}
	
	/**
	 * 检测消费金额
	 * @param out
	 */
	@RequestMapping(value="check_amount.do")
	public void checkAmount(PrintWriter out)
	{
		String prevAmount = this.getUTF8Parameter("prevAmount");
		String amount = this.getParameter("amount");
		if(StringUtils.isNotEmpty(prevAmount) && StringUtils.equals(prevAmount, amount))
			out.print("true");
		else
		{
			List<MemberRank> list = this.rankService.getListFromProperty("amount", Double.valueOf(amount));
			if (list.size() > 0)
				out.print("false");
			else
				out.print("true");
		}
		out.flush();
	}
	
	/**
	 * 检测消费金额
	 * @param out
	 */
	@RequestMapping(value="check_level.do")
	public void checkLevel( PrintWriter out)
	{
		String prevLevel = this.getParameter("prevLevel");
		String level = this.getParameter("level");
		
		if(prevLevel != null && level != null && prevLevel.trim().equalsIgnoreCase(level.trim())){
			
			out.print("true");
		}else{
			
			List<MemberRank> list = this.rankService.getListFromProperty("level", Integer.valueOf(level));
			
			if (list.size() > 0){
				out.print("false");
			}else{
				out.print("true");
			}
		}
		
		out.flush();
	}
}
