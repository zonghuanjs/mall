package com.mall.util;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 并发工具集
 * @author zonghuan
 *
 */

public abstract class ConcurrentUtils
{
	/**
	 * 将 Hibernate Session绑定到线程
	 * @param sessionFactory		sessionFactory
	 * @return
	 */
	public static boolean bindHibernateSessionToThread(SessionFactory sessionFactory)
	{
		if(TransactionSynchronizationManager.hasResource(sessionFactory))
		{
			return true;
		}
		else
		{
			Session session = sessionFactory.openSession();
			session.setFlushMode(FlushMode.MANUAL);
			SessionHolder sessionHolder = new SessionHolder(session);
			TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);
		}
		
		return false;
	}
	
	/**
	 * 将Hibernate Session从线程解绑
	 * @param participate
	 * @param sessionFactory
	 */
	public static void closeHibernateSessionFromThread(boolean participate, Object sessionFactory)
	{
		if(!participate)
		{
			SessionHolder sessionHolder = (SessionHolder)TransactionSynchronizationManager.unbindResource(sessionFactory);
			SessionFactoryUtils.closeSession(sessionHolder.getSession());
		}
	}
}
