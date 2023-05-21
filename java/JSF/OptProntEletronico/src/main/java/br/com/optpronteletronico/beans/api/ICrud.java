/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.beans.api;

import br.com.optpronteletronico.exception.OPTException;

/**
 *
 * @author evandro
 */
public interface ICrud {
    
    void delete(Object T) throws OPTException;
    void delete(Object... T) throws OPTException;
    
    void persist(Object T) throws OPTException;
    void persist(Object... T) throws OPTException;
    
    Object merge(Object T) throws OPTException;
    boolean merge(Object... T) throws OPTException;
}
