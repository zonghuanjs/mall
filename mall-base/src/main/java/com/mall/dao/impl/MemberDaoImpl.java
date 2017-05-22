package com.mall.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mall.dao.MemberDao;
import com.mall.entity.Member;

@Repository
public class MemberDaoImpl extends BaseDaoImpl<Long, Member> implements MemberDao {

	@Override
	public Member getByUsername(String username) {
		Session session = null;
		Member member = null;
		try {
			session = this.currentSession();
			String hql = "from Member m where m.username=?";
			member = (Member) session.createQuery(hql).setString(0, username).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

}
