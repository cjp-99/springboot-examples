package cn.selinx.kafka.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
@Component
@Slf4j
public class FirstConsumer implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        log.info("FirstConsumer 监听到发布者发生变化: {} ",arg.toString());
        System.out.println(o);
    }
}
