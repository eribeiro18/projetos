/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author evandro
 */
@Entity
@Table(name = "USUARIO_PAPELUSUARIO")
public class UsuarioPapelUsuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_papelUsuario_id_seq")
    @SequenceGenerator(name = "usuario_papelUsuario_id_seq", sequenceName = "usuario_papelUsuario_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    
    @JoinColumn(name = "IDPAPELUSUARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PapelUsuario papelUsuario;
    
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    
    public UsuarioPapelUsuario() {
    }

    public UsuarioPapelUsuario(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public PapelUsuario getPapelUsuario() {
        return papelUsuario;
    }

    public void setPapelUsuario(PapelUsuario papelUsuario) {
        this.papelUsuario = papelUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof UsuarioPapelUsuario)) {
            return false;
        }
        UsuarioPapelUsuario other = (UsuarioPapelUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.UsuarioPapelusuario[ id=" + id + " ]";
    }
    
}
