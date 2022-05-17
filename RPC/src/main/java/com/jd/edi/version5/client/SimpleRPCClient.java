package com.jd.edi.version5.client;



import com.jd.edi.version5.common.RPCRequest;
import com.jd.edi.version5.common.RPCResponse;
import com.jd.edi.version5.register.ServiceRegister;
import com.jd.edi.version5.register.ZkServiceRegister;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class SimpleRPCClient implements RPCClient {
    private String host;
    private int port;
    // 服务注册
    private ServiceRegister serviceRegister;
    public SimpleRPCClient(String connectionAddress, String rootPath) {
        serviceRegister = new ZkServiceRegister(connectionAddress, rootPath);
    }

    // 客户端发起一次请求调用，Socket建立连接，发起请求Request，得到响应Response
    // 这里的request是封装好的，不同的service需要进行不同的封装， 客户端只知道Service接口，需要一层动态代理根据反射封装不同的Service
    public RPCResponse sendRequest(RPCRequest request) {
        // 从注册中心获取
        InetSocketAddress address = serviceRegister.discoveryService(request.getInterfaceName());
        this.host = address.getHostName();
        this.port = address.getPort();
        try {
            Socket socket = new Socket(host, port);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            System.out.println(request);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            RPCResponse response = (RPCResponse) objectInputStream.readObject();

            //System.out.println(response.getData());
            return response;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println();
            return null;
        }
    }
}
