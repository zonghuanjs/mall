package cn.tekism.mall.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;

import cn.tekism.mall.bean.SystemConfig;

/**
 * 系统配置工具集
 * @author zonghuan
 *
 */

public class SystemConfigUtil
{
	private static final Logger logger = LoggerFactory.getLogger(SystemConfigUtil.class);
	
	/**
	 * 缓存配置文件
	 */
	private static final String CONFIG_FILE_NAME = "config_cache.properties";//系统配置文件
	
	private static final String CONFIG_KEY = "mall_system_config";
	
	/**
	 * 缓存时间：5分钟
	 */
	private static final int updatePeriod = 60 * 5;//配置同步周期
	
	private static DruidDataSource dataSource = new DruidDataSource();
	
	/**
	 * 缓存客户端
	 */
	private static MemcachedClient cacheClient = null;
	
	static
	{
		InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE_NAME);
		if(ins != null)
		{			
			try
			{
				Properties settings = new Properties();
				settings.load(ins);
				ins.close();
				
				logger.info("系统配置缓存设置文件{}加载完毕...", CONFIG_FILE_NAME);
				
				//配置数据源
				dataSource.setDriverClassName(settings.getProperty("config.jdbc.driver"));
				dataSource.setUrl(settings.getProperty("config.jdbc.url"));
				dataSource.setUsername(settings.getProperty("config.jdbc.user"));
				dataSource.setPassword(settings.getProperty("config.jdbc.password"));
				logger.info("配置数据连接池设置完毕...");
				
				//配置缓存
				String server = settings.getProperty("config.cache.server");
				if(server != null && !"".equals(server.trim()))
				{
					XMemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(server));
					try
					{
						builder.setCommandFactory(new BinaryCommandFactory());
						cacheClient = builder.build();
						cacheClient.setConnectTimeout(1);
					}
					catch(Exception ex)
					{
						logger.error("缓存服务器({})连接失败!", server);
					}
				}
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
			
		}
		
		//配置连接池
		dataSource.setInitialSize(1);
		dataSource.setMinIdle(1);
		dataSource.setMaxActive(2);
		dataSource.setValidationQuery("select 1");
		dataSource.setTestWhileIdle(true);
	}
			
	/**
	 * 获取系统配置
	 * @return
	 */
	public static SystemConfig getSystemConfig()
	{
		SystemConfig config = null;
		
		if(cacheClient != null)
		{
			try
			{
				config = (SystemConfig)cacheClient.get(CONFIG_KEY);
				System.out.println("aaa");
			}
			catch(Exception ex)
			{
				logger.error("从缓存读取配置失败: {}", ex.getMessage());
			}
		}
		else
		{
			logger.info("未设置系统缓存,从数据库读取配置信息");
		}
		
		try
		{			
			if(config == null)
			{
				//加系统配置
				config = fromDataBase();
				if(cacheClient != null)
				{
					cacheClient.set(CONFIG_KEY, updatePeriod, config);
					logger.debug("系统配置存入缓存:key{}!", CONFIG_KEY);
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
		return config;
	}
	
	/**
	 * 更新系统配置
	 * @param config
	 */
	public static synchronized void updateSystemConfig(SystemConfig config)
	{
		try
		{			
			toDataBase(config);
			
			if(cacheClient != null)
			{
				cacheClient.set(CONFIG_KEY, updatePeriod, config);
				logger.debug("系统缓存更新: key{}", CONFIG_KEY);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * 将系统配置写入数据库
	 */
	public static void toDataBase(SystemConfig config)
	{
		Map<String, String> configValues = configToMap(config);
		writeConfigValue(configValues);
	}
	
	/**
	 * 从数据库获取系统配置
	 * @param 
	 * @return
	 */
	public static SystemConfig fromDataBase()
	{
		SystemConfig config = new SystemConfig();
		
		Map<String, String> configValues = getConfigValues();
		if(configValues.isEmpty())
		{
			return config;
		}
		
		Field[] fields = config.getClass().getDeclaredFields();
		for(Field field : fields)
		{
			field.setAccessible(true);
			String fieldName = field.getName();
			try
			{
				String objValue = configValues.get(fieldName);
				int flag = Modifier.STATIC | Modifier.FINAL;
				boolean read = (flag & field.getModifiers()) == 0;
				if (read && objValue != null && StringUtils.isNotEmpty(objValue))
				{
					// fill the field
					if (field.getType() == String.class)
					{
						field.set(config, objValue);
					}
					else if(field.getType() == boolean.class || field.getType() == Boolean.class)
					{
						field.setBoolean(config, Boolean.valueOf(objValue));
					}
					else if(field.getType() == int.class || field.getType() == Integer.class)
					{
						field.setInt(config, Integer.valueOf(objValue));
					}
					else if(field.getType() == double.class || field.getType() == Double.class)
					{
						field.setDouble(config, Double.valueOf(objValue));
					}
					else if(field.getType() == SystemConfig.RoundType.class)
					{
						field.set(config, SystemConfig.RoundType.valueOf(objValue));
					}
					else if(field.getType() == long.class || field.getType() == Long.class)
					{
						field.set(config,  Long.valueOf(objValue));
					}
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return config;
	}

	/**
	 * 一次加载所有配置信息
	 * @return
	 */
	private static Map<String, String> getConfigValues()
	{
		Map<String, String> configValues = new HashMap<>();
		try(Connection connection = dataSource.getConnection())
		{
			PreparedStatement ps = connection.prepareStatement("select c.attribute_key as c_key, c.attribute as c_value from tb_config c");
			CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
			crs.populate(ps.executeQuery());
			ps.close();
			
			while(crs.next())
			{
				String key = crs.getString(1);
				String value = crs.getString(2);
				configValues.put(key, value);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return configValues;
	}
	
	/**
	 * 将配置对象转换成键值对
	 * @param config
	 * @return
	 */
	private static Map<String, String> configToMap(SystemConfig config)
	{
		Map<String, String> configValues = new HashMap<String, String>();
		
		if(config != null)
		{
			Field[] fields = config.getClass().getDeclaredFields();
			for(Field field : fields)
			{
				field.setAccessible(true);
				String fieldName = field.getName();
				int flag = Modifier.STATIC | Modifier.FINAL;
				boolean save = (flag & field.getModifiers()) == 0;
				if (save )
				{
					try
					{
						Object objValue = field.get(config);
						String value = objValue == null ? "" : objValue.toString();
						configValues.put(fieldName, value);											
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
				field.setAccessible(false);
			}
		}
		
		return configValues;
	}
	
	/**
	 * 向数据库写入配置项值
	 * @param key		配置项key
	 * @param value		配置项value
	 */
	private static void writeConfigValue(Map<String, String> configValues)
	{
		
		if(configValues == null || configValues.isEmpty())
		{
			return;
		}
		
		try(Connection connection = dataSource.getConnection())
		{
			connection.setAutoCommit(false);
			Statement statement = connection.createStatement();
			statement.addBatch("set sql_safe_updates=0");
			//statement.addBatch("delete from tb_config where attribute_key not like 'MobileHomeConfig.%'");
			statement.addBatch("delete from tb_config");
			statement.addBatch("set sql_safe_updates=0");
			int[] result = statement.executeBatch();
			boolean success = true;
			if(result[1] <= 0)
			{
				logger.error("数据清除失败");
				success = false;
			}
			else
			{
				String insertSQL = "insert into tb_config(attribute_key, attribute) values(?, ?)";
				
				PreparedStatement ps2 = connection.prepareStatement(insertSQL);
				Iterator<?> iter = configValues.entrySet().iterator();
				while (iter.hasNext())
				{
					@SuppressWarnings("unchecked")
					Map.Entry<String, String> entity = (Map.Entry<String, String>)iter.next();
					ps2.setString(1, entity.getKey());
					ps2.setString(2, entity.getValue());
					ps2.addBatch();
				}
				result = ps2.executeBatch();
				int count = 0;
				for(int i = 0; i < result.length; i++)
				{
					count += result[i];
				}
				if(count != result.length)
				{
					success = false;
				}
			}
			
			if(success)
			{
				connection.commit();
			}
			else
			{
				connection.rollback();
			}
			
			connection.setAutoCommit(true);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
