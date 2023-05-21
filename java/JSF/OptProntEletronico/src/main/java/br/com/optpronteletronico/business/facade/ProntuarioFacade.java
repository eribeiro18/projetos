/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IProntuario;
import br.com.optpronteletronico.entity.Prontuario;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class ProntuarioFacade {
    
    @Inject IProntuario prontuarioImpl;

    public Prontuario recuperarProntuarioPorId(Long id) throws OPTException {
        return prontuarioImpl.recuperarProntuarioPorId(id);
    }

    public List<Prontuario> recuperarTodosRegistrosPaginacao(Prontuario prontuario, Integer pag, Integer maxReg) throws OPTException {
        return prontuarioImpl.recuperarTodosRegistrosPaginacao(prontuario, pag, maxReg);
    }

    public Integer getTotal() {
        return prontuarioImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        prontuarioImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        prontuarioImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return prontuarioImpl.merge(T);
    }
    
}
