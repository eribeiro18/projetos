package br.com.account.payment.api.infraestructure.controller.commons;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.account.payment.api.application.dto.UserJwtDto;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JsonUserProvider {
  
    public static UserJwtDto getContextDto(String authorization) {
    	return getUserContext(authorization);
    }

    private static UserJwtDto getUserContext(String token) {
		String[] jwt = token.split("\\.");
		JSONObject jsonObject = new JSONObject(new String(Base64.getUrlDecoder().decode(jwt[1])));
		String userContext = jsonObject.getString("sub");
		try {
			return new ObjectMapper().readValue(userContext, UserJwtDto.class);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
