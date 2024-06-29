package br.com.account.payment.api.infraestructure.config;

import com.google.gson.Gson;

import br.com.account.payment.api.application.dto.UserJwtDto;
import io.jsonwebtoken.Jwts;
import jakarta.validation.ValidationException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

import static java.util.Collections.emptyList;

public class TokenAuthenticationService {
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";


    static Authentication getAuthentication(HttpServletRequest request) throws ValidationException {
    	try {
    		String token = request.getHeader(HEADER_STRING);
            if (token == null) {
                throw new ValidationException("Token not found");
            }
            String userPayload = Jwts.parser()
                                  .setSigningKey(SECRET)
                                  .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                                  .getBody()
                                  .getSubject();

            UserJwtDto userContext = new Gson().fromJson(userPayload, UserJwtDto.class);
            return userContext != null ? new UsernamePasswordAuthenticationToken(userContext.getUsername(), null, emptyList()) : null;
		} catch (Exception e) {
			throw new ValidationException("Token invalid or expired");
		}
    }
	
}
