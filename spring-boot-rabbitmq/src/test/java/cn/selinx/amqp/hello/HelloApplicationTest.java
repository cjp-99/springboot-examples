package cn.selinx.amqp.hello;

import cn.selinx.amqp.HelloApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class HelloApplicationTest {

    @Autowired
    private HelloSender sender;

    @Test
    public void hello() throws Exception {
        sender.send();
    }

}
