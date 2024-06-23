package br.com.account.payment.api.infraestructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter	{

    private final GenerateAuthToken generateAuthToken;

	public JWTLoginFilter(String url, AuthenticationManager authManager, GenerateAuthToken generateAuthToken) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.generateAuthToken = generateAuthToken;
    }
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException {
        buildResponseHeader(request, response);
        LoginSecurity credentials = new ObjectMapper().readValue(request.getInputStream(), LoginSecurity.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword(),
                                                        Collections.emptyList())
        );
	}

    private void buildResponseHeader(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers",
                request.getHeader("Access-Control-Request-Headers"));
    }
	
	@Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException {
        generateAuthToken.addAuthentication(res, auth.getName());
    }

}
	