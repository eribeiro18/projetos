/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IPreConsulta;
import br.com.optpronteletronico.entity.PreConsulta;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class PreConsultaFacade {
    
    @Inject IPreConsulta preConsultaImpl;

    public PreConsulta recuperarPreConsultaPorId(Long id) throws OPTException {
        return preConsultaImpl.recuperarPreConsultaPorId(id);
    }

    public List<PreConsulta> recuperarTodosRegistrosPaginacao(PreConsulta preConsulta, Integer pag, Integer maxReg) throws OPTException {
        return preConsultaImpl.recuperarTodosRegistrosPaginacao(preConsulta, pag, maxReg);
    }

    public Integer getTotal() {
        return preConsultaImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        preConsultaImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        preConsultaImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return preConsultaImpl.merge(T);
    }
    
}
