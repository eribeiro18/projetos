/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.entity;

import br.com.optpronteletronico.interceptors.impl.Required;
import br.com.optpronteletronico.util.SimpleMD5;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author evandro
 */
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
    @SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;

    @Required(isRequired = true, campo = "LOGIN")
    @Size(min = 1, max = 30)
    @Column(name = "LOGIN")
    private String login;

    @Required(isRequired = true, campo = "SENHA")
    @Size(min = 1, max = 50)
    @Column(name = "SENHA")
    private String senha;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<UsuarioPapelUsuario> usuarioPapelUsuarioSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<PreConsulta> preConsultaSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<AgendaConsulta> agendaConsultaSet;

    @JoinColumn(name = "IDPESSOA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pessoa pessoa;

    public Usuario() {
    }

    public Usuario(BigDecimal id) {
        this.id = id;
    }

    public Usuario(BigDecimal id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public Set<PreConsulta> getPreConsultaSet() {
        return preConsultaSet;
    }

    public void setPreConsultaSet(Set<PreConsulta> preConsultaSet) {
        this.preConsultaSet = preConsultaSet;
    }

    public Set<AgendaConsulta> getAgendaConsultaSet() {
        return agendaConsultaSet;
    }

    public void setAgendaConsultaSet(Set<AgendaConsulta> agendaConsultaSet) {
        this.agendaConsultaSet = agendaConsultaSet;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String setSenhaCrip(String senha) {
        String senhaCrip = null;
        if (senha != null && senha.length() > 0) {
            try {
                String senhaMD5 = SimpleMD5.MD5(senha);
                if (!senha.equals(senhaMD5)) {
                    senhaCrip = senhaMD5;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return senhaCrip;
    }

    public Set<UsuarioPapelUsuario> getUsuarioPapelUsuarioSet() {
        return usuarioPapelUsuarioSet;
    }

    public void setUsuarioPapelUsuarioSet(Set<UsuarioPapelUsuario> usuarioPapelUsuarioSet) {
        this.usuarioPapelUsuarioSet = usuarioPapelUsuarioSet;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Usuario[ id=" + id + " ]";
    }

}
