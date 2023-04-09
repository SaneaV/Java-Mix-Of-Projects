package com.example;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class KafkaAvroProducerV1 {

    public static void main(String[] args) {
        String bootstrapServers = "127.0.0.1:9092";

        Properties properties = new Properties();
        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        /*
        Note, this application uses Confluent Kafka. To get familiar with Avro and Confluent Kafka,
        you can use MockSchemaRegistry.java, an example of usage is in the next line of code.

        What are the steps to follow on a Linux system:
        1. Install Confluent Platform Community
        2. Install Confluent CLI
        3. Run the following script: "confluent local start"
        4. Assign "schema.registry.url" key to the following value "127.0.0.1:8081"

        On Windows:
        1. Run docker-compose up -d
        2. Assign "schema.registry.url" key to the following value "127.0.0.1:8081"
         */
        properties.setProperty("schema.registry.url", "http://127.0.0.1:8081");

        final KafkaProducer<String, Customer> kafkaProducer = new KafkaProducer<>(properties);
        final String topic = "customer-avro";

        Customer customer = Customer.newBuilder()
                .setAge(34)
                .setAutomatedEmail(false)
                .setFirstName("John")
                .setLastName("Doe")
                .setHeight(178f)
                .setWeight(75f)
                .build();

        ProducerRecord<String, Customer> producerRecord = new ProducerRecord<>(topic, customer);

        System.out.println(customer);
        kafkaProducer.send(producerRecord, (metadata, exception) -> {
            if (exception == null) {
                System.out.println(metadata);
            } else {
                exception.printStackTrace();
            }
        });

        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
