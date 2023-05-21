/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.ITipoPessoa;
import br.com.optpronteletronico.entity.TipoPessoa;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class TipoPessoaFacade {
    
    @Inject ITipoPessoa tipoPessoaImpl;

    public void delete(Object T) throws OPTException{
        tipoPessoaImpl.delete(T);
    }

    public void persist(Object T) throws OPTException{
        tipoPessoaImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return tipoPessoaImpl.merge(T);
    }

    public List<TipoPessoa> recuperar() throws OPTException {
        return tipoPessoaImpl.recuperar();
    }

    public TipoPessoa recuperarTipoPessoaPorId(Long id) throws OPTException {
        return tipoPessoaImpl.recuperarTipoPessoaPorId(id);
    }
}
