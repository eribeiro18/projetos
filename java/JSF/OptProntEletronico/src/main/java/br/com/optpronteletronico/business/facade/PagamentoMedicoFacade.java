/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IPagamentoMedico;
import br.com.optpronteletronico.entity.PagamentoMedico;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class PagamentoMedicoFacade {
    
    @Inject IPagamentoMedico pagamentoMedicoImpl;

    public PagamentoMedico recuperarPagamentoMedicoPorId(Long id) throws OPTException {
        return pagamentoMedicoImpl.recuperarPagamentoMedicoPorId(id);
    }

    public List<PagamentoMedico> recuperarTodosRegistrosPaginacao(PagamentoMedico pagamentoMedico, Integer pag, Integer maxReg) throws OPTException {
        return pagamentoMedicoImpl.recuperarTodosRegistrosPaginacao(pagamentoMedico, pag, maxReg);
    }

    public Integer getTotal() {
        return pagamentoMedicoImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        pagamentoMedicoImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        pagamentoMedicoImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return pagamentoMedicoImpl.merge(T);
    }
    
}
