/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.entity;

import br.com.optpronteletronico.interceptors.impl.Required;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author evandro
 */
@Entity
@Table(name = "AGENDACONSULTA")
public class AgendaConsulta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agendaConsulta_id_seq")
    @SequenceGenerator(name = "agendaConsulta_id_seq", sequenceName = "agendaConsulta_id_seq", allocationSize = 1)
    private Long id;

    @Required(isRequired = true, campo = "DATA CONSULTA")
    @Column(name = "DATAHORACONSULTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraConsulta;

    @Required(isRequired = true, campo = "DATA AGENDAMENTO")
    @Column(name = "DATAAGENDAMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAgendamento;

    @JoinColumn(name = "IDCLINICA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Clinica clinica;

    @Required(isRequired = true, campo = "PACIENTE")
    @JoinColumn(name = "IDPESSOA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pessoa pessoa;

    @Required(isRequired = true, campo = "MEDICO")
    @JoinColumn(name = "IDPESSOACRM", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PessoaCrm pessoaCrm;

    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agendaConsulta", fetch = FetchType.LAZY)
    private Set<PreConsulta> preConsultaSet;

    public AgendaConsulta() {
    }

    public AgendaConsulta(Long id) {
        this.id = id;
    }

    public Date getDataHoraConsulta() {
        return dataHoraConsulta;
    }

    public void setDataHoraConsulta(Date dataHoraConsulta) {
        this.dataHoraConsulta = dataHoraConsulta;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PessoaCrm getPessoaCrm() {
        return pessoaCrm;
    }

    public void setPessoaCrm(PessoaCrm pessoaCrm) {
        this.pessoaCrm = pessoaCrm;
    }

    public AgendaConsulta(Long id, Date datahoraconsulta, Date dataagendamento) {
        this.id = id;
        this.dataHoraConsulta = datahoraconsulta;
        this.dataAgendamento = dataagendamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getIdpessoa() {
        return pessoa;
    }

    public void setIdpessoa(Pessoa idpessoa) {
        this.pessoa = idpessoa;
    }

    public PessoaCrm getIdpessoacrm() {
        return pessoaCrm;
    }

    public void setIdpessoacrm(PessoaCrm idpessoacrm) {
        this.pessoaCrm = idpessoacrm;
    }

    @XmlTransient
    public Set<PreConsulta> getPreConsultaSet() {
        return preConsultaSet;
    }

    public void setPreconsultaSet(Set<PreConsulta> preconsultaSet) {
        this.preConsultaSet = preconsultaSet;
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
        if (!(object instanceof AgendaConsulta)) {
            return false;
        }
        AgendaConsulta other = (AgendaConsulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Agendaconsulta[ id=" + id + " ]";
    }
}
