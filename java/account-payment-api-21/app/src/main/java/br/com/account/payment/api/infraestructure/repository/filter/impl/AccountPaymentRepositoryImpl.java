package br.com.account.payment.api.infraestructure.repository.filter.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import br.com.account.payment.api.infraestructure.entity.AccountPayment;
import br.com.account.payment.api.infraestructure.entity.AccountPayment_;
import br.com.account.payment.api.infraestructure.repository.filter.api.AccountPaymentRepositoryFilter;
import br.com.account.payment.api.infraestructure.repository.filter.data.AccountPaymentFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class AccountPaymentRepositoryImpl implements AccountPaymentRepositoryFilter {
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public Specification<AccountPayment> buildFilters(AccountPaymentFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            addExpirationDate(predicates, root, criteriaBuilder, filter);
            addPaymentDate(predicates, root, criteriaBuilder, filter);
            addPaymentAmount(predicates, root, criteriaBuilder, filter);
            addDescription(predicates, root, criteriaBuilder, filter);
            addSituation(predicates, root, criteriaBuilder, filter);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
	}
	
    private void addExpirationDate(List<Predicate> predicates, Root<AccountPayment> root, CriteriaBuilder criteriaBuilder, AccountPaymentFilter filter) {
        if (filter != null && filter.getExpirationDateStart() != null && !filter.getExpirationDateStart().isBlank()) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(AccountPayment_.EXPIRATION_DATE), LocalDate.parse(filter.getExpirationDateStart(), FORMATTER)));
        }
        if (filter != null && filter.getExpirationDateEnd() != null && !filter.getExpirationDateEnd().isBlank()) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(AccountPayment_.EXPIRATION_DATE), LocalDate.parse(filter.getExpirationDateEnd(), FORMATTER)));
        }
    }
    
    private void addPaymentDate(List<Predicate> predicates, Root<AccountPayment> root, CriteriaBuilder criteriaBuilder, AccountPaymentFilter filter) {
        if (filter != null && filter.getPaymentDateStart() != null && !filter.getPaymentDateStart().isBlank()) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(AccountPayment_.PAYMENT_DATE), LocalDate.parse(filter.getPaymentDateStart(), FORMATTER)));
        }
        if (filter != null && filter.getPaymentDateEnd() != null && !filter.getPaymentDateEnd().isBlank()) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(AccountPayment_.PAYMENT_DATE), LocalDate.parse(filter.getPaymentDateEnd(), FORMATTER)));
        }
    }
    
    private void addPaymentAmount(List<Predicate> predicates, Root<AccountPayment> root, CriteriaBuilder criteriaBuilder, AccountPaymentFilter filter) {
        if (filter != null && filter.getPaymentAmountStart() != null && !filter.getPaymentAmountStart().isBlank()) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(AccountPayment_.PAYMENT_AMOUNT), new BigDecimal(filter.getPaymentAmountStart())));
        }
        if (filter != null && filter.getPaymentAmountEnd() != null && !filter.getPaymentAmountEnd().isBlank()) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(AccountPayment_.PAYMENT_AMOUNT), new BigDecimal(filter.getPaymentAmountEnd())));
        }
    }    

    private void addDescription(List<Predicate> predicates, Root<AccountPayment> root, CriteriaBuilder criteriaBuilder, AccountPaymentFilter filter) {
        if (filter != null && filter.getDescription() != null && !filter.getDescription().isBlank()) {
            predicates.add(criteriaBuilder.like(root.get(AccountPayment_.DESCRIPTION), "%" + filter.getDescription() + "%"));
        }
    }
    
    private void addSituation(List<Predicate> predicates, Root<AccountPayment> root, CriteriaBuilder criteriaBuilder, AccountPaymentFilter filter) {
        if (filter != null && filter.getSituation() != null && !filter.getSituation().isBlank()) {
            predicates.add(criteriaBuilder.equal(root.get(AccountPayment_.SITUATION), AccountPayment.Status.valueOf(filter.getSituation())));
        }
    }
    
}