package br.com.account.payment.api;

import org.springframework.http.HttpHeaders;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.account.payment.api.application.dto.DemoKongDto;

@SpringBootApplication
public class ApiDemoKongApplication {

	private static final ObjectMapper mapperJson = new ObjectMapper(); 
	
	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(ApiDemoKongApplication.class, args);
		ApiDemoKongApplication.callVirtualThreads();
	}

	private static DemoKongDto buildDto() {
		return DemoKongDto.builder()
				.consumerId("CONSUMIDOR%s")
				.validate(true)
				.category("ANONIMA")
				.empresa("EMPRESA1")
				.record("OUTRA INFORMACAO RELEVANTE")
				.build();
	}
	
	private static DemoKongDto buildDto2() {
		return DemoKongDto.builder()
				.consumerId("CONSUMIDOR1")
				.validate(true)
				.category("ANONIMA")
				.empresa("EMPRESA1")
				.record("OUTRA INFORMACAO RELEVANTE")
				.build();
	}
	
	private static void callVirtualThreads() throws JsonProcessingException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        RestTemplate restTemplate = new RestTemplate();
        
        String payload = mapperJson.writeValueAsString(buildDto());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        int numRequests = 100000;
        IntStream.range(0, numRequests).forEach(i -> 
            executor.submit(() -> {
                try {
                	HttpEntity<String> request = new HttpEntity<>(String.format(payload, i), headers);
                    String url = "http://localhost:8000/api/teste-kong/v1/teste-kong";
                    ResponseEntity<String> response  = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
                    System.out.println("Response " + i + ": " + response.getStatusCode());
                } catch (Exception e) {
                    System.err.println("Erro na requisição " + i + ": " + e.getMessage());
                }
            })
        );
        executor.shutdown();
	}
	
	
}
