package com.mall.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.dao.ArticleDao;
import com.mall.entity.Article;

@Repository
public class ArticleDaoImpl extends BaseDaoImpl<Long, Article> implements ArticleDao {

}
