package com.mall.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.BeanRetain;
import com.mall.entity.BeanUsed;
import com.mall.entity.Member;
import com.mall.entity.MemberRank;
import com.mall.pager.Pager;
import com.mall.service.AreaService;
import com.mall.service.BeanRetainService;
import com.mall.service.BeanUsedService;
import com.mall.service.MemberRankService;
import com.mall.service.MemberService;
import com.mall.service.StatisticService;
import com.mall.util.CommonUtil;


@Controller
@RequestMapping(value="/member/")
public class MemberController extends BaseController
{
	@Resource
	private MemberService memberService;
	
	@Resource
	private MemberRankService rankService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private StatisticService statisticService;
	
	
	/**
	 * 芯豆使用服务
	 */
	@Resource
	private BeanUsedService beanUsedService;
	/**
	 * 芯豆获取服务
	 */
	@Resource
	private BeanRetainService beanRetainService;
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView index(@RequestParam(required=false, value="searchValue") String searchValue)
	{
		ModelAndView mv = new ModelAndView("member/member/list");
		Pager<Member> pager = new Pager<>();
		pager.setOrderby("createDate desc");
		
		if(this.getIntParameter("pageNumber") != null)
		{
			pager.setCurrentIdx(this.getIntParameter("pageNumber"));
		}
		
		if(this.getIntParameter("pageSize") != null)
		{
			pager.setPageSize(this.getIntParameter("pageSize"));
		}
		
		//查询条件
		String searchProperty = this.getParameter("searchProperty");
		if(StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue))
		{	
			Map<String, Object> likes = new HashMap<>();	
			likes.put(searchProperty, searchValue.trim());
			pager.setLikes(likes);
		}
		
		mv.addObject("searchValue", searchValue);
		mv.addObject("searchProperty",searchProperty);		
		mv.addObject("members", this.memberService.findByPager(pager));
		
		//检测是否ID查询
		if("id".equals(searchProperty) && StringUtils.isNotEmpty(searchValue))
		{
			Member[] members = { memberService.get(new Long(searchValue)) };
			if(members[0] != null)
			{
				
				mv.addObject("members", members);
				pager.setTotalCount(1);
			}			
		}
		
		mv.addObject("pager", pager);
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("member/member/add");
		mv.addObject("ranks", this.rankService.getAll());
		return mv;
	}
	
	/**
	 * 添加会员
	 * @return
	 */
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String newMember()
	{
		Member model = new Member();
		model.setUsername(this.getParameter("username"));
		String password = this.getParameter("password");
		password = CommonUtil.toMD5(password);
		model.setPassword(password);
		model.setEmail(this.getParameter("email"));
		MemberRank rank = this.rankService.get(Long.valueOf(this.getParameter("memberRankId")));
		model.setMemberRank(rank);
		boolean isEabled = StringUtils.isNotEmpty(this.getParameter("isEnabled")) ? Boolean.valueOf(this.getParameter("isEnabled")) : false;
		model.setEnabled(isEabled);
		model.setPoint(Long.valueOf(this.getParameter("point")));
		model.setBalance(Double.valueOf(this.getParameter("balance")));
		//person information
		model.setName(this.getParameter("name"));
		model.setBirth(CommonUtil.fromDateString(this.getParameter("birth")));
		model.setGender(Integer.valueOf(this.getParameter("gender")));
		model.setAddress(this.getParameter("address"));
		String areaId = this.getParameter("areaId");
		if(StringUtils.isNotEmpty(areaId))
			model.setArea(this.areaService.get(Long.valueOf(areaId)));
		model.setLocation(0L);
		this.memberService.add(model);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") Long id)
	{
		ModelAndView mv = new ModelAndView("member/member/add");
		mv.addObject("ranks", this.rankService.getAll());
		Member model = this.memberService.get(id);
		if(model != null)
			mv.addObject("member", model);
		return mv;
	}
	
	/**
	 * 查看会员信息
	 * @param id	会员标识
	 * @return
	 */
	@RequestMapping(value="view", method=RequestMethod.GET)
	public ModelAndView view(@RequestParam("id") Long id)
	{
		ModelAndView mv = new ModelAndView("member/member/view");
		mv.addObject("ranks", this.rankService.getAll());
		Member member = this.memberService.get(id);
		if(member != null)
		{
			List<BeanRetain> beanRetains = this.beanRetainService.getList("member", member, "createDate desc");
			List<BeanUsed> beanUseds = this.beanUsedService.getList("member", member, "createDate desc");
			mv.addObject("member", member);
			//mv.addObject("memberView", new MemberView(member));
			mv.addObject("beanRetains", beanRetains);
			mv.addObject("beanUseds", beanUseds);
		}
		return mv;
	}
	
	/**
	 * 会员详情显示
	 * @param id
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String save(@RequestParam("id") Long id)
	{
		Member model = this.memberService.get(id);
		if(model != null)
		{
			model.setUsername(this.getParameter("username"));
			if(StringUtils.isNotEmpty(this.getParameter("password")))
				model.setPassword(CommonUtil.toMD5(this.getParameter("password")));
			model.setEmail(this.getParameter("email"));
			boolean isEabled = StringUtils.isNotEmpty(this.getParameter("isEnabled")) ? Boolean.valueOf(this.getParameter("isEnabled")) : false;
			model.setEnabled(isEabled);
			MemberRank rank = this.rankService.get(Long.valueOf(this.getParameter("memberRankId")));
			model.setMemberRank(rank);
			model.setPoint(Long.valueOf(this.getParameter("point")));
			model.setBalance(Double.valueOf(this.getParameter("balance")));
			//persion information
			model.setName(this.getParameter("name"));
			model.setBirth(CommonUtil.fromDateString(this.getParameter("birth")));
			model.setGender(Integer.valueOf(this.getParameter("gender")));
			model.setAddress(this.getParameter("address"));
			String areaId = this.getParameter("areaId");
			if(StringUtils.isNotEmpty(areaId))
				model.setArea(this.areaService.get(Long.valueOf(areaId)));
			this.memberService.update(model);
		}
		return "redirect:/member/list.do";
	}
	
	/**
	 * 删除会员
	 * @param out
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public void delete(OutputStream out)
	{
		String[] ids = this.getParameters("ids");
		Long[] delIds = new Long[ids.length];
		for(int i = 0; i < ids.length; i++)
		{
			delIds[i] = Long.valueOf(ids[i]);
		}
		int errCode = this.memberService.delete(delIds) ? 0 : 1222;
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode rootNode = mapper.createObjectNode();
			rootNode.put("errCode", errCode);
			mapper.writeValue(out, rootNode);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * 检测用户名是否已经使用
	 * @param out
	 */
	@RequestMapping(value="/check_username.do")
	public void checkUsername(PrintWriter out)
	{
		String prevUsername = this.getUTF8Parameter("prevUsername");
		String username = this.getUTF8Parameter("username");
		if(StringUtils.isNotEmpty(prevUsername) && StringUtils.equals(prevUsername, username))
			out.print("true");
		else
		{
			String result = this.memberService.checkUserNameExists(username) ? "false" : "true";
			out.print(result);
		}
		out.flush();
	}
	
	/**
	 * 检测email是否存在
	 * @param out
	 */
	@RequestMapping(value="/check_email.do")
	public void checkEmail(PrintWriter out)
	{
		String prevEmail = this.getUTF8Parameter("prevEmail");
		String email = this.getUTF8Parameter("email");
		if(StringUtils.isNotEmpty(prevEmail) && StringUtils.equals(prevEmail, email))
			out.print("true");
		else
		{
			String result = this.memberService.checkEmailExists(email) ? "false" : "true";
			out.print(result);
		}
		out.flush();
	}
	
	/**
	 * 校验会员等级
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value="/check_rank.do", method=RequestMethod.POST)
	@ResponseBody
	public String checkMemberRank(@RequestParam("id") Long memberId)
	{
		Member member = memberService.get(memberId);
		if(member != null)
		{
			MemberRank rank = rankService.getAccuracyMemberRank(member);
			if(!member.getMemberRank().getId().equals(rank.getId()))
			{
				member.setMemberRank(rank);
				memberService.update(member);
			}
		}
		return "true";
	}
}
