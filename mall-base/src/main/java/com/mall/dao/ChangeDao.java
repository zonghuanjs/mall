package com.mall.dao;

import com.mall.entity.Member;
import com.mall.entity.Product;
import com.mall.service.ChangeType;

public interface ChangeDao
{
	/**
	 * 操作商品库存
	 * @param product	商品对象
	 * @param quantity	操作数量
	 * @param ot		操作类型
	 * @return	true, 操作成功; false, 操作失败
	 */
	boolean changeStock(Product product, int quantity, ChangeType ot);
	
	/**
	 * 操作会员积分
	 * @param member	会员对象
	 * @param quantity	操作数量
	 * @param ct		操作类型
	 * @return	true, 操作成功; false, 操作失败
	 */
	boolean changeMemberPoint(Member member, int quantity, ChangeType ct);
	
	/**
	 * 操作会员累计消费金额
	 * @param member	会员对象
	 * @param quantity	操作数量
	 * @param ct		操作类型
	 * @return	true, 操作成功; false, 操作失败
	 */
	boolean changeMemberAmount(Member member, double quantity, ChangeType ct);
	
	/**
	 * 操作商品销量值
	 * @param product	商品对象
	 * @param quantity	操作数量
	 * @param ct		操作类型
	 * @return	true, 操作成功; false, 操作失败
	 */
	boolean changeSales(Product product, int quantity, ChangeType ct);
}
