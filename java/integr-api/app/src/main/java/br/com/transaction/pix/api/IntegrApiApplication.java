package br.com.transaction.pix.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"br.com.transaction.pix.api.*"})
@EntityScan("br.com.transaction.pix.api.infraestructure.entity")
@EnableJpaRepositories("br.com.transaction.pix.api.infraestructure.repository")
public class IntegrApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrApiApplication.class, args);
	}

}
