/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.ICid;
import br.com.optpronteletronico.entity.Cid;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class CidFacade {
    
    @Inject ICid cidImpl;

    public Cid recuperarCidPorId(Long id) throws OPTException {
        return cidImpl.recuperarCidPorId(id);
    }

    public List<Cid> recuperarTodosRegistrosPaginacao(Cid cid, Integer pag, Integer maxReg) throws OPTException {
        return cidImpl.recuperarTodosRegistrosPaginacao(cid, pag, maxReg);
    }

    public Integer getTotal() {
        return cidImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        cidImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        cidImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return cidImpl.merge(T);
    }
    
}
