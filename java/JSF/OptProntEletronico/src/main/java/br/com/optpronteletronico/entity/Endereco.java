/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.entity;

import br.com.optpronteletronico.interceptors.impl.Required;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author evandro
 */
@Entity
@Table(name = "ENDERECO")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_id_seq")
    @SequenceGenerator(name = "endereco_id_seq", sequenceName = "endereco_id_seq", allocationSize = 1)
    private BigDecimal id;
    
    @Required(isRequired = true, campo = "BAIRRO")
    @Size(min = 1, max = 30)
    @Column(name = "BAIRRO")
    private String bairro;
    
    @Required(isRequired = true, campo = "CEP")
    @Size(min = 1, max = 10)
    @Column(name = "CEP")
    private String cep;
    
    @Required(isRequired = true, campo = "LOGRADOURO")
    @Size(min = 1, max = 50)
    @Column(name = "LOGRADOURO")
    private String logradouro;
    
    @Required(isRequired = true, campo = "NÚMERO")
    @Size(min = 1, max = 5)
    @Column(name = "NUMERO")
    private String numero;
    
    @Required(isRequired = true, campo = "COMPLEMENTO")
    @Size(min = 1, max = 100)
    @Column(name = "COMPLEMENTO")
    private String complemento;
    
    @Required(isRequired = true, campo = "MUNICIPIO")
    @JoinColumn(name = "IDMUNICIPIO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Municipio municipio;
    
    @JoinColumn(name = "IDPESSOA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pessoa pessoa;

    public Endereco() {
    }

    public Endereco(BigDecimal id) {
        this.id = id;
    }

    public Endereco(BigDecimal id, String bairro, String cep, String logradouro, String numero, String complemento) {
        this.id = id;
        this.bairro = bairro;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Municipio getIdmunicipio() {
        return municipio;
    }

    public void setIdmunicipio(Municipio idmunicipio) {
        this.municipio = idmunicipio;
    }

    public Pessoa getIdpessoa() {
        return pessoa;
    }

    public void setIdpessoa(Pessoa idpessoa) {
        this.pessoa = idpessoa;
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
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Endereco[ id=" + id + " ]";
    }
    
}
