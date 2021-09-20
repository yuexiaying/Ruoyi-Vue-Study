package com.study.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义导出Excel数据注解
 *
 * @author fjding
 * @date 2021/9/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

    /**
     * 导出到Excel中的名字
     */
    String name() default "";

    /**
     * 导出类型（0数字 1字符串）
     */
    ColumnType cellType() default ColumnType.STRING;

    /**
     * 内容转换表达式(如: 0=男,1=女,2=未知)
     */
    String readConverterExp() default "";


    /**
     * 字段类型
     */
    enum ColumnType {
        /**
         * 数字
         */
        NUMERIC(0),
        /**
         * 字符串
         */
        STRING(1),
        /**
         * 图片
         */
        IMAGE(2);

        private final int value;

        ColumnType(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}
