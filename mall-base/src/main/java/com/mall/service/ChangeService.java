package com.mall.service;

import com.mall.entity.Member;
import com.mall.entity.Product;

public interface ChangeService
{
	/**
	 * 检测商品库存
	 * @param product  商品对象
	 * @param quantity 购买数量
	 * @return true,可以购买; false, 数量不购
	 */
	boolean checkStock(Product product, int quantity);
	
	/**
	 * 锁定商品数量
	 * @param product	商品对象
	 * @param quantity  购买数量
	 * @return true,锁定成功; false, 锁定失败
	 */
	boolean lockStock(Product product, int quantity);
	
	/**
	 * 恢复库存
	 * @param product	商品对象
	 * @param quantity	恢复数量
	 * @return true,恢复成功; false, 恢复失败
	 */
	boolean recoveryStock(Product product, int quantity);
	
	/**
	 * 操作商品库存
	 * @param product	商品对象
	 * @param quantity	操作数量
	 * @param ot		操作类型
	 * @return	true, 操作成功; false, 操作失败
	 */
	boolean doStockOperation(Product product, int quantity, ChangeType ot);
	
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
