package com.jd.edi.version0.client;

import com.jd.edi.version0.common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            // ObjectOutputStream它可以将一个对象转换成二进制流
            // 然后可以通过ObjectInputStream将二进制流还原成对象。
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            // 传给服务id
            objectOutputStream.writeInt(123);
            objectOutputStream.flush();
            // 服务器查询数据，返回对象
            User user = (User) objectInputStream.readObject();
            System.out.println("服务端返回的数据: " + user.toString());
        } catch (Exception e) {

        }

    }
}
