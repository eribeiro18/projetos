package br.com.evandro.enviandoemail;

public class AppTestEmailGmail {
	
	@org.junit.Test
	public void testeEmailSimples() {
		try {
			ObjEnviaEmail objEnviaEmail = 
					new ObjEnviaEmail("eribeiro18@gmail.com, eribeiro18@myyahoo.com", 
									  "Evandro Ribeiro", 
									  "Chegou e-mail enviado com java utilizando gmail", 
									  "Olá programador, vc acaba de receber um e-mail enviado com Java do curso Formação Java Web!!! ");
			objEnviaEmail.enviarEmail(false);
			
			System.out.println(objEnviaEmail.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void testeEmailHtml() {
		try {
			
			String textoEmail = this.gerarTextoHml();
			
			ObjEnviaEmail objEnviaEmail = 
					new ObjEnviaEmail("eribeiro18@gmail.com", 
									  "Evandro Ribeiro", 
									  "Chegou e-mail enviado com java utilizando gmail", 
									  textoEmail);
			objEnviaEmail.enviarEmail(true);
			
			System.out.println(objEnviaEmail.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@org.junit.Test
	public void testeEmailHtmlAnexo() {
		try {
			
			String textoEmail = this.gerarTextoHml();
			
			ObjEnviaEmail objEnviaEmail = 
					new ObjEnviaEmail("eribeiro18@gmail.com", 
									  "Evandro Ribeiro", 
									  "Chegou e-mail enviado com java utilizando gmail", 
									  textoEmail);
			objEnviaEmail.enviarEmailAnexo(true);
			
			System.out.println(objEnviaEmail.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String gerarTextoHml() {
		StringBuilder builder = new StringBuilder();
		builder.append("Olá <br/><br/>");
		builder.append("Você está recebendo o acesso ao curso de Java <br/><br/>");
		builder.append("<b>Para ter acesso clique no botão abaixo.</b> <br/><br/>");
		builder.append("<a target=\"_blank\" href=\"www.google.com\" style=\"color: #2525a7; padding: 14px 25px; text-align: center; text-decoration: none; display: inline-block; border-radius: 30px; font-size: 20px; font-family: courier; border: 3px solid green; background-color: #99DA39;\">Acessar Site </a> <br/><br/>");
		builder.append("<span style=\"font-size:8px\">Ass.: Evandro Ribeiro </span>");
		return builder.toString();
	}

}
