package org.hongxi.boot4.kafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer implements CommandLineRunner {

	private final KafkaTemplate<Object, SampleMessage> kafkaTemplate;

	Producer(KafkaTemplate<Object, SampleMessage> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		SampleMessage message = new SampleMessage(1, "test");
		this.kafkaTemplate.send("testTopic", message);
		System.out.println("Sent sample message [" + message + "]");
	}
}