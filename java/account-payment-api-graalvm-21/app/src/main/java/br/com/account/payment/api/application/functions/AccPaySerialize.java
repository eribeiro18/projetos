package br.com.account.payment.api.application.functions;

import java.util.List;
import java.util.function.Function;

import br.com.account.payment.api.application.dto.AccountPaymentDto;
import br.com.account.payment.api.infraestructure.entity.AccountPayment;

public interface AccPaySerialize {

	Function<AccountPayment, AccountPaymentDto> accountPaymentSerialize = accountPayment -> Mappers.ACCOUNT_PAYMENT_MAPPER.toDto(accountPayment);
	Function<List<AccountPayment>, List<AccountPaymentDto>> accountPaymentSerializeList = accountPayment -> Mappers.ACCOUNT_PAYMENT_MAPPER.toDtoList(accountPayment);	
}
