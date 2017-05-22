package com.mall.dao.impl;

import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mall.dao.CommentDao;
import com.mall.entity.Comment;
import com.mall.pager.Pager;
import com.mall.util.CriteriaUtils;

@Repository
public class CommentDaoImpl extends BaseDaoImpl<Long, Comment> implements CommentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findByPager(Pager<Comment> pager, Integer geScore, Integer ltScore, Boolean isHasImage) {
		// TODO Auto-generated method stub
		if (pager == null) {
			return null;
		} else {
			Session session = null;
			List<Comment> list = new LinkedList<>();
			try {
				session = this.currentSession();
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Comment.class);

				Criteria query = detachedCriteria.getExecutableCriteria(session);

				// 集合筛选
				if (pager.getInset() != null) {
					CriteriaUtils.setInCondition(query, pager.getInset());
				}
				if (pager.getFilter() != null) {
					CriteriaUtils.setEQCondition(query, pager.getFilter());
				}
				// 模糊查询
				if (pager.getLikes() != null && !pager.getLikes().isEmpty()) {
					CriteriaUtils.setLikeCondition(query, pager.getLikes());
				}
				if (geScore != null) {
					query.add(Restrictions.ge("score", geScore));
				}
				if (ltScore != null) {
					query.add(Restrictions.lt("score", ltScore));
				}
				if (isHasImage != null) {
					query.add(Restrictions.isNotEmpty("imgs"));
				}

				// 查询数量
				query.setProjection(Projections.rowCount());
				pager.setTotalCount(((Number) query.uniqueResult()).longValue());

				// 排序条件
				if (pager.getOrderby() != null && pager.getOrderby().length() > 0) {
					CriteriaUtils.setOrderCondition(query, pager.getOrderby());
				}

				query.setProjection(null);
				query.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

				if (pager.getFirst() > 0)
					query.setFirstResult(pager.getFirst());
				if (pager.getPageSize() > 0)
					query.setMaxResults(pager.getPageSize());
				list.addAll(query.list());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return list;
		}
	}

	@Override
	public long getCount(Pager<Comment> pager, Integer geScore, Integer ltScore, Boolean isHasImage) {
		// TODO Auto-generated method stub
		Session session = null;
		long count = 0;
		try {
			session = this.currentSession();
			Criteria query = session.createCriteria(Comment.class);

			// 集合筛选
			if (pager.getInset() != null) {
				CriteriaUtils.setInCondition(query, pager.getInset());
			}
			if (pager.getFilter() != null) {
				CriteriaUtils.setEQCondition(query, pager.getFilter());
			}
			// 模糊查询
			if (pager.getLikes() != null && !pager.getLikes().isEmpty()) {
				CriteriaUtils.setLikeCondition(query, pager.getLikes());
			}
			if (geScore != null) {
				query.add(Restrictions.ge("score", geScore));
			}
			if (ltScore != null) {
				query.add(Restrictions.lt("score", ltScore));
			}
			if (isHasImage != null) {
				query.add(Restrictions.isNotEmpty("imgs"));
			}

			query.setProjection(Projections.rowCount());
			// 查询数量
			count = ((Number) query.uniqueResult()).longValue();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

}
