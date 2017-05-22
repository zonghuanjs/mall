package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.QueueDao;
import com.mall.service.QueueService;
import com.mall.util.QiangQueue;

@Service
public class QueueServiceImpl implements QueueService {
	@Autowired
	private QueueDao dao;

	@Override
	public boolean add(Long key1, Long key2, Long value) {
		if (key1 == null || key2 == null || value == null) {
			return false;
		}

		return dao.add(key1, key2, value);
	}

	@Override
	public List<Long> get(Long key1, Long key2) {
		if (key1 == null || key2 == null) {
			return null;
		}

		return dao.get(key1, key2);
	}

	@Override
	public void clear(Long key1, Long key2) {
		if (key1 == null || key2 == null) {
			return;
		}

		dao.clear(key1, key2);
	}

	@Override
	public void remove(Long key1, Long key2, Long value) {
		if (key1 == null || key2 == null || value == null) {
			return;
		}

		dao.remove(key1, key2, value);
	}

	@Override
	public QiangQueue getQueueData() {

		return dao.getQueueData();
	}

}
