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
@Table(name = "PRONTUARIO")
public class Prontuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prontuario_id_seq")
    @SequenceGenerator(name = "prontuario_id_seq", sequenceName = "prontuario_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Required(isRequired = true, campo = "RESUMO")
    @Size(min = 1, max = 1000)
    @Column(name = "RESUMO")
    private String resumo;
    
    @Required(isRequired = true, campo = "DIAGNÓSTICO COMPLETO")
    @Size(min = 1, max = 4000)
    @Column(name = "DIAGNOSTICOCOMPLETO")
    private String diagnosticoCompleto;
    
    @Required(isRequired = true, campo = "CONSULTA")
    @JoinColumn(name = "IDCONSULTA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Consulta consulta;

    public Prontuario() {
    }

    public Prontuario(Long id) {
        this.id = id;
    }

    public Prontuario(Long id, String resumo, String diagnosticocompleto) {
        this.id = id;
        this.resumo = resumo;
        this.diagnosticoCompleto = diagnosticocompleto;
    }

    public String getDiagnosticoCompleto() {
        return diagnosticoCompleto;
    }

    public void setDiagnosticoCompleto(String diagnosticoCompleto) {
        this.diagnosticoCompleto = diagnosticoCompleto;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
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
        if (!(object instanceof Prontuario)) {
            return false;
        }
        Prontuario other = (Prontuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Prontuario[ id=" + id + " ]";
    }
    
}
