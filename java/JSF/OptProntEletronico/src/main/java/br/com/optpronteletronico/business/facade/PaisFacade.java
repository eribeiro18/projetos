/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IPais;
import br.com.optpronteletronico.entity.Pais;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class PaisFacade {
    
    @Inject IPais paisImpl;

    public Pais recuperarPaisPorId(Long id) throws OPTException {
        return paisImpl.recuperarPaisPorId(id);
    }

    public Integer getTotal() {
        return paisImpl.getTotal();
    }

    public List<Pais> recuperarTodosRegistrosPaginacao(Pais pais, Integer pag, Integer maxReg) throws OPTException {
        return paisImpl.recuperarTodosRegistrosPaginacao(pais, pag, maxReg);
    }

    public void delete(Object T) throws OPTException {
        paisImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        paisImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return paisImpl.merge(T);
    }
    
}
