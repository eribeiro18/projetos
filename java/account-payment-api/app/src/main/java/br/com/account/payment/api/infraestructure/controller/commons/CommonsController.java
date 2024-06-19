package br.com.account.payment.api.infraestructure.controller.commons;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.account.payment.api.application.dto.Response;
import lombok.SneakyThrows;

@Component
public class CommonsController {

	@SneakyThrows
	public ResponseEntity<Response> buildResponse(HttpStatus status, Optional<?> response) {
		final Object o = response.orElseThrow(ClassNotFoundException::new);
        final List<?> respList = o instanceof Collection ? (List<?>) o : Collections.singletonList(o);
        return new ResponseEntity<>(Response.builder()
							                .response(respList)
							                .build(), status);
	}

	@SneakyThrows
	public ResponseEntity<Response> buildResponse(HttpStatus status) {
		return new ResponseEntity<>(Response.builder()
											.build(), status);
	}
	
	@SneakyThrows
	public ResponseEntity<Response> buildResponse(HttpStatus status, Optional<?> response, Integer totalPages,
												  Long totalRecords) {
		final Object o = response.orElseThrow(ClassNotFoundException::new);
        final List<?> respList = o instanceof Collection ? (List<?>) o : Collections.singletonList(o);
        
        return new ResponseEntity<>(Response.builder()
							                .response(respList)
							                .totalPages(totalPages)
							                .totalRecords(totalRecords)
							                .build(), status);
	}
}
