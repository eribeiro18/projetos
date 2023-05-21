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
@Table(name = "MUNICIPIO")
public class Municipio implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipio_id_seq")
    @SequenceGenerator(name = "municipio_id_seq", sequenceName = "municipio_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Required(isRequired = true, campo = "CÓDIGO IBGE")
    @Size(min = 1, max = 10)
    @Column(name = "CODIBGE")
    private String codIbge;
    
    @Required(isRequired = true, campo = "DESCRIÇÃO")
    @Size(min = 1, max = 50)
    @Column(name = "DESCRICAO")
    private String descricao;
    
    @Required(isRequired = true, campo = "ESTADO")
    @JoinColumn(name = "IDESTADO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estado estado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipio", fetch = FetchType.LAZY)
    private Set<Endereco> enderecoSet;

    public Municipio() {
    }

    public Municipio(Long id) {
        this.id = id;
    }

    public Municipio(Long id, String codibge, String descricao) {
        this.id = id;
        this.codIbge = codibge;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getCodIbge() {
        return codIbge;
    }

    public void setCodIbge(String codIbge) {
        this.codIbge = codIbge;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Endereco> getEnderecoSet() {
        return enderecoSet;
    }

    public void setEnderecoSet(Set<Endereco> enderecoSet) {
        this.enderecoSet = enderecoSet;
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
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Municipio[ id=" + id + " ]";
    }
    
}
