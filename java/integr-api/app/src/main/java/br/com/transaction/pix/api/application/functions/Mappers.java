package br.com.transaction.pix.api.application.functions;

import br.com.transaction.pix.api.application.mapper.AccountPaymentMapper;
import br.com.transaction.pix.api.application.mapper.AccountPaymentMapperImpl;

public interface Mappers {

	AccountPaymentMapper ACCOUNT_PAYMENT_MAPPER = new AccountPaymentMapperImpl();
	
}
