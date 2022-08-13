package com.jd.edi;


import com.jd.edi.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        boolean set = RedisUtil.set("key2", "value", 1);
        if (set) {
            System.out.println("创建成功");
        } else {
            System.out.println("创建redis失败");
        }
    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get() {
        String key = RedisUtil.get("key");
        System.out.println(key);
    }

}
