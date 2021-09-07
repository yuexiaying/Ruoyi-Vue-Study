package com.study.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 精确的浮点运算
 *
 * @author fjding
 * @date 2021/9/4
 */
public class Arith {

    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 2;

    /**
     * 禁止实例化
     */
    private Arith() {
    }

    /**
     * 精确加法运算
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double add(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 精确减法运算
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 精确乘法运算
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double mul(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 相对精确的除法运算，保留2位
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double div(double d1, double d2) {
        return div(d1, d2, DEF_DIV_SCALE);
    }

    /**
     * 相对精确的除法运算
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double div(double d1, double d2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("scale不能是负数");
        }

        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        if (b2.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO.doubleValue();
        }
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供精确的四舍五入，默认2位
     *
     * @param v 需要四舍五入的数字
     * @return
     */
    public static double round(double v) {
        return round(v, DEF_DIV_SCALE);
    }

    /**
     * 提供精确的四舍五入
     *
     * @param v
     * @param scale 小数点后保留几位
     * @return
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("scale不能是负数");
        }
        BigDecimal b = new BigDecimal(v);
        return b.divide(BigDecimal.ONE, scale, RoundingMode.HALF_UP).doubleValue();
    }

}
