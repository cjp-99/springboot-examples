package cn.selinx.amqp.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 1. 手动创建，需在RabbitMQ中手动创建myQueue1 队列，否则报错.queues = "hello"
 * --spring-boot-2.0版本以上支持自动创建队列
 * 2. 自动创建队列: queuesToDeclare = @Queue("myQueue2")
 * @author JiePeng Chen
 * @since 1.0.0
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }
}
