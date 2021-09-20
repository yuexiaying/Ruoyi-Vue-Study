package com.study.system.service.impl;

import com.study.common.constant.Constants;
import com.study.common.core.redis.RedisCache;
import com.study.common.core.text.Convert;
import com.study.common.utils.StringUtils;
import com.study.system.domain.SysConfig;
import com.study.system.mapper.SysConfigMapper;
import com.study.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fjding
 * @date 2021/9/20
 */
@Service
public class ISysConfigServiceImpl implements ISysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 根据键名查询参数配置
     *
     * @param configKey
     * @return
     */
    @Override
    public String selectConfigByKey(String configKey) {
        // 从缓存中查询配置
        String cacheValue = Convert.toStr(redisCache.getCacheObject(getCacheKey(configKey)));
        if (StringUtils.isNotEmpty(cacheValue)) {
            return cacheValue;
        }
        // 如果为空，去数据库中查找
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigKey(configKey);
        SysConfig retConfig = sysConfigMapper.selectConfig(sysConfig);
        // 如果数据库中可以查到数据，存入缓存并返回
        if (StringUtils.isNotEmpty(retConfig)){
            redisCache.setCacheObject(getCacheKey(configKey),retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StringUtils.NULL_STR;
    }

    /**
     * 获取验证码开关
     *
     * @return true开启，false关闭
     */
    @Override
    public boolean selectCaptchaOnOff() {
        // 查询验证码开关的配置
        String captchaOnOff = selectConfigByKey(CAPTCHA_KEY);
        if (StringUtils.isBlank(captchaOnOff)) {
            return true;
        }
        return Convert.toBool(captchaOnOff, true);
    }

    /**
     * 获得存在缓存中的key
     *
     * @param configKey 配置key
     * @return
     */
    private String getCacheKey(String configKey) {
        return Constants.SYS_CONFIG_KEY + configKey;
    }
}
