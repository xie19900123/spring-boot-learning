package cn.lqdev.learning.springboot.chapter11.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author oKong
 *
 */
@Configuration
@EnableCaching
public class RedisConfig {
	
	/**
	 *  定义 StringRedisTemplate ，指定序列号和反序列化的处理类
	 * @param factory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		//序列列化 值时使用此序列化方法
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
	
	@Bean
    public CacheManager cacheManager(RedisTemplate<String,String> redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //使用前缀
        rcm.setUsePrefix(true);
        //缓存分割符 默认为 ":"
//        rcm.setCachePrefix(new DefaultRedisCachePrefix(":"));
        //设置缓存过期时间
        //rcm.setDefaultExpiration(60);//秒
        return rcm;
    }
}
