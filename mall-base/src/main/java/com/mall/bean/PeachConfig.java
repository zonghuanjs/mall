package com.mall.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 砸蟠桃活动系统配置
 * @author huan.zong
 *
 */
public class PeachConfig
{
	/**
	 * 奖品数据表：商品ID, 数量
	 */
	public static final int[][] rewards = {
		{0, 0},//无奖
		{108, 1},//1:PER820 1个  特等奖
		{290, 20},//2:U盘 20个           一等奖
		{291, 100},//3:台历   100个         二等奖
		{155, 8},//4:PER310  16GB   三等奖
		{156, 8},//5:PER310  32GB   三等奖
		{157, 8},//6:PER310  64GB   三等奖
		{150, 16},//7:TEK UM300 2GB 内存条 69元     三等奖
		{193, 16},//8:TEK SM300 2GB 内存条 69元     三等奖
		{277, 160},//9:2015纪念版 4GB笔记本内存条 99元             三等奖
		{278, 160},//10:2015纪念版 4GB台式机内存条 99元          三等奖
		{152, 16},//11:UM600 8GB 内存条 199元          三等奖
		{236, 16},//12:SM600 8GB 内存条 199元         三等奖
		{178, 8},//13:UM800 DDR4 8GB 内存条 399元   三等奖
		{108, 8},//14:PER820 128GB 1元1GB   三等奖		
	};
	public static Map<Integer ,String> awards ()
	{
		Map<Integer,String> map = new HashMap<>();
		map.put(1, "特等奖");
		map.put(2, "一等奖");
		map.put(3, "二等奖");
		for(int i=4; i < 15; i++)
		{
			map.put(i, "三等奖");
		}
		return map;		
	}
	/**
	 * 奖品活动价格表
	 */
	public static double[] prices = {
		0.0,//无奖
		0.0,//1:PER820  特等奖
		0.0,//2:U盘            一等奖
		0.0,//3:台历           二等奖
		16.0,//4:PER310  16GB   三等奖
		32.0,//5:PER310  32GB   三等奖
		64.0,//6:PER310  64GB   三等奖
		69.0,//7:TEK UM300 2GB 内存条 69元     三等奖
		69.0,//8:TEK SM300 2GB 内存条 69元     三等奖
		99.0,//9:2015纪念版 4GB笔记本内存条 99元             三等奖
		99.0,//10:2015纪念版 4GB台式机内存条 99元          三等奖
		199.0,//11:UM600 8GB 内存条 199元          三等奖
		199.0,//12:SM600 8GB 内存条 199元         三等奖
		399.0,//13:UM800 DDR4 8GB 内存条 399元   三等奖
		128.0,//14:PER820 128GB 1元1GB   三等奖
	};
	
	/**
	 * 奖品表
	 */
	public static final int[] peachRewards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
	
	/**
	 * 优惠券表:49减5元  99减10元 49减10元 99减20元 199减30元 399减50元 49减15 99减30 199减50 399减80 499减100 5元代金券 10元代金券 15元代金券 20元代金券
	 */
	public static final int[] peachCoupons = {33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47};
	
	
	/**
	 * 订单标记KEY
	 */
	public static final String prefix ="PEACH_";
	
	/**
	 * 中奖概率
	 */
	public static final int percent = 100;//百分比
	/**
	 * 奖品与优惠券的中奖几率(按100算)
	 */
	public static final int[] random = {10 , 90}; 
	/**
	 * 会员抽奖次数限制
	 */
	public static final int dayLimitMember = 3;
}
