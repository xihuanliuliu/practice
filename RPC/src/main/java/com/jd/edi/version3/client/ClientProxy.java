package com.jd.edi.version3.client;

import com.jd.edi.version3.common.RPCRequest;
import com.jd.edi.version3.common.RPCResponse;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * invocationHandler位于JDK反射包下，其作用是在实现JDK动态代理的时候提供了动态执行增强逻辑的方法。
 * InvocationHandler的用法如下：
 *
 *     TargetInterface target;
 * 	 * InvocationHandler的实现类需要提供一个构造器，用来接收目标对象
 * 	 * 否则接口中的method是无法执行的，因为没有对象就没有办法执行对象的方法
 * 	 * @param target 目标对象
	public JdkInvocationHandler(TargetInterface targt) {
         		this.taret = target;*
   }
 * 	 *
 * 	 * @param proxy 代理对象
 * 	 * @param method 目标对象方法
 * 	 * @param args 目标对象参数
 * 	 * @return
 * 	 * @throws Throwable
 *
 * 	  * 	puic Object invoke(Object proxy, Metod method, Objct[] args) thows Throwable {
 *         *        // 可以在调用目标对象方法之前或之后执行任意代理逻辑
 *         *System.out.println("advisor");
 *         *return method.invoke(target, ars);
 *         * 	}
 * 	 */

@Slf4j
public class ClientProxy implements InvocationHandler {

    private RPCClient rpcClient;
    public ClientProxy(RPCClient rpcClient) {
        this.rpcClient = rpcClient;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 根据request
        // request的构建，使用了lombok中的builder，代码简洁
        //数据传输
        RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args).paramsType(method.getParameterTypes()).build();
        System.out.println("invke");
        RPCResponse response = rpcClient.sendResponse(request);
        System.out.println(response);
        return response.getData();

    }

    <T>T getProxy(Class<T> clazz){
        System.out.println("调用方法");
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T)o;
    }
}
