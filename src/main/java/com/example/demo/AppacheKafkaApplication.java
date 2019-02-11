package com.example.demo;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AppacheKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppacheKafkaApplication.class, args);
	}
	
	@Autowired
	private KafkaTemplate<String, Student> template;

	@GetMapping("/produce")
	public String produceKafka() {
		Student st = new Student();
		st.setId(1111);
		st.setName("Anil111");
		template.send("topic_example1", st);
		return "Message produced to kafka";
	}
	
	@KafkaListener(topics="topic_example1", group="group-id")
	public void kafkaListener(Student message) {
		System.out.println("Message received " +message.getId()+" "+message.getName());
	}

}
 class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

