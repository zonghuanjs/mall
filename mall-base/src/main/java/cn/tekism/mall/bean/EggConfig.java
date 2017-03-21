package cn.tekism.mall.bean;

/**
 * 砸金蛋活动系统配置
 * @author ChenMingcai
 * 2015-10-15
 *
 */

public class EggConfig
{
	/**
	 * 奖品数据表：商品ID, S, P, C
	 */
	public static final int[][] rewards = {
		{0, 0, 0, 0},//无奖
		{155, 0, 0, 0},//1:16GB U盘 16元:155
		{156, 0, 0, 0},//2:32GB U盘32元:156
		{157, 0, 0, 0},//3:64GB U盘64元:157
		{150, 0, 0, 0},//4:TEK UM300 2GB 69元:150
		{193, 0, 0, 0},//5:TEK SM300 2GB 69元:193
		{205, 0, 0, 0},//6:TEK U3 4GB 99元:205
		{203, 0, 0, 0},//7:TEK S3 4GB 99元:203
		{152, 0, 0, 0},//8:UM600 8GB 199元:152
		{236, 0, 0, 0},//9:SM600 8GB 199元:236
		{178, 0, 0, 0},//10:UM800 8GB 399元:178
		{107, 0, 0, 0},//11:PER820 60GB 60元:107
		{206, 0, 0, 0},//12:PER860 128GB 128元:206
		{127, 0, 0, 0},//13:PER810 256GB 256元:127
		{143, 0, 0, 0},//14:PER840 512GB 512元:143
		{144, 0, 0, 0}//15:PER840 1TB 2999元:144
	};
	
	/**
	 * 奖品活动价格表
	 */
	public static double[] prices = {
		0.0,//无奖
		16.0,//1:16GB U盘
		32.0,//2:32GB U盘
		64.0,//3:64GB U盘
		69.0,//4:UM300 2GB
		69.0,//5:SM300 2GB
		99.0,//6:U3 4GB
		99.0,//7:S3 4GB
		199.0,//8:UM600 8GB
		199.0,//9:SM600 8GB
		399.0,//10:UM800 8GB
		60.0,//11:PER820 60GB
		128.0,//12:PER860 128GB
		256.0,//13:PER810 256GB
		512.0,//14:PER840 512GB
		2999.0//15:PER840 1TB
	};
	
	/**
	 * 简单模式奖品表
	 */
	public static final int[] simple = {0, 1, 2, 4, 5, 6, 7, 11};
	
	/**
	 * 简单模式优惠券表: 20减2, 50减5, 100减10, 5元
	 */
	public static final int[] simpleCoupons = {11, 12, 13, 14};
	
	/**
	 * 专家模式奖品表
	 */
	public static final int[] professial = {0, 11, 12, 6, 7, 3, 8, 9, 13}; 
	
	/**
	 * 专家模式优惠券表: 50减8, 100减15, 200减30, 400减50, 10, 5
	 */
	public static final int[] professialCoupons = {15, 16, 17, 18, 19};
	
	/**
	 * 疯狂模式奖品表
	 */
	public static final int[] crazy = {0, 11, 12, 6, 7, 3, 8, 9, 10, 14, 15};
	
	/**
	 * 疯狂模式优惠券表: 50减15, 100减30, 200减50, 400减75, 500减100, 15, 10, 5
	 */
	public static final int[] crazyCoupons = {20, 21, 22, 23, 24, 25};
	
	/**
	 * 订单标记KEY
	 */
	public static final String[] prefix = {
		"EGG_S_", "EGG_P_", "EGG_C_"
	};
	
	/**
	 * 中奖概率
	 */
	public static final int percent = 10;//百分比
	
	/**
	 * 会员抽奖次数限制
	 */
	public static final int dayLimitMember = 6;
}
