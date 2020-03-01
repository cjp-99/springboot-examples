package cn.selinx.amqp.client;

import cn.selinx.amqp.common.util.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Test;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
public class WorkTest {

    @Test
    public void send() throws Exception {
        //获取连接
        Connection conn = ChannelUtil.getConnection("work消息发送者");
        Channel chan = conn.createChannel();
        //声明队列
        chan.queueDeclare("work", false, false, false, null);
        chan.basicQos(1);
        for (int i = 0; i < 100; i++) {
            String msg = "work-message:" + i;
            chan.basicPublish("", "work", null, msg.getBytes());
            System.out.println("第" + i + "条信息已经发送");
        }
        chan.close();
        conn.close();
    }

    @Test
    public void consumer1() throws Exception {
        //获取连接
        Connection conn = ChannelUtil.getConnection("work消息发送者");
        Channel chan = conn.createChannel();
        chan.queueDeclare("work", false, false, false, null);
        //同一时刻服务器只发送一条消息给同一消费者,消费者空闲,才发送一条
        chan.basicQos(1);
        //定义消费者
        QueueingConsumer consumer = new QueueingConsumer(chan);
        chan.basicConsume("work", false, consumer);
        //监听
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            byte[] result = delivery.getBody();
            String msg = new String(result);
            System.out.println("consumer1:" + msg);
//            Thread.sleep(50);
            //返回服务器,回执
            if (msg.contains("98") || msg.contains("99")) {
                chan.basicReject(delivery.getEnvelope().getDeliveryTag(), true);
            } else {
                chan.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        }

    }

    @Test
    public void consumer2() throws Exception {
        //获取连接
        Connection conn = ChannelUtil.getConnection("work消息发送者");
        Channel chan = conn.createChannel();
        chan.queueDeclare("work", false, false, false, null);

        //同一时刻服务器只发送一条消息给同一消费者,消费者空闲,才发送一条
        chan.basicQos(1);
        //定义消费者
        QueueingConsumer consumer = new QueueingConsumer(chan);
        chan.basicConsume("work", false, consumer);
        //监听
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            byte[] result = delivery.getBody();
            String msg = new String(result);
            System.out.println("consumer2:" + msg);
//            Thread.sleep(150);
            //返回服务器,回执
            chan.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }

    }

}
