/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IPosConsulta;
import br.com.optpronteletronico.entity.PosConsulta;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class PosConsultaFacade {
    
    @Inject IPosConsulta posConsultaImpl;

    public PosConsulta recuperarPosConsultaPorId(Long id) throws OPTException {
        return posConsultaImpl.recuperarPosConsultaPorId(id);
    }

    public List<PosConsulta> recuperarTodosRegistrosPaginacao(PosConsulta posConsulta, Integer pag, Integer maxReg) throws OPTException {
        return posConsultaImpl.recuperarTodosRegistrosPaginacao(posConsulta, pag, maxReg);
    }

    public Integer getTotal() {
        return posConsultaImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        posConsultaImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        posConsultaImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return posConsultaImpl.merge(T);
    }
    
}
