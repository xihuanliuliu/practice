package com.jd.edi.version5.loadbalance;

import java.util.List;
import java.util.Random;

/**
 * 随机
 */
public class RandomLoadBalance implements LoadBalance{
    @Override
    public String balance(List<String> addressList) {
        Random random = new Random();
        int choose = random.nextInt(addressList.size());
        return addressList.get(choose);
    }
}
