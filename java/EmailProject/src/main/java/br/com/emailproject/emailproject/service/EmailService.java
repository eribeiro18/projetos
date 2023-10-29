package br.com.emailproject.emailproject.service;

import br.com.emailproject.emailproject.model.Email;
import br.com.emailproject.emailproject.util.LogUtil;
import jakarta.ejb.Stateless;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Stateless
public class EmailService extends Thread {
    
    private List<Email> emails;
    private static final String HEADER_CONTEXT = "text/html; charset=utf-8";

    public void enviar(Email email) {
        emails = new ArrayList<>();
        emails.add(email);
        this.send();
    }
    
    public void enviar(List<Email> emails){
        this.emails = emails;
        this.send();
    }
    
    private EmailService copy(){
        EmailService emailService = new EmailService();
        emailService.emails = emails;
        return emailService;
    }

    private void send(){
        new Thread(this.copy()).start();
    }

    @Override
    public void run() {
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "1025");
        
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(false);
        
        for(Email email: emails){
            try {
                String from = "evandromailformacaojavaweb@gmail.com";                
                Address[] toUser = InternetAddress.parse(email.getDestinatario());
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from, "Evandro Ribeiro"));
                message.setRecipients(Message.RecipientType.TO, toUser);
                message.setSubject(email.getAssunto());
                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setHeader("Content-Type", HEADER_CONTEXT);
                textPart.setContent(email.getTexto(), HEADER_CONTEXT);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(textPart);
                message.setContent(multipart);

                Transport.send(message);                
            } catch (MessagingException | UnsupportedEncodingException e) {
                LogUtil.erro(e.getMessage());
            }
        }
    }
}
