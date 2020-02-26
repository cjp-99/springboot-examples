package cn.selinx.amqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
@Configuration
public class HelloRabbitConfig {

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    @Bean
    public Queue workQueue(){
        return new Queue("work");
    }
}
