package com.mall.service;

import com.mall.entity.Member;
import com.mall.entity.MemberRank;

public interface MemberRankService extends BaseService<Long, MemberRank> {
	/**
	 * 获取默认会员等级
	 * 
	 * @return
	 */
	MemberRank getDefaultMemberRank();

	/**
	 * 根据会员信息确认会员的准确会员等级
	 * 
	 * @param member
	 *            会员对象
	 * @return 返回会员等级对象
	 */
	MemberRank getAccuracyMemberRank(Member member);
}
