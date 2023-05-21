/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IPessoa;
import br.com.optpronteletronico.entity.Pessoa;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class PessoaFacade {
    
    @Inject IPessoa pessoaImpl;

    public Pessoa recuperarPessoaPorId(Long id) throws OPTException {
        return pessoaImpl.recuperarPessoaPorId(id);
    }

    public List<Pessoa> recuperarTodosRegistrosPaginacao(Pessoa pessoaFisica, Integer pag, Integer maxReg) throws OPTException {
        return pessoaImpl.recuperarTodosRegistrosPaginacao(pessoaFisica, pag, maxReg);
    }

    public Integer getTotal() {
        return pessoaImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        pessoaImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        pessoaImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return pessoaImpl.merge(T);
    }
    
}
