package cn.selinx.amqp.hello;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * work模式
 *
 * @author JiePeng Chen
 * @since 1.0.0
 */
//@Component
public class HelloWorkReceiver {

    @RabbitListener(queues = "work")
    public void listen(String msg){
        System.out.println("work listen1 : "+msg);
    }

    @RabbitListener(queues = "work")
    public void listen2(String msg){
        System.out.println("work listen2 : "+msg);
    }

    @RabbitListener(queues = "work")
    public void listen3(String msg){
        System.out.println("work listen3 : "+msg);
    }

    @RabbitListener(queues = "work")
    public void listen4(String msg){
        System.out.println("work listen4 : "+msg);
    }
}
