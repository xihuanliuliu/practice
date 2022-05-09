package com.jd.edi.version2.client;

import com.jd.edi.version2.common.RPCRequest;
import com.jd.edi.version2.common.RPCResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端根据不同的Service进行动态代理：
 * 代理对象增强的公共行为：把不同的Service方法封装成统一的Request对象格式，并且建立与Server的通信
 */
public class IOClient {

    // 这里负责底层与服务端的通信，发送的Request，接受的是Response对象
    // 客户端发起一次请求调用，Socket建立连接，发起请求Request，得到响应Response

    // 这里的request是封装好的（上层进行封装），不同的service需要进行不同的封装，
    // 客户端只知道Service接口，需要一层动态代理根据反射封装不同的Service

    public static RPCResponse sendResponse(String host, Integer port, RPCRequest request) {
        try {
            Socket socket = new Socket(host, port);
            // ObjectOutputStream它可以将一个对象转换成二进制流
            // 然后可以通过ObjectInputStream将二进制流还原成对象。
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            // 传给服务端
               // 接口，方法，参数，参数类型
            System.out.println("传入的参数: " + request);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            // 从服务获取的数据
            RPCResponse response = (RPCResponse) objectInputStream.readObject();
            return response;
        } catch (Exception e) {
            System.out.println("sendResponse 错误");
            return null;
        }

    }

}
