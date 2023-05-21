/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IMunicipio;
import br.com.optpronteletronico.entity.Municipio;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class MunicipioFacade {
    
    @Inject IMunicipio municipioImpl;

    public Municipio recuperarMunicipioPorId(Long id) throws OPTException {
        return municipioImpl.recuperarMunicipioPorId(id);
    }

    public List<Municipio> recuperarTodosRegistrosPaginacao(Municipio municipio, Integer pag, Integer maxReg) throws OPTException {
        return municipioImpl.recuperarTodosRegistrosPaginacao(municipio, pag, maxReg);
    }

    public Integer getTotal() {
        return municipioImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        municipioImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        municipioImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return municipioImpl.merge(T);
    }
    
}
