package com.mall.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.mall.dao.CommentDao;
import com.mall.entity.Comment;
import com.mall.entity.Product;
import com.mall.pager.Pager;
import com.mall.service.BeanRetainService;
import com.mall.service.CommentService;
import com.mall.service.ProductService;
import com.mall.util.CommonUtil;

@Repository
public class CommentServiceImpl extends BaseServiceImpl<Long, Comment> implements CommentService {
	@Resource
	private ProductService productService;

	@Resource
	private BeanRetainService beanService;

	@Override
	public boolean add(Comment model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
			// 安全检测
			this.secureCheck(model);
		}

		boolean result = super.add(model);

		Product product = model.getOrderItem().getProduct();
		if (result && product != null) {
			// 更新商品评分
			productService.updateScore(product);
		}

		if (result) {
			// 发放芯豆
			beanService.issueBean(model);
		}

		return result;
	}

	@Override
	public boolean update(Comment model) {
		if (model != null) {
			model.setModifyDate(new Date());
			// 安全检测
			this.secureCheck(model);
		}
		return super.update(model);
	}

	@Override
	public List<Comment> findByPager(Pager<Comment> pager) {
		// TODO Auto-generated method stub
		CommentDao dao = (CommentDao) this.getDao();
		return dao.findByPager(pager);
	}

	/**
	 * 安全性检测
	 * 
	 * @param model
	 */
	private void secureCheck(Comment model) {
		if (model != null) {
			model.setContent(CommonUtil.htmlToPlainText(model.getContent()));
		}
	}

	@Override
	public List<Comment> findByPager(Pager<Comment> pager, Integer geScore, Integer ltScore, Boolean isHasImage) {
		// TODO Auto-generated method stub
		CommentDao dao = (CommentDao) this.getDao();
		return dao.findByPager(pager, geScore, ltScore, isHasImage);
	}

	@Override
	public long getCount(Pager<Comment> pager, Integer geScore, Integer ltScore, Boolean isHasImage) {
		// TODO Auto-generated method stub
		CommentDao dao = (CommentDao) this.getDao();
		return dao.getCount(pager, geScore, ltScore, isHasImage);
	}
}
