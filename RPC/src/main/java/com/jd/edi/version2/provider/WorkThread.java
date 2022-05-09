package com.jd.edi.version2.provider;

import com.jd.edi.version2.common.RPCRequest;
import com.jd.edi.version2.common.RPCResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class WorkThread implements Runnable{
    private Socket socket;
    private ServiceProvider serviceProvider;

    public WorkThread(Socket socket, ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            //
            RPCRequest request  = (RPCRequest) objectInputStream.readObject();
            System.out.println("服务器端: request: " + request.toString() );
            //
            Object server = serviceProvider.getServer(request.getInterfaceName());
            Method method = server.getClass().getMethod(request.getMethodName(), request.getParamsType());
            Object invoke = method.invoke(server, request.getParams());
            // out
            objectOutputStream.writeObject(RPCResponse.success(invoke));
            objectOutputStream.flush();

        } catch (Exception e) {

        }
    }
}
