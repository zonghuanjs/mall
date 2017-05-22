package com.mall.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 队列处理工具类
 * @author zonghuan
 *
 */

public class QiangQueue
{
	private Map<Key, List<Long> > cache = new HashMap<>();
	
	/**
	 * 向队列添加元素
	 * @param key1
	 * @param key2
	 * @param value
	 */
	public synchronized void add(Long key1, Long key2, Long value)
	{
		Key key = new Key(key1, key2);
		List<Long> values = cache.get(key);
		if(values == null)
		{
			values = new LinkedList<>();
			cache.put(key, values);
		}
		values.add(value);
	}
	
	/**
	 * 获取队列
	 * @param key1
	 * @param key2
	 * @return
	 */
	public synchronized List<Long> get(Long key1, Long key2)
	{
		Key key = new Key(key1, key2);;
		return cache.get(key);
	}
	
	/**
	 * 清空队列
	 * @param key1
	 * @param key2
	 */
	public synchronized void clear(Long key1, Long key2)
	{
		Key key = new Key(key1, key2);
		List<Long> values = cache.get(key);
		if(values != null)
		{
			values.clear();
		}
	}
	
	/**
	 * 删除队列中的值
	 * @param key1
	 * @param key2
	 * @param value
	 */
	public synchronized void remove(Long key1, Long key2, Long value)
	{
		Key key = new Key(key1, key2);
		List<Long> values = cache.get(key);
		if(values != null)
		{
			values.remove(value);
		}
	}
	
	/**
	 * 获取缓存队列数据
	 * @return
	 */
	public Map<Key, List<Long> > getCacheData()
	{
		Map<Key, List<Long> > tmpData = new HashMap<>(cache);
		return tmpData;
	}
	
	/**
	 * 特殊键
	 * @author ChenMingcai
	 *
	 */
	public static class Key
	{
		private Long key1;
		private Long key2;
		
		public Key(Long key1, Long key2)
		{
			this.key1 = key1;
			this.key2 = key2;
		}

		public Long getKey1()
		{
			return key1;
		}

		public void setKey1(Long key1)
		{
			this.key1 = key1;
		}

		public Long getKey2()
		{
			return key2;
		}

		public void setKey2(Long key2)
		{
			this.key2 = key2;
		}
		
		@Override
		public int hashCode()
		{
			return (new HashCodeBuilder()).append(key1).append(key2).hashCode();
		}
		
		@Override
		public boolean equals(Object other)
		{
			if(!(other instanceof Key))
			{
				return false;
			}
			else
			{
				Key oKey = (Key)other;
				return (new EqualsBuilder()).append(key1, oKey.key1).append(key2, oKey.key2).isEquals();
			}
		}
	}
}
