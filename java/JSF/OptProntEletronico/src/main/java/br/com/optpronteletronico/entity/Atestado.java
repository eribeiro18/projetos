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
import javax.persistence.FetchType;
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
@Table(name = "ATESTADO")
public class Atestado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atestado_id_seq")
    @SequenceGenerator(name = "atestado_id_seq", sequenceName = "atestado_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Required(isRequired = true, campo = "QUANTIDADE DE DIAS")
    @Column(name = "QUANTDIAS")
    private int quantdias;
    
    @Required(isRequired = true, campo = "COMPLEMENTO")
    @Size(min = 1, max = 1000)
    @Column(name = "COMPLEMENTO")
    private String complemento;
    
    @Required(isRequired = true, campo = "CID")
    @JoinColumn(name = "IDCID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cid cid;
    
    @Required(isRequired = true, campo = "CONSULTA")
    @JoinColumn(name = "IDCONSULTA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Consulta consulta;

    public Atestado() {
    }

    public Atestado(Long id) {
        this.id = id;
    }

    public Atestado(Long id, int quantdias, String complemento) {
        this.id = id;
        this.quantdias = quantdias;
        this.complemento = complemento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantdias() {
        return quantdias;
    }

    public void setQuantdias(int quantdias) {
        this.quantdias = quantdias;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cid getCid() {
        return cid;
    }

    public void setCid(Cid cid) {
        this.cid = cid;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
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
        if (!(object instanceof Atestado)) {
            return false;
        }
        Atestado other = (Atestado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Atestado[ id=" + id + " ]";
    }
    
}
