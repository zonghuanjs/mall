package com.mall.dao;

import com.mall.entity.Member;
import com.mall.entity.MemberRank;

public interface MemberRankDao extends BaseDao<Long, MemberRank> {
	/**
	 * 根据会员信息确认会员的准确会员等级
	 * 
	 * @param member
	 *            会员对象
	 * @return 返回会员等级对象ID
	 */
	Long getAccuracyMemberRank(Member member);
}
