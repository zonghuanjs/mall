package com.mall.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.entity.Article;
import com.mall.entity.ArticleCategory;
import com.mall.entity.ArticleResource;
import com.mall.entity.Tag;
import com.mall.pager.Pager;
import com.mall.service.ArticleCategoryService;
import com.mall.service.ArticleResourceService;
import com.mall.service.ArticleService;
import com.mall.service.TagService;

@Controller
@RequestMapping(value = "/article/")
public class ArticleController extends BaseController {

	@Resource
	private ArticleService articleService;

	@Resource
	private ArticleResourceService articleResourceService;

	@Resource
	private TagService tagService;

	@Resource
	private ArticleCategoryService articleCategoryService;

	/**
	 * 文章管理列表页面
	 * 
	 * @param searchValue
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false, value = "pageNumber") Integer pageNumber,
			@RequestParam(required = false, value = "pageSize") Integer pageSize,
			@RequestParam(required = false, value = "searchValue") String searchValue) {
		ModelAndView mv = new ModelAndView("content/article/list");
		Pager<Article> pager = new Pager<>();
		pager.setOrderby("createDate desc");

		if (pageNumber != null) {
			pager.setCurrentIdx(pageNumber);
		}
		if (pageSize != null) {
			pager.setPageSize(pageSize);
		}
		// 查询条件
		String searchProperty = this.getParameter("searchProperty");
		String category = this.getParameter("category");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> likes = new HashMap<>();
		if (searchValue != null && !"".equals(searchValue)) {
			likes.put(searchProperty, searchValue.trim());
		}
		if (category != null && !category.equals("")) {
			map.put("category", this.articleCategoryService.get(Long.valueOf(category)));

		}
		pager.setFilter(map);
		pager.setLikes(likes);
		mv.addObject("searchValue", searchValue);
		mv.addObject("searchProperty", searchProperty);
		mv.addObject("category", category);
		mv.addObject("articleList", this.articleService.findByPager(pager));
		mv.addObject("articlecategorys", this.articleCategoryService.getAll());
		mv.addObject("pager", pager);
		return mv;
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("content/article/add");
		mv.addObject("articleCategoryList", this.articleCategoryService.getAll());
		mv.addObject("tagList", this.tagService.getList("type", 1, "orders"));
		return mv;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String newArticle() {
		Article model = new Article();
		model.setTitle(this.getParameter("title"));
		ArticleCategory category = this.articleCategoryService.get(Long.valueOf(this.getParameter("category")));
		model.setCategory(category);
		model.setAuthor(this.getParameter("author"));
		model.setPublication(this.getParameter("publication").equals("true"));
		model.setTop(this.getParameter("top").equals("true"));
		model.setContent(this.getParameter("content"));
		model.setSeoTitle(this.getParameter("seoTitle"));
		model.setSeoKeywords(this.getParameter("seoKeywords"));
		model.setSeoDescription(this.getParameter("seoDescription"));
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());
		String[] tagIdsStr = this.getParameters("tagIds");
		Set<Tag> tags = new HashSet<>();
		if (tagIdsStr != null) {
			for (String tagIdStr : tagIdsStr) {
				Tag tag = this.tagService.get(Long.valueOf(tagIdStr));
				if (tag != null)
					tags.add(tag);
			}
		}
		this.articleService.add(model);
		model.setTags(tags);
		this.articleService.update(model);
		return "redirect:/article/list.do";
	}

	// 保存文章
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveArticle(@RequestParam("id") Long articleId) {
		Article model = this.articleService.get(articleId);
		if (articleId != null) {
			// 保存文档对应的资源列表
			saveResources(model);

			model.setTitle(this.getParameter("title"));
			ArticleCategory category = this.articleCategoryService.get(Long.valueOf(this.getParameter("category")));
			model.setCategory(category);
			model.setAuthor(this.getParameter("author"));
			model.setPublication(this.getParameter("publication").equals("true"));
			model.setTop(this.getParameter("top").equals("true"));
			model.setContent(this.getParameter("content"));
			model.setSeoTitle(this.getParameter("seoTitle"));
			model.setSeoKeywords(this.getParameter("seoKeywords"));
			model.setSeoDescription(this.getParameter("seoDescription"));

			model.setModifyDate(new Date());
			String[] tagIdsStr = this.getParameters("tagIds");
			Set<Tag> tags = new HashSet<>();
			if (tagIdsStr != null) {
				for (String tagIdStr : tagIdsStr) {
					Tag tag = this.tagService.get(Long.valueOf(tagIdStr));
					if (tag != null)
						tags.add(tag);
				}
			}
			this.articleService.add(model);
			model.setTags(tags);
			this.articleService.update(model);

		}
		return "redirect:/admin/article/list.do";
	}

	// 编辑文章
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editArticle(@RequestParam("id") Long articleId) {
		ModelAndView mv = new ModelAndView("content/article/add");
		if (articleId != null) {
			Article article = this.articleService.get(articleId);
			if (article != null)
				mv.addObject("article", article);
			mv.addObject("articleCategoryList", this.articleCategoryService.getAll());
			mv.addObject("tagList", this.tagService.getList("type", 1, "orders"));
		}
		return mv;
	}

	// 删除文章
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void deleteArticle(HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();

		String[] ids = this.getParameters("ids");
		Long[] deleteIds = new Long[ids.length];
		for (int i = 0; i < ids.length; i++) {
			deleteIds[i] = Long.valueOf(ids[i]);
		}
		if (this.articleService.delete(deleteIds)) {
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

	/**
	 * 保存文档对应的资源列表
	 * 
	 * @param article
	 */
	private void saveResources(Article article) {

		Assert.notNull(article);

		Set<ArticleResource> oldResources = article.getResources();

		Long[] resourceIds = this.getLongParameters("resourceId");

		// 更新已有资源
		if (resourceIds != null && resourceIds.length > 0) {
			List<ArticleResource> resourceList = articleResourceService.get(resourceIds);
			for (ArticleResource ar : resourceList) {
				ar.setUrl(this.getParameter("resourceUrl_" + ar.getId()));
				ar.setTitle(this.getParameter("resourceTitle_" + ar.getId()));

				String order = this.getParameter("resourceOrder_" + ar.getId());
				ar.setOrders(Integer.parseInt(order));
				this.articleResourceService.update(ar);
				oldResources.remove(ar);
			}
		}

		// 删除资源
		Iterator<ArticleResource> iter = oldResources.iterator();
		while (iter.hasNext()) {
			ArticleResource resource = iter.next();
			this.articleResourceService.delete(resource.getId());

			// 清除物理文件
			String realFile = this.getServletContext().getRealPath(resource.getUrl());
			try {
				File file = new File(realFile);
				if (file.exists())
					file.delete();
			} catch (Exception ex) {

			}
		}

		// 保存新商品资源商品资源
		String[] resourceUrls = this.getParameters("resourceUrl");
		String[] resourceTitles = this.getParameters("resourceTitle");
		Integer[] resourceOrders = this.getIntegerParameters("resourceOrder");

		if (resourceUrls != null && resourceUrls.length > 0) {
			for (int i = 0; i < resourceUrls.length; i++) {
				ArticleResource ar = new ArticleResource();
				ar.setUrl(resourceUrls[i]);
				ar.setTitle(resourceTitles[i]);
				ar.setOrders(resourceOrders[i]);
				ar.setArticles(article.getId());
				ar.setCreateDate(new Date());
				ar.setModifyDate(new Date());
				this.articleResourceService.add(ar);
			}
		}

	}
}
