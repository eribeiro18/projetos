/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IUsuario;
import br.com.optpronteletronico.entity.Usuario;
import br.com.optpronteletronico.exception.OPTException;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class UsuarioFacade {

    @Inject
    IUsuario usuarioImpl;

    public Usuario recuperarUsuarioPorId(Long id) throws OPTException {
        return usuarioImpl.recuperarUsuarioPorId(id);
    }

    public void delete(Object T) throws OPTException {
        usuarioImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        usuarioImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return usuarioImpl.merge(T);
    }

    public Usuario recuperarUsuarioPorLoginSenha(String login, String Senha) throws OPTException {
        return usuarioImpl.recuperarUsuarioPorLoginSenha(login, Senha);
    }
}
