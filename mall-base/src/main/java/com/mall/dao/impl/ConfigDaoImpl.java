package com.mall.dao.impl;

import java.lang.reflect.Constructor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.mall.dao.ConfigDao;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 配置访问接口实现
 * @author zonghuan
 *
 */

@Repository
public class ConfigDaoImpl implements ConfigDao
{
	@Resource
	private ComboPooledDataSource dataSource;//配置数据源

	@Override
	public Object read(String key, Class<?> clazz)
	{
		Connection connection = null;
		Object object = null;
		
		try
		{
			connection = dataSource.getConnection();
			String sql = "select attribute from tb_config where attribute_key=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setObject(1, key);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				String value = rs.getString("attribute");
				if(value != null && !"".equals(value))
				{
					try
					{
						Class<?>[] paramType = {String.class };
						Object[] params = { value };
						Constructor<?> constructor = clazz.getConstructor(paramType);
						object = constructor.newInstance(params);
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}				
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(connection != null)
				{
					connection.close();
				}
			}
			catch(SQLException ex)
			{
				
			}
		}
		
		return object;
	}

	@Override
	public void write(String key, Object value)
	{
		Connection connection = null;
		
		try
		{
			connection = dataSource.getConnection();
			CallableStatement call = connection.prepareCall("{call write_config_value(?, ?)}");
			call.setString(1, key);
			call.setString(2, value.toString());
			call.execute();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(connection != null)
				{
					connection.close();
				}
			}
			catch(SQLException ex)
			{
				
			}
		}
	}

}
