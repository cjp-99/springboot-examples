package cn.selinx.kafka.job.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
public enum EventTypeEnum {

    START,
    DONGING,
    FINISH;

    /**
     * json 序列化
     * @return
     */
    @JsonValue
    public int toValue(){
        return ordinal();
    }

    @JsonCreator
    public EventTypeEnum fromValue(int ordinal){
        return EventTypeEnum.values()[ordinal];
    }

}
