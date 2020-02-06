package cn.selinx.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;
import sun.security.krb5.Config;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
public class KafkaConsumerTest {

    public static void main(String[] args) {
        KafkaConsumerTest test = new KafkaConsumerTest();
        test.test1();
    }

    @Test
    public void test1() {
        Consumer<String, String> consumer = initConsumer();
        consumer.subscribe(Arrays.asList("test"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            System.out.println("拉取数目大小: " + records.count());
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("[consumer-1] offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }

    @Test
    public void test2() {
        Consumer<String, String> consumer = initConsumer();
        String topicName = "test";
        //用于分配topic和partition
        consumer.assign(Arrays.asList(new TopicPartition(topicName, 0)));
        consumer.seekToBeginning(Arrays.asList(new TopicPartition(topicName, 0)));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("[consumer-2] offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }

    private Consumer<String, String> initConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "master.docker:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test_group2");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        return consumer;
    }
}
