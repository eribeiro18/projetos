package br.com.transaction.pix.api.application.dto;

import br.com.transaction.pix.api.infraestructure.entity.Pix;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PixDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Data do pagamento não pode ser nula!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @NotNull(message = "Valor do pagamento não pode ser nulo!")
    @Digits(integer = 15, fraction = 3, message = "Valor não pode ultrapassar 15 dígitos inteiros e 3 decimais.")
    private BigDecimal paymentAmount;

    @NotBlank(message = "Descrição não pode estar vazia!")
    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres.")
    private String description;

    @NotBlank(message = "Situação não pode estar vazia!")
    @Size(max = 100, message = "Situação deve ter no máximo 100 caracteres.")
    private Pix.Status situation;

    @NotNull(message = "Data de criação não pode ser nula!")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate createAt;

    private LocalDate updateAt;

    public void validateUpdate() {
        if (id == null) {
            throw new RuntimeException("id é requerido!");
        }
    }
}