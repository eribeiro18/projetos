package br.com.evandro.enviandoemail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AppTestEmailOutlook {
	
	String emailOutLook = "eribeiro18formacaojavaweb@outlook.com";
	//Entrar em seguraça da conta ativar o 2fa para evitar problemas de segurança
        //senha abaixo deve ser a da propria conta
	String authOutLook = "ahahahah";
	
	@org.junit.Test
	public void testeEmail() {
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth","true");
			properties.put("main.smtp.starttls","true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp-mail.outlook.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.socketFactory.port", "587");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");	
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailOutLook, authOutLook);
				}
			});
			
			Address[] toUser = InternetAddress.parse("eribeiro18@gmail.com, eribeiro18@myyahoo.com");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailOutLook, "Evandro Ribeiro"));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Chegou e-mail enviado com java utilizando outlook");
			message.setText("Olá programador, vc acaba de receber um e-mail enviado com Java do curso Formação Java Web!!! ");
			
			Transport.send(message);
			
			System.out.println(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
