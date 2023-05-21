/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IAgendaConsulta;
import br.com.optpronteletronico.entity.AgendaConsulta;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class AgendaConsultaFacade {
    
    @Inject IAgendaConsulta agendaConsultaImpl;

    public AgendaConsulta recuperarAgendaConsultaPorId(Long id) throws OPTException {
        return agendaConsultaImpl.recuperarAgendaConsultaPorId(id);
    }

    public List<AgendaConsulta> recuperarTodosRegistrosPaginacao(AgendaConsulta agendaConsulta, Integer pag, Integer maxReg) throws OPTException {
        return agendaConsultaImpl.recuperarTodosRegistrosPaginacao(agendaConsulta, pag, maxReg);
    }

    public Integer getTotal() {
        return agendaConsultaImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        agendaConsultaImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        agendaConsultaImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return agendaConsultaImpl.merge(T);
    }
    
}
