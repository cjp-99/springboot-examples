package cn.selinx.kafka.producer;

import cn.selinx.kafka.job.domain.StartEvent;
import cn.selinx.kafka.job.producer.KafkaEventProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.Future;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaProducerTest {

    @Autowired
    KafkaTemplate kafkaTemplate;
    @Autowired
    ProducerListener producerListener;
    @Autowired
    KafkaEventProducer producer;

    @Test
    public void testSend() {
//        kafkaTemplate.setProducerListener(producerListener);
//        for (int i = 0; i < 100; i++) {
//            String msg = "test---"+i;
//            ListenableFuture future  = kafkaTemplate.send("test",msg);
//
//        }
//        kafkaTemplate.flush();
        StartEvent startEvent = new StartEvent();
        startEvent.setTopic("event");
        startEvent.setEventId("001");
        Date now = new Date();
        startEvent.setStartTime(now);
        Future<RecordMetadata> future = producer.sendAsync(startEvent, null);

        System.out.println("耗时：" + (System.currentTimeMillis() - now.getTime()));

    }

}
