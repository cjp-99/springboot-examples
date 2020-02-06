package cn.selinx.kafka.util;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RListMultimap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.List;

/**
 * @author JiePeng Chen
 * @since 1.0.0
 */
public class RedissonTest {

    @Test
    public void test() {
        RedissonClient redissonClient = redissonClient();
        RListMultimap<String, String> map = redissonClient.getListMultimap("test");
        String key = "key1";
        for (int i = 0; i < 100; i++) {
            if (!map.containsKey(key)) {
                System.out.println("第一次====="+key);
            }
            map.put(key, "value" + i);
        }
    }
    @Test
    public void test2() {
        RedissonClient redissonClient = redissonClient();
        RListMultimap<String, String> map = redissonClient.getListMultimap("test");
        String key = "key1";
        List<String> strList = map.get(key);
        strList.forEach(System.out::println);
        map.removeAll(key);
    }



    private RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("127.0.0.1:6379");
//                .setPassword("9pbsoq6hoNhhTzl");
        return Redisson.create(config);
    }
}
