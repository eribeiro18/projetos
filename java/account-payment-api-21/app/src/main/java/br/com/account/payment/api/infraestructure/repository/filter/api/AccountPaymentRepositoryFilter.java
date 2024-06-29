package br.com.account.payment.api.infraestructure.repository.filter.api;

import org.springframework.data.jpa.domain.Specification;

import br.com.account.payment.api.infraestructure.entity.AccountPayment;
import br.com.account.payment.api.infraestructure.repository.filter.data.AccountPaymentFilter;

public interface AccountPaymentRepositoryFilter {

	Specification<AccountPayment> buildFilters(AccountPaymentFilter categoryFilter);
}
