package com.mall.dao.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.mall.dao.BeanUsedDao;
import com.mall.entity.BeanUsed;
import com.mall.pager.Pager;
import com.mall.util.CriteriaUtils;


@Repository
public class BeanUsedDaoImpl extends BaseDaoImpl<Long, BeanUsed> implements BeanUsedDao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<BeanUsed> findByPager(Pager<BeanUsed> pager,
			Map<String, Object> geFilter, Map<String, Object> leFilter)
	{
		// TODO Auto-generated method stub
		if(pager == null)
		{
			return null;
		}
		else
		{
			Session session = null;
			List<BeanUsed> list = new LinkedList<>();
			try
			{
				session = this.currentSession();
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BeanUsed.class);
				
				Criteria query = detachedCriteria.getExecutableCriteria(session);
																
				//集合筛选
				if(pager.getInset() != null)
				{
					CriteriaUtils.setInCondition(query, pager.getInset());
				}
				if(pager.getFilter() != null)
				{
					CriteriaUtils.setEQCondition(query, pager.getFilter());
				}
				//模糊查询
				if(pager.getLikes() != null && !pager.getLikes().isEmpty())
				{
					CriteriaUtils.setLikeCondition(query, pager.getLikes());
				}
				if(geFilter != null)
				{
					CriteriaUtils.setGECondition(query, geFilter);
				}
				if(leFilter != null)
				{
					CriteriaUtils.setLECondition(query, leFilter);
				}
				
				//查询数量
				query.setProjection(Projections.rowCount());
				pager.setTotalCount(((Number)query.uniqueResult()).longValue());
				
				//排序条件
				if(pager.getOrderby() != null && pager.getOrderby().length() > 0)
				{
					CriteriaUtils.setOrderCondition(query, pager.getOrderby());
				}
				
				query.setProjection(null);
				query.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
						
				if(pager.getFirst() > 0)
					query.setFirstResult(pager.getFirst());
				if(pager.getPageSize() > 0)
					query.setMaxResults(pager.getPageSize());
				list.addAll(query.list());
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return list;
		}
	}
}
