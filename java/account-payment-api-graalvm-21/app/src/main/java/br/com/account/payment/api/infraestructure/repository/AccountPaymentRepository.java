package br.com.account.payment.api.infraestructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.account.payment.api.infraestructure.entity.AccountPayment;
import br.com.account.payment.api.infraestructure.repository.filter.api.AccountPaymentRepositoryFilter;

@Repository
public interface AccountPaymentRepository extends CrudRepository<AccountPayment, Long>, AccountPaymentRepositoryFilter {

    Page<AccountPayment> findAll(Pageable pageable);
    Page<AccountPayment> findAll(Specification<AccountPayment> spec, Pageable pageable);

}
