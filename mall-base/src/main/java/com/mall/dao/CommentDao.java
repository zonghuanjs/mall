package com.mall.dao;

import java.util.List;

import com.mall.entity.Comment;
import com.mall.pager.Pager;

public interface CommentDao extends BaseDao<Long, Comment> {
	/**
	 * 评论列表
	 * 
	 * @param pager
	 *            分页
	 * @param geScore
	 *            大于等于评分
	 * @param ltScore
	 *            小于评分
	 * @param isHasImage
	 *            是否有图
	 * @return
	 */
	public List<Comment> findByPager(Pager<Comment> pager, Integer geScore, Integer ltScore, Boolean isHasImage);

	/**
	 * 评论数
	 * 
	 * @param pager
	 *            筛选
	 * @param geScore
	 *            大于等于评分
	 * @param ltScore
	 *            小于评分
	 * @param isHasImage
	 *            是否有图
	 * @return
	 */
	public long getCount(Pager<Comment> pager, Integer geScore, Integer ltScore, Boolean isHasImage);
}
