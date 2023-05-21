/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IReceita;
import br.com.optpronteletronico.entity.Receita;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class ReceitaFacade {
    
    @Inject IReceita receitaImpl;

    public Receita recuperarReceitaPorId(Long id) throws OPTException {
        return receitaImpl.recuperarReceitaPorId(id);
    }

    public List<Receita> recuperarTodosRegistrosPaginacao(Receita posConsulta, Integer pag, Integer maxReg) throws OPTException {
        return receitaImpl.recuperarTodosRegistrosPaginacao(posConsulta, pag, maxReg);
    }

    public Integer getTotal() {
        return receitaImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        receitaImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        receitaImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return receitaImpl.merge(T);
    }
    
}
