package br.com.account.payment.api.infraestructure.repository.filter.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountPaymentFilter {

    private String expirationDateStart;
    private String expirationDateEnd;
    private String paymentDateStart;
    private String paymentDateEnd;
    private String paymentAmountStart;
    private String paymentAmountEnd;
    private String description;
    private String situation;

}
