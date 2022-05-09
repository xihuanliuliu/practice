package com.jd.edi.version2.provider;


import java.net.ServerSocket;
import java.net.Socket;

public class SimpleRpcServer implements RPCServer {
    private ServiceProvider serviceProvider;

    public SimpleRpcServer(ServiceProvider serviceProvider){
        this.serviceProvider = serviceProvider;
    }
    @Override
    public void start(Integer port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("简单服务启动了");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, serviceProvider)).start();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void stop() {

    }
}
