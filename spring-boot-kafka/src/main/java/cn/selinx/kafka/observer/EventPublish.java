package cn.selinx.kafka.observer;

import org.springframework.stereotype.Component;

import java.util.Observable;

/**
 * 继承java.util.Observable的通知者，被观察者类（也是消息发布方）
 *
 * @author JiePeng Chen
 * @since 1.0.0
 */
@Component
public class EventPublish extends Observable {

    public void start(){
        notifyObserver("开始事件");
    }

    public void end(){
        notifyObserver("结束事件");
    }

    private void notifyObserver(String msg){
        setChanged();
        notifyObservers(msg);
    }

    /**
     * 测试监听者模式
     *
     * @param args
     */
    public static void main(String[] args) {
        EventPublish eventPublish = new EventPublish();
        eventPublish.addObserver(new FirstConsumer());
        eventPublish.addObserver(new SecondConsumer());
        eventPublish.start();
        eventPublish.end();
    }
}
