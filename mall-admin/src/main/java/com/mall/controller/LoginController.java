package com.mall.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.Admin;
import com.mall.entity.Role;
import com.mall.service.AdminService;

import cn.tekism.mall.bean.SystemConfig;
import cn.tekism.mall.util.CommonUtil;

/**
 * 
 * @author zonghuan
 *
 */
@Controller
public class LoginController extends BaseController
{
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/login.html", method=RequestMethod.GET)
	public ModelAndView openLogin()
	{
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@RequestMapping(value="/login.html", method=RequestMethod.POST)
	//@AdminLoginMethod(remark="管理员登录")
	public void login(HttpServletResponse response)
	{
		int errCode = 0;
		String username = this.getParameter("username");
		String password = this.getParameter("password");
		String validateCode = this.getParameter("validateCode").toUpperCase();
				
		//check validate code
		if(!this.checkValidateCode(validateCode))
		{
			errCode = 1310;
		}
		else
		{
			//check username and password
			password = CommonUtil.toMD5(password);
			List<Admin> list = this.adminService.getListFromProperty("username", username);
			if(list.size() == 0)
				errCode = 1320;
			else
			{
				Admin user = list.iterator().next();
				//check if user enabled
				if(!user.getEnabled())
				{
					errCode = 1350;
				}
				else
				{
					// check whether the administrator is locked
					if (user.getLocked())
					{
						errCode = 1340;
					}
					else
					{
						// check password
						if (!password.equals(user.getPassword()))
						{
							int count = user.getLoginFailureCount();
							user.setLoginFailureCount(count + 1);
							this.adminService.update(user);
							errCode = 1330;
						}
						else
						{
							// login success
							this.getSession().setAttribute(SystemConfig.ADMIN_LOGIN_KEY, user.getId());
							// update admin
							user.setLoginIP(this.getRemoteIp());
							user.setLoginFailureCount(0);
							user.setLoginDate(new Date());
							this.adminService.update(user);
							// set roles id
							if(user.getRoles().size() > 0)
							{
								Role role = user.getRoles().iterator().next();
								this.getSession().setAttribute(SystemConfig.USER_PERMISSION_KEY, role.getId());
							}
						}
					}
				}
			}
		}
		
		//output result
		try
		{
			System.out.println(errCode);
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode rootNode = mapper.createObjectNode();
			rootNode.put("errCode", errCode);
			mapper.writeValue(response.getOutputStream(), rootNode);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	@RequestMapping(value="/logout.html")
	public String logout()
	{
		this.getSession().removeAttribute(SystemConfig.ADMIN_LOGIN_KEY);
		return "redirect:/login.html";
	}
}
