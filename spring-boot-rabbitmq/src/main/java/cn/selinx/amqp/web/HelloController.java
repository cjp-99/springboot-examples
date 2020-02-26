package cn.selinx.amqp.web;

import cn.selinx.amqp.hello.HelloSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
@RestController
public class HelloController {

    @Autowired
    private HelloSender helloSender;

    /**
     * 简单队列(模式)
     * @return
     */
    @GetMapping("/hello")
    public String hello(){
        helloSender.send();
        return "hello";
    }

    /**
     * Work模式
     *
     * @return
     */
    @GetMapping("/work")
    public String work(){
        helloSender.work();
        return "work";
    }

    /**
     * 订阅模型-Fanout，广播模式
     * @return
     */
    @GetMapping("/fanout")
    public String fanout(){
        helloSender.fanout();
        return "fanout";
    }

    /**
     * 订阅模型-Direct (路由模式)
     * @return
     */
    @GetMapping("/direct")
    public String direct(){
        helloSender.direct();
        return "direct";
    }


    /**
     * 订阅模型-topic (路由模式)
     * @return
     */
    @GetMapping("/topic")
    public String topic(){
        helloSender.topic();
        return "topic";
    }
}
