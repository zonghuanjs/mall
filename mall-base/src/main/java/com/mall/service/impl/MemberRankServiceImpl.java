package com.mall.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mall.dao.MemberRankDao;
import com.mall.entity.Member;
import com.mall.entity.MemberRank;
import com.mall.service.MemberRankService;

@Service
public class MemberRankServiceImpl extends BaseServiceImpl<Long, MemberRank> implements MemberRankService {

	@Override
	public MemberRank getDefaultMemberRank() {
		List<MemberRank> memberRanks = this.getAll();
		MemberRank rank = null;
		if (memberRanks != null) {
			for (MemberRank memberRank : memberRanks) {
				if (memberRank.getIsDefault()) {
					rank = memberRank;
				}
			}
		}
		return rank;
	}

	@Override
	public MemberRank getAccuracyMemberRank(Member member) {
		if (member == null) {
			return this.getDefaultMemberRank();
		}

		MemberRankDao dao = (MemberRankDao) this.getDao();
		Long id = dao.getAccuracyMemberRank(member);
		return super.get(id);
	}

}
