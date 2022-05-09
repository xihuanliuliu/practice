package com.jd.edi.version0.provider;

import com.jd.edi.version0.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
                        // 读取客户端传过来的id
                        Integer id = ois.readInt();
                        System.out.println("id: " +id);
                        User userByUserId = userService.getUser(id);
                        // 写入User对象给客户端
                        oos.writeObject(userByUserId);
                        oos.flush();
                    } catch (IOException e){
                        e.printStackTrace();
                        System.out.println("从IO中读取数据错误");
                    }
                }).start();
            }
        } catch (Exception e) {
            System.out.println("服务端启动失败");
        }


    }
}
