package br.com.account.payment.api.application.functions;

import java.util.function.Function;

import br.com.account.payment.api.application.dto.AccountPaymentDto;
import br.com.account.payment.api.infraestructure.entity.AccountPayment;

public interface AccPayDeserialize {

	Function<AccountPaymentDto, AccountPayment> accountPaymentDeserialize = accountPaymentDto -> Mappers.ACCOUNT_PAYMENT_MAPPER.toEntity(accountPaymentDto);
}
