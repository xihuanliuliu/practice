package com.jd.edi.version3.client;

import com.jd.edi.version3.common.RPCRequest;
import com.jd.edi.version3.common.RPCResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class NettyRPCClient implements RPCClient {

    private static final Bootstrap bootstrap;
    private static final EventLoopGroup eventLoopGroup;
    private String host;
    private Integer port;

    public NettyRPCClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    // netty客户端初始化，重复使用
    static {
        System.out.println("netty客户端初始化，重复使用");
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer());
    }

    /**
     * 这里需要操作一下，因为netty的传输都是异步的，你发送request，会立刻返回一个值， 而不是想要的相应的response
     */
    @Override
    public RPCResponse sendResponse(RPCRequest request) {
        try {
            ChannelFuture channelFuture  = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            // 发送数据
            System.out.println("NettyRPCClient sendResponse 发送数据");
            channel.writeAndFlush(request);
            channel.closeFuture().sync();
            // 阻塞的获得结果，通过给channel设计别名，获取特定名字下的channel中的内容（这个在hanlder中设置）
            // AttributeKey是，线程隔离的，不会由线程安全问题。
            // 实际上不应通过阻塞，可通过回调函数
            AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
            System.out.println("---");
            RPCResponse response = channel.attr(key).get();
            System.out.println("response: " + response.toString());
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return null;
    }
}
