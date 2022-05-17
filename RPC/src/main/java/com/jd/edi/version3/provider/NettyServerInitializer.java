package com.jd.edi.version3.provider;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * netty server初始化类
 * 初始化，主要负责序列化的编码解码， 需要解决netty的粘包问题
 *
 * 1 通过FixedLengthFrameDecoder 定长解码器来解决定长消息的黏包问题；
 * 2 通过LineBasedFrameDecoder和StringDecoder来解决以回车换行符作为消息结束符的TCP黏包的问题；
 * 3 通过DelimiterBasedFrameDecoder 特殊分隔符解码器来解决以特殊符号作为消息结束符的TCP黏包问题；
 * 4 通过LengthFieldBasedFrameDecoder 自定义长度解码器解决TCP黏包问题。
 *
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private ServiceProvider serviceProvider;

    public NettyServerInitializer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("NettyServerInitializer initChannel socketChannel");
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 消息格式 [长度][消息体]，解决沾包的问题
        // addLast就是在处理器链的最后添加一个channelHandler

        // LengthFieldBasedFrameDecoder它是基于自定义长度的解码器.
        // lengthFieldOffset=0,即长度的偏移字节为零。放在首字节。
        //lengthFieldLength=4，长度4个字节。
        //lengthAdjustment=14，即修正字节是14，也就是包头长度18-4(4个包头长度)，body长度本身不包含包头长度，所以用来计算实际帧长度的左右。
        //initialBytesToStrip=18，即跳过包头18个字节。直接读取body.如果要验证包头的参数。则不能跳过
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));

        // 计算当前待发送消息的长度，写入到前四个字节中
        // byteOrder：ByteOrder定义了写入buffer时字节的顺序
        //lengthFieldLength：前置长度字段的长度。 仅允许1,2,3,4和8
        //lengthAdjustment：要添加到长度字段的值的补偿值
        //lengthIncludesLengthFieldLength：为true时，length字段的值=length字段的长度+Content的长度。为false时，length字段的值=Content的长度。
        // 通过LengthFieldPrepender可以将待发送消息的长度写入到ByteBuf的前2个字节，编码后的消息组成为长度字段Length+原消息Content的方式。Length值为12，不包括Length自身
        pipeline.addLast(new LengthFieldPrepender(4));

        // 这里使用的还是java 序列化方式， netty的自带的解码编码支持传输这种结构
        pipeline.addLast(new ObjectEncoder());
        pipeline.addLast(new ObjectDecoder(new ClassResolver() {
            @Override
            public Class<?> resolve(String className) throws ClassNotFoundException {
                return Class.forName(className);
            }
        }));

        pipeline.addLast(new NettyRPCServerHandler(serviceProvider));
    }
}
