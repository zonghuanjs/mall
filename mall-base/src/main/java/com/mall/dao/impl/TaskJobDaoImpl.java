package com.mall.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.dao.TaskJobDao;
import com.mall.entity.TaskJob;

@Repository
public class TaskJobDaoImpl extends BaseDaoImpl<Long, TaskJob> implements TaskJobDao {

}
