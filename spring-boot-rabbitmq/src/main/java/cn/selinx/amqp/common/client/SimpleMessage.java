package cn.selinx.amqp.common.client;

import cn.selinx.amqp.common.util.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
public class SimpleMessage {

    public static void main(String[] args) throws Exception {
        simpleSend();

        receive();
    }

    /**
     * 简单模式
     */
    public static void simpleSend() throws Exception {
        String message = "simple-消息";
        String queueName = "simple";
        Connection connection = ChannelUtil.getConnection("simple队列消息发送者");
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);

        // 2. 发送消息，简单模型不需要exchange，直接到queue
        for (int i = 0; i < 10; i++) {
            String msg = message + "--" + i;
            channel.basicPublish("", queueName, null, msg.getBytes());
            System.out.println("[x]Sent '" + msg + "'");
        }
        Thread.sleep(500);
//        channel.close();
//        connection.close();
    }

    public static void receive() throws Exception {
        String queueName = "simple";
        Connection connection = ChannelUtil.getConnection("simple队列消息发送者");
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, false, false, false, null);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            //该方法会阻塞
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[x] Received '" + message + "'");
        }

    }


}
