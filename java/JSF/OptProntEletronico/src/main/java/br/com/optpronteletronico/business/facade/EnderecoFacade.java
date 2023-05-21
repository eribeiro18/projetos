/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.facade;

import br.com.optpronteletronico.business.api.IEndereco;
import br.com.optpronteletronico.entity.Endereco;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author evandro
 */
public class EnderecoFacade {
    
    @Inject IEndereco enderecoImpl;

    public Endereco recuperarEnderecoPorId(Long id) throws OPTException {
        return enderecoImpl.recuperarEnderecoPorId(id);
    }

    public List<Endereco> recuperarTodosRegistrosPaginacao(Endereco endereco, Integer pag, Integer maxReg) throws OPTException {
        return enderecoImpl.recuperarTodosRegistrosPaginacao(endereco, pag, maxReg);
    }

    public Integer getTotal() {
        return enderecoImpl.getTotal();
    }

    public void delete(Object T) throws OPTException {
        enderecoImpl.delete(T);
    }

    public void persist(Object T) throws OPTException {
        enderecoImpl.persist(T);
    }

    public Object merge(Object T) throws OPTException {
        return enderecoImpl.merge(T);
    }
    
}
