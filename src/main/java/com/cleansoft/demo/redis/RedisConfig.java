package com.cleansoft.demo.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableCaching // 开启缓存
public class RedisConfig extends CachingConfigurerSupport{
    /**S
     *  redisConnectionFactory 此为SpringBoot自动帮忙注入的连接工厂
     *  org.springframework.data.redis.core.RedisTemplate
     *  redisTemplate配置 这个是直接可以调用redis方法来执行业务
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        // 传入连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 传入需要配置的对象的序列化和反序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.json());
        return redisTemplate;
    }


    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        //使用默认缓存配置
        //和配置序列化方案
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return redisCacheConfiguration;

    }
}