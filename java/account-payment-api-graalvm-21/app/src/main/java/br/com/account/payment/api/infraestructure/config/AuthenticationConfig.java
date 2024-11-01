package br.com.account.payment.api.infraestructure.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticationConfig {
	
    private final DataSource dataSource;
    private static final String LOGIN_QUERY = "select username, password, id from user_system where username=?";
    private static final String ROLES_QUERY = "select u.username, 'ADMIN' from user_system u where u.username=?";
    
    public AuthenticationConfig(DataSource dataSource) {
    	this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Primary
    @Bean("configure")
    public AuthenticationManagerBuilder configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(LOGIN_QUERY)
            .authoritiesByUsernameQuery(ROLES_QUERY)
            .passwordEncoder(passwordEncoder());
        return auth;
    }
}
