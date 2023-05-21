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
@Table(name = "FATURASALARIO")
public class FaturaSalario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faturaSalario_id_seq")
    @SequenceGenerator(name = "faturaSalario_id_seq", sequenceName = "faturaSalario_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Required(isRequired = true, campo = "TIPO CONTA")
    @Column(name = "TIPOCONTA")
    private BigDecimal tipoConta;
    
    @Size(max = 10)
    @Column(name = "CONTABANCARIA")
    private String contaBancaria;
    
    @Size(max = 10)
    @Column(name = "AGENCIABANCARIA")
    private String agenciaBancaria;
    
    @JoinColumn(name = "IDCLINICA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Clinica clinica;
    
    @JoinColumn(name = "IDPESSOACRM", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PessoaCrm pessoaCrm;

    public FaturaSalario() {
    }

    public FaturaSalario(Long id) {
        this.id = id;
    }

    public FaturaSalario(Long id, BigDecimal tipoconta) {
        this.id = id;
        this.tipoConta = tipoconta;
    }

    public PessoaCrm getIdpessoacrm() {
        return pessoaCrm;
    }

    public void setIdpessoacrm(PessoaCrm idpessoacrm) {
        this.pessoaCrm = idpessoacrm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(BigDecimal tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public String getAgenciaBancaria() {
        return agenciaBancaria;
    }

    public void setAgenciaBancaria(String agenciaBancaria) {
        this.agenciaBancaria = agenciaBancaria;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public PessoaCrm getPessoaCrm() {
        return pessoaCrm;
    }

    public void setPessoaCrm(PessoaCrm pessoaCrm) {
        this.pessoaCrm = pessoaCrm;
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
        if (!(object instanceof FaturaSalario)) {
            return false;
        }
        FaturaSalario other = (FaturaSalario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Faturasalario[ id=" + id + " ]";
    }
}
