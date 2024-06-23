package br.com.account.payment.api.application.dto;

import lombok.*;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String firstName;
	private String lastName;
	private String telephone;
	
    @NotBlank(message = "Usuário sem preenchimento!")
    @Size(min = 1, max = 200, message = "Código deve ser maior que 1 e menor que 200 caracteres.")
	private String username;
    
    @NotBlank(message = "Senha sem preenchimento!")
    @Size(min = 1, max = 600, message = "Senha deve ser maior que 1 e menor que 600 caracteres.")
	private String password;
	private String passwordAffirm;
	private boolean active;
	private boolean isTempPassword;

//	public void validateUpdate() {
//		validatePassword();
//	}
//
//	public void validateCreate() {
//		if (password != null) {
//			validatePassword();
//		}
//	}
//
//	private void validatePassword() {
//		if (!password.equals(passwordAffirm)) {
//			throw new ValidationException("As senhas não conferem! favor verificar!");
//		}
//		if (password.length() < 8) {
//			throw new ValidationException("O mínimo são 8 caracteres para a senha!");
//		}
//	}

}
