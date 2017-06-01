package com.mall.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.entity.Article;
import com.mall.entity.ArticleResource;
import com.mall.service.ArticleResourceService;
import com.mall.service.ArticleService;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<Long, Article> implements ArticleService {

	@Autowired
	private ArticleResourceService articleResourceService;

	@Override
	public boolean add(Article model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
			model.setHits(0L);
		}
		return super.add(model);
	}

	@Override
	public boolean update(Article model) {
		if (model != null) {
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		Article model = this.get(id);
		if (model != null) {
			preRemove(id);
		}
		return super.delete(id);
	}

	@Override
	public boolean delete(Long[] ids) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			preRemove(id);
		}
		return super.delete(ids);
	}

	/**
	 * 预删除
	 * 
	 * @param id
	 *            文章ID
	 */
	private void preRemove(Long id) {
		Article model = this.get(id);
		if (model != null) {
			// 清理资源
			for (ArticleResource resource : model.getResources()) {
				this.articleResourceService.delete(resource.getId());
			}

		}
	}
}
