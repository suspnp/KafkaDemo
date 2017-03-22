package com.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by lianhai on 2017/3/20.
 */
public class KafkaProducer {

    private final Producer<String, String> producer;
    public final static String TOPIC = "TEST-TOPIC";


    private KafkaProducer() {
        Properties props = new Properties();
        //此处配置的是kafka的端口
        props.put("metadata.broker.list", "60.205.231.97:9091");
        //配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        producer = new Producer<String, String>(new ProducerConfig(props));
    }

    void produce() {
        int messageNo = 15000;
        final int COUNT = 20000;
        long startTime = System.currentTimeMillis();
        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = "hello kafka message " + key;
            producer.send(new KeyedMessage<String, String>(TOPIC, key, data));
            System.out.println(data);
            messageNo++;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }

    public static void main(String[] args) {
        KafkaProducer producer = new KafkaProducer();
        producer.produce();
    }

}
