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
@Table(name = "ESTADO")
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_id_seq")
    @SequenceGenerator(name = "estado_id_seq", sequenceName = "estado_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Required(isRequired = true, campo = "CÓDIGO IBGE")
    @Size(min = 1, max = 2)
    @Column(name = "CODIBGE")
    private String codIbge;
    
    @Required(isRequired = true, campo = "DESCRIÇÃO")
    @Size(min = 1, max = 30)
    @Column(name = "DESCRICAO")
    private String descricao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private Set<Municipio> municipioSet;
    
    @Required(isRequired = true, campo = "PAIS")
    @JoinColumn(name = "IDPAIS", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pais pais;

    public Estado() {
    }

    public Estado(Long id) {
        this.id = id;
    }

    public Estado(Long id, String codibge, String descricao) {
        this.id = id;
        this.codIbge = codibge;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodIbge() {
        return codIbge;
    }

    public void setCodIbge(String codIbge) {
        this.codIbge = codIbge;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Set<Municipio> getMunicipioSet() {
        return municipioSet;
    }

    public void setMunicipioSet(Set<Municipio> municipioSet) {
        this.municipioSet = municipioSet;
    }

    public Pais getIdpais() {
        return pais;
    }

    public void setIdpais(Pais idpais) {
        this.pais = idpais;
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
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Estado[ id=" + id + " ]";
    }
    
}