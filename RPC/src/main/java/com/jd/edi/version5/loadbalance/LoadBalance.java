package com.jd.edi.version5.loadbalance;

import java.util.List;

public interface LoadBalance {

    String balance(List<String> addressList);
}
