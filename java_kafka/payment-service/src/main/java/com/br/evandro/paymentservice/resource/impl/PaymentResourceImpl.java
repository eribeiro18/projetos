package com.br.evandro.paymentservice.resource.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.evandro.paymentservice.model.Payment;
import com.br.evandro.paymentservice.resource.PaymentResource;
import com.br.evandro.paymentservice.service.PaymentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/payments")
@Tag(name = "Pagamentos", description = "API de integração de pagamentos")
public class PaymentResourceImpl implements PaymentResource{
	
	private final PaymentService paymentService;

	@Override
	public ResponseEntity<Payment> payment(Payment payment) {
		paymentService.sendPayment(payment);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
