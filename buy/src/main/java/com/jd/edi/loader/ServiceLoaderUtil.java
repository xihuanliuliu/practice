package com.jd.edi.loader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class ServiceLoaderUtil implements ApplicationContextAware {

    private static ApplicationContext cxt;

    private static volatile Map<Class, ServiceLoader> serviceLoaderMap = new HashMap<>(100);

    public ServiceLoaderUtil() {

    }

    public static final <T> List<T> loadServices(Class<T> service) {
        return loaderServices(service, getClassLoader());
    }

    public static final <T> T loaderService(Class<T> serviceClass, ClassLoader classLoader) {
        List<T> services = loaderServices(serviceClass, classLoader);
        if(services.size() >= 1) {
            T ret = services.get(0);
            if (services.size() > 1) {
                log.warn("发现【{}】个【{}】的服务实现，当前使用的是:{}", services.size(), ret.getClass().getName(),ret.getClass().getName());
            }
            return ret;
        } else {
          log.warn("没有发现【{}】的实现", serviceClass.getName());
          return null;
        }
    }

    public static final <T> List<T> loaderServices(Class<T> serviceClass, ClassLoader classLoader) {
        List<T> servicesList = new ArrayList<>();
        List<T> springServiceList = loadSpringServices(serviceClass);
        if(!isEmpty(springServiceList)) {
            servicesList.addAll(springServiceList);
        }
        return servicesList;
    }

    public static final <T> List<T> loadSpringServices(Class<T> serviceClass) {
        if (cxt != null) {
            try {
                Map<String, T> beansServiceMap = cxt.getBeansOfType(serviceClass);
                List<T> res = new ArrayList<>(beansServiceMap.size());
                log.info("BeanNames = {}", beansServiceMap.values());
                res.add((T) beansServiceMap.values());
                return res;

            }catch (BeansException exception) {
                return Collections.EMPTY_LIST;
            }
        }else {
            log.warn("无法从SpringContext中获取到服务【{}】", serviceClass.getName());
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ServiceLoaderUtil.cxt = applicationContext;
    }

    private final static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    private static final ClassLoader getClassLoader(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader == null) {
            classLoader = ServiceLoaderUtil.class.getClassLoader();
        }
        return classLoader;
    }
}
