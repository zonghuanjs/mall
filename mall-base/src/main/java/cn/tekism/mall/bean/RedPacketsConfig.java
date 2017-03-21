package cn.tekism.mall.bean;

public class RedPacketsConfig
{

	/**
	 * 优惠券表: 5元:26, 10元:27, 20元:28, 30元:29, 50元:30, 100元:31
	 */
	public static final Long[] coupons = {26L, 27L, 28L, 29L, 30L, 31L};

	/**
	 * 订单标记KEY
	 */
	public static final String[] prefix = {
		"RED_Packets_"
	};
	
	/**
	 * 中奖概率5元 25%, 10元 25%, 20元 20%, 30元 16%, 50元 13%, 100元 1%
	 */                   
	public static final int[] percent = {259, 250, 200, 160, 130, 1};//百分比
	
	/**
	 * 会员抽奖次数限制
	 */
	public static final int dayLimitMember = 5;
}
