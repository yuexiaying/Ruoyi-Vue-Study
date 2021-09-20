package com.study.common.utils.uuid;

import java.util.UUID;

/**
 * Id工具类
 *
 * @author fjding
 * @date 2021/9/11
 */
public class IdUtils {

    /**
     * 获取随机UUID
     * 16f3b6ca-f12d-4417-a7ed-cdccc3e8f005
     *
     * @return
     */
    public static String randUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     * 045fdc6b5bfa435c9403ccd02b3be1ff
     *
     * @return
     */
    public static String simpleUUID() {
        return randUUID().replace("-", "");
    }

}
