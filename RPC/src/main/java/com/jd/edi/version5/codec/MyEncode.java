package com.jd.edi.version5.codec;

import com.jd.edi.version5.common.RPCRequest;
import com.jd.edi.version5.common.RPCResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

/**
 * 依次按照自定义的消息格式写入，传入的数据为request或者response
 * 需要持有一个serialize器，负责将传入的对象序列化成字节数组
 */
@AllArgsConstructor
public class MyEncode extends MessageToByteEncoder {
    private Serializer serializer;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object obj, ByteBuf byteBuf) throws Exception {
        System.out.println("MyEncode encode");
        // 写入消息类型
        if(obj instanceof RPCRequest){
            byteBuf.writeShort(MessageType.REQUEST.getCode());
        }
        else if(obj instanceof RPCResponse){
            byteBuf.writeShort(MessageType.RESPONSE.getCode());
        }
        // 写入序列化方式
        byteBuf.writeShort(serializer.getType());
        // 得到序列化数组
        byte[] serialize = serializer.serialize(obj);
        // 写入长度
        byteBuf.writeInt(serialize.length);
        // 写入序列化字节数组
        byteBuf.writeBytes(serialize);
    }
}
