package cn.selinx.kafka.job.producer;

import cn.selinx.kafka.job.domain.Event;
import com.vip.vjtools.vjkit.mapper.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
@Slf4j
@Component
public class KafkaEventProducer {

    private final KafkaProperties properties;

    private final KafkaProducer<String, Event> producer;

    public KafkaEventProducer(KafkaProperties properties) {
        this.properties = properties;
        this.producer = createProducer();
    }

    /**
     * 创建生产者
     *
     * @return
     */
    private KafkaProducer<String,Event> createProducer(){
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return new KafkaProducer<String, Event>(prop);
    }

    /**
     * 异步发送消息
     *
     * @param event
     * @param callback
     * @return
     */
    public Future<RecordMetadata> sendAsync(Event event, Callback callback){
        String value = JsonMapper.INSTANCE.toJson(event);
        ProducerRecord record = new ProducerRecord(event.getTopic(),event.getEventId(),value);
        return producer.send(record,callback);
    }
}
