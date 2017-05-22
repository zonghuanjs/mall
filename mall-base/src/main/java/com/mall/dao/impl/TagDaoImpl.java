package com.mall.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.dao.TagDao;
import com.mall.entity.Tag;

@Repository
public class TagDaoImpl extends BaseDaoImpl<Long, Tag> implements TagDao {

}
