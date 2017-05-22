package com.mall.dao;

import java.util.List;

import com.mall.entity.Ad;
import com.mall.entity.AdPosition;


public interface AdPositionDao extends BaseDao<Long, AdPosition>
{
	/**
	 * 查找广告位的有效广告
	 * @param pos	广告位
	 * @return	广告列表
	 */
	List<Ad> findEffectiveAds(AdPosition pos);
}
