package br.com.account.payment.api.application.service;

import static br.com.account.payment.api.application.functions.AccPayDeserialize.accountPaymentDeserialize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.account.payment.api.application.dto.AccountPaymentDto;
import br.com.account.payment.api.infraestructure.entity.AccountPayment;
import br.com.account.payment.api.infraestructure.repository.AccountPaymentRepository;
import br.com.account.payment.api.infraestructure.repository.filter.data.AccountPaymentFilter;
import jakarta.validation.ValidationException;

@Service
public class AccountPaymentService {

	private final AccountPaymentRepository accountPaymentRepository;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public AccountPaymentService(AccountPaymentRepository accountPaymentRepository) {
        this.accountPaymentRepository = accountPaymentRepository;
    }

    public AccountPayment save(AccountPaymentDto accountPaymentDto) {
    	AccountPayment accountPayment = accountPaymentDeserialize.apply(accountPaymentDto);
        return accountPaymentRepository.save(accountPayment);
    }

    public AccountPayment update(AccountPaymentDto accountPaymentDto) {
    	accountPaymentDto.validateUpdate();

    	AccountPayment accountPayment = findBy(accountPaymentDto.getId())
                            .orElseThrow(() -> new ValidationException("Contas a Pagar não encontrado"));
    	
    	accountPayment.setDescription(Optional.ofNullable(accountPaymentDto.getDescription()).orElse(accountPayment.getDescription()));
    	accountPayment.setExpirationDate(Optional.ofNullable(accountPaymentDto.getExpirationDate()).orElse(accountPayment.getExpirationDate()));
    	accountPayment.setPaymentAmount(Optional.ofNullable(accountPaymentDto.getPaymentAmount()).orElse(accountPayment.getPaymentAmount()));
    	accountPayment.setPaymentDate(Optional.ofNullable(accountPaymentDto.getPaymentDate()).orElse(accountPayment.getPaymentDate()));
    	accountPayment.setSituation(Optional.ofNullable(accountPaymentDto.getSituation()).orElse(accountPayment.getSituation()));
    	
        return accountPaymentRepository.save(accountPayment);
    }
    
    public Optional<AccountPayment> findBy(Long id) {
        Optional<AccountPayment> accountPayment = accountPaymentRepository.findById(id);
        return accountPayment;
    }
    
    public void delete(Long id) {
    	AccountPayment accountPayment = accountPaymentRepository.findById(id).orElseThrow(() ->  new RuntimeException("Contas a Pagar não encontrado"));
        try {
        	accountPaymentRepository.delete(accountPayment);
        } catch (DataIntegrityViolationException e) {
            throw new ValidationException("Exclusão não autorizada, existem registros vinculados a este Contas a Pagar.");
        }
    }

    public Page<AccountPayment> findAllByFilter(AccountPaymentFilter filters, Pageable pageable) {
        Specification<AccountPayment> spec = accountPaymentRepository.buildFilters(filters);
        Page<AccountPayment> accountPayments = accountPaymentRepository.findAll(spec, pageable);
        return accountPayments;
    }
    
    public Optional<String> importCsv(String request) {
    	String csvPure = decodeArchive(request);
    	String msg = null;
    	if(!csvPure.isBlank()) {
    		BufferedReader reader = new BufferedReader(new StringReader(csvPure));
    		String linha;
    		int contador = 0;
    		try {
				while ((linha = reader.readLine()) != null) {
					if(contador == 0) {
						contador++; 
						continue;
					}
					String[] array = linha.replace(",",";").replace("|",";").split(";");
					AccountPayment accountPayment = this.buildEntity(array);
					accountPaymentRepository.save(accountPayment);
				}
				msg = "Importação de contas processado com sucesso!";
			} catch (IOException | DataIntegrityViolationException | IndexOutOfBoundsException e) {
				msg = null;
			}    		
    	}
    	return Optional.of(msg);
    }
    
    private String decodeArchive(String request) {
    	return new String(Base64.getDecoder().decode(request));
    }
    
    private AccountPayment buildEntity(String[] array) {
    	return AccountPayment.builder()
    			.expirationDate(LocalDate.parse(array[0], FORMATTER))
    			.paymentDate(LocalDate.parse(array[1], FORMATTER))
    			.paymentAmount(new BigDecimal(array[2]))
    			.description(array[3])
    			.situation(AccountPayment.Status.valueOf(array[4]))
    			.build();
    }
}
