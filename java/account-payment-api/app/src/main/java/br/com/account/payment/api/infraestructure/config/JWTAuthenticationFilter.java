package br.com.account.payment.api.infraestructure.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Authentication authentication = null;
		var servletResponse = (HttpServletResponse) response;
		try {
			authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);
		} catch (Exception e) {
			servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
	}

}
