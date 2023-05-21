/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.entity;

import br.com.optpronteletronico.interceptors.impl.Required;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author evandro
 */
@Entity
@Table(name = "PESSOAFISICA")
public class PessoaFisica implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoaFisica_id_seq")
    @SequenceGenerator(name = "pessoaFisica_id_seq", sequenceName = "pessoaFisica_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Required(isRequired = true, campo = "RG")
    @Size(min = 1, max = 15)
    @Column(name = "RG")
    private String rg;
    
    @Required(isRequired = true, campo = "DATA NASCIMENTO")
    @Column(name = "DATANASCIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;
    
    @Required(isRequired = true, campo = "SEXO")
    @Column(name = "SEXO")
    private BigDecimal sexo;
    
    @Required(isRequired = true, campo = "CPF")
    @Size(min = 1, max = 11)
    @Column(name = "CPF")
    private String cpf;
    
    @Size(max = 30)
    @Column(name = "NOMEPAI")
    private String nomePai;
    
    @Size(max = 30)
    @Column(name = "NOMEMAE")
    private String nomeMae;
    
    @Required(isRequired = true, campo = "ESTADO CIVIL")
    @Column(name = "ESTADOCIVIL")
    private BigDecimal estadoCivil;
    
    @JoinColumn(name = "IDPESSOA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pessoa pessoa;

    public PessoaFisica() {
    }

    public PessoaFisica(Long id) {
        this.id = id;
    }

    public PessoaFisica(Long id, String rg, Date datanascimento, BigDecimal sexo, String cpf, BigDecimal estadocivil) {
        this.id = id;
        this.rg = rg;
        this.dataNascimento = datanascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.estadoCivil = estadocivil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public BigDecimal getSexo() {
        return sexo;
    }

    public void setSexo(BigDecimal sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public BigDecimal getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(BigDecimal estadoCivil) {
        this.estadoCivil = estadoCivil;
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
        if (!(object instanceof PessoaFisica)) {
            return false;
        }
        PessoaFisica other = (PessoaFisica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Pessoafisica[ id=" + id + " ]";
    }
    
}
