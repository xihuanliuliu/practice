package com.jd.edi.version2.provider;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {

    /**
     * 将所有的接口放到map中
     */
    private Map<String, Object> interfaceMap;

    public ServiceProvider() {
        interfaceMap = new HashMap<>();
    }

    public void putServer(Object object) {
        Class<?>[] interfaces = object.getClass().getInterfaces();
        for (Class clazz : interfaces) {
            interfaceMap.put(clazz.getName(), object);
        }
    }

    public Object getServer(String interfaceName) {
        return interfaceMap.get(interfaceName);
    }

}
