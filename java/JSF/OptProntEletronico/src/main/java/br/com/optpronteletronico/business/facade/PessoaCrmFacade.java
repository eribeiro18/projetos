/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IPessoaCrm;
import br.com.optpronteletronico.entity.PessoaCrm;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class PessoaCrmFacade {
    
    @Inject IPessoaCrm pessoaCrmImpl;

    public PessoaCrm recuperarPessoaCrmPorId(Long id) throws OPTException {
        return pessoaCrmImpl.recuperarPessoaCrmPorId(id);
    }

    public List<PessoaCrm> recuperarTodosRegistrosPaginacao(PessoaCrm pessoaCrm, Integer pag, Integer maxReg) throws OPTException {
        return pessoaCrmImpl.recuperarTodosRegistrosPaginacao(pessoaCrm, pag, maxReg);
    }

    public Integer getTotal() {
        return pessoaCrmImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        pessoaCrmImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        pessoaCrmImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return pessoaCrmImpl.merge(T);
    }
    
}
