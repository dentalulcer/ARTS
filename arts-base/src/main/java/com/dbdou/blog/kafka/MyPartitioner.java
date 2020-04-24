package com.dbdou.blog.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

/**
 * <h1>自定义分区分配器</h1>
 * Created by dentalulcer
 */
public class MyPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int size = partitionInfos.size();
        System.out.println("current topic is: " + topic + ", partition count: " + size);

        if (null == keyBytes || !(key instanceof String)) {
            throw new InvalidRecordException("kafka message must have key!");
        }

        if (size == 1) {
            return 0;
        }

        if ("my-own-key".equals(key)) {
            return size - 1;
        }

        return Math.abs(Utils.murmur2(keyBytes)) % size;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }

}
