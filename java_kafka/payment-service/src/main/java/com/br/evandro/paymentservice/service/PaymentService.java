package com.br.evandro.paymentservice.service;

import com.br.evandro.paymentservice.model.Payment;

public interface PaymentService {

	void sendPayment(Payment payment);
}
