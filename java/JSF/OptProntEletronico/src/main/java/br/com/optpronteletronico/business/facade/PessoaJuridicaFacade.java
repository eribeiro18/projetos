/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IPessoaJuridica;
import br.com.optpronteletronico.entity.PessoaJuridica;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class PessoaJuridicaFacade {
    
    @Inject IPessoaJuridica pessoaJuridicaImpl;

    public PessoaJuridica recuperarPessoaJuridicaPorId(Long id) throws OPTException {
        return pessoaJuridicaImpl.recuperarPessoaJuridicaPorId(id);
    }

    public List<PessoaJuridica> recuperarTodosRegistrosPaginacao(PessoaJuridica pessoaJuridica, Integer pag, Integer maxReg) throws OPTException {
        return pessoaJuridicaImpl.recuperarTodosRegistrosPaginacao(pessoaJuridica, pag, maxReg);
    }

    public Integer getTotal() {
        return pessoaJuridicaImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        pessoaJuridicaImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        pessoaJuridicaImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return pessoaJuridicaImpl.merge(T);
    }
    
}
