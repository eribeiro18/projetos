/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IClinica;
import br.com.optpronteletronico.entity.Clinica;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class ClinicaFacade {
    
    @Inject IClinica clinicaImpl;

    public Clinica recuperarClinicaPorId(Long id) throws OPTException {
        return clinicaImpl.recuperarClinicaPorId(id);
    }

    public Integer getTotal() {
        return clinicaImpl.getTotal();
    }

    public List<Clinica> recuperarTodosRegistrosPaginacao(Clinica clinica, Integer pag, Integer maxReg) throws OPTException {
        return clinicaImpl.recuperarTodosRegistrosPaginacao(clinica, pag, maxReg);
    }

    public void delete(Object T) throws OPTException {
        clinicaImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        clinicaImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return clinicaImpl.merge(T);
    }
    
}
