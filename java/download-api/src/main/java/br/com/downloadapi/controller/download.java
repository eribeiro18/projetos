package br.com.downloadapi.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CrossOrigin(value = "*")
@RestController
@RequestMapping("/download")
public class download {

	@GetMapping(path = "/pdf1")
	public ResponseEntity<byte[]> downloadPdf1() {
		byte[] contents = null;
		try {
			contents = this.lerDoc();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpStatus status = HttpStatus.OK;
		//contents = null;
		ResponseEntity<byte[]> response = this.trataRetorno(contents, status);
		return response;
	}

	@GetMapping(path = "/pdf2")
	public HttpEntity<byte[]> downloadPdf2() {
		byte[] arquivo = null;
		try {
			arquivo = this.lerDoc();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Disposition", "attachment;filename=teste.pdf");
		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);
		return entity;
	}

	private ResponseEntity<byte[]> trataRetorno(byte[] contents, HttpStatus status) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(status == HttpStatus.OK ? MediaType.APPLICATION_PDF : this.contentType(status));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		if(contents == null) {
			contents = this.contents(status);
		}
		return new ResponseEntity<>(contents, headers, status);

	}

	private byte[] lerDoc() throws IOException {
		File file = new File("/home/evandro/Downloads/teste/teste.pdf");
		byte[] b = Files.readAllBytes(file.toPath());
		return b;
	}

	private MediaType contentType(HttpStatus status) {
		MediaType type = null;
		if (status == HttpStatus.NOT_FOUND || status == HttpStatus.FORBIDDEN) {
			type = MediaType.TEXT_HTML;
		}
		return type;
	}

	private byte[] contents(HttpStatus status) {
		if (status == HttpStatus.NOT_FOUND) {
			return html("404 Documento não encontrado", "404", "Oops, Documento não econtrado...").getBytes();
		}else {
			return html("403 acesso negado", "403", "Oops, acesso negado verifique sua URL...").getBytes();
		}
	}
	
	private String html(String titulo, String codigo, String mensagem) {
		StringBuilder html = new StringBuilder();
		html.append(" <!DOCTYPE html> ").append(" <html lang=\"en\"> ").append(" <head> ")
				.append(" <meta charset=\"UTF-8\"> ")
				.append(" <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> ")
				.append(" <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ")
				.append(" <title>").append(titulo).append("</title>").append(" </head> ").append(" <body> ")
				.append(" <div class=\"container\"><h1>").append(codigo).append("</h1><h3>").append(mensagem).append("</h3></div> ")
				.append(" </body> ").append(" </html> ");
		return html.toString();
	}
}
