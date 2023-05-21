/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.beans.mb;

import br.com.optpronteletronico.business.facade.UsuarioFacade;
import br.com.optpronteletronico.entity.Usuario;
import br.com.optpronteletronico.exception.OPTException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.primefaces.event.SelectEvent;
import br.com.optpronteletronico.util.FacesUtil;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author evandro
 */
@ManagedBean(name = "initializeBean")
@SessionScoped
public class InitializeBean extends FacesUtil implements Serializable, PhaseListener {

    @Inject
    private UsuarioFacade usuarioFacade;
    private Usuario usuario;
    private Boolean procced;
    private Boolean isLogged;
    private String username;
    private String password;
    private String ipAddress;

    @Override
    public void afterPhase(PhaseEvent pe) {
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        if (!pe.getFacesContext().getViewRoot().getViewId().equals("/login.xhtml")) {
            InitializeBean initializeBean = (InitializeBean) getSessionBean(InitializeBean.class);
            if (initializeBean != null && initializeBean.getUsuario() != null && initializeBean.getUsuario().getId() != null) {
                setProcced(Boolean.TRUE);
            } else {
                pe.getFacesContext().getExternalContext().invalidateSession();//Inválidando sessão do usuário
                setProcced(Boolean.FALSE);
                try {
                    page("/OptProntEletronico/login");
                } catch (IOException ex) {
                    Logger.getLogger(InitializeBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.INVOKE_APPLICATION;
    }

    //@Override
    public void onRowSelect(SelectEvent e) {
    }

    @Override
    public void processAction(ActionEvent ae) throws AbortProcessingException {
        switch (ae.getComponent().getId()) {
            case "btnLogin":
                this.login();
                break;
            default:
                break;
        }
    }

    public void login() {
        if (getUsername() != null && !getUsername().equals("") && getPassword() != null && !getPassword().equals("")) {
            this.setPassword(this.getUsuario().setSenhaCrip(this.getPassword()));
            try {
                setUsuario(usuarioFacade.recuperarUsuarioPorLoginSenha(getUsername(), getPassword()));
            } catch (OPTException ex) {
                Logger.getLogger(InitializeBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (getUsuario() != null && getUsuario().getId() != null) {
                setIsLogged(Boolean.TRUE);
                RequestContext req = callGrowlMsg(FacesMessage.SEVERITY_INFO, "Informação", "Dados autenticos!");
                this.addParamsCallback(req, new Object[]{"dialog"}, new Object[]{"dialogLogin"});
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

                ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                try {
                    page("pages/menu");
                } catch (IOException ex) {
                    Logger.getLogger(InitializeBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "Usuário inexistente!");
            }
        } else {
            exibirMensagem(FacesMessage.SEVERITY_WARN, "Informação", "Usuário e/ou Senha inválido!");
        }
    }

    public void logout() throws Exception {
        Map<String, Object> sessions = getExternalContext().getSessionMap();
        for (Map.Entry<String, Object> value : sessions.entrySet()) {
            System.out.println("Dataset => " + value.getKey() + " = " + value.getValue());
            try {
                getExternalContext().getSessionMap().remove(value.getKey());
                System.out.println("Valor " + value.getValue() + " removido!");
            } catch (Exception e) {
                throw e;
            }
        }
        page("/OptProntEletronico/login");
    }

    public String getUsername() {
        if (this.username == null) {
            this.username = "";
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        if (this.password == null) {
            this.password = "admin";
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsLogged() {
        if (this.isLogged == null) {
            this.isLogged = Boolean.FALSE;
        }
        return isLogged;
    }

    public void setIsLogged(Boolean isLogged) {
        this.isLogged = isLogged;
    }

    public Boolean isProcced() {
        if (this.procced == null) {
            this.procced = Boolean.FALSE;
        }
        return procced;
    }

    public void setProcced(Boolean procced) {
        this.procced = procced;
    }

    public Usuario getUsuario() {
        if(this.usuario == null){
            this.usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }   
}