/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.api;

import br.com.optpronteletronico.entity.Usuario;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;

/**
 *
 * @author evandro
 */
public interface IUsuario {

    public Usuario recuperarUsuarioPorId(Long id) throws OPTException;
    public Integer getTotal();
    public List<Usuario> recuperarTodosRegistrosPaginacao(Usuario usuario, Integer pag, Integer maxReg) throws OPTException;
    public void delete(Object T) throws OPTException;
    public void persist(Object T) throws OPTException;
    public Object merge(Object T) throws OPTException;
    public Usuario recuperarUsuarioPorLoginSenha(String login, String Senha) throws OPTException;
}
