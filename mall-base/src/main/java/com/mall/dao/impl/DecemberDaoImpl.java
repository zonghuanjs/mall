package com.mall.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.mall.dao.DecemberDao;
import com.mall.entity.Member;
import com.mall.util.SQLUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.StringUtils;

@Repository
public class DecemberDaoImpl implements DecemberDao
{
	@Resource
	private ComboPooledDataSource dataSource;//数据源

	@Override
	public String lockMemberBook(Member member)
	{
		String ret = "";
		
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			CallableStatement call = connection.prepareCall("{call lock_december_book(?,?)}");
			call.setLong(1, member.getId());
			call.registerOutParameter(2, Types.VARCHAR);
			call.execute();
			String sn = call.getString(2);
			if(!StringUtils.isNullOrEmpty(sn))
			{
				ret = sn;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			SQLUtils.closeConnection(connection);
		}
		
		return ret;
	}

	@Override
	public boolean inFilterList(Member member)
	{
		boolean exist = false;
		
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			String sql = "select count(*) as num from tb_filter_list where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, member.getId());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				int count = rs.getInt(1);
				exist = count >= 1;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			SQLUtils.closeConnection(connection);
		}
		
		return exist;
	}

	@Override
	public List<Long> filterList()
	{
		List<Long> list = new LinkedList<>();
		
		Connection connection = null;
		try
		{
			connection = dataSource.getConnection();
			String sql = "select id from tb_filter_list";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(rs.getLong(1));
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			SQLUtils.closeConnection(connection);
		}
		return list;
	}

}
