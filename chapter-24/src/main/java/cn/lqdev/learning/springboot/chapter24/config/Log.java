package cn.lqdev.learning.springboot.chapter24.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * 日志注解类
 * @author oKong
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})//只能在方法上使用此注解
public @interface Log {
	/**
	 * 日志描述，这里使用了@AliasFor 别名。spring提供的
	 * @return
	 */
	@AliasFor("desc")
	String value() default "";
	
	/**
	 * 日志描述
	 * @return
	 */
	@AliasFor("value")
	String desc() default "";
	
	/**
	 * 是否不记录日志
	 * @return
	 */
	boolean ignore() default false;
}
