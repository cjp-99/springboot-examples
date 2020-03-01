package cn.selinx.amqp.client;

import cn.selinx.amqp.common.util.ChannelUtil;
import com.rabbitmq.client.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
public class DirectTest {

    private static final String EXCHANGE_NAME = "direct-test";

    /**
     * 删除后，消费者报错，退出
     *
     * @throws Exception
     */
    @Test
    public void delete() throws Exception {
        //获取连接
        Connection conn = ChannelUtil.getConnection("direct消息发送");
        Channel chan = conn.createChannel();
        chan.exchangeDelete(EXCHANGE_NAME);
        chan.queueDelete("white");
        chan.queueDelete("black");
    }

    /**
     * 申明queue和routeKey绑定，可以一个queue绑定多个routekey
     * 至少绑定一个routeKey
     * @throws Exception
     */
    @Test
    public void send() throws Exception {
        //获取连接
        Connection conn = ChannelUtil.getConnection("direct消息发送");
        Channel chan = conn.createChannel();
        chan.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);
        String queueName = "black";
        String queueName2 = "white";
        chan.queueDeclare(queueName, true, false, false, null);
        chan.queueDeclare(queueName2, true, false, false, null);
        chan.queueBind(queueName, EXCHANGE_NAME, queueName);
        chan.queueBind(queueName2, EXCHANGE_NAME, queueName2);
        String routeKey = queueName;
        for (int i = 0; i < 100; i++) {
            String msg = "direct-message:" + i;
            if (i >= 80) {
                routeKey = queueName2;
            }
            chan.basicPublish(EXCHANGE_NAME, routeKey, null, msg.getBytes("UTF-8"));
            System.out.println("第" + i + "条信息已经发送");
        }
//        chan.close();
//        conn.close();
    }

    @Test
    public void receiver1() throws Exception {
        //获取连接
        Connection conn = ChannelUtil.getConnection("direct1消息接收");
        Channel channel = conn.createChannel();
        //声明队列-queue为空时
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true,false,null);
        String queueName = "black";
//        channel.queueDeclare(queueName,true, false, false, null);
//        channel.queueBind(queueName, EXCHANGE_NAME, queueName);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [direct1] Received '" + message + "'");
            }
        };
        while (true) {
            channel.basicConsume(queueName, true, consumer);
        }
    }

    @Test
    public void receiver2() throws Exception {
        //获取连接
        Connection conn = ChannelUtil.getConnection("direct2消息接收");
        Channel channel = conn.createChannel();
//        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true,false,null);
        String queueName = "white";
//        channel.queueDeclare(queueName,true, false, false, null);
//        channel.queueBind(queueName, EXCHANGE_NAME, queueName);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [direct2] Received '" + message + "'");
            }
        };
        while (true) {
            channel.basicConsume(queueName, true, consumer);
        }
    }
}
