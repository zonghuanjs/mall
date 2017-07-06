package com.mall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;


@Controller
public class MemberCommentAction extends BaseController
{
	@RequestMapping(value="/member/comment.do", method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("member/Comment");
		mv.addObject("base", this.getBasePath());
		return mv;
	}
}
