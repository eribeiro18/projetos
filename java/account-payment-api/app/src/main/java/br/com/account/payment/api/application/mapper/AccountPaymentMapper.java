package br.com.account.payment.api.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.account.payment.api.application.dto.AccountPaymentDto;
import br.com.account.payment.api.infraestructure.entity.AccountPayment;

@Mapper()
public interface AccountPaymentMapper {

    AccountPayment toEntity(AccountPaymentDto accountPaymentDto);
    AccountPaymentDto toDto(AccountPayment accountPayment);
    List<AccountPaymentDto> toDtoList(List<AccountPayment> accountPaymentDtoList);

}
