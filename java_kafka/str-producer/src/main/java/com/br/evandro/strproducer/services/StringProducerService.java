package com.br.evandro.strproducer.services;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class StringProducerService {

	private final KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String message) {
		Integer part = message.contains("4") ? 0 : 1;;
		CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("str-topic", part, "", message);
		future.whenComplete((sucess, error) -> {
			if(error == null) {
				log.info("Send message with sucess {}", message);
				log.info("Partition {}, Offset {}", 
						sucess.getRecordMetadata().partition(),
						sucess.getRecordMetadata().offset());
			} else {
				log.error("Error send message {}", error);
			}
		});
	}
	
}
