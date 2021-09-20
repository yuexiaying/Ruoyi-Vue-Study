package com.study.framework.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.study.common.test.domain.Person;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 使用FastJson序列化Redis
 *
 * @param <T>
 * @author fjding
 * @date 2021/9/19
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

    private Class<T> clazz;

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public FastJson2JsonRedisSerializer(Class<T> clazz){
        this.clazz = clazz;
    }

    static {
        // 开启全局指定反序列化类型的支持，必须加，否则反序列化时，会报autoType is not support的错误
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
       if (t == null){
           return new byte[0];
       }
       // 序列化时，指定类型，这样当反序列化时，虽然是指定的Object类型，但是实际类型会使指定的类型
        return JSON.toJSONString(t,SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0){
            return null;
        }

        return JSON.parseObject(new String(bytes,DEFAULT_CHARSET),clazz);
    }

    public static void main(String[] args) {
        Person person = Person.builder().age(12).name("张三").height(172.5).build();
        // {"age":12,"height":172.5,"name":"张三"}
        String str = JSON.toJSONString(person);
        System.out.println(str);
        // 指定序列化形式
        // {"@type":"com.study.common.test.domain.Person","age":12,"height":172.5D,"name":"张三"}
        str = JSON.toJSONString(person,SerializerFeature.WriteClassName);
        System.out.println(str);
    }

}
