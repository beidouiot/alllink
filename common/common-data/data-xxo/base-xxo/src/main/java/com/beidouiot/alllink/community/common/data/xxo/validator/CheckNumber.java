package com.beidouiot.alllink.community.common.data.xxo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 *
 * @Description 数字校验
 * @author longww
 * @date 2021年4月11日
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckNumberValidator.class)
public @interface CheckNumber {
	
    /**
     * @return size the element must be higher or equal to
     */
    long min() default 0;
 
    /**
     * @return size the element must be lower or equal to
     */
    long max() default Long.MAX_VALUE;
 
    String message() default "当前字符串必须为数字类型";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
}
