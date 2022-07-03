package com.beidouiot.alllink.community.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DoubleUtil {
	
	/**
	 * 转换字符串
	 * @param d
	 * @param f
	 * @return
	 */
	public static String convertDoubleFormat(Double d, String f) {
		if (null == d) {
			d = 0D;
		}
		DecimalFormat df = new DecimalFormat(f);
		String s = df.format(d);
		return s;
	}
	
	/**
	 * 检测obj是否是大于0
	 * @param obj
	 * @return true 是大于 0 false 小于0
	 */
	public static boolean isNotBlank(Double obj){
		if(null==obj||obj.doubleValue()<=0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 检测obj是否是小于或等于0
	 * @param obj
	 * @return true 小于或等于 0 false 不小于或等于0
	 */
	public static boolean isBlank(Double obj){
		if(null==obj||obj.doubleValue()<=0){
			return true;
		}else{
			return false;
		}
	}
	
	public static String isBlankToMsg(Double obj,String msg){
		String returnstr="";
		if (DoubleUtil.isBlank(obj)) {
			returnstr=msg+",";
		}
		return returnstr;
	}
	
	/**
	 * 检测obj是否不为空或者不为0
	 * @param obj
	 * @return true 是  false 不是
	 */
	public static boolean isNotEmpty(Double obj){
		if(null==obj||obj.doubleValue()==0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 检测obj是否为空或者为0
	 * @param obj
	 * @returntrue 是  false 不是
	 */
	public static boolean isEmpty(Double obj){
		if(null==obj||obj.doubleValue()==0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 比较两个数字是否相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equals(Double a, Double b){
		boolean flag=false;
		if(null==a){
			a=0D;
		}
		if(null==b){
			b=0D;
		}
		if(a.equals(b)){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 比较两数大小（a是否大于b）
	 * @param a
	 * @param b
	 * @return true 大于 false 小于
	 */
	public static boolean expMore(Double a, Double b) {
		boolean is = false;
		if (null == b ) {
			b= 0D;
		}
		if (null == a ) {
			a= 0D;
		}
		if(a.doubleValue()>b){
			is = true;
		}
		return is;
	}
	
	/**
	 * 比较两数大小（a是否大于等于b）
	 * @param a
	 * @param b
	 * @return true 大于 false 小于
	 */
	public static boolean expGe(Double a, Double b) {
		boolean is = false;
		if (null == b ) {
			b= 0D;
		}
		if (null == a ) {
			a= 0D;
		}
		if(a.doubleValue()>=b){
			is = true;
		}
		return is;
	}

    /**
     * 浮点数精确计算 a+b
     * @param a
     * @param b
     * @return
     */
    public static double preciseAdd(Double a, Double b) {
        if (null == a) {
            a=0D;
        }
        if (null == b) {
            b=0D;
        }
        BigDecimal r = new BigDecimal(Double.toString(a));
        r = r.add(new BigDecimal(Double.toString(b)));
        return r.doubleValue();
    }

	/**
	 * 浮点数 加 小数位四舍五入精确计算 a+b
	 * @param a
	 * @param b
	 * @param scale 精确的小数位
	 * @return
	 */
	public static double preciseAdd(Double a, Double b, int scale) {
		if (null == b ) {
			b= 0D;
		}
		if (null == a ) {
			a= 0D;
		}
		BigDecimal r = new BigDecimal(Double.toString(a));
		r = r.add(new BigDecimal(Double.toString(b)));
		return DoubleUtil.round(r.doubleValue(), scale);
	}

    /**
     * 浮点数精确计算 a-b
     * @param a
     * @param b
     * @return
     */
    public static double preciseSub(Double a, Double b) {
    	if (null == a) {
            a=0D;
        }
        if (null == b) {
            b=0D;
        }
        BigDecimal r = new BigDecimal(Double.toString(a));
        r = r.subtract(new BigDecimal(Double.toString(b)));
        return r.doubleValue();
    }

	/**
	 * 浮点数 减 小数位四舍五入精确计算 a-b
	 * @param a
	 * @param b
	 * @param scale 精确的小数位
	 * @return
	 */
	public static double preciseSub(Double a, Double b, int scale) {
		if (null == b ) {
			b= 0D;
		}
		if (null == a ) {
			a= 0D;
		}
		BigDecimal r = new BigDecimal(Double.toString(a));
		r = r.subtract(new BigDecimal(Double.toString(b)));
		return DoubleUtil.round(r.doubleValue(), scale);
	}

    /**
     * 浮点数精确计算 a*b
     * @param a
     * @param b
     * @return
     */
    public static double preciseMul(Double a, Double b) {
        if (null == b || null == a) {
            return 0D;
        }
        BigDecimal r = new BigDecimal(Double.toString(a));
        r = r.multiply(new BigDecimal(Double.toString(b)));
        
        return r.doubleValue();
    }

	/**
	 * 浮点数 乘 小数位四舍五入精确计算 a*b
	 * @param a
	 * @param b
	 * @param scale 精确的小数位
	 * @return
	 */
	public static double preciseMul(Double a, Double b, int scale) {
		if (null == b || null == a) {
			return 0D;
		}
		return DoubleUtil.round(new BigDecimal(Double.toString(a)).multiply(
				new BigDecimal(Double.toString(b))).doubleValue(), scale);
	}

    /**
     * 浮点数精确计算 a/b
     * 
     * @param a
     * @param b
     * @return
     */
    public static double preciseDev(Double a, Double b) {
        if (null == b || b == 0D || null == a) {
            return 0D;
        }
        BigDecimal r = new BigDecimal(Double.toString(a));
        r = r.divide(new BigDecimal(Double.toString(b)), 6, BigDecimal.ROUND_HALF_UP);
        return r.doubleValue();
    }

    /**
     * 浮点数精确计算 a/b
     * 
     * @param a
     * @param b
     * @param scale
     *            精确的小数位
     * @return
     */
    public static double preciseDev(Double a, Double b, int scale) {
        if (null == b || b == 0D || null == a) {
            return 0D;
        }
        BigDecimal r = new BigDecimal(Double.toString(a));
        r = r.divide(new BigDecimal(Double.toString(b)), scale, BigDecimal.ROUND_HALF_UP);
        return r.doubleValue();
    }

    /**
     * a(X)b
     * @param a
     * @param b
     * @param t
     * @return
     * @throws IllegalArgumentException
     */
    public static Double precise(Double a, Double b, String t) throws IllegalArgumentException {
        if (null == a)
            a = 0D;
        if (null == b)
            b = 0D;
        switch (t.charAt(0)) {
            case '+':
                return preciseAdd(a, b);
            case '-':
                return preciseSub(a, b);
            case '*':
                return preciseMul(a, b);
            case '/':
                return preciseDev(a, b);
            default:
                return null;
        }
    }
    /**
     * 算术运行
     * @param a
     * @param b
     * @param t -- 运算符，如：+、-、*、/
     * @param scale -- round小数位
     * @return
     * @throws IllegalArgumentException
     * <br>-----------------------------------------------------<br>
     * @author	 flotage
     * @create 	 2016年7月8日 上午10:03:54  
     * @note
     */
    public static Double precise(Double a, Double b, String t,int scale) throws IllegalArgumentException {
        switch (t.charAt(0)) {
            case '+':
                return preciseAdd(a, b);
            case '-':
                return preciseSub(a, b);
            case '*':
                Double d = preciseMul(a, b);
                return round(d,scale);
            case '/':
                return preciseDev(a, b,scale);
            default:
                return null;
        }
    }
    /**
     * 提供精确的小数位四舍五入处理。

     * 
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
