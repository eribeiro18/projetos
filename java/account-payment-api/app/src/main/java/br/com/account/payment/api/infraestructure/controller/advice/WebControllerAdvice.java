package br.com.account.payment.api.infraestructure.controller.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.account.payment.api.application.dto.ErrorDto;
import br.com.account.payment.api.application.dto.Response;
import br.com.account.payment.api.exception.ValidationException;

@RestControllerAdvice
public class WebControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Response> handleValidationException(ValidationException ex) {
		ErrorDto error = ErrorDto.builder().error(ex.getMessage())
						   .build();
		return new ResponseEntity<>(Response.builder()
											.errors(List.of(error))
											.build(), HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	  List<ErrorDto> errors = this.createListErrors(ex.getBindingResult());
	  return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	private List<ErrorDto> createListErrors(BindingResult bindingResult){
		List<ErrorDto> erros = new ArrayList<>();
		bindingResult.getFieldErrors().forEach(fieldError->{
			String messageInterface = fieldError.getDefaultMessage();
			erros.add(new ErrorDto(messageInterface));
		});
		return erros;
	}
}