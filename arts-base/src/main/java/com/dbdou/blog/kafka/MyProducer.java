package com.dbdou.blog.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * <h1>Kafka Producer</h1>
 * 启动 zookeeper: zookeeper-server-start zookeeper.properties &
 * 启动 kafka: kafka-server-start server.properties &
 * 创建 topic: kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 2 --topic kafka-study-m
 * kafka producer: kafka-console-producer --broker-list localhost:9092 --topic kafka-study-m
 * kafka consumer: kafka-console-consumer --bootstrap-server localhost:9092 --topic kafka-study-m --from-beginning
 * <p>
 * Created by dentalulcer
 */
@Slf4j
public class MyProducer {

    private static KafkaProducer<String, String> producer;

    static {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("partitioner.class", "com.dbdou.blog.kafka.MyPartitioner");

        producer = new KafkaProducer<>(properties);
    }

    /**
     * <h2>producer 发送消息不考虑翻回信息</h2>
     */
    private static void sendMsgForgetResult() {

        ProducerRecord<String, String> record = new ProducerRecord<>(
                "kafka-study-m", "name", "ForgetResult");
        producer.send(record);
        producer.close();
    }

    /**
     * <h2>producer 发送消息同步等待返回信息</h2>
     *
     * @throws Exception
     */
    private static void sendMsgSync() throws Exception {

        ProducerRecord<String, String> record = new ProducerRecord<>(
                "kafka-study-m", "name", "sync");
        Future<RecordMetadata> sendFuture = producer.send(record);
        RecordMetadata recordMetadata = sendFuture.get();

        System.out.println(recordMetadata.topic());
        System.out.println(recordMetadata.partition());
        System.out.println(recordMetadata.offset());

        producer.close();
    }

    /**
     * <h2>producer 发送消息异步回调翻回信息</h2>
     *
     * @throws Exception
     */
    private static void sendMsgCallback() throws Exception {

        ProducerRecord<String, String> record = new ProducerRecord<>(
                "kafka-study-m", "name", "callback");
        producer.send(record, new MyProducerCallback());
        producer.close();
    }

    private static class MyProducerCallback implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {
                e.printStackTrace();
                return;
            }

            System.out.println(recordMetadata.topic());
            System.out.println(recordMetadata.partition());
            System.out.println(recordMetadata.offset());
            System.out.println("comming in MyProducerCallback");
        }
    }

    public static void main(String[] args) throws Exception {

//        sendMsgForgetResult();
//        sendMsgSync();
        sendMsgCallback();

    }

}
