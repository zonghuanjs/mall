package com.mall.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.controller.base.BaseController;


/**
 * 验证码模块控制器
 * @author ChenMingcai
 * 2014-09-28(2016-01-16 升级    @author ChenMingcai)
 *
 */

@Controller
public class ValidateCode extends BaseController
{
	/**
	 * 普通验证码
	 */
	public static final String COMMON_KEY = "validate_code";
	
	/**
	 * 计算表达式
	 */
	public static final String COMPUTE_KEY = "compute_code";
	
	/**
	 * 产生验证码
	 * @param response
	 */
	@RequestMapping(value="/validateCode/code.html", method=RequestMethod.GET)
	public void getCodeImage(HttpSession session, HttpServletResponse response)
	{
		response.setHeader("Pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		//获取验证码
		String verifyCode = Image.generateVerifyCode(4);
        int w = 100, h = 30; 
		BufferedImage image = Image.getImage(w, h, verifyCode);
		
		try
		{
			ServletOutputStream sos = response.getOutputStream();
			if(image != null){
				ImageIO.write(image, "png", sos);
		        session.setAttribute("validate_code",verifyCode.toString());
		        //7.禁止图像缓存。
		        response.setHeader("Pragma", "no-cache");
		        response.setHeader("Cache-Control", "no-cache");
		        response.setDateHeader("Expires", 0);
		        response.setContentType("image/png");
			}				
			sos.flush();
			sos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
}
