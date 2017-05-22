package com.mall.util;

import java.util.concurrent.TimeUnit;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缓存工具集
 * @author ChenMingcai
 * 2016-11-10
 *
 */

public class CacheUtils
{
	private static final Logger logger = LoggerFactory.getLogger(CacheUtils.class);
	
	/**
	 * 默认过期时间：秒
	 */
	private static int EXPIRED = 60;
	
	/**
	 * 缓存资源池大小
	 */
	private static int RES_POOL_SIZE = 20;
	
	/**
	 * 缓存管理器
	 */
	private static CacheManager cacheManager;
	
	static
	{
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
		cacheManager.init();
	}
	
	/**
	 * 获取缓存
	 * @param name	缓存名称
	 * @param keyClass	缓存键类型
	 * @param valueClass	缓存值类型
	 * @return	
	 */
	public static<K, V> Cache<K, V> getCache(String name, Class<K> keyClass, Class<V> valueClass)
	{
		Cache<K, V> cache = cacheManager.getCache(name, keyClass, valueClass);
		if(cache == null)
		{			
			CacheConfigurationBuilder<K, V> builder = CacheConfigurationBuilder.newCacheConfigurationBuilder(keyClass, valueClass, ResourcePoolsBuilder.heap(RES_POOL_SIZE)).withExpiry(Expirations.timeToLiveExpiration(Duration.of(EXPIRED, TimeUnit.SECONDS)));
			CacheConfiguration<K, V> config = builder.build();
			cache = cacheManager.createCache(name, config);
			logger.debug("缓存{}初始化完毕, 自动过期: {}", name, config.getExpiry());
		}
		return cache;
	}
	
	/**
	 * 获取系统缓存
	 * @param name	缓存名称
	 * @param poolSize	缓存池大小
	 * @param keyClass	缓存键类型
	 * @param valueClass	缓存值类型
	 * @return
	 */
	public static<K, V> Cache<K, V> getCache(String name, int poolSize, Class<K> keyClass, Class<V> valueClass)
	{
		Cache<K, V> cache = cacheManager.getCache(name, keyClass, valueClass);
		if(cache == null)
		{
			if(poolSize <= 0)
			{
				poolSize = RES_POOL_SIZE;
			}
			
			CacheConfigurationBuilder<K, V> builder = CacheConfigurationBuilder.newCacheConfigurationBuilder(keyClass, valueClass, ResourcePoolsBuilder.heap(poolSize)).withExpiry(Expirations.timeToLiveExpiration(Duration.of(EXPIRED, TimeUnit.SECONDS)));
			CacheConfiguration<K, V> config = builder.build();
			cache = cacheManager.createCache(name, config);
			logger.debug("缓存{}初始化完毕, 自动过期: {}", name, config.getExpiry());
		}
		return cache;
	}
}
