package com.jd.edi.version3.client;


import com.jd.edi.version3.common.Blog;
import com.jd.edi.version3.common.User;
import com.jd.edi.version3.service.BlogService;
import com.jd.edi.version3.service.UserService;


public class client {

    public static void main(String[] args) {
        try {
            //ClientProxy clientProxy = new ClientProxy("127.0.0.1", 9999);
            //SimpleClient simpleClient = new SimpleClient("127.0.0.1", 9999);
            //ClientProxy clientProxy = new ClientProxy(simpleClient);
            NettyRPCClient nettyRPCClient = new NettyRPCClient("127.0.0.1", 9999);
            ClientProxy clientProxy = new ClientProxy(nettyRPCClient);
            UserService proxy =  clientProxy.getProxy(UserService.class);
            // 在这里调用方法的时候就会 去调用 ClientProxy.invoke方法
            User user = proxy.getUser(23);
            System.out.println("user: " + user.toString());
            System.out.println("client: " + user.toString());

            System.out.println("--------------------");
            BlogService blogService = clientProxy.getProxy(BlogService.class);
            Blog blog = blogService.getBlog("blog");
            System.out.println("blog: " + blog.toString());
        } catch (Exception e) {

        }

    }
}
