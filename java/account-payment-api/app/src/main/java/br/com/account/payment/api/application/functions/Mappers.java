package br.com.account.payment.api.application.functions;

import br.com.account.payment.api.application.mapper.AccountPaymentMapper;
import br.com.account.payment.api.application.mapper.AccountPaymentMapperImpl;

public interface Mappers {

	AccountPaymentMapper ACCOUNT_PAYMENT_MAPPER = new AccountPaymentMapperImpl();
	
}