/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.impl;

import br.com.optpronteletronico.beans.api.IOpt;
import br.com.optpronteletronico.business.api.ITipoPessoa;
import br.com.optpronteletronico.entity.TipoPessoa;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author evandro
 */
public class TipoPessoaImpl implements ITipoPessoa {

    private @Inject
    EntityManager em;
    private @Inject
    IOpt<TipoPessoa> optImpl;

    @Override
    public List<TipoPessoa> recuperar() throws OPTException {
        try {
            Root<TipoPessoa> from = this.optImpl.from(TipoPessoa.class);
            Query qry = this.em.createQuery(this.optImpl.criteriaQuery().select(from));
            List<TipoPessoa> tiposList = qry.getResultList();
            return tiposList;
        } catch (Exception e) {
            throw new OPTException(e.getMessage());
        }
    }

    @Override
    public TipoPessoa recuperarTipoPessoaPorId(Long id) throws OPTException {
        try {
            Root<TipoPessoa> from = this.optImpl.from(TipoPessoa.class);
            Predicate p1 = this.optImpl.criteriaBuilder().equal(from.get("id"), id);

            Query qry = this.em.createQuery(this.optImpl.criteriaQuery().select(from).where(p1));
            TipoPessoa empresa = (TipoPessoa) qry.getSingleResult();
            return empresa;
        } catch (Exception e) {
            throw new OPTException(e.getMessage());
        }
    }

    @Override
    public void delete(Object T) throws OPTException {
        optImpl.delete(T);
    }

    @Override
    public void persist(Object T) throws OPTException {
        optImpl.persist(T);
    }

    @Override
    public Object merge(Object T) throws OPTException {
        return optImpl.merge(T);
    }
}
