/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.impl;

import br.com.optpronteletronico.beans.api.IOpt;
import br.com.optpronteletronico.business.api.IFaturaSalario;
import br.com.optpronteletronico.entity.Estado;
import br.com.optpronteletronico.entity.FaturaSalario;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author evandro
 */
public class FaturaSalarioImpl implements IFaturaSalario {

    private @Inject
    EntityManager em;
    private @Inject
    IOpt<FaturaSalario> optImpl;
    private Integer total;

    @Override
    public Integer getTotal() {
        if (this.total == null) {
            this.total = 0;
        }
        return total;
    }

    @Override
    public List<FaturaSalario> recuperarTodosRegistrosPaginacao(FaturaSalario faturaSalario, Integer pag, Integer maxReg) throws OPTException {

        Query qry = this.em.createQuery(" SELECT c FROM FaturaSalario c "
                + " LEFT JOIN FETCH c.clinica"
                + " LEFTJOIN  FETCH c.pessoaCrm ");

        Query qryMax = this.em.createQuery(" SELECT Count(c) FROM FaturaSalario c ");

        qry.setFirstResult((pag - 1) * maxReg);
        qry.setMaxResults(maxReg);

        this.total = Integer.parseInt(qryMax.getSingleResult().toString());
        List<FaturaSalario> result = qry.getResultList();
        return result;
    }

    @Override
    public FaturaSalario recuperarFaturaSalarioPorId(Long id) throws OPTException {
        try {
            Root<FaturaSalario> from = this.optImpl.from(FaturaSalario.class);
            from.fetch("clinica", JoinType.LEFT);
            from.fetch("pessoaCrm", JoinType.LEFT);
            Order order = this.optImpl.criteriaBuilder().asc(from.get("id"));

            Predicate p1 = this.optImpl.criteriaBuilder().equal(from.get("id"), id);
            Query qry = this.em.createQuery(this.optImpl.criteriaQuery().select(from).where(p1).orderBy(order));
            FaturaSalario object = (FaturaSalario) qry.getSingleResult();
            return object;
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
