package com.example.ia.demo;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class DemoApplication {
	
	private static String accesToken = "seu token aqui";
	private static String modelId = "FacebookAI/xlm-roberta-base";
	private static String url = "https://api-inference.huggingface.co/models/" + modelId;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);
		methodTest();
	}
	
	private static void methodTest() throws IOException {
		String text = "The capital of <mask> is Brasilia.";
		try (CloseableHttpClient client = HttpClients.createDefault()){
	        HttpPost request = new HttpPost(url);

	        request.addHeader("Authorization", "Bearer " + accesToken);

	        String json = "{ \"inputs\": \"" + text + "\", \"parameters\": {\"top_k\": 5} }";
	        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

	        var response = client.execute(request);
	        var responseBody = new String(response.getEntity().getContent().readAllBytes());

	        // Parse JSON
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode root = mapper.readTree(responseBody);

	        System.out.println("Predições para: " + text);

	        for (JsonNode node : root) {
	            String sequence = node.get("sequence").asText();
	            String tokenStr = node.get("token_str").asText();
	            double score = node.get("score").asDouble() * 100;
	            System.out.printf("Predição '%s' com score %.2f%% -> %s%n", tokenStr, score, sequence);
	        }
		}
	}

}
