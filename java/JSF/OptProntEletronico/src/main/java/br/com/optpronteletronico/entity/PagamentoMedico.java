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

/**
 *
 * @author evandro
 */
@Entity
@Table(name = "PAGAMENTOMEDICO")
public class PagamentoMedico implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pagamentoMedico_id_seq")
    @SequenceGenerator(name = "pagamentoMedico_id_seq", sequenceName = "pagamentoMedico_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagamentoMedico", fetch = FetchType.LAZY)
    private Set<PagamentoMedicosMensal> pagamentoMedicosMensalSet;
    
    @Required(isRequired = true, campo = "CLINICA")
    @JoinColumn(name = "IDCLINICA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Clinica clinica;
    
    @Required(isRequired = true, campo = "MÉDICO")
    @JoinColumn(name = "IDPESSOACRM", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PessoaCrm pessoaCrm;

    public PagamentoMedico() {
    }

    public PagamentoMedico(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PagamentoMedicosMensal> getPagamentomedicosmensalSet() {
        return pagamentoMedicosMensalSet;
    }

    public void setPagamentomedicosmensalSet(Set<PagamentoMedicosMensal> pagamentomedicosmensalSet) {
        this.pagamentoMedicosMensalSet = pagamentomedicosmensalSet;
    }

    public Set<PagamentoMedicosMensal> getPagamentoMedicosMensalSet() {
        return pagamentoMedicosMensalSet;
    }

    public void setPagamentoMedicosMensalSet(Set<PagamentoMedicosMensal> pagamentoMedicosMensalSet) {
        this.pagamentoMedicosMensalSet = pagamentoMedicosMensalSet;
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
        if (!(object instanceof PagamentoMedico)) {
            return false;
        }
        PagamentoMedico other = (PagamentoMedico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Pagamentomedico[ id=" + id + " ]";
    }
    
}
