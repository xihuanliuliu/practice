package com.jd.edi.mq;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaTest {

    public static void main(String[] args) throws Exception {
        product();
    }

    public static void product( ) throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");//多个逗号隔开
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        //ProducerConfig.RETRIES_CONFIG重试次数
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>
                (props);
        for (int i = 0; i < 1; i++) {
            Future<RecordMetadata> test = kafkaProducer.send(
                    new ProducerRecord<String, String>("test", "first" + i));
            System.out.println(test.get());
            System.out.println("send "+i+" ok");
        }
        kafkaProducer.close();
    }


    public static void produce_sync() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //KafkaProducer类发送数据，kafka Producer是线程安全的，可以在多个线程之间共享生产者实例
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        // -- 同步发送消息
        for (int i = 1; i <= 600; i++) {
            //参数1：topic名, 参数2：消息文本； ProducerRecord多个重载的构造方法
            kafkaProducer.send(new ProducerRecord<String, String>("test20200519", "message"+i));
            System.out.println("message"+i);
        }

        //或者
        ProducerRecord<String, String> syncRecord = new ProducerRecord<>("test20200519", "Kafka_Products", "测试"); //Topic Key Value
        try{
            Future future = kafkaProducer.send(syncRecord);
            future.get(); //不关心是否发送成功，则不需要这行。
        } catch(Exception e) {
            e.printStackTrace();//连接错误、No Leader错误都可以通过重试解决；消息太大这类错误kafkaProducer不会进行任何重试，直接抛出异常
        }

        kafkaProducer.close();
    }



    public static void produce_async() throws InterruptedException {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //KafkaProducer类发送数据，kafka Producer是线程安全的，可以在多个线程之间共享生产者实例
        org.apache.kafka.clients.producer.KafkaProducer<String, String> kafkaProducer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(properties);


        // -- 异步发送消息
        ProducerRecord<String, String> asyncRecord = new ProducerRecord<String, String>("test20200519", "Kafka_Products","测试--1");//Topic Key Value

        //发送消息时，传递一个回调对象，该回调对象必须实现org.apache.kafka.clients.producer.Callback接口
        kafkaProducer.send(asyncRecord, new DemoProducerCallback());
        kafkaProducer.close();
    }

    static class DemoProducerCallback implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {//如果Kafka返回一个错误，onCompletion方法抛出一个non null异常。
                e.printStackTrace();//对异常进行一些处理，这里只是简单打印出来
            } else {

            }
        }
    }
}
