package spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//Spring中ApplicationContextAware主要用来获取Spring上下文已经实例化的Bean对象

/**
 * 项目使用Spring管理各个实例对象Bean,
 * 如果为了使用已被实例化的Bean对象,
 * 如果使用再次加载配置文件的方法,可能会出现一个问题,
 * 如一些线程配置任务, 会启动两份,产生了冗余.
 *
 * 当一个类实现了ApplicationContextAware接口,
 * 这个类就可以方便获取ApplicationContext中的所有bean,
 * 即这个类可以获取Spring配置文件中管理的所有Bean对象.
 */
public class SpringBeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        //this.applicationContext = applicationContext; 不能直接使用
        initApplicationContext(applicationContext);
    }

    public static void initApplicationContext(ApplicationContext applicationContext) {
        if (SpringBeanUtils.applicationContext == null ) {
            SpringBeanUtils.applicationContext = applicationContext;
        }
    }

    //获取
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }




}
