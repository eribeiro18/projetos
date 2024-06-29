package br.com.account.payment.api.infraestructure.controller.commons;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.account.payment.api.application.dto.Response;
import br.com.account.payment.api.application.dto.UserJwtDto;
import br.com.account.payment.api.application.service.UserService;
import br.com.account.payment.api.exception.UnauthorizedException;
import br.com.account.payment.api.infraestructure.entity.User;
import jakarta.validation.ValidationException;
import lombok.SneakyThrows;

@Component
public class CommonsController {
	
	private final UserService userService;
	
	public CommonsController(UserService userService) {
		this.userService = userService;
	}

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
	
	public void validateAuthor(String authorization) throws UnauthorizedException {
		try {
			UserJwtDto userJwt = JsonUserProvider.getContextDto(authorization);
			User user = this.userService.findByUsername(userJwt.getUsername());
			if(user == null) {
				throw new UnauthorizedException("Token invalido!");
			}
		} catch (Exception e) {
			throw new ValidationException("Falha na validação do token! Token invalido ou mal formatado!", e);
		}
	}
}
