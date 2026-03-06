package com.rescue.framework.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.redislabs.lettusearch.RediSearchClient;
import com.redislabs.lettusearch.StatefulRediSearchConnection;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * Redis 配置
 */
@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host:127.0.0.1}")
    private String host;

    @Value("${spring.data.redis.port:6379}")
    private int port;

    private static final int redisearchDatabase=0;

     @Value("${spring.data.redis.password:com.rescue.redis}")
     private String pwd;

    /**
     * redisearch 连接, 数据库 0,
     * 用于索引和文档的存储,数据库指定0，其他数据库用于普通的key-value存储，
     * @return
     */
    @Bean(destroyMethod = "close")
    public StatefulRediSearchConnection searchConnection() {
        // 不再拼字符串，直接 new 出来
        RedisURI uri = new RedisURI();
        uri.setHost(host);
        uri.setPort(port);
        uri.setPassword(pwd.toCharArray());
        uri.setDatabase(redisearchDatabase);
        RediSearchClient client = RediSearchClient.create(uri);
        return client.connect();
    }

    /**
     * 自定义 RedisTemplate
     * <p>
     * 修改 Redis 序列化方式，默认 JdkSerializationRedisSerializer
     *
     * @param factory {@link RedisConnectionFactory}
     * @return {@link RedisTemplate}
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置值（value）的序列化采用FastJsonRedisSerializer。
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        // Hash 的 key 采用 String 的序列化方式
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        // Hash 的 value 采用 jackson 的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 把所有的配置 set 进 template
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


}
