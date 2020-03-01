package cn.selinx.amqp.client;

import cn.selinx.amqp.common.util.ChannelUtil;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
public class FanoutTest {

    private static final String queueName = "fanout-test";
    private static final String EXCHANGE_NAME = "fanout-test";

    @Test
    public void send() throws Exception {
        //获取连接
        Connection conn = ChannelUtil.getConnection("fanout消息发送");
        Channel chan = conn.createChannel();
        chan.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        for (int i = 0; i < 100; i++) {
            String msg = "fanout-message:" + i;
            chan.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes("UTF-8"));
            System.out.println("第" + i + "条信息已经发送");
        }
        chan.close();
        conn.close();
    }

    @Test
    public void receiver1() throws Exception {
        //获取连接
        Connection conn = ChannelUtil.getConnection("fanout消息接收");
        Channel channel = conn.createChannel();
        //声明队列-queue为空时
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        while (true) {
            channel.basicConsume("", true, consumer);
        }
    }

    @Test
    public void receiver2() throws Exception {
        //获取连接
        Connection conn = ChannelUtil.getConnection("fanout消息接收");
        Channel channel = conn.createChannel();
        //声明队列-queue为空时
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [2] Received '" + message + "'");
            }
        };
        while (true) {
            channel.basicConsume("", true, consumer);
        }
    }


}
