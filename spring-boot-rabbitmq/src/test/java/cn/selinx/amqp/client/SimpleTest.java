package cn.selinx.amqp.client;

import cn.selinx.amqp.common.util.ChannelUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
public class SimpleTest {

    private static final String queueName = "hello";

    @Test
    public void send() throws Exception {
        //获取连接
        Connection conn = ChannelUtil.getConnection("simple消息发送者");
        Channel chan = conn.createChannel();
        // queueName不为空，最好是有意义的字符串

        //声明队列-queue为空时
        chan.queueDeclare(queueName, false, false, false, null);
        for (int i = 0; i < 10; i++) {
            String msg = "simple-message:" + i;
            chan.basicPublish("", queueName, null, msg.getBytes());
            System.out.println("第" + i + "条信息已经发送");
        }
        chan.close();
        conn.close();
    }

    @Test
    public void delete() throws Exception {
        //获取连接
        Connection conn = ChannelUtil.getConnection("simple消息发送者");
        Channel chan = conn.createChannel();
        //声明队列-queue为空时
        chan.queueDelete(queueName);
        chan.close();
        conn.close();
    }


}
