package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.AdPositionDao;
import com.mall.entity.Ad;
import com.mall.entity.AdPosition;
import com.mall.service.AdPositionService;
import com.mall.service.AdService;

@Service
public class AdPositionServiceImpl extends BaseServiceImpl<Long, AdPosition> implements AdPositionService {
	// 删除文章
	@Autowired
	private AdService adService;

	@Override
	public boolean delete(Long id) {

		List<Ad> ads = this.adService.getListFromProperty("position", this.get(id));
		for (Ad ad : ads) {
			this.adService.delete(ad.getId());
		}
		return super.delete(id);
	}

	@Override
	public boolean delete(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				List<Ad> ads = this.adService.getListFromProperty("position", this.get(id));
				for (Ad ad : ads) {
					this.adService.delete(ad.getId());
				}

			}
		}
		return super.delete(ids);
	}

	@Override
	public List<Ad> findEffectiveAds(AdPosition pos) {
		AdPositionDao dao = (AdPositionDao) getDao();
		return dao.findEffectiveAds(pos);
	}

	@Override
	public List<Ad> findEffectiveAds(Long posId) {
		AdPosition position = get(posId);
		return findEffectiveAds(position);
	}
}
