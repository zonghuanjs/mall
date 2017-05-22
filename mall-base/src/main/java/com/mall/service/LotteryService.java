package com.mall.service;

import com.mall.entity.Lottery;
import com.mall.entity.Member;
import com.mall.entity.Order;

public interface LotteryService extends BaseService<Long, Lottery> {
	/**
	 * 创建抽奖订单
	 * 
	 * @param lottery
	 *            抽奖活动s
	 * @param number
	 *            抽奖份数
	 * @param member
	 *            会员对象
	 * @param addr
	 *            收货地址ID
	 * @return
	 */
	Order createLotteryOrder(Lottery lottery, int number, Member member, Long addr);

	/**
	 * 检测抽奖活动有效性
	 * 
	 * @param lottery
	 *            抽奖活动
	 * @param number
	 *            购买份数
	 * @return errCode 0: 检测通过; 3: 未开始; 4：已售完; 5：已结束
	 */
	int checkLottery(Lottery lottery, int number);

	/**
	 * 求抽奖活动参与人数
	 * 
	 * @param lottery
	 *            抽奖活动
	 * @return 返回参与抽奖活动的人数
	 */
	int buyNumber(Lottery lottery);

	/**
	 * 抽奖活动结束:标记抽奖活动为已开奖状态, 并将未中奖订单标记为交易完成状态
	 * 
	 * @param lottery
	 *            抽奖活动
	 */
	void endPrized(Lottery lottery);
}
