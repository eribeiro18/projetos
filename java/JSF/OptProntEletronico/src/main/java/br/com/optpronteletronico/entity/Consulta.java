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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author evandro
 */
@Entity
@Table(name = "CONSULTA")
public class Consulta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consulta_id_seq")
    @SequenceGenerator(name = "consulta_id_seq", sequenceName = "consulta_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Required(isRequired = true, campo = "DIAGNOSTICO MÉDICO")
    @Size(min = 1, max = 4000)
    @Column(name = "DIAGNOSTICOMEDICO")
    private String diagnosticoMedico;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consulta", fetch = FetchType.LAZY)
    private Set<PosConsulta> posConsultaSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consulta", fetch = FetchType.LAZY)
    private Set<Receita> receitaSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consulta", fetch = FetchType.LAZY)
    private Set<Atestado> atestadoSet;

    @JoinColumn(name = "IDPRECONSULTA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PreConsulta preConsulta;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consulta", fetch = FetchType.LAZY)
    private Set<Prontuario> prontuarioSet;

    public Consulta() {
    }

    public Consulta(Long id) {
        this.id = id;
    }

    public Consulta(Long id, String diagnosticomedico) {
        this.id = id;
        this.diagnosticoMedico = diagnosticomedico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnosticoMedico() {
        return diagnosticoMedico;
    }

    public void setDiagnosticoMedico(String diagnosticoMedico) {
        this.diagnosticoMedico = diagnosticoMedico;
    }

    public Set<PosConsulta> getPosConsultaSet() {
        return posConsultaSet;
    }

    public void setPosConsultaSet(Set<PosConsulta> posConsultaSet) {
        this.posConsultaSet = posConsultaSet;
    }

    public PreConsulta getPreConsulta() {
        return preConsulta;
    }

    public void setPreConsulta(PreConsulta preConsulta) {
        this.preConsulta = preConsulta;
    }

    @XmlTransient
    public Set<Receita> getReceitaSet() {
        return receitaSet;
    }

    public void setReceitaSet(Set<Receita> receitaSet) {
        this.receitaSet = receitaSet;
    }

    @XmlTransient
    public Set<Atestado> getAtestadoSet() {
        return atestadoSet;
    }

    public void setAtestadoSet(Set<Atestado> atestadoSet) {
        this.atestadoSet = atestadoSet;
    }

    public PreConsulta getIdpreconsulta() {
        return preConsulta;
    }

    public void setIdpreconsulta(PreConsulta idpreconsulta) {
        this.preConsulta = idpreconsulta;
    }

    @XmlTransient
    public Set<Prontuario> getProntuarioSet() {
        return prontuarioSet;
    }

    public void setProntuarioSet(Set<Prontuario> prontuarioSet) {
        this.prontuarioSet = prontuarioSet;
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
        if (!(object instanceof Consulta)) {
            return false;
        }
        Consulta other = (Consulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Consulta[ id=" + id + " ]";
    }
    
}
