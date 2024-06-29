package br.com.account.payment.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"br.com.account.payment.api.*"})
@EntityScan("br.com.account.payment.api.infraestructure.entity")
@EnableJpaRepositories("br.com.account.payment.api.infraestructure.repository")
public class AccountPaymentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountPaymentApiApplication.class, args);
	}

}
