package com.jd.edi.version5.register;

import java.net.InetSocketAddress;

public interface ServiceRegister {
    // 注册
    void register(String serviceName, InetSocketAddress serverAddress);
    // 查找，根据服务返回地址
    InetSocketAddress discoveryService(String serviceName);
}
