package cn.selinx.kafka.job.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
@Data
public class StartEvent extends Event {

    private Date startTime;

    public StartEvent(){
        this.type = EventTypeEnum.START;
    }
}
