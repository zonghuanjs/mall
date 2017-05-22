package com.mall.view;

import com.mall.entity.Promotion;

public class PromotionView {
	private Promotion promotion;// 促销活动数据

	public PromotionView(Promotion promotion) {
		this.promotion = promotion;
	}

	public Promotion getPromotion() {
		return promotion;
	}

}
