package com.study.common.utils.bean;

import org.springframework.beans.BeansException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bean 工具类
 *
 * @author fjding
 * @date 2021/9/4
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * 匹配getter方法的正则表达式
     * todo \\p{javaUpperCase}暂时没理解其作用
     */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    /**
     * 匹配setter方法的正则表达式
     */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * Bean方法名中属性名开始的下标（去除get/set）
     */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /**
     * Bean属性复制
     * <p>
     * 深拷贝，要求属性的类型名字和类型必须一致，参考测试类
     *
     * @param dest 目标对象
     * @param src  源对象
     */
    public static void copyBeanProp(Object dest, Object src) {
        try {
            copyProperties(src, dest);
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得对象的getter方法
     *
     * @param obj 对象
     * @return 对象getter方法列表
     */
    public static List<Method> getGetterMethods(Object obj) {
        List<Method> getterMethods = new ArrayList<>();
        // 获得对象的所有方法
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            Matcher matcher = GET_PATTERN.matcher(method.getName());
            if (matcher.matches()
                    // 方法的参数数组长度是否为0
                    && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        return getterMethods;
    }

    /**
     * 获得对象的setter方法
     *
     * @param obj 对象
     * @return 对象setter方法列表
     */
    public static List<Method> getSetterMethods(Object obj) {
        List<Method> setterMethods = new ArrayList<>();
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            Matcher matcher = SET_PATTERN.matcher(method.getName());
            if (matcher.matches() && method.getParameterTypes().length == 1) {
                setterMethods.add(method);
            }
        }
        return setterMethods;
    }

    /**
     * 检查Bean方法名中的属性是否相等
     *
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */
    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }

}
