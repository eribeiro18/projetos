/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IAtestado;
import br.com.optpronteletronico.entity.Atestado;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class AtestadoFacade {
    
    @Inject IAtestado atestadoImpl;

    public Atestado recuperarAtestadoPorId(Long id) throws OPTException {
        return atestadoImpl.recuperarAtestadoPorId(id);
    }

    public List<Atestado> recuperarTodosRegistrosPaginacao(Atestado atestado, Integer pag, Integer maxReg) throws OPTException {
        return atestadoImpl.recuperarTodosRegistrosPaginacao(atestado, pag, maxReg);
    }

    public Integer getTotal() {
        return atestadoImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        atestadoImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        atestadoImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return atestadoImpl.merge(T);
    }
    
}
