package br.com.account.payment.api.infraestructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.account.payment.api.application.dto.DemoKongDto;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping(path = "/v1/teste-kong")
@Slf4j
public class DemoKongController {

	private final ObjectMapper mapperJson = new ObjectMapper(); 
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody DemoKongDto request) throws JsonProcessingException {
		log.info("Model receive DemoKongDto => " + mapperJson.writeValueAsString(request));
		return ResponseEntity.ok().build();
	}
}
