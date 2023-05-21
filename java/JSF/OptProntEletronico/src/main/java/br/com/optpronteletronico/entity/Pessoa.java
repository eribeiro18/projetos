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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author evandro
 */
@Entity
@Table(name = "PESSOA")
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_id_seq")
    @SequenceGenerator(name = "pessoa_id_seq", sequenceName = "pessoa_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Required(isRequired = true, campo = "CÓDIGO")
    @Size(min = 1, max = 30)
    @Column(name = "CODIGO")
    private String codigo;
    
    @Required(isRequired = true, campo = "NOME FANTASIA")
    @Size(min = 1, max = 90)
    @Column(name = "NOMEFANTASIA")
    private String nomeFantasia;
    
    @Required(isRequired = true, campo = "RAZAO SOCIAL")
    @Size(min = 1, max = 50)
    @Column(name = "RAZAOSOCIAL")
    private String razaoSocial;
    
    @Required(isRequired = true, campo = "DATA CADASTRO")
    @Column(name = "DATACADASTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    
    @Size(max = 30)
    @Column(name = "APELIDO")
    private String apelido;
    
    @Required(isRequired = true, campo = "TELEFONE")
    @Size(min = 1, max = 11)
    @Column(name = "TELEFONE")
    private String telefone;
    
    @Size(max = 11)
    @Column(name = "TELEFONE2")
    private String telefone2;
    
    @Size(max = 12)
    @Column(name = "CELULAR")
    private String celular;
    
    @Required(isRequired = true, campo = "NATUREZA")
    @Column(name = "NATUREZA")
    private BigDecimal natureza;
    
    @Size(max = 30)
    @Column(name = "EMAIL")
    private String email;
    
    @Size(max = 50)
    @Column(name = "PROFISSAO")
    private String profissao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch = FetchType.LAZY)
    private Set<Clinica> clinicaSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch = FetchType.LAZY)
    private Set<PessoaCrm> pessoaCrmSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch = FetchType.LAZY)
    private Set<AgendaConsulta> agendaConsultaSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch = FetchType.LAZY)
    private Set<PessoaFisica> pessoaFisicaSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch = FetchType.LAZY)
    private Set<Usuario> usuarioSet;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch = FetchType.LAZY)
    private Set<PessoaJuridica> pessoaJuridicaSet;
    
    @Required(isRequired = true, campo = "TIPO PESSOA")
    @JoinColumn(name = "IDTIPO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoPessoa tipo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch = FetchType.LAZY)
    private Set<Endereco> enderecoSet;

    public Pessoa() {
    }

    public Pessoa(Long id) {
        this.id = id;
    }

    public Pessoa(Long id, String codigo, String nomefantasia, String razaosocial, Date datacadastro, String telefone, BigDecimal natureza) {
        this.id = id;
        this.codigo = codigo;
        this.nomeFantasia = nomefantasia;
        this.razaoSocial = razaosocial;
        this.dataCadastro = datacadastro;
        this.telefone = telefone;
        this.natureza = natureza;
    }

    public BigDecimal getNatureza() {
        return natureza;
    }

    public void setNatureza(BigDecimal natureza) {
        this.natureza = natureza;
    }

    public Set<PessoaCrm> getPessoaCrmSet() {
        return pessoaCrmSet;
    }

    public void setPessoaCrmSet(Set<PessoaCrm> pessoaCrmSet) {
        this.pessoaCrmSet = pessoaCrmSet;
    }

    public Set<AgendaConsulta> getAgendaConsultaSet() {
        return agendaConsultaSet;
    }

    public void setAgendaConsultaSet(Set<AgendaConsulta> agendaConsultaSet) {
        this.agendaConsultaSet = agendaConsultaSet;
    }

    public Set<PessoaFisica> getPessoaFisicaSet() {
        return pessoaFisicaSet;
    }

    public void setPessoaFisicaSet(Set<PessoaFisica> pessoaFisicaSet) {
        this.pessoaFisicaSet = pessoaFisicaSet;
    }

    public Set<PessoaJuridica> getPessoaJuridicaSet() {
        return pessoaJuridicaSet;
    }

    public void setPessoaJuridicaSet(Set<PessoaJuridica> pessoaJuridicaSet) {
        this.pessoaJuridicaSet = pessoaJuridicaSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public Set<Clinica> getClinicaSet() {
        return clinicaSet;
    }

    public void setClinicaSet(Set<Clinica> clinicaSet) {
        this.clinicaSet = clinicaSet;
    }

    public Set<Usuario> getUsuarioSet() {
        return usuarioSet;
    }

    public void setUsuarioSet(Set<Usuario> usuarioSet) {
        this.usuarioSet = usuarioSet;
    }

    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.optpronteletronico.entity.Pessoa[ id=" + id + " ]";
    }
    
}
