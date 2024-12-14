package br.com.transaction.pix.api.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pix_remetente")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PixRemetente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pix_id_seq")
    @SequenceGenerator(name = "pix_id_seq", sequenceName = "pix_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pix_id", nullable = false)
    private Pix pix;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "documento", length = 150, nullable = false)
    private String documento;

    @Column(name = "banco", length = 150, nullable = false)
    private String banco;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "create_by", length = 100, nullable = false)
    private String createBy;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "update_by", length = 100)
    private String updateBy;
}

