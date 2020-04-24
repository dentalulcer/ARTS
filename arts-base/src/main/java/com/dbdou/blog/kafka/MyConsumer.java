package com.dbdou.blog.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * <h1>Kafka Consumer</h1>
 *
 * Created by dentalulcer
 */
public class MyConsumer {

    private static Properties properties;

    static {
        properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("group.id", "my-group");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    /**
     * <h2>consumer 自动提交消息位移</h2>
     */
    private static void generalConsumerMsgAutoCommit() {

        properties.put("enable.auto.commit", true);

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Collections.singletonList("my-two-partitions-topic"));
            while (true) {
                boolean flag = true;
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(String.format("topic = %s, partition = %s, offset = %d, key = %s, value = %s\n",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value()));

                    if ("done".equals(record.value())) {
                        flag = false;
                    }
                }

                if (!flag) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {

        generalConsumerMsgAutoCommit();

    }

}
