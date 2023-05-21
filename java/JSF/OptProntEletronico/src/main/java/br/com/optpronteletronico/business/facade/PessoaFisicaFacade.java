/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IPessoaFisica;
import br.com.optpronteletronico.entity.PessoaFisica;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class PessoaFisicaFacade {
    
    @Inject IPessoaFisica pessoaFisicaImpl;

    public PessoaFisica recuperarPessoaFisicaPorId(Long id) throws OPTException {
        return pessoaFisicaImpl.recuperarPessoaFisicaPorId(id);
    }

    public List<PessoaFisica> recuperarTodosRegistrosPaginacao(PessoaFisica pessoaFisica, Integer pag, Integer maxReg) throws OPTException {
        return pessoaFisicaImpl.recuperarTodosRegistrosPaginacao(pessoaFisica, pag, maxReg);
    }

    public Integer getTotal() {
        return pessoaFisicaImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        pessoaFisicaImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        pessoaFisicaImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return pessoaFisicaImpl.merge(T);
    }
    
}
