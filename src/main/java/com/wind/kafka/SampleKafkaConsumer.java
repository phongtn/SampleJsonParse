package com.wind.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

/**
 * @author PhongTn
 * @since 1.0 at 8/3/17 12:11 AM
 */
public class SampleKafkaConsumer {

    private static Logger logger = LoggerFactory.getLogger(SampleKafkaConsumer.class);

    final String TOPIC = "game-metric";

    final String BOOTSTRAP_SERVER = "118.70.15.62:9092 ";

    public static void main(String[] args) {
        SampleKafkaConsumer kafkaConsumer = new SampleKafkaConsumer();
        kafkaConsumer.runConsumer();
    }

    private void runConsumer() {
        Consumer<Long, String> consumer = this.createConsumer();
        int giveUp = 1000;
        int noRecordsCount = 0;

        while (true) {
            ConsumerRecords<Long, String> consumerRecord = consumer.poll(1000);
            if (consumerRecord.count() == 0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecord.forEach(record -> {
//                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
//                        record.key(), record.value(),
//                        record.partition(), record.offset());

                logger.info("{}", record);
            });

            consumer.commitAsync();
        }

        consumer.close();
        logger.info("DONE");
    }


    private Consumer<Long, String> createConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "sample");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());

        final Consumer<Long, String> consumer = new KafkaConsumer<Long, String>(props);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }
}
