package com.jd.edi.version5.server;



import com.jd.edi.version5.register.ServiceRegister;
import com.jd.edi.version5.register.ZkServiceRegister;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 存放服务接口名与服务端对应的实现类
 * 服务启动时要暴露其相关的实现类
 * 根据request中的interface调用服务端中相关实现类
 */
public class ServiceProvider {
    /**
     * 一个实现类可能实现多个接口
     */
    private Map<String, Object> interfaceProvider;

    // zk改造，需要添加host，port
    private String host;
    private Integer port;
    private ServiceRegister serviceRegister;
    public ServiceProvider(String connectionAddress, String rootPath,String host, Integer port){
        this.host = host;
        this.port = port;
        this.interfaceProvider = new HashMap<>();
        this.serviceRegister = new ZkServiceRegister(connectionAddress, rootPath);
    }
//
//    public void provideServiceInterface(Object service){
//        Class<?>[] interfaces = service.getClass().getInterfaces();
//
//        for(Class clazz : interfaces){
//            interfaceProvider.put(clazz.getName(),service);
//        }
//
//    }

    public void provideServiceInterface(Object service){
        Class<?>[] interfaces = service.getClass().getInterfaces();

        for(Class clazz : interfaces){
            // 本机的映射表
            interfaceProvider.put(clazz.getName(),service);
            // 在注册中心注册服务
            serviceRegister.register(clazz.getName(), new InetSocketAddress(host, port));
        }

    }
    public Object getService(String interfaceName){
        return interfaceProvider.get(interfaceName);
    }
}
