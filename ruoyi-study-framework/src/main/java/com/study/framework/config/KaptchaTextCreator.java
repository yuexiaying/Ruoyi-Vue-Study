package com.study.framework.config;

import com.google.code.kaptcha.text.impl.DefaultTextCreator;

import java.util.Random;

/**
 * 自定义验证码文本生成器
 *
 * @author fjding
 * @date 2021/9/20
 */
public class KaptchaTextCreator extends DefaultTextCreator {

    /**
     * 数字对应的字符串
     */
    private static String[] NUMBER_STRS = "0,1,2,3,4,5,6,7,8,9".split(",");

    @Override
    public String getText() {
        Random random = new Random();
        // 计算方式
        int calculation = random.nextInt(4);
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        // 最终计算结果
        int result = 0;
        StringBuilder sb = new StringBuilder();
        // + - * / 计算
        if (calculation == 0){
            result = x + y;
            sb.append(NUMBER_STRS[x]).append(" + ").append(NUMBER_STRS[y]);
        }else if (calculation == 1){
            if (x > y){
                result = x - y;
                sb.append(NUMBER_STRS[x]).append(" - ").append(NUMBER_STRS[y]);
            }else {
                result = y - x;
                sb.append(NUMBER_STRS[y]).append(" - ").append(NUMBER_STRS[x]);
            }
        }else if (calculation == 2){
            result = x * y;
            sb.append(NUMBER_STRS[x]).append(" * ").append(NUMBER_STRS[y]);
        }else if (calculation == 3){
            if ( y == 0 || x % y !=0){
                result = x + y;
                sb.append(NUMBER_STRS[x]).append(" + ").append(NUMBER_STRS[y]);
            }else {
                result = x / y;
                sb.append(NUMBER_STRS[x]).append(" / ").append(NUMBER_STRS[y]);
            }
        }
        sb.append("=?@").append(result);
        return sb.toString();
    }
}
