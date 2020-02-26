package cn.selinx.amqp.common.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
public class ChannelUtil {
    private static final String host = "172.16.6.146";
    private static final String password = "px1pV1mOpB4mrz5Q";

    public static Connection getConnection(String connectionName) {
        try {
            ConnectionFactory connectionFactory = getConnectionFactory();
            Connection connection = connectionFactory.newConnection(connectionName);
            return connection;
        } catch (Exception e) {
            throw new RuntimeException("get channel instance error : " + e.getMessage());
        }
    }

    /**
     * 查看ConnectionFactory 里面有些默认配置信息
     *
     * @return
     */
    private static ConnectionFactory getConnectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 配置交换信息
        connectionFactory.setHost(host);
        // 网络异常自动连接恢复，默认:true
        connectionFactory.setAutomaticRecoveryEnabled(true);
//        connectionFactory.setVirtualHost("/");
        // 每10秒尝试重试连接一次，默认:5000毫秒
        connectionFactory.setNetworkRecoveryInterval(10000);
        return connectionFactory;
    }
}
