package com.jd.edi.version1.provider;

import com.jd.edi.version1.common.RPCRequest;
import com.jd.edi.version1.common.RPCResponse;
import com.jd.edi.version1.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class provider {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("服务端启动了");
            // 使用BIO的方式
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(()->{
                    try {
                        // ObjectOutputStream它可以将一个对象转换成二进制流
                        // 然后可以通过ObjectInputStream将二进制流还原成对象。
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        // 传过来的是reques
                        RPCRequest request = (RPCRequest) ois.readObject();
                        System.out.println("server: " + request.toString());
                        // 反射调用对应方法
                        Method method = userService.getClass().getMethod(request.getMethodName(), request.getParamsType());
                        Object invoke = method.invoke(userService, request.getParams());
                        // 封装，写入response对象
                        oos.writeObject(RPCResponse.success(invoke));
                        oos.flush();
                    } catch (IOException | ClassNotFoundException e){
                        e.printStackTrace();
                        System.out.println("从IO中读取数据错误");
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            System.out.println("服务端启动失败");
        }


    }
}
