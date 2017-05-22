package com.mall.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mall.dao.PromotionDao;
import com.mall.entity.Product;
import com.mall.entity.Promotion;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class PromotionDaoImpl extends BaseDaoImpl<Long, Promotion> implements PromotionDao
{
	@Autowired
	private ComboPooledDataSource dataSource;


	@Override
	public int getTokenCount(Promotion promotion, Product product)
	{
		int count = 0;
		Connection connection = null;
		StringBuffer tokenBuilder = new StringBuffer(Promotion.Keys.xinCodeKey);
		tokenBuilder.append(promotion.getId()).append("_").append(product.getId());
		try
		{
			connection = dataSource.getConnection();
			String sql = "select count(*) from tb_member_attributes where attributes_key = ? and attributes <> 'false'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, tokenBuilder.toString());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				count = rs.getInt(1);
			}
			connection.close();
		}
		catch(SQLException ex)
		{
			
		}
		return count;
	}

}
