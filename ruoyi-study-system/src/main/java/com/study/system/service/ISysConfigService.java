package com.study.system.service;

/**
 * 参数配置 服务层
 *
 * @author fjding
 * @date 2021/9/19
 */
public interface ISysConfigService {

    /**
     * 验证码key
     */
    String CAPTCHA_KEY = "sys.account.captchaOnOff";

    /**
     * 用户注册key
     */
    String REGISTER_USER_KEY = "sys.account.captchaOnOff";

    /**
     * 侧边栏主题key
     */
    String SIDE_THEME_KEY = "sys.index.sideTheme";

    /**
     * 初始密码key
     */
    String INIT_PASSWORD_KEY = "sys.user.initPassword";

    /**
     * 默认皮肤key
     */
    String SKIN_NAME_KEY = "sys.index.skinName";

    /**
     * 根据键名查询参数配置
     *
     * @param configKey
     * @return
     */
    String selectConfigByKey(String configKey);

    /**
     * 获取验证码开关
     *
     * @return
     */
    boolean selectCaptchaOnOff();
}
