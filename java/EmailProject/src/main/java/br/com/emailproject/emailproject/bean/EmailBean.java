package br.com.emailproject.emailproject.bean;

import java.io.Serializable;
import br.com.emailproject.emailproject.dto.EmailLayout;
import br.com.emailproject.emailproject.model.Email;
import br.com.emailproject.emailproject.service.EmailService;
import br.com.emailproject.emailproject.util.LogUtil;
import jakarta.enterprise.context.RequestScoped;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Date;

@Named
@RequestScoped
public class EmailBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmailService emailService;

    public String enviarEmail() {
        LogUtil.info("Iniciando o Envio de email..." + new Date(System.currentTimeMillis()));
        Email email = this.montarEmail();
        emailService.enviar(email);
        LogUtil.info("Finalizando o Envio... "+ new Date(System.currentTimeMillis()));
        return null;
    }

    private Email montarEmail() {
        
        EmailLayout emailLayout = new EmailLayout();
        return emailLayout.montarEmailAdmnistrador("eribeiro18@gmail.com", "Mudança de senha!");
    }
}
