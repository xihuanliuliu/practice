package com.jd.edi.version5.codec;

/**
 * 原来传输是用的java自带的，现在使用自定义的
 */
public interface Serializer {
    // 把对象序列化成字节数组
    byte[] serialize(Object obj);
    // 把字节数组转为对象，
    // 从其他方式需要指定消息格式，再根据message转化为相应的对象
    Object deserialize(byte[] bytes, int messageType);

    // 返回使用的序列化器号码
    // 0:java自带的序列化。 1：json序列化
    int getType();

    // 根据序列化器号码取出序列化器，如果需要添加序列化器，就实现该接口
    static Serializer getSerializeByCode(int typeCode) {
        switch (typeCode) {
            case 0:
                return new ObjectSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }

}
