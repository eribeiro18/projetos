package br.com.transaction.pix.api.application.functions;

import java.util.List;
import java.util.function.Function;

import br.com.transaction.pix.api.application.dto.PixDto;
import br.com.transaction.pix.api.infraestructure.entity.Pix;

public interface AccPaySerialize {

	Function<Pix, PixDto> accountPaymentSerialize = accountPayment -> Mappers.ACCOUNT_PAYMENT_MAPPER.toDto(accountPayment);
	Function<List<Pix>, List<PixDto>> accountPaymentSerializeList = accountPayment -> Mappers.ACCOUNT_PAYMENT_MAPPER.toDtoList(accountPayment);
}
