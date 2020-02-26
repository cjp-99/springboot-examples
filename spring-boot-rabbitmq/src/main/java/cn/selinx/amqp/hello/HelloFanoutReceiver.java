package cn.selinx.amqp.hello;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * exchange
 *
 * @author JiePeng Chen
 * @since 1.0.0
 */
//@Component
public class HelloFanoutReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "fanout1", durable = "true"),
            exchange = @Exchange(value = "fanout",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.FANOUT)
    ))
    public void listen(String msg) {
        System.out.println("fanout listen1 : " + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "fanout2", durable = "true"),
            exchange = @Exchange(value = "fanout",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.FANOUT)
    ))
    public void listen2(String msg) {
        System.out.println("fanout listen2 : " + msg);
    }
}
