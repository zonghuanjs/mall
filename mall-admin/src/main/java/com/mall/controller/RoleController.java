package com.mall.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.annotation.LogMethod;
import com.mall.controller.base.BaseController;
import com.mall.entity.Role;
import com.mall.log.OperationType;
import com.mall.pager.Pager;
import com.mall.service.RoleService;
import com.mall.util.CommonUtil;

/**
 * 
 * @author zonghuan
 *
 */

@Controller
@RequestMapping(value="/role/")
public class RoleController extends BaseController
{
	@Resource
	private RoleService roleSerivce;
	
	/**
	 * 查看角色列表
	 * @return
	 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView listRoles(@RequestParam(required=false, value="pageNumber") Integer pageNumber,
			@RequestParam(required=false, value="pageSize") Integer pageSize,
			@RequestParam(required = false, value="searchValue") String searchValue)
	{
		ModelAndView mv = new ModelAndView("system/role/list");
		
		Pager<Role> pager = new Pager<>();
		pager.setOrderby("createDate asc");
		
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
		if(StringUtils.isNotEmpty(searchValue) && StringUtils.isNotEmpty(searchProperty))
		{
			Map<String,Object> likeFilter =new HashMap<>();		
			likeFilter.put(searchProperty, searchValue.trim());
			pager.setLikes(likeFilter);    
		}
		
		mv.addObject("searchValue",searchValue);
		mv.addObject("searchProperty",searchProperty);
		mv.addObject("roleList", this.roleSerivce.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView addRole()
	{
		ModelAndView mv = new ModelAndView("system/role/add");
		return mv;
	}
	
	/**
	 * 增加管理员角色
	 * @return
	 */
	@RequestMapping(value="add", method=RequestMethod.POST)
	@LogMethod(type=OperationType.add, message="添加角色", parameter={"name"})
	public String newRole(@RequestParam("name") String name)
	{
		Role model = new Role();
		model.setName(name);
		model.setDescription(this.getParameter("description"));
		
		//get authorities
		String[] authorities = this.getParameters("role_authorities");
		if(authorities.length > 0)
		{
			String pession = CommonUtil.toString(authorities, ",");
			model.setAuthorities(pession);
		}
		
		model.setSystem(false);
		this.roleSerivce.add(model);
		return "redirect:/role/list.html";
	}
	
	/**
	 * 跳转编辑页面
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editRole(@RequestParam("id") Long roleId)
	{
		ModelAndView mv = new ModelAndView("system/role/add");
		if(roleId != null)
		{
			Role role = this.roleSerivce.get(roleId);
			if(role != null)
			{
				mv.addObject("role", role);
				String[] array = role.getAuthorities().split(",");
				mv.addObject("role_authorities", array);
			}
		}
		return mv;
	}
	
	/**
	 * 保存角色
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	@LogMethod(type=OperationType.update, message="编辑角色", parameter={"id"})
	public String saveRole(@RequestParam("id") Long roleId)
	{
		if(roleId != null)
		{
			Role model = this.roleSerivce.get(roleId);
			if(model != null)
			{
				//save the role
				model.setName(this.getParameter("name"));
				model.setDescription(this.getParameter("description"));
				
				//get authorities
				String[] authorities = this.getParameters("role_authorities");
				if(authorities.length > 0)
				{
					String pession = CommonUtil.toString(authorities, ",");
					model.setAuthorities(pession);
				}
				this.roleSerivce.update(model);
			}
		}
		return "redirect:/role/list.html";
	}
	
	/**
	 * 删除角色
	 * @param response
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	@LogMethod(type = OperationType.delete, message = "删除角色", parameter = {"ids"})
	public void deleteRole(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		
		String[] ids = this.getParameters("ids");
		Long[] deleteIds = new Long[ids.length];
		for(int i = 0; i < ids.length; i++)
		{
			deleteIds[i] = Long.valueOf(ids[i]);
		}
		if(this.roleSerivce.delete(deleteIds))
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
