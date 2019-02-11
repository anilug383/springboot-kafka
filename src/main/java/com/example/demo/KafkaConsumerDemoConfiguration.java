package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;



@Configuration
@EnableKafka
public class KafkaConsumerDemoConfiguration {
	
	@Bean
	public ConsumerFactory<String, Student> consumerFactory(){
		Map<String, Object> consumerPropertuConfig = new HashMap<>();
		consumerPropertuConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		consumerPropertuConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
		consumerPropertuConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerPropertuConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(consumerPropertuConfig, new StringDeserializer(), new JsonDeserializer<>(Student.class));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Student> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Student> kafkaFactory = new ConcurrentKafkaListenerContainerFactory<>();
		kafkaFactory.setConsumerFactory(consumerFactory());
		return kafkaFactory;
	}
	

}
