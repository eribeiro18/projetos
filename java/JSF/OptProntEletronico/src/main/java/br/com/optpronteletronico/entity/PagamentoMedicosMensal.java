/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.entity;

import br.com.optpronteletronico.interceptors.impl.Required;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author evandro
 */
@Entity
@Table(name = "PAGAMENTOMEDICOSMENSAL")
public class PagamentoMedicosMensal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pagamentoMedicosMensal_id_seq")
    @SequenceGenerator(name = "pagamentoMedicosMensal_id_seq", sequenceName = "pagamentoMedicosMensal_id_seq", allocationSize = 1)    
    @Column(name = "ID")
    private Long id;
    
    @Required(isRequired = true, campo = "VALOR")
    @Column(name = "VALOR")
    private BigDecimal valor;
    
    @Required(isRequired = true, campo = "DATA PAGAMENTO")
    @Column(name = "DATAPAGAMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;
    
    @Required(isRequired = true, campo = "PAGAMENTO MÉDICO")
    @JoinColumn(name = "IDPAGAMENTOMEDICO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PagamentoMedico pagamentoMedico;

    public PagamentoMedicosMensal() {
    }

    public PagamentoMedicosMensal(Long id) {
        this.id = id;
    }

    public PagamentoMedicosMensal(Long id, BigDecimal valor, Date datapagamento) {
        this.id = id;
        this.valor = valor;
        this.dataPagamento = datapagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public PagamentoMedico getPagamentoMedico() {
        return pagamentoMedico;
    }

    public void setPagamentoMedico(PagamentoMedico pagamentoMedico) {
        this.pagamentoMedico = pagamentoMedico;
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
        if (!(object instanceof PagamentoMedicosMensal)) {
            return false;
        }
        PagamentoMedicosMensal other = (PagamentoMedicosMensal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Pagamentomedicosmensal[ id=" + id + " ]";
    }
    
}
