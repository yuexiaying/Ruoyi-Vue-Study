package com.study.web.controller.common;

import com.google.code.kaptcha.Producer;
import com.study.common.constant.Constants;
import com.study.common.core.domain.AjaxResult;
import com.study.common.core.redis.RedisCache;
import com.study.common.utils.sign.Base64;
import com.study.common.utils.uuid.IdUtils;
import com.study.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码处理类
 *
 * @author fjding
 * @date 2021/9/19
 */
@RestController
public class CaptchaController {

    /**
     * 字符串验证码生成方式
     */
    @Resource(name = "captchaProducerChar")
    private Producer captchaProducerChar;

    /**
     * 数学计算验证码生成方式
     */
    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /**
     * 验证码类型
     */
    @Value("${ruoyi.study.captchaType}")
    private String captchaType;

    /**
     * 参数配置服务
     */
    @Autowired
    private ISysConfigService sysConfigService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     *
     * @return
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode() {
        AjaxResult result = AjaxResult.success();
        boolean captchaOnOff = sysConfigService.selectCaptchaOnOff();
        // 如果未开启验证码服务，不生成相关信息
        if (!captchaOnOff){
            return result;
        }
        String uuid = IdUtils.simpleUUID();
        // 生产验证码校验key
        String verifyKey = Constants.CAPTCHA_CODE_KEY+uuid;
        // 验证码文本
        String capStr = null;
        // 验证码结果
        String code = null;
        // 验证码图片
        BufferedImage image = null;

        if (Constants.CAPTCHA_MATH.equals(captchaType)){
            String capText =captchaProducerMath.createText();
            capStr = capText.substring(0,capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@")+1);
            image = captchaProducerMath.createImage(capStr);
        }else if (Constants.CAPTCHA_CHAR.equals(captchaType)){
            capStr = code = captchaProducerChar.createText();
            image = captchaProducerChar.createImage(capStr);
        }
        // 结果存入缓存中
        redisCache.setCacheObject(verifyKey,code,Constants.CAPTCHA_EXPIRATION, TimeUnit.SECONDS);
        // 字节输出流（代替ByteArrayOutputStream）
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image,"jpg",os);
        }catch (IOException e){
            return AjaxResult.error("获得验证码失败");
        }
        result.put("uuid",uuid);
        // 使用自定义的Base64工具，使用java自带的不可以
        result.put("img", Base64.encode(os.toByteArray()));
        return result;
    }

    private String img() {
        return "";
    }
}
