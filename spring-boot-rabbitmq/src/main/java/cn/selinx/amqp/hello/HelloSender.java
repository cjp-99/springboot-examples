package cn.selinx.amqp.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 简单模式
     */
    public void send() {
        String context = "hello" + new Date();
        System.out.println("sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    /**
     * Work模式
     */
    public void work() {
        String message = "work模式";
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend("work", message + "---" + i);
        }
    }

    /**
     * 发布订阅模式：fanout
     * Publish/Subscribe
     */
    public void fanout() {
        String message = "发布订阅模式-fanout";
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend("fanout", "", message + "---" + i);
        }
    }

    /**
     * 订阅模式路由模式：direct
     * Publish/Subscribe
     */
    public void direct() {
        String message = "发布订阅模式-路由模式-direct";
        for (int i = 0; i < 100; i++) {
            String routingKey = "direct";
            if (i >= 80) {
                routingKey = "direct-test";
            }
            rabbitTemplate.convertAndSend("hello-direct", routingKey, message + "---" + i);
        }
    }

    /**
     * 订阅模型-Topic (主题模式)
     * Publish/Subscribe
     */
    public void topic() {
        rabbitTemplate.convertAndSend("hello-topic", "person.insert", "增加人员");
        rabbitTemplate.convertAndSend("hello-topic", "person.delete", "删除人员");
        rabbitTemplate.convertAndSend("hello-topic", "person.select.one", "查询单个人员");
        rabbitTemplate.convertAndSend("hello-topic", "money.insert", "加钱");
        rabbitTemplate.convertAndSend("hello-topic", "money.delete", "减钱");
        rabbitTemplate.convertAndSend("hello-topic", "money.select.one", "查询单个人钱");
    }


}
