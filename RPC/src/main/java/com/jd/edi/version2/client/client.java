package com.jd.edi.version1.client;


import com.jd.edi.version1.common.User;

import com.jd.edi.version1.service.UserService;


public class client {

    public static void main(String[] args) {
        try {
            ClientProxy clientProxy = new ClientProxy("127.0.0.1", 9999);

            UserService proxy = (UserService) clientProxy.getProxy(com.jd.edi.version1.service.UserService.class);
            // 在这里调用方法的时候就会 去调用 ClientProxy.invoke方法
            User user = proxy.getUser(23);
            System.out.println("user: " + user.toString());
            System.out.println("client: " + user.toString());
        } catch (Exception e) {

        }

    }
}
