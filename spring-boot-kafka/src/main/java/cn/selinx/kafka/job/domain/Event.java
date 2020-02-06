package cn.selinx.kafka.job.domain;

import lombok.Data;

/**
 * 事件基类
 *
 * @author JiePeng Chen
 * @since 1.0.0
 */
@Data
public abstract class Event {
    /**
     * 事件topic
     */
    protected String topic;
    /**
     * 事件Id
     */
    protected String eventId;
    /**
     * 事件类型
     */
    protected EventTypeEnum type;

}
