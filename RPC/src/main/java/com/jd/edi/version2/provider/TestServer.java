package com.jd.edi.version2.provider;

public class TestServer {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        BlogServiceImpl blogService = new BlogServiceImpl();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.putServer(userService);
        serviceProvider.putServer(blogService);

//        SimpleRpcServer server = new SimpleRpcServer(serviceProvider);
//        server.start(9999);
        ThreadPoolProvider threadPoolProvider = new ThreadPoolProvider(serviceProvider);
        threadPoolProvider.start(9999);
    }
}
