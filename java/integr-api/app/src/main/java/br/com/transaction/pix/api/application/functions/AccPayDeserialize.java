package br.com.transaction.pix.api.application.functions;

import java.util.function.Function;

import br.com.transaction.pix.api.application.dto.PixDto;
import br.com.transaction.pix.api.infraestructure.entity.Pix;

public interface AccPayDeserialize {

	Function<PixDto, Pix> accountPaymentDeserialize = accountPaymentDto -> Mappers.ACCOUNT_PAYMENT_MAPPER.toEntity(accountPaymentDto);
}
