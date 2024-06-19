package br.com.account.payment.api.application.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.account.payment.api.infraestructure.entity.AccountPayment.Status;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@Builder
public class AccountPaymentDto implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private Long id;
    
    @NotNull(message = "Data de vencimento sem preenchimento!")  
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    
    @NotNull(message = "Data do pagamento sem preenchimento!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    
    @NotNull(message = "Valor sem preenchimento!")  
    @Digits(integer = 13, fraction = 3, message = "Valor nao pode ultrapassar o tamanho 15.3")
    private BigDecimal paymentAmount;

    @NotBlank(message = "Descrição sem preenchimento!")
    @Size(min = 1, max = 500, message = "Descrição deve ser maior que 1 e menor que 500 caracteres.")
    private String description;
    
    @NotNull(message = "Situação sem preenchimento!")
    private Status situation;
    
    public void validateUpdate() {
        if (id == null) {
            throw new RuntimeException("id é requerido!");
        }
    }

}
