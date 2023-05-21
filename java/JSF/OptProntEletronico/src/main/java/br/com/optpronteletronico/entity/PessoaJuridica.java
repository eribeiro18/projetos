/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.entity;

import br.com.optpronteletronico.interceptors.impl.Required;
import java.io.Serializable;
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
@Table(name = "PESSOAJURIDICA")
public class PessoaJuridica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoaJuridica_id_seq")
    @SequenceGenerator(name = "pessoaJuridica_id_seq", sequenceName = "pessoaJuridica_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Required(isRequired = true, campo = "CNPJ")
    @Size(min = 1, max = 14)
    @Column(name = "CNPJ")
    private String cnpj;

    @Required(isRequired = true, campo = "CNAE")
    @Size(min = 1, max = 10)
    @Column(name = "CNAE")
    private String cnae;

    @Required(isRequired = true, campo = "INSCRIÇÃO ESTADUAL")
    @Size(min = 1, max = 20)
    @Column(name = "INSCESTADUAL")
    private String inscEstadual;

    @Required(isRequired = true, campo = "DESCRIÇÃO")
    @JoinColumn(name = "IDPESSOA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pessoa pessoa;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Long id) {
        this.id = id;
    }

    public PessoaJuridica(Long id, String cnpj, String cnae, String inscestadual) {
        this.id = id;
        this.cnpj = cnpj;
        this.cnae = cnae;
        this.inscEstadual = inscestadual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getInscEstadual() {
        return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
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
        if (!(object instanceof PessoaJuridica)) {
            return false;
        }
        PessoaJuridica other = (PessoaJuridica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Pessoajuridica[ id=" + id + " ]";
    }
}
