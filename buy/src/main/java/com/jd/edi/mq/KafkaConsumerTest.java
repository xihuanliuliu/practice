package com.jd.edi.mq;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import sun.util.resources.cldr.el.TimeZoneNames_el;

import java.util.*;

public class KafkaConsumerTest {


    public static void consumerSync() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");

        //反序列化
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        //消费组---必须
        properties.put("group.id", "test");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        //订阅主题
        kafkaConsumer.subscribe(Arrays.asList("test2"));

        while (true){
            //拉去数据
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            //
            for (ConsumerRecord record : records){
                System.out.println("offset: " + record.offset() + ", key: " + record.key() + ", value: " + record.value());
            }
            //同步提交，当前线程会阻塞直到 offset提交成功
            kafkaConsumer.commitSync();
        }
    }

    public static void main(String[] args) {

        consumerOffset();
    }


    public static void consumerOffset() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");

        //反序列化
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        //消费组---必须
        properties.put("group.id", "test");

        // topic
        List<String> topic = new ArrayList<>();
        topic.add("topic");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(topic);

        while (true) {
            // 拉取数据
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(1000);
            Iterator<ConsumerRecord<String, String>> recordIterator = consumerRecords.iterator();
            while (recordIterator.hasNext()) {
                ConsumerRecord<String, String> record = recordIterator.next();
                System.out.println("-------------------------------");
                System.out.println("topic: " + record.topic() + ", partition: " + record.partition() + ", offset: " + record.offset());
                System.out.println("value: " + record.value());

            }
            kafkaConsumer.commitAsync(new OffsetCommitCallback() {
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                    if (e != null) {
                        System.out.println("------------消费异常----------");
                    }
                }
            });
        }
    }




}
