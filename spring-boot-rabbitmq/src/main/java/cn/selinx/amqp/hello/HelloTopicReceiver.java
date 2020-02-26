package cn.selinx.amqp.hello;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Topic类型的Exchange与Direct相比，都是可以根据RoutingKey把消息路由到不同的队列。
 * 只不过Topic类型Exchange可以让队列在绑定Routing key 的时候使用通配符
 * <p>
 * Routingkey 一般都是有一个或多个单词组成，多个单词之间以”.”分割，例如： user.insert
 * #：匹配一个或多个单词	person.#：能够匹配person.insert.save 或者 person.insert
 * *：匹配不多不少恰好1个单词	person.*：只能匹配person.insert
 *
 * @author JiePeng Chen
 * @since 1.0.0
 */
//@Component
public class HelloTopicReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "hello-topic", durable = "true"),
            exchange = @Exchange(value = "hello-topic",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC),
            key = "person.*"
    ))
    public void listen(String msg) {
        System.out.println("person : " + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "hello-topic", durable = "true"),
            exchange = @Exchange(value = "hello-topic",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC),
            key = "money.#"
    ))
    public void listen2(String msg) {
        System.out.println("money : " + msg);
    }
}
