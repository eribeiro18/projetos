package com.br.evandro.paymentservice.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.br.evandro.paymentservice.model.Payment;

public interface PaymentResource {

	@Operation(summary = "API de envio", description = "API de envio de pagamentos ", tags = {
			"Pagamentos" }, responses = { @ApiResponse(description = "Sucesso", responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Payment.class))) }) })
	@PostMapping
	ResponseEntity<Payment> payment(@RequestBody Payment payment);

}
