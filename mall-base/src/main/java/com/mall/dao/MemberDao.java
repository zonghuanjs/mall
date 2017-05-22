package com.mall.dao;

import com.mall.entity.Member;

public interface MemberDao extends BaseDao<Long, Member> {
	public Member getByUsername(String username);

}
