package com.jd.edi.version5.server;



import com.jd.edi.version5.service.BlogService;
import com.jd.edi.version5.service.BlogServiceImpl;
import com.jd.edi.version5.service.UserService;
import com.jd.edi.version5.service.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

//        Map<String, Object> serviceProvide = new HashMap<>();
//        serviceProvide.put("com.ganghuan.myRPCVersion2.service.UserService",userService);
//        serviceProvide.put("com.ganghuan.myRPCVersion2.service.BlogService",blogService);
        // String connectionAddress, String rootPath,String host, Integer port
        String connectionAddress = "";
        String rootPath = "";
        String host = "";
        Integer port = 9999;
        ServiceProvider serviceProvider = new ServiceProvider(connectionAddress, rootPath, host, port);
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        RPCServer RPCServer = new NettyRPCServer(serviceProvider);
        RPCServer.start(8899);
    }
}