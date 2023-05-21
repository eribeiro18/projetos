/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IEstado;
import br.com.optpronteletronico.entity.Estado;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class EstadoFacade {
    
    @Inject IEstado estadoImpl;

    public Estado recuperarEstadoPorId(Long id) throws OPTException {
        return estadoImpl.recuperarEstadoPorId(id);
    }

    public List<Estado> recuperarTodosRegistrosPaginacao(Estado estado, Integer pag, Integer maxReg) throws OPTException {
        return estadoImpl.recuperarTodosRegistrosPaginacao(estado, pag, maxReg);
    }

    public Integer getTotal() {
        return estadoImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        estadoImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        estadoImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return estadoImpl.merge(T);
    }
    
}
