/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IPagamentoMedicoMensal;
import br.com.optpronteletronico.entity.PagamentoMedicosMensal;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class PagamentoMedicoMensalFacade {
    
    @Inject IPagamentoMedicoMensal pagamentoMedicoMensalImpl;

    public PagamentoMedicosMensal recuperarPagamentoMedicosMensalPorId(Long id) throws OPTException {
        return pagamentoMedicoMensalImpl.recuperarPagamentoMedicosMensalPorId(id);
    }

    public List<PagamentoMedicosMensal> recuperarTodosRegistrosPaginacao(PagamentoMedicosMensal pagamentoMedicoMensal, Integer pag, Integer maxReg) throws OPTException {
        return pagamentoMedicoMensalImpl.recuperarTodosRegistrosPaginacao(pagamentoMedicoMensal, pag, maxReg);
    }

    public Integer getTotal() {
        return pagamentoMedicoMensalImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        pagamentoMedicoMensalImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        pagamentoMedicoMensalImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return pagamentoMedicoMensalImpl.merge(T);
    }
    
}
