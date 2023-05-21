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
@Table(name = "CLINICA")
public class Clinica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clinica_id_seq")
    @SequenceGenerator(name = "clinica_id_seq", sequenceName = "clinica_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Required(isRequired = true, campo = "LOGO")
    @Column(name = "LOGOTIPO")
    private byte[] logoTipo;

    @Required(isRequired = true, campo = "CLÍNICA DEFAULT")
    @Column(name = "CLINICADEFAULT")
    private Boolean clinicaDefault;

    @JoinColumn(name = "IDPESSOA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pessoa pessoa;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinica", fetch = FetchType.LAZY)
    private Set<AgendaConsulta> agendaConsultaSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinica", fetch = FetchType.LAZY)
    private Set<PagamentoMedico> pagamentoMedicoSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinica", fetch = FetchType.LAZY)
    private Set<FaturaSalario> faturaSalarioSet;

    public Clinica() {
    }

    public Clinica(Long id) {
        this.id = id;
    }

    public Clinica(Long id, byte[] logotipo, Boolean clinicadefault) {
        this.id = id;
        this.logoTipo = logotipo;
        this.clinicaDefault = clinicadefault;
    }

    public Long getId() {
        return id;
    }

    public byte[] getLogoTipo() {
        return logoTipo;
    }

    public void setLogoTipo(byte[] logoTipo) {
        this.logoTipo = logoTipo;
    }

    public Boolean getClinicaDefault() {
        return clinicaDefault;
    }

    public void setClinicaDefault(Boolean clinicaDefault) {
        this.clinicaDefault = clinicaDefault;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clinica)) {
            return false;
        }
        Clinica other = (Clinica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Clinica[ id=" + id + " ]";
    }
}
