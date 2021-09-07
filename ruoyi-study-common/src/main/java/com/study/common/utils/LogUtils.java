package com.study.common.utils;

/**
 * 处理日志文件
 *
 * @author fjding
 * @date 2021/9/4
 */
public class LogUtils {
    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
