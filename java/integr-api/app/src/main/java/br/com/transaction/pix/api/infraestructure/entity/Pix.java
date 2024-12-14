package br.com.transaction.pix.api.infraestructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "pix")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pix_id_seq")
    @SequenceGenerator(name = "pix_id_seq", sequenceName = "pix_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "payment_amount", precision = 15, scale = 3, nullable = false)
    private BigDecimal paymentAmount;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "situation", length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status situation;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "create_by", length = 100, nullable = false)
    private String createBy;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "update_by", length = 100)
    private String updateBy;
    
    public enum Status {
        AWAITING_PAYMENT,
        PAYMENT_MADE,
        AWAITING_RECEIPT
    }
}
