/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IUsuarioPapelUsuario;
import br.com.optpronteletronico.entity.UsuarioPapelUsuario;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class UsuarioPapelUsuarioFacade {
    
    @Inject IUsuarioPapelUsuario usuarioPapelUsuarioImpl;

    public UsuarioPapelUsuario recuperarUsuarioPapelUsuarioPorId(Long id) throws OPTException {
        return usuarioPapelUsuarioImpl.recuperarUsuarioPapelUsuarioPorId(id);
    }

    public List<UsuarioPapelUsuario> recuperarTodosRegistrosPaginacao(UsuarioPapelUsuario preConsulta, Integer pag, Integer maxReg) throws OPTException {
        return usuarioPapelUsuarioImpl.recuperarTodosRegistrosPaginacao(preConsulta, pag, maxReg);
    }

    public Integer getTotal() {
        return usuarioPapelUsuarioImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        usuarioPapelUsuarioImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        usuarioPapelUsuarioImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return usuarioPapelUsuarioImpl.merge(T);
    }
    
}
