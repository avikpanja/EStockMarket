package com.estock.company.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.estock.company.constant.Constants;

@Service
public class KafkaConsumer {

	private String consumedMsg;
	
	@KafkaListener(topics = Constants.TOPIC_TOKEN, groupId = "mygroup")
	public void consumeTokenValidationTopic(String message) {
		System.out.println("consumed-message: "+ message);
		this.consumedMsg = message;
	}
	
	public String getConsumedMsg() { return this.consumedMsg; }
}
