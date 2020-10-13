package com.example.springboot08cache.config;

import com.example.springboot08cache.pojo.Employee;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
public class MySerializerConfig {

    @Bean(name = "empRedisTemplate")
    public RedisTemplate<Object, Employee> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //创建一个序列化规则给employee使用 方便我们将这个对象存入数据库的时候用这个序列化规则
        Jackson2JsonRedisSerializer<Employee> employeeJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Employee.class);
        template.setDefaultSerializer(employeeJackson2JsonRedisSerializer);
        return template;
    }



    //在之前我们没有引入redis的starter的时候 是使用ConcurrentMapCacheManager  但是我们现在引入了redis的
    //starter那SimpleCacheConfiguration 这个就不起作用了 我们就在存入对象的时候就要自定义cacheManager  这个有点绕 不像我们上面的配置自定义redisTemplate
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory) {
//        RedisCacheConfiguration cacheConfiguration =
//                RedisCacheConfiguration.defaultCacheConfig()
//                        .entryTtl(Duration.ofDays(1))
//                        .disableCachingNullValues()
//                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new
//                                GenericJackson2JsonRedisSerializer()));
//        return RedisCacheManager.builder(factory).cacheDefaults(cacheConfiguration).build();
//    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(jsonSerializer);
        RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(pair);
        //设置默认超过期时间是30秒
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }
}
