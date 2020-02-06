package cn.selinx.kafka.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("172.16.4.226:6381")
                .setPassword("9pbsoq6hoNhhTzl");
        return Redisson.create(config);
    }

}
