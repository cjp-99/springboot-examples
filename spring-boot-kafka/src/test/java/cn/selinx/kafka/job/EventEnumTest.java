package cn.selinx.kafka.job;

import cn.selinx.kafka.job.domain.EventTypeEnum;
import cn.selinx.kafka.job.domain.StartEvent;
import com.vip.vjtools.vjkit.mapper.JsonMapper;
import org.junit.Test;

import java.util.Date;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
public class EventEnumTest {

    @Test
    public void serialized(){
        EventTypeEnum start = EventTypeEnum.FINISH;
        System.out.println(JsonMapper.INSTANCE.toJson(start));

        String a = "2";
        EventTypeEnum finish = JsonMapper.INSTANCE.fromJson(a, EventTypeEnum.class);
        System.out.println(finish);

        StartEvent startEvent = new StartEvent();
        startEvent.setTopic("event");
        startEvent.setEventId("001");
        startEvent.setStartTime(new Date());
        System.out.println(JsonMapper.INSTANCE.toJson(startEvent));

    }
}
