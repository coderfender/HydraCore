package com.hydra.restservices.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hydra.restservices.models.cex.io.CurrencyObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.bson.Document;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Properties;

public class HydraConsumer {
    KafkaConsumer<String, String> consumer;
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> collection;
    Type type;

    public HydraConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.97:9093");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put("group.id", "test");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumer = new KafkaConsumer<String, String>(props);
        mongoClient = new MongoClient(new MongoClientURI("mongodb://root:root@ds115569.mlab.com:15569/hydra"));
        getCoinsFromAnExchange("cex.io");
    }

    public void getCoinsFromAnExchange(String exchangeName) {
        mongoDatabase = mongoClient.getDatabase("hydra");
        collection = mongoDatabase.getCollection("exchange");
        TopicPartition topicPartition = new TopicPartition(exchangeName, 0);
        consumer.assign(Collections.singleton(topicPartition));
        while (true) {
            try {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(5000);
                System.out.println("Number of records are" + consumerRecords.count());
                for (ConsumerRecord<String, String> record : consumerRecords) {
                    System.out.println("Got the vale " + record.value() + "  and partition being " + record.key());
                    CurrencyObject dataObject = new Gson().fromJson(record.value(), CurrencyObject.class);
                    dataObject.setExchangeName(exchangeName);
                    dataObject.setType(record.key());
                    System.out.println("writing this into \t" + dataObject.toString());
                    collection.insertOne(dataObject.getDocument());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
