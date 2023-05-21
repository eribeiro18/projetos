/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IPapelUsuario;
import br.com.optpronteletronico.entity.PapelUsuario;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class PapelUsuarioFacade {
    
    @Inject IPapelUsuario papelusuarioImpl;

    public PapelUsuario recuperarPapelUsuarioPorId(Long id) throws OPTException {
        return papelusuarioImpl.recuperarPapelUsuarioPorId(id);
    }

    public Integer getTotal() {
        return papelusuarioImpl.getTotal();
    }

    public List<PapelUsuario> recuperarTodosRegistrosPaginacao(PapelUsuario papelUsuario, Integer pag, Integer maxReg) throws OPTException {
        return papelusuarioImpl.recuperarTodosRegistrosPaginacao(papelUsuario, pag, maxReg);
    }

    public void delete(Object T) throws OPTException {
        papelusuarioImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        papelusuarioImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return papelusuarioImpl.merge(T);
    }
    
}
