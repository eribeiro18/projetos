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
@Table(name = "CID")
public class Cid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cid_id_seq")
    @SequenceGenerator(name = "cid_id_seq", sequenceName = "cid_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Required(isRequired = true, campo = "DESCRIÇÃO ABRECIADA")
    @Size(min = 1, max = 150)
    @Column(name = "DESCRABREV")
    private String descrabrev;
    
    @Required(isRequired = true, campo = "DESCRIÇÃO")
    @Size(min = 1, max = 150)
    @Column(name = "DESCRICAO")
    private String descricao;

    @Required(isRequired = true, campo = "CLASSIFICAÇÃO")
    @Size(min = 1, max = 4)
    @Column(name = "CLASSIFICACAO")
    private String classificacao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cid", fetch = FetchType.LAZY)
    private Set<Atestado> atestadoSet;

    public Cid() {
    }

    public Cid(Long id) {
        this.id = id;
    }

    public Cid(Long id, String descrabrev, String descricao, String classificacao) {
        this.id = id;
        this.descrabrev = descrabrev;
        this.descricao = descricao;
        this.classificacao = classificacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrabrev() {
        return descrabrev;
    }

    public void setDescrabrev(String descrabrev) {
        this.descrabrev = descrabrev;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    @XmlTransient
    public Set<Atestado> getAtestadoSet() {
        return atestadoSet;
    }

    public void setAtestadoSet(Set<Atestado> atestadoSet) {
        this.atestadoSet = atestadoSet;
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
        if (!(object instanceof Cid)) {
            return false;
        }
        Cid other = (Cid) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Cid[ id=" + id + " ]";
    }

}
