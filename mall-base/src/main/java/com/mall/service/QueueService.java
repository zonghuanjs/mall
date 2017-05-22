package com.mall.service;

import java.util.List;

import com.mall.util.QiangQueue;


public interface QueueService
{
	/**
	 * 向队列添加元素
	 * @param key1
	 * @param key2
	 * @param value
	 */
	boolean add(Long key1, Long key2, Long value);
	
	/**
	 * 获取队列
	 * @param key1
	 * @param key2
	 * @return
	 */
	 List<Long> get(Long key1, Long key2);
	 
	 /**
	  * 清空队列
	  * @param key1
	  * @param key2
	  */
	 void clear(Long key1, Long key2);
	 
	 /**
	  * 清空队列
	  * @param key1
	  * @param key2
	  * @param value
	  */
	 void remove(Long key1, Long key2, Long value);
	 
	 /**
	  * 获取队列数据
	  * @return
	  */
	QiangQueue getQueueData();
}
