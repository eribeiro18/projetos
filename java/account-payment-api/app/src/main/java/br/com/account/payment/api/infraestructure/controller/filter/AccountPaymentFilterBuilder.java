package br.com.account.payment.api.infraestructure.controller.filter;

import br.com.account.payment.api.infraestructure.repository.filter.data.AccountPaymentFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountPaymentFilterBuilder {

    public static AccountPaymentFilter create(HttpServletRequest request) {
        return AccountPaymentFilter.builder()
                .expirationDateStart(request.getParameter("expirationDateStart"))
                .expirationDateEnd(request.getParameter("expirationDateEnd"))
                .paymentDateStart(request.getParameter("paymentDateStart"))
                .paymentDateEnd(request.getParameter("paymentDateEnd"))
                .paymentAmountStart(request.getParameter("paymentAmountStart"))
                .paymentAmountEnd(request.getParameter("paymentAmountEnd"))
                .description(request.getParameter("description"))
                .situation(request.getParameter("situation"))
                .build();
    }
}