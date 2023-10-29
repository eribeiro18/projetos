package br.com.evandro.enviandoemail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjEnviaEmail {

	String emailGmail = "evandromailformacaojavaweb@gmail.com";
	//Entrar em seguraça da conta ativar o 2fa 
	//Após item acima adicionar uma chave extra, esta deve ser usada para autenticação
	String authGmail = "hahahaha";
	
	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";
	
	public ObjEnviaEmail(String listaDestinatarios, String nomeRemetente,
			String assuntoEmail, String textoEmail) {
		super();
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}

	public void enviarEmail(boolean envioHtml) throws Exception {
		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");
		properties.put("main.smtp.starttls", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailGmail, authGmail);
			}
		});

		Address[] toUser = InternetAddress.parse(listaDestinatarios);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailGmail, nomeRemetente));
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(assuntoEmail);
		
		if(envioHtml) {
			message.setContent(textoEmail, "text/html; charset=utf-8");
		}else {
			message.setText(textoEmail);
		}

		Transport.send(message);
	}
	
	public void enviarEmailAnexo(boolean envioHtml) throws Exception {
		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");
		properties.put("main.smtp.starttls", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailGmail, authGmail);
			}
		});

		Address[] toUser = InternetAddress.parse(listaDestinatarios);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailGmail, nomeRemetente));
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(assuntoEmail);
		
		MimeBodyPart corpoEmail = new MimeBodyPart();
		
		if(envioHtml) {
			corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");
		}else {
			corpoEmail.setText(textoEmail);
		}
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoEmail);
		
		//com 1 anexo sem o for, com mais de 1 arquivo incluir dentro do for
		for(int i = 0; i < 2; i++) {
			MimeBodyPart anexoEmail = new MimeBodyPart();
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladorDePdf(), "application/pdf")));
			anexoEmail.setFileName(i + "_Anexoemail.pdf");
			multipart.addBodyPart(anexoEmail);			
		}
		message.setContent(multipart);
		
		Transport.send(message);
	}
	
	/**
	 * 
	 * Este metodo simula o PDF ou qualquer arquivo que possa ser enviado por anexo no email;
	 * Voce pode pegar o arquivo no seu banco de dados base64, byte[], Stream de Arquivo;
	 * 
	 * Retorno um PDF em branco com o texto do paragrafo de exemplo
	 * 
	 * */
	private FileInputStream simuladorDePdf() throws Exception{
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com Java Mail, este texto é do PDF"));
		document.close();
		return new FileInputStream(file);
	}
}
