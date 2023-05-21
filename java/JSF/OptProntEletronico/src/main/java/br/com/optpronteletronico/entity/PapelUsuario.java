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
@Table(name = "PAPELUSUARIO")
public class PapelUsuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "papelUsuario_id_seq")
    @SequenceGenerator(name = "papelUsuario_id_seq", sequenceName = "papelUsuario_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Required(isRequired = true, campo = "PAPEL")
    @Size(min = 1, max = 30)
    @Column(name = "PAPEL")
    private String papel;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "papelUsuario", fetch = FetchType.LAZY)
    private Set<UsuarioPapelUsuario> usuarioPapelusuarioSet;

    public PapelUsuario() {
    }

    public PapelUsuario(Long id) {
        this.id = id;
    }

    public PapelUsuario(Long id, String papel) {
        this.id = id;
        this.papel = papel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    @XmlTransient
    public Set<UsuarioPapelUsuario> getUsuarioPapelusuarioSet() {
        return usuarioPapelusuarioSet;
    }

    public void setUsuarioPapelusuarioSet(Set<UsuarioPapelUsuario> usuarioPapelusuarioSet) {
        this.usuarioPapelusuarioSet = usuarioPapelusuarioSet;
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
        if (!(object instanceof PapelUsuario)) {
            return false;
        }
        PapelUsuario other = (PapelUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Papelusuario[ id=" + id + " ]";
    }
    
}
