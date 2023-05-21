/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.api;

import br.com.optpronteletronico.entity.Estado;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;

/**
 *
 * @author evandro
 */
public interface IEstado {

    public Estado recuperarEstadoPorId(Long id) throws OPTException;
    public List<Estado> recuperarTodosRegistrosPaginacao(Estado estado, Integer pag, Integer maxReg) throws OPTException;
    public Integer getTotal();
    public void delete(Object T) throws OPTException;
    public void persist(Object T) throws OPTException;
    public Object merge(Object T) throws OPTException;
}
