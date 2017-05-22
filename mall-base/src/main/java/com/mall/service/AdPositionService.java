package com.mall.service;

import java.util.List;

import com.mall.entity.Ad;
import com.mall.entity.AdPosition;

public interface AdPositionService extends BaseService<Long, AdPosition> {
	/**
	 * 查找广告位的有效广告
	 * 
	 * @param pos
	 *            广告位
	 * @return 广告列表
	 */
	List<Ad> findEffectiveAds(AdPosition pos);

	/**
	 * 查找广告位的有效广告
	 * 
	 * @param posId
	 *            广告位id
	 * @return 广告列表
	 */
	List<Ad> findEffectiveAds(Long posId);
}
