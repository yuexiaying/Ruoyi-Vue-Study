package com.study.common.core.text;

/**
 * 类型转换器
 *
 * @author fjding
 * @date 2021/9/19
 */
public final class Convert {

    private Convert() {
    }

    /**
     * 对象转Str，默认为null
     *
     * @param obj
     * @return
     */
    public static String toStr(Object obj) {
        return toStr(obj, null);
    }

    /**
     * 对象转Str
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String toStr(Object obj, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return obj.toString();
    }

    /**
     * 转换为boolean类型
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static boolean toBool(Object value, boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Boolean) {
            return (boolean) value;
        }
        if (value instanceof String) {
            String str = (String) value;
            switch (str) {
                case "true":
                case "yes":
                case "ok":
                case "1":
                    return true;
                case "false":
                case "no":
                case "0":
                    return false;
            }
        }
        return defaultValue;
    }

    /**
     * 转换为boolean类型,默认false
     *
     * @param value
     * @return
     */
    public static boolean toBool(Object value) {
        return toBool(value, false);
    }

}
