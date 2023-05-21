/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.entity;

import br.com.optpronteletronico.interceptors.impl.Required;
import java.io.Serializable;
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
@Table(name = "PESSOACRM")
public class PessoaCrm implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoaCrm_id_seq")
    @SequenceGenerator(name = "pessoaCrm_id_seq", sequenceName = "pessoaCrm_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
     
    @Required(isRequired = true, campo = "CRM")
    @Size(min = 1, max = 30)
    @Column(name = "CRM")
    private String crm;
    
    @Required(isRequired = true, campo = "ASSINATURA MÉDICO")
    @Column(name = "ASSINATURAMEDICO")
    private byte[] assinaturaMedico;
    
    @Required(isRequired = true, campo = "ESPECIALIDADE")
    @Size(min = 1, max = 30)
    @Column(name = "ESPECIALIDADE")
    private String especialidade;
    
    @Required(isRequired = true, campo = "PESSOA")
    @JoinColumn(name = "IDPESSOA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pessoa pessoa;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaCrm", fetch = FetchType.LAZY)
    private Set<AgendaConsulta> agendaConsultaSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaCrm", fetch = FetchType.LAZY)
    private Set<PagamentoMedico> pagamentoMedicoSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaCrm", fetch = FetchType.LAZY)
    private Set<FaturaSalario> faturaSalarioSet;

    public PessoaCrm() {
    }

    public PessoaCrm(Long id) {
        this.id = id;
    }

    public PessoaCrm(Long id, String crm, byte[] assinaturamedico, String especialidade) {
        this.id = id;
        this.crm = crm;
        this.assinaturaMedico = assinaturamedico;
        this.especialidade = especialidade;
    }

    public Long getId() {
        return id;
    }

    public byte[] getAssinaturaMedico() {
        return assinaturaMedico;
    }

    public void setAssinaturaMedico(byte[] assinaturaMedico) {
        this.assinaturaMedico = assinaturaMedico;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Set<AgendaConsulta> getAgendaConsultaSet() {
        return agendaConsultaSet;
    }

    public void setAgendaConsultaSet(Set<AgendaConsulta> agendaConsultaSet) {
        this.agendaConsultaSet = agendaConsultaSet;
    }

    public Set<PagamentoMedico> getPagamentoMedicoSet() {
        return pagamentoMedicoSet;
    }

    public void setPagamentoMedicoSet(Set<PagamentoMedico> pagamentoMedicoSet) {
        this.pagamentoMedicoSet = pagamentoMedicoSet;
    }

    public Set<FaturaSalario> getFaturaSalarioSet() {
        return faturaSalarioSet;
    }

    public void setFaturaSalarioSet(Set<FaturaSalario> faturaSalarioSet) {
        this.faturaSalarioSet = faturaSalarioSet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
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
        if (!(object instanceof PessoaCrm)) {
            return false;
        }
        PessoaCrm other = (PessoaCrm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Pessoacrm[ id=" + id + " ]";
    }
    
}
