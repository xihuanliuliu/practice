package com.jd.edi.version2.provider;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolProvider implements RPCServer{

    private ServiceProvider serviceProvider;
    private ThreadPoolExecutor threadPoolExecutor;

    public ThreadPoolProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        this.threadPoolExecutor = new ThreadPoolExecutor(2,10,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
    }

    @Override
    public void start(Integer port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("线程池服务启动了");
            while (true) {
                Socket socket = serverSocket.accept();
                threadPoolExecutor.execute(new WorkThread(socket, serviceProvider));
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void stop() {

    }
}
