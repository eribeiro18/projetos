package com.br.evandro.strconsumer.listerners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.br.evandro.strconsumer.custom.StrConsumerCustomListener;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class strConsumerListener {

	//containerFactory(contido dentro da anotation abaixo) 
	//deve ser o mesmo nome do metodo existente no configurado StringConsumerConfig.java
	@SneakyThrows //lançar excessão
	@StrConsumerCustomListener(groupId = "group-1")
	public void create(String message) {
		log.info("CREATE ::: Receive message {}", message);
		//fazendo o tratamento com throw igual abaixo a mensagem cairá no topic e ficará tentando processar por varias vezes só que sem exito
		//forma ideal é criar um handle igual ao implementado na classe ErrorCustomHandler é incluir na anotação do consumidor o nome da classe implementada com a letra incial minuscula
		throw new IllegalArgumentException("EXCEPTION .... "); 
	}
	
	//esta anotação é customizada para deixar o codigo mais limpo, dentro dele tem as especificações do consumidor
	@StrConsumerCustomListener(groupId = "group-1")
	public void log(String message) {
		log.info("LOG ::: Receive message {}", message);
	}

	//@StrConsumerCustomListener(groupId = "group-2")
	@KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory", errorHandler = "errorCustomHanlder")
	public void history(String message) {
		log.info("HISTORY ::: Receive message {}", message);
	}
}
