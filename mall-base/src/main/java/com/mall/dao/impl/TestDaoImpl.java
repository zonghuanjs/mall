package com.mall.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.dao.TestDao;
import com.mall.entity.Model;


@Repository                   
public class TestDaoImpl  extends BaseDaoImpl<Long, Model>  implements TestDao
{

	/*@Autowired
	public void setHibernateSessionFactory(SessionFactory factory)
	{
		super.setSessionFactory(factory);
	}
	
	@Override
	public Model getObject(Long id)
	{
		// TODO Auto-generated method stub
		Session session = null;
		Model model = null;
		try
		{
			session = this.currentSession();
			model = (Model) session.get(Model.class, id);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public boolean add(Model model) {
		// TODO Auto-generated method stub
		try
		{
			if(model != null)
			{
				super.getHibernateTemplate().save(model);
			}
			else
			{
				logger.error("实体对象为空, 放弃保存");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	*/
	

}
