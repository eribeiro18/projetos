package br.com.emailproject.emailproject.dto;

import br.com.emailproject.emailproject.model.Email;

public class EmailLayout {
	
	private static final String QUEBRA_DE_LINHA_DYPLA = "<br/><br/>";

	public Email montarEmailAdmnistrador(String destinatario, String assunto) {
		StringBuilder texto = new StringBuilder();
		texto.append(" A/C Adminstrador")
			 .append(QUEBRA_DE_LINHA_DYPLA)
			 .append("Solicito alteração de senha do sistema")
			 .append(QUEBRA_DE_LINHA_DYPLA);
		return new Email(destinatario, assunto, gerarRodape(texto));
		
	}
	
	private String gerarRodape(StringBuilder texto) {
		return texto.append("Email automatico. Favor não responder este email.").toString();
	}
	
	
}
