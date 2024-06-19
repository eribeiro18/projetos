package br.com.account.payment.api.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.account.payment.api.application.dto.AccountPaymentDto;
import br.com.account.payment.api.builder.AccountPaymentBuilder;
import br.com.account.payment.api.infraestructure.entity.AccountPayment;
import br.com.account.payment.api.infraestructure.repository.AccountPaymentRepository;
import jakarta.validation.ValidationException;

public class AccountPaymentServiceTest {
	
    private AccountPaymentService accountPaymentService;
    
    @Mock
    private AccountPaymentRepository accountPaymentRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.accountPaymentService = new AccountPaymentService(accountPaymentRepository);
    }
    
    @Test
    void save() {
        AccountPaymentDto accountPaymentToSave = AccountPaymentBuilder.buildDto();

        when(accountPaymentRepository.save(any())).thenReturn(AccountPaymentBuilder.build());
        AccountPayment AccountPaymentSaved = accountPaymentService.save(AccountPaymentBuilder.buildDto());
        assertEquals(accountPaymentToSave.getDescription(), AccountPaymentSaved.getDescription());
    }

    @Test
    void update() {
    	AccountPaymentDto accountPaymentToUpdate = AccountPaymentBuilder.buildDto();
        accountPaymentToUpdate.setId(1L);
        accountPaymentToUpdate.setDescription("Teste de descrição de contas");
        accountPaymentToUpdate.setSituation(AccountPayment.Status.AWAITING_PAYMENT);

        when(accountPaymentRepository.save(any())).thenReturn(AccountPaymentBuilder.build());
        when(accountPaymentRepository.findById(any())).thenReturn(Optional.of(AccountPaymentBuilder.build()));

        AccountPayment accountPaymentSaved = accountPaymentService.update(accountPaymentToUpdate);
        assertNotEquals(accountPaymentToUpdate.getDescription(), accountPaymentSaved.getDescription());
    }

    @Test
    public void updateNonexistentTest() {
    	AccountPaymentDto accountPaymentToUpdate = AccountPaymentBuilder.buildDto();
        accountPaymentToUpdate.setId(10L);
        accountPaymentToUpdate.setDescription("Teste de descrição de contas 2");
        accountPaymentToUpdate.setSituation(AccountPayment.Status.PAYMENT_MADE);
        when(accountPaymentRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ValidationException.class, () -> accountPaymentService.update(accountPaymentToUpdate));
    }

    @Test
    void findBy() {
        when(accountPaymentRepository.findById(any())).thenReturn(Optional.of(AccountPaymentBuilder.build()));
        Optional<AccountPayment> accountPayment = accountPaymentService.findBy(1L);
        assertTrue(accountPayment.isPresent());
    }
    
    @Test
    void importCsv() {
    	String request = "ZXhwaXJhdGlvbkRhdGU7cGF5bWVudERhdGU7cGF5bWVudEFtb3VudDtkZXNjcmlwdGlvbjtzaXR1YXRpb24KMjAyNC0xMi0zMTsyMDI0LTEyLTMwOzEwLjUwO2Rlc2NyacOnw6NvIGRvIGNvbnRhcyAxO0FXQUlUSU5HX1BBWU1FTlQ=";

        when(accountPaymentRepository.save(any())).thenReturn(AccountPaymentBuilder.build());

        Optional<String> msg = accountPaymentService.importCsv(request);
        assertEquals(msg.get(), "Importação de contas processado com sucesso!");
    }
}
