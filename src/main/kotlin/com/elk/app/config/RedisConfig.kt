package com.elk.app.config
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories


@Configuration
@EnableRedisRepositories
class Configuration {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val properties = redisProperties()
        val configuration = RedisStandaloneConfiguration()
        configuration.hostName = properties!!.host
        configuration.port = properties!!.port
        return JedisConnectionFactory(configuration)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String?, Any?>? {
        val template: RedisTemplate<String?, Any?> = RedisTemplate()

        template.setConnectionFactory(redisConnectionFactory())
        return template
    }

    @Bean
    @Primary
    fun redisProperties(): RedisProperties? {
        return RedisProperties()
    }
}
