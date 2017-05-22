package com.mall.exception;

/**
 * 订单异常类
 * @author ChenMingcai
 * 2016-03-09
 *
 */

public class OrderException extends Exception
{
	private static final long serialVersionUID = -4281725497024747016L;
	
	public OrderException(String message)
	{
		super(message);
	}
}
