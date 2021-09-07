package com.study.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常信息处理工具类
 *
 * @author fjding
 * @date 2021/9/4
 */
public class ExceptionUtils {

    public static String getExceptionMsg(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        // todo 不明白这里的自动刷新是什么意思
        e.printStackTrace(new PrintWriter(stringWriter, true));
        String str = stringWriter.toString();
        return str;
    }

    /**
     * 获得根错误信息
     *
     * @param e
     * @return
     */
    public static String getRootErrorMessage(Exception e) {
        Throwable root = org.apache.commons.lang3.exception.ExceptionUtils.getRootCause(e);
        if (root == null) {
            root = e;
        }
        String msg = root.getMessage();
        if (msg == null) {
            return "null";
        }
        return msg;
    }

}
