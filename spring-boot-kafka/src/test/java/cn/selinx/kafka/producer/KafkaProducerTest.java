package cn.selinx.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
public class KafkaProducerTest {

    @Test
    public void testTopic() {
        Producer<String, String> producer = initProducer();
        String topic = "test";
        for (int i = 1; i < 50; i++) {
            ProducerRecord record = new ProducerRecord(topic, "hell" + i, "你好" + i);
            producer.send(record);
        }
        producer.close();
    }

    private Producer<String, String> initProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master.docker:9092");
//        props.put(ProducerConfig.ACKS_CONFIG, "-1");
        //配置key和value的序列化类
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        Producer<String, String> producer = new KafkaProducer<>(props);
        return producer;
    }
}
