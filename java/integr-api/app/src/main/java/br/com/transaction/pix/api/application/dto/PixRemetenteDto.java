package br.com.transaction.pix.api.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PixRemetenteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "ID da transação Pix não pode ser nulo!")
    private Long pixId;

    @NotBlank(message = "Nome do remetente não pode estar vazio!")
    @Size(max = 150, message = "Nome do remetente deve ter no máximo 150 caracteres.")
    private String nome;

    @NotBlank(message = "Documento do remetente não pode estar vazio!")
    @Size(max = 150, message = "Documento do remetente deve ter no máximo 150 caracteres.")
    private String documento;

    @NotBlank(message = "Banco do remetente não pode estar vazio!")
    @Size(max = 150, message = "Banco do remetente deve ter no máximo 150 caracteres.")
    private String banco;

    @NotNull(message = "Data de criação não pode ser nula!")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public void validateUpdate() {
        if (id == null) {
            throw new RuntimeException("id é requerido!");
        }
    }
}