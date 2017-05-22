package com.mall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.mall.entity.Admin;
import com.mall.entity.Role;
import com.mall.log.OperationType;
import com.mall.log.annotation.LogMethod;
import com.mall.pager.Pager;
import com.mall.service.AdminService;
import com.mall.service.RoleService;
import com.mall.util.CommonUtil;

/**
 * 管理员管理
 * @author zonghuan
 *
 */

@Controller
@RequestMapping(value="/admin/")
public class AdminController extends BaseController
{
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public ModelAndView adminUserList(@RequestParam(required=false, value="pageNumber") Integer pageNumber,
			@RequestParam(required=false, value="pageSize") Integer pageSize,
			@RequestParam(required = false, value="searchValue") String searchValue)
	{
		ModelAndView mv = new ModelAndView("system/admin/list");
		Pager<Admin> pager = new Pager<>();
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
		mv.addObject("adminUserList", this.adminService.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView addAdminUser()
	{
		ModelAndView mv = new ModelAndView("system/admin/add");
		mv.addObject("roles", this.roleService.getAll());
		return mv;
	}
	
	/**
	 * 添加管理员
	 * @return
	 */
	@RequestMapping(value="add", method=RequestMethod.POST)
	@LogMethod(type=OperationType.add, message="添加管理员", parameter={"username"})
	public String newAdminUser(@RequestParam("username") String username)
	{
		Admin model = new Admin();
		model.setUsername(username);
		String password = CommonUtil.toMD5(this.getParameter("password"));
		model.setPassword(password);
		model.setEmail(this.getParameter("email"));
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());
		model.setDepartment(this.getParameter("department"));
		model.setName(this.getParameter("name"));
		//set default data
		model.setEnabled(Boolean.valueOf(this.getParameter("isEnabled")));
		model.setLocked(false);
		//save data to admin
		Long[] roleIds = this.getLongParameters("roleIds");
		Set<Role> roles = new HashSet<>();
		roles.addAll(this.roleService.get(roleIds));
		model.setRoles(roles);	
		this.adminService.add(model);
		return "redirect:/admin/list.html";
	}
	
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView editAdmin(@RequestParam(value="id") Long adminId)
	{
		ModelAndView mv = new ModelAndView("system/admin/add");
		mv.addObject("roles", this.roleService.getAll());
		Admin admin = this.adminService.get(adminId);
		if(admin != null)
			mv.addObject("admin", admin);
		return mv;
	}
	
	/**
	 * 编辑管理员
	 * @param adminId
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	@LogMethod(type=OperationType.update, message="编辑管理员", parameter={"id"})
	public String saveAdmin(@RequestParam(value="id") Long adminId)
	{
		Admin admin = this.adminService.get(adminId);
		if(admin != null)
		{
			String password = this.getParameter("password");
			if(password != null && !password.isEmpty())
			{
				admin.setPassword(CommonUtil.toMD5(password));
			}
			admin.setEmail(this.getParameter("email"));
			admin.setDepartment(this.getParameter("department"));
			admin.setName(this.getParameter("name"));
			admin.setModifyDate(new Date());
			admin.setEnabled(Boolean.valueOf(this.getParameter("isEnabled")));
			
			/*admin.getRoles().clear();
			Long[] roleIds = this.getLongParameters("roleIds");
			admin.getRoles().addAll(this.roleService.get(roleIds));	*/
			Long[] roleIds = this.getLongParameters("roleIds");
			Set<Role> roles = new HashSet<>();
			roles.addAll(this.roleService.get(roleIds));
			admin.setRoles(roles);
			//update admin
			this.adminService.update(admin);
		}
		return "redirect:/admin/list.html";
	}
	
	/**
	 * 删除管理员
	 * @param response
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	@LogMethod(type=OperationType.delete, message="删除管理员", parameter={"ids"})
	public void deleteAdmin(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		
		Long[] delIds = this.getLongParameters("ids");
		int errCode = this.adminService.delete(delIds) ? 0 : -1;
		rootNode.put("errCode", errCode);
		//output result
		try
		{
			mapper.writeValue(response.getOutputStream(), rootNode);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@RequestMapping(value="checkAdminName")
	public void checkAdminName(PrintWriter out)
	{
		String username = this.getParameter("username");
		out.print(!this.AdminUsernameExists(username));
	}
	
	/**
	 * 检测管理员账号是否存在
	 * @param username
	 * @return
	 */
	public boolean AdminUsernameExists(String username)
	{
		List<Admin> list = this.adminService.getListFromProperty("username", username);
		return list.size() > 0;
	}
}
