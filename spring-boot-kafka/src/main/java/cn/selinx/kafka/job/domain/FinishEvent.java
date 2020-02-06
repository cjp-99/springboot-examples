package cn.selinx.kafka.job.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
@Data
public class FinishEvent extends Event {

    private Date startTime;

    public FinishEvent(){
        this.type = EventTypeEnum.FINISH;
    }
}
