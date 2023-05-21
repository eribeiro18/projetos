/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.entity;

import br.com.optpronteletronico.interceptors.impl.Required;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author evandro
 */
@Entity
@Table(name = "POSCONSULTA")
public class PosConsulta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posConsulta_id_seq")
    @SequenceGenerator(name = "posConsulta_id_seq", sequenceName = "posConsulta_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Required(isRequired = true, campo = "FINALIZA CONSULTA")
    @Column(name = "FINALIZACONSULTA")
    private BigInteger finalizaConsulta;

    @Required(isRequired = true, campo = "CONSULTA")
    @JoinColumn(name = "IDCONSULTA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Consulta consulta;

    public PosConsulta() {
    }

    public PosConsulta(Long id) {
        this.id = id;
    }

    public PosConsulta(Long id, BigInteger finalizaconsulta) {
        this.id = id;
        this.finalizaConsulta = finalizaconsulta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getFinalizaConsulta() {
        return finalizaConsulta;
    }

    public void setFinalizaConsulta(BigInteger finalizaConsulta) {
        this.finalizaConsulta = finalizaConsulta;
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
        if (!(object instanceof PosConsulta)) {
            return false;
        }
        PosConsulta other = (PosConsulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Posconsulta[ id=" + id + " ]";
    }

}
