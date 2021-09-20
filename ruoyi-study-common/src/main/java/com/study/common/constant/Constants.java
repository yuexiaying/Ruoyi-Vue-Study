package com.study.common.constant;

/**
 * 通用常量
 *
 * @author fjding
 * @date 2021/9/5
 */
public class Constants {

    /**
     * http请求
     */
    public static final String HTTP = "http://";
    /**
     * https请求
     */
    public static final String HTTPS = "https://";


    /**
     * 字符串验证码
     */
    public static final String CAPTCHA_CHAR = "char";
    /**
     * 数学计算验证码
     */
    public static final String CAPTCHA_MATH = "math";


    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";
    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";


    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";
    /**
     * 验证码有效期（秒）
     */
    public static final Integer CAPTCHA_EXPIRATION = 120;

}
