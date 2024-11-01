package br.com.account.payment.api.builder;

import br.com.account.payment.api.application.dto.UserDto;
import br.com.account.payment.api.infraestructure.entity.User;

public class UserBuilder {
	
    public static UserDto buildDto() {
        return UserDto.builder()
        			  .id(1L)
        			  .firstName("Validação")
        			  .username("validacao")
                      .build();
    }

    public static User build() {
        return User.builder()
  			       .id(1L)
  			       .firstName("Validação")
  			       .username("validacao")
  			       	.build();
    }
}
