/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.impl;

import br.com.optpronteletronico.beans.api.IOpt;
import br.com.optpronteletronico.business.api.IAgendaConsulta;
import br.com.optpronteletronico.entity.AgendaConsulta;
import br.com.optpronteletronico.entity.AgendaConsulta_;
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
public class AgendaConsultaImpl implements IAgendaConsulta {

    private @Inject
    EntityManager em;
    private @Inject
    IOpt<AgendaConsulta> optImpl;
    private Integer total;

    @Override
    public AgendaConsulta recuperarAgendaConsultaPorId(Long id) throws OPTException {
        try {
            Root<AgendaConsulta> from = this.optImpl.from(AgendaConsulta.class);
            from.fetch(AgendaConsulta_.clinica, JoinType.LEFT);
            from.fetch(AgendaConsulta_.pessoa, JoinType.LEFT);
            from.fetch(AgendaConsulta_.pessoaCrm, JoinType.LEFT);
            from.fetch(AgendaConsulta_.preConsultaSet, JoinType.LEFT);
            from.fetch(AgendaConsulta_.usuario, JoinType.LEFT);
//          tabelas filhas
//            Fetch<AgendaConsulta, Clinica> clinica = from.fetch("clinica", JoinType.LEFT);
//            clinica.fetch("pessoa", JoinType.LEFT);
            Order order = this.optImpl.criteriaBuilder().asc(from.get("id"));

            Predicate p1 = this.optImpl.criteriaBuilder().equal(from.get("id"), id);
            Query qry = this.em.createQuery(this.optImpl.criteriaQuery().select(from).where(p1).orderBy(order));
            AgendaConsulta object = (AgendaConsulta) qry.getSingleResult();
            return object;
        } catch (Exception e) {
            throw new OPTException(e.getMessage());
        }
    }

    @Override
    public List<AgendaConsulta> recuperarTodosRegistrosPaginacao(AgendaConsulta agendaConsulta, Integer pag, Integer maxReg) throws OPTException {

        Query qry = this.em.createQuery(" SELECT c FROM AgendaConsulta c "
                + " JOIN FETCH c.clinica cl "
                + " JOIN FETCH c.pessoa p "
                + " JOIN FETCH c.pessoaCrm pc "
                + " JOIN FETCH c.usuario u ");

        Query qryMax = this.em.createQuery(" SELECT Count(c) FROM AgendaConsulta c ");

        qry.setFirstResult((pag - 1) * maxReg);
        qry.setMaxResults(maxReg);

        this.total = Integer.parseInt(qryMax.getSingleResult().toString());
        List<AgendaConsulta> result = qry.getResultList();
        return result;
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

    @Override
    public Integer getTotal() {
        if(this.total == null){
            this.total = 0;
        }
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
