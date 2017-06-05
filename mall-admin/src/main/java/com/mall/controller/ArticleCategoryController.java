package com.mall.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.ArticleCategory;
import com.mall.pager.Pager;
import com.mall.service.ArticleCategoryService;
import com.mall.service.ArticleService;


@Controller
@RequestMapping(value="/article_category/")
public class ArticleCategoryController extends BaseController
{

	@Resource
	private ArticleService articleService;
	
	@Resource
	private ArticleCategoryService articleCategoryService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, value="searchValue") String searchValue)
	{
		ModelAndView mv = new ModelAndView("content/article_category/list");
		Pager<ArticleCategory> pager = new Pager<>();
		pager.setOrderby("orders asc");
		if(this.getIntParameter("pageNumber") != null)
			pager.setCurrentIdx(this.getIntParameter("pageNumber"));
		if(this.getIntParameter("pageSize") != null)
			pager.setPageSize(this.getIntParameter("pageSize"));
		
		//查询条件
		String searchProperty=this.getParameter("searchProperty");
		if(searchValue != null && !"".equals(searchValue))
		{
			Map<String,Object> likes = new HashMap<>();		
			likes.put(searchProperty, searchValue.trim());
			pager.setLikes(likes);
		}
		mv.addObject("searchValue", searchValue);
		mv.addObject("searchProperty",searchProperty);
		mv.addObject("articlecategoryList", this.articleCategoryService.findByPager(pager));
		mv.addObject("pager", pager);
		return mv;
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("content/article_category/add");
		mv.addObject("articlecategoryList", this.articleCategoryService.getAll("orders asc"));
		return mv;
	}

	//添加文章分类
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String newArticleCategory()
	{
		ArticleCategory model = new ArticleCategory();
		model.setName(this.getParameter("name"));
		model.setParent(Long.valueOf(this.getParameter("parent")));
		model.setSeoTitle(this.getParameter("seoTitle"));
		model.setSeoKeywords(this.getParameter("seoKeywords"));
		model.setSeoDescription(this.getParameter("seoDescription"));
		if(this.getParameter("orders")==null||this.getParameter("orders").equals("")){
			model.setOrders(0);
		}else{
			
			model.setOrders(Integer.valueOf(this.getParameter("orders")));
		}
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());

		this.articleCategoryService.add(model);
		return "redirect:/article_category/list.do";
	}

	// 保存文章分类
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveArticleCategory(@RequestParam("id") Long id)
	{
		ArticleCategory model = this.articleCategoryService.get(id);
		if (id != null)
		{
			model.setName(this.getParameter("name"));			
			model.setParent(Long.valueOf(this.getParameter("parent")));
			model.setSeoTitle(this.getParameter("seoTitle"));
			model.setSeoKeywords(this.getParameter("seoKeywords"));
			model.setSeoDescription(this.getParameter("seoDescription"));			
			if(this.getParameter("orders")==null||this.getParameter("orders").equals("")){
				model.setOrders(0);
			}else{
				
				model.setOrders(Integer.valueOf(this.getParameter("orders")));
			}
			model.setModifyDate(new Date());
			this.articleCategoryService.update(model);
		}
		return "redirect:/article_category/list.do";
	}

	// 编辑文章分类
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editArticleCategory(@RequestParam("id") Long id)
	{
		ModelAndView mv = new ModelAndView("content/article_category/add");
		if (id != null)
		{
			ArticleCategory articlecategory = this.articleCategoryService.get(id);
			if (articlecategory != null)
				mv.addObject("articlecategory", articlecategory);
			mv.addObject("articlecategoryList", this.articleCategoryService.getAll("orders asc"));
		}
		return mv;
	}

	// 删除文章分类
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void deleteArticleCategory(HttpServletResponse response)
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		String[] ids = this.getParameters("ids");
		Long[] deleteIds = new Long[ids.length];
		
		for (int i = 0; i < ids.length; i++)
		{
			
			deleteIds[i] = Long.valueOf(ids[i]);
		}
		if (this.articleCategoryService.delete(deleteIds))
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
