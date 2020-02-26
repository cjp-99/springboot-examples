package cn.selinx.amqp.hello;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * exchange
 * 只有队列的 Routingkey与消息的 Routing key完全一致，才会接收到消息
 *
 * @author JiePeng Chen
 * @since 1.0.0
 */
//@Component
public class HelloDirectReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "direct-1", durable = "true"),
            exchange = @Exchange(value = "hello-direct",
                    ignoreDeclarationExceptions = "true"),
            key = "direct"
    ))
    public void listen(String msg) {
        System.out.println("direct-1 : " + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "direct-2", durable = "true"),
            exchange = @Exchange(value = "hello-direct",
                    ignoreDeclarationExceptions = "true"),
            key = "direct-test"
    ))
    public void listen2(String msg) {
        System.out.println("direct-test-1 : " + msg);
    }
}
