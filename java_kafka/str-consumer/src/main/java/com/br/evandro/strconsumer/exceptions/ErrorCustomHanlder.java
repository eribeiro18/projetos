package com.br.evandro.strconsumer.exceptions;

import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ErrorCustomHanlder implements KafkaListenerErrorHandler{

	@Override
	public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
		log.info("EXCEPTION_HANDLER ::: Capturado um erro no consumidor");
		log.info("Payload ::: {}", message.getPayload());
		log.info("Headers ::: {}", message.getHeaders());
		log.info("Offset ::: {}", message.getHeaders().get("kafka_offset"));
		log.info("Message exception ::: {} {}", exception.getLocalizedMessage(), exception.getCause().getMessage());
		return null;
	}

}
