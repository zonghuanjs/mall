package com.mall.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.mall.log.*;

/**
 * 日志注解
 * @author zonghuan
 *
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogMethod
{
	//操作类型
	OperationType type();
	//操作描述
	String message() default "";
	//日志记录的参数名称
	String[] parameter() default {};
}
