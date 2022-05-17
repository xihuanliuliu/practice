package com.jd.edi.version3.provider;

import com.jd.edi.version3.service.BlogServiceImpl;
import com.jd.edi.version3.service.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.putServer(userService);
        serviceProvider.putServer(blogService);

        //SimpleRpcServer server = new SimpleRpcServer(serviceProvider);
       // server.start(9999);
//        ThreadPoolProvider threadPoolProvider = new ThreadPoolProvider(serviceProvider);
//        threadPoolProvider.start(9999);


        /**
         * netty
         */
        NettyRpcProvider nettyRpcProvider = new NettyRpcProvider(serviceProvider);
        nettyRpcProvider.start(9999);
    }
}
