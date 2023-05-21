/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IConsulta;
import br.com.optpronteletronico.entity.Consulta;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class ConsultaFacade {
    
    @Inject IConsulta consultaImpl;

    public Consulta recuperarConsultaPorId(Long id) throws OPTException {
        return consultaImpl.recuperarConsultaPorId(id);
    }

    public List<Consulta> recuperarTodosRegistrosPaginacao(Consulta consulta, Integer pag, Integer maxReg) throws OPTException {
        return consultaImpl.recuperarTodosRegistrosPaginacao(consulta, pag, maxReg);
    }

    public Integer getTotal() {
        return consultaImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        consultaImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        consultaImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return consultaImpl.merge(T);
    }
    
}
