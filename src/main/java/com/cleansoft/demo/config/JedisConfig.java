package com.cleansoft.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {

	@Bean
	public RedisTemplate<String,String> restTemplate() {
		// 配置连接池
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 最大空闲数
		jedisPoolConfig.setMaxIdle(50);
		// 最大连接数
		jedisPoolConfig.setMaxTotal(100);
		// 最大等待亳秒数
		jedisPoolConfig.setMaxWaitMillis(20000);


		// Redis 连接服务器配置
		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
		redisConfig.setHostName("127.0.0.1");
		redisConfig.setPort(8080);
		redisConfig.setPassword("");
		redisConfig.setDatabase(0);


		// 修改 Redis 的连接器 Jedis 的默认连接池
		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder builder =
				(JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
		JedisClientConfiguration jedisClientConfiguration = builder.poolConfig(jedisPoolConfig).build();


		// 采用 Jedis 作为 Redis 客户端,可用用 JedisConnectionFactory 工厂初始化连接池配置
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(redisConfig,jedisClientConfiguration);
		// 调用后初始化方法，没有它将抛出异常
		connectionFactory.afterPropertiesSet();

		// 定义 RedisTemplate，并设置连接工程
		RedisTemplate<String,String> redisTemplate = new RedisTemplate<String,String>();
		redisTemplate.setConnectionFactory(connectionFactory);


		// 设置自定 Redis 序列化器
		RedisSerializer<Object> jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
		RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
		// 默认使用 StringRedisSerializer 作为 K,V 序列化存储
		redisTemplate.setDefaultSerializer(stringRedisSerializer);
		// 对于普通类型 K 使用 StringRedisSerializer 序列化存储
		redisTemplate.setKeySerializer(stringRedisSerializer);
		// 对于普通类型 V 使用 JdkSerializationRedisSerializer 序列化存储(不推荐存储占用空间大)
		redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
		// 对于 Hash 类型 K 使用 StringRedisSerializer 序列化存储
		redisTemplate.setHashKeySerializer(stringRedisSerializer);
		// 对于 Hash 类型 V 使用 JdkSerializationRedisSerializer 序列化存储(不推荐存储占用空间大)
		redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);
		return redisTemplate;
	}
}


