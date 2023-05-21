/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.entity;

import br.com.optpronteletronico.interceptors.impl.Required;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "PRECONSULTA")
public class PreConsulta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preConsulta_id_seq")
    @SequenceGenerator(name = "preConsulta_id_seq", sequenceName = "preConsulta_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Required(isRequired = true, campo = "DESCRIÇÃO PACIENTE")
    @Size(min = 1, max = 1000)
    @Column(name = "DESCRPACIENTE")
    private String descrPaciente;
    
    @Required(isRequired = true, campo = "ALTURA")
    @Column(name = "ALTURA")
    private BigDecimal altura;
    
    @Required(isRequired = true, campo = "PRESSÃO ARTERIAL")
    @Size(min = 1, max = 5)
    @Column(name = "PRESSAOARTERIAL")
    private String pressaoArterial;
    
    @Required(isRequired = true, campo = "PESO")
    @Column(name = "PESO")
    private BigDecimal peso;
    
    @Required(isRequired = true, campo = "TEMPERATURA")
    @Column(name = "TEMPERATURA")
    private BigDecimal temperatura;
    
    @JoinColumn(name = "IDAGENDACONNSULTA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AgendaConsulta agendaConsulta;
    
    @Required(isRequired = true, campo = "USUARIO")
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preConsulta", fetch = FetchType.LAZY)
    private Set<Consulta> consultaSet;

    public PreConsulta() {
    }

    public PreConsulta(Long id) {
        this.id = id;
    }

    public PreConsulta(Long id, String descrpaciente, BigDecimal altura, String pressaoarterial, BigDecimal peso, BigDecimal temperatura) {
        this.id = id;
        this.descrPaciente = descrpaciente;
        this.altura = altura;
        this.pressaoArterial = pressaoarterial;
        this.peso = peso;
        this.temperatura = temperatura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        this.altura = altura;
    }

    public String getDescrPaciente() {
        return descrPaciente;
    }

    public void setDescrPaciente(String descrPaciente) {
        this.descrPaciente = descrPaciente;
    }

    public String getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(String pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(BigDecimal temperatura) {
        this.temperatura = temperatura;
    }

    public AgendaConsulta getIdagendaconnsulta() {
        return agendaConsulta;
    }

    public void setIdagendaconnsulta(AgendaConsulta idagendaconnsulta) {
        this.agendaConsulta = idagendaconnsulta;
    }

    public AgendaConsulta getAgendaConsulta() {
        return agendaConsulta;
    }

    public void setAgendaConsulta(AgendaConsulta agendaConsulta) {
        this.agendaConsulta = agendaConsulta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Set<Consulta> getConsultaSet() {
        return consultaSet;
    }

    public void setConsultaSet(Set<Consulta> consultaSet) {
        this.consultaSet = consultaSet;
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
        if (!(object instanceof PreConsulta)) {
            return false;
        }
        PreConsulta other = (PreConsulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Preconsulta[ id=" + id + " ]";
    }
    
}
