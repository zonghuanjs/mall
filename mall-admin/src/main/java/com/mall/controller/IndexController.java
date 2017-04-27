package com.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;

/**
 * 后台管理首页控制器
 * 
 * @author zonghuan
 *
 */

@Controller
public class IndexController extends BaseController {
	/**
	 * 后台管理主页框架页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	/**
	 * 后台管理首页页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");

		// 系统信息
		mv.addObject("serverName", this.getRequest().getServerName());
		mv.addObject("serverPort", this.getRequest().getServerPort());
		mv.addObject("serverVersion", this.getServletContext().getServerInfo());
		mv.addObject("majorVersion", this.getServletContext().getMajorVersion());
		mv.addObject("minorVersion", this.getServletContext().getMinorVersion());

		// 内存信息
		Runtime runtime = Runtime.getRuntime();
		mv.addObject("freeMemory", runtime.freeMemory() / 1024 / 1024);
		mv.addObject("maxMemory", runtime.maxMemory() / 1024 / 1024);
		mv.addObject("totalMemory", runtime.totalMemory() / 1024 / 1024);
		mv.addObject("availableProcessors", runtime.availableProcessors());
		return mv;
	}

}
