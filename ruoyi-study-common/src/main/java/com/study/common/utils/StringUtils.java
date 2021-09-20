package com.study.common.utils;

import com.study.common.constant.Constants;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.EMPTY;


/**
 * 字符串工具类（不仅仅是）
 *
 * @author fjding
 * @date 2021/9/4
 */
public class StringUtils {


    /**
     * 空串
     */
    public static final String NULL_STR = EMPTY;

    /**
     * 下划线
     */
    public static final char SEPARATOR = '_';

    /**
     * 返回非空值
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static <T> T nvl(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    /**
     * 判断一个对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        return ObjectUtils.isEmpty(obj);
    }

    /**
     * 判断一个对象是否非空
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 判断一个字符串是否为空串
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }

    /**
     * 去空格
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }


    /**
     * 截取字符串，支持负数
     *
     * @param str
     * @param start
     * @return
     */
    public static String subString(final String str, int start) {
        if (str == null) {
            return NULL_STR;
        }

        // 几个判断是精髓
        if (start < 0) {
            start = str.length() + start;
        }
        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return NULL_STR;
        }
        return str.substring(start);
    }

    /**
     * 截取字符串，支持负数
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String subString(final String str, int start, int end) {
        if (str == null) {
            return NULL_STR;
        }
        if (start < 0) {
            start = start + str.length();
        }
        if (end < 0) {
            end = end + str.length();
        }
        if (start > end) {
            return NULL_STR;
        }
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        return str.substring(start, end);
    }

    /**
     * todo 格式化文本
     *
     * @param template
     * @param params
     * @return
     */
    public static String format(String template, Object... params) {
        return null;
    }

    /**
     * 是否为http(s)://开头
     *
     * @param link
     * @return
     */
    public static boolean isHttp(String link) {
        return org.apache.commons.lang3.StringUtils.startsWithAny(link, Constants.HTTP, Constants.HTTPS);
    }

    /**
     * 字符串转List
     *
     * @param str         字符串
     * @param sep         分隔符
     * @param filterBlank 过滤纯空白
     * @param trim        去掉首尾空白
     * @return
     */
    public static List<String> strToList(String str, String sep, boolean filterBlank, boolean trim) {
        List<String> list = new ArrayList<>();
        if (isEmpty(str)) {
            return list;
        }
        // 过滤空白
        if (filterBlank && isBlank(str)) {
            return list;
        }
        String[] split = str.split(sep);
        for (String s : split) {
            if (filterBlank && isBlank(s)) {
                continue;
            }
            if (trim) {
                s = s.trim();
            }
            list.add(s);
        }
        return list;
    }

    /**
     * 字符串转Set
     *
     * @param str 字符串
     * @param sep 分隔符
     * @return
     */
    public static Set<String> strToSet(String str, String sep) {
        return new HashSet<>(strToList(str, sep, true, true));
    }

    /**
     * 指定字符串是否包含指定字符串列表忽略大小写任意字符
     *
     * @param cs
     * @param searchCs
     * @return
     */
    public static boolean containsAnyIgnoreCase(CharSequence cs, CharSequence... searchCs) {
        if (isEmpty(cs) || isEmpty(searchCs)) {
            return false;
        }
        for (CharSequence searchC : searchCs) {
            if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(cs, searchC)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 驼峰转下划线命名
     *
     * @param str
     * @return
     */
    public static String toUnderScoreCase(String str) {
        if (isBlank(str)) {
            return str;
        }
        // 新字符串构造器
        StringBuilder sb = new StringBuilder();
        // 前置字符是否大写
        boolean preCharIsUpperCase = true;
        // 当前字符是否大写
        boolean currentCharIsUpperCase = true;
        // 后置字符是否大写
        boolean nextCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                // 0的前置为false
                preCharIsUpperCase = false;
            }
            // 当前字符
            currentCharIsUpperCase = Character.isUpperCase(c);
            //
            if (i < (str.length() - 1)) {
                nextCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }
            // todo 几个判断不是很明白，不仅仅是大写的前面插入_
            if (preCharIsUpperCase && currentCharIsUpperCase && !nextCharIsUpperCase) {
                sb.append(SEPARATOR);
            } else if (i != 0 && !preCharIsUpperCase && currentCharIsUpperCase) {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    public static String toCamelCase(String str) {
        if (isBlank(str)) {
            return str;
        }
        // 这里容量大了，包含了_的数量
        StringBuilder sb = new StringBuilder(str.length());
        str = str.toLowerCase();
        boolean upperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(c);
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 字符串匹配
     * ? 单个字符
     * * 任意字符
     *
     * @param pattern 字符类型
     * @param strings 字符串列表
     * @return
     */
    public static boolean matches(String pattern, List<String> strings) {
        if (isEmpty(pattern) || isEmpty(strings)) {
            return false;
        }
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String string : strings) {
            if (antPathMatcher.match(pattern, string)) {
                return true;
            }
        }
        return false;
    }


}
