package com.mall.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.mall.dao.MemberRankDao;
import com.mall.entity.Member;
import com.mall.entity.MemberRank;
import com.mall.util.SQLUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class MemberRankDaoImpl extends BaseDaoImpl<Long, MemberRank> implements MemberRankDao {
	@Resource
	private ComboPooledDataSource dataSource;// 数据源

	@Override
	public Long getAccuracyMemberRank(Member member) {
		Long rank = null;
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			CallableStatement call = connection.prepareCall("{call get_rank(?, ?)}");
			call.setDouble(1, member.getAmount());
			call.registerOutParameter(2, Types.INTEGER);
			call.execute();
			rank = call.getLong(2);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			SQLUtils.closeConnection(connection);
		}

		return rank;
	}

}
