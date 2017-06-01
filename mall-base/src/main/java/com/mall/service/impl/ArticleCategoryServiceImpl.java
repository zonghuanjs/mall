package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.ArticleCategoryDao;
import com.mall.entity.Article;
import com.mall.entity.ArticleCategory;
import com.mall.pager.Pager;
import com.mall.service.ArticleCategoryService;
import com.mall.service.ArticleService;

@Service
public class ArticleCategoryServiceImpl extends BaseServiceImpl<Long, ArticleCategory>
		implements ArticleCategoryService {
	// 删除文章
	@Autowired
	private ArticleService articleService;

	@Override
	public boolean delete(Long id) {

		List<Article> articles = this.articleService.getListFromProperty("category", this.get(id));
		for (Article article : articles) {
			this.articleService.delete(article.getId());
		}
		return super.delete(id);
	}

	@Override
	public boolean delete(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				List<Article> articles = this.articleService.getListFromProperty("category", this.get(id));
				for (Article article : articles) {
					this.articleService.delete(article.getId());
				}

			}
		}
		return super.delete(ids);
	}

	@Override
	public List<ArticleCategory> findByPager(Pager<ArticleCategory> pager) {
		// TODO Auto-generated method stub
		ArticleCategoryDao dao = (ArticleCategoryDao) this.getDao();
		return dao.findByPager(pager);
	}

}
