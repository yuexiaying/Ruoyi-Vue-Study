package com.study.common.core.domain;

import com.study.common.constant.HttpStatus;
import com.study.common.utils.StringUtils;

import java.util.HashMap;

/**
 * 与前端交互的对象
 *
 * @author fjding
 * @date 2021/9/11
 */
public class AjaxResult extends HashMap<String, Object> {

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 信息
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    private AjaxResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    private AjaxResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotEmpty(data)) {
            super.put(DATA_TAG, data);
        }
    }

    public static AjaxResult success() {
        return success("成功");
    }

    public static AjaxResult success(String msg) {
        return success(msg, null);
    }

    public static AjaxResult success(Object data) {
        return success("成功", data);
    }

    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

    public static AjaxResult error() {
        return error("失败");
    }

    public static AjaxResult error(String msg) {
        return error(msg, null);
    }

    public static AjaxResult error(Object data) {
        return error("失败", data);
    }

    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }

}
