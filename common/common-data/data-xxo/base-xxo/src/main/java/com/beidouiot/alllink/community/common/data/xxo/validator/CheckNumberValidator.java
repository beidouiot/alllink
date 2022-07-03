package com.beidouiot.alllink.community.common.data.xxo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *
 * @Description 数字校验
 * @author longww
 * @date 2021年4月11日
 */
public class CheckNumberValidator implements ConstraintValidator<CheckNumber, Object> {
	private CheckNumber checkNumber;
	 
    @Override
    public void initialize(CheckNumber constraintAnnotation) {
        this.checkNumber = constraintAnnotation;
    }
 
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null || StringUtils.isEmpty(value.toString())) {
            //当参数值为null或者length = 0时，不做校验
            return true;
        }
        if (!StringUtils.isNumeric(value.toString())) {
            return false;
        }
        try {
            long max = checkNumber.max();
            long min = checkNumber.min();
            long curValue = Long.parseLong(value.toString());
            if (curValue < min || curValue > max) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
