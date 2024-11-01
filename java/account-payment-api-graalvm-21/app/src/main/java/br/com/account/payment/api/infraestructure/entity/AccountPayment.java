package br.com.account.payment.api.infraestructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "account_payment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountPayment implements Serializable {
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
    	
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;
    
    @Column(name = "payment_amount", nullable = false)
    private BigDecimal paymentAmount;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status situation;
    
    public enum Status {
        AWAITING_PAYMENT,
        PAYMENT_MADE,
        AWAITING_RECEIPT
    }
}
