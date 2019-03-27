//package com.intuit.redispoc;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//@Configuration
//class AppConfig {
//
//
//  @Bean
//  JedisConnectionFactory jedisConnectionFactory() {
//    return new JedisConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
//  }
//
//  @Bean
//  public RedisTemplate<String, Object> redisTemplate() {
//    RedisTemplate<String, Object> template = new RedisTemplate<>();
//    template.setConnectionFactory(jedisConnectionFactory());
//    return template;
//  }
//}