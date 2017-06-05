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
import com.mall.entity.FriendLink;
import com.mall.pager.Pager;
import com.mall.service.FriendLinkService;

@Controller
@RequestMapping(value = "/friendlink/")
public class FriendLinkController extends BaseController {

	@Resource
	private FriendLinkService friendLinkService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView listFriendLinks(@RequestParam(required = false, value = "searchValue") String searchValue) {
		ModelAndView mv = new ModelAndView("content/friendlink/list");
		Pager<FriendLink> pager = new Pager<>();
		pager.setTotalCount(friendLinkService.getCount());
		pager.setOrderby("orders asc");
		if (this.getIntParameter("pageNumber") != null)
			pager.setCurrentIdx(this.getIntParameter("pageNumber"));
		if (this.getIntParameter("pageSize") != null)
			pager.setPageSize(this.getIntParameter("pageSize"));

		// 查询条件
		String searchProperty = this.getParameter("searchProperty");
		if (searchValue != null && !"".equals(searchValue)) {
			Map<String, Object> likes = new HashMap<>();
			likes.put(searchProperty, searchValue.trim());
			pager.setLikes(likes);
		}
		mv.addObject("searchValue", searchValue);
		mv.addObject("searchProperty", searchProperty);
		mv.addObject("friendlinkList", this.friendLinkService.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView addRole() {
		ModelAndView mv = new ModelAndView("content/friendlink/add");
		return mv;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String newFriendLink() {
		FriendLink model = new FriendLink();
		model.setName(this.getParameter("name"));
		model.setUrl(this.getParameter("url"));
		if (this.getParameter("type") == null || this.getParameter("type").equals("")) {
			model.setType(1);
		} else {
			model.setType(Integer.valueOf(this.getParameter("type")));
		}
		if (this.getParameter("orders") == null || this.getParameter("orders").equals("")) {
			model.setOrders(0);
		} else {

			model.setOrders(Integer.valueOf(this.getParameter("orders")));
		}
		model.setLogo(this.getParameter("logo"));
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());

		this.friendLinkService.add(model);
		return "redirect:/friendlink/list.do";
	}

	// 保存友情链接
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveFriendLink(@RequestParam("id") Long friendlinkId) {
		FriendLink model = this.friendLinkService.get(friendlinkId);
		if (friendlinkId != null) {
			model.setName(this.getParameter("name"));
			model.setUrl(this.getParameter("url"));
			if (this.getParameter("type") == null || this.getParameter("type").equals("")) {
				model.setType(1);
			} else {

				model.setType(Integer.valueOf(this.getParameter("type")));
			}
			if (this.getParameter("orders") == null || this.getParameter("orders").equals("")) {
				model.setOrders(0);
			} else {

				model.setOrders(Integer.valueOf(this.getParameter("orders")));
			}
			model.setLogo(this.getParameter("logo"));
			model.setModifyDate(new Date());
			this.friendLinkService.update(model);
		}
		return "redirect:/friendlink/list.do";
	}

	// 编辑友情链接
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editFriendLink(@RequestParam("id") Long friendlinkId) {
		ModelAndView mv = new ModelAndView("/content/friendlink/add");
		if (friendlinkId != null) {
			FriendLink friendlink = this.friendLinkService.get(friendlinkId);
			if (friendlink != null)
				mv.addObject("friendlink", friendlink);
		}
		return mv;
	}

	// 删除链接
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void deleteFriendLink(HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		String[] ids = this.getParameters("ids");
		Long[] deleteIds = new Long[ids.length];
		for (int i = 0; i < ids.length; i++) {
			deleteIds[i] = Long.valueOf(ids[i]);
		}
		if (this.friendLinkService.delete(deleteIds)) {
			rootNode.put("errCode", 0);
		} else {
			rootNode.put("errCode", -1);
		}
		try {
			mapper.writeValue(response.getOutputStream(), rootNode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
