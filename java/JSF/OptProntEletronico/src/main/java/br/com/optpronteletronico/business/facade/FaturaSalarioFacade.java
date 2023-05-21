/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IFaturaSalario;
import br.com.optpronteletronico.entity.FaturaSalario;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class FaturaSalarioFacade {
    
    @Inject IFaturaSalario faturaSalarioImpl;

    public FaturaSalario recuperarFaturaSalarioPorId(Long id) throws OPTException {
        return faturaSalarioImpl.recuperarFaturaSalarioPorId(id);
    }

    public List<FaturaSalario> recuperarTodosRegistrosPaginacao(FaturaSalario faturaSalario, Integer pag, Integer maxReg) throws OPTException {
        return faturaSalarioImpl.recuperarTodosRegistrosPaginacao(faturaSalario, pag, maxReg);
    }

    public Integer getTotal() {
        return faturaSalarioImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        faturaSalarioImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        faturaSalarioImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return faturaSalarioImpl.merge(T);
    }
    
}
