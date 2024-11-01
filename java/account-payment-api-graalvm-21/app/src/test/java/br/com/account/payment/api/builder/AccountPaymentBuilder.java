package br.com.account.payment.api.builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.account.payment.api.application.dto.AccountPaymentDto;
import br.com.account.payment.api.infraestructure.entity.AccountPayment;

public class AccountPaymentBuilder {
	
    public static AccountPaymentDto buildDto() {
        return AccountPaymentDto.builder()
                                .expirationDate(LocalDate.now())
                                .paymentDate(LocalDate.now())
                                .paymentAmount(new BigDecimal("15.90"))
                                .description("Descrição teste")
                                .situation(AccountPayment.Status.AWAITING_PAYMENT)
                          .build();
    }

    public static AccountPayment build() {
        return AccountPayment.builder()
        					 .id(1L)
			                 .expirationDate(LocalDate.now())
			                 .paymentDate(LocalDate.now())
			                 .paymentAmount(new BigDecimal("15.90"))
			                 .description("Descrição teste")
			                 .situation(AccountPayment.Status.AWAITING_PAYMENT)
                        .build();
    }
}
