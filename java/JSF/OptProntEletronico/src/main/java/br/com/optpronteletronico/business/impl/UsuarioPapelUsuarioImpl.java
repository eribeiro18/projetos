/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.impl;

import br.com.optpronteletronico.beans.api.IOpt;
import br.com.optpronteletronico.business.api.IUsuarioPapelUsuario;
import br.com.optpronteletronico.entity.UsuarioPapelUsuario;
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
public class UsuarioPapelUsuarioImpl implements IUsuarioPapelUsuario {

    private @Inject
    EntityManager em;
    private @Inject
    IOpt<UsuarioPapelUsuario> optImpl;
    private Integer total;

    @Override
    public Integer getTotal() {
        if (this.total == null) {
            this.total = 0;
        }
        return total;
    }

    @Override
    public List<UsuarioPapelUsuario> recuperarTodosRegistrosPaginacao(UsuarioPapelUsuario preConsulta, Integer pag, Integer maxReg) throws OPTException {

        Query qry = this.em.createQuery(" SELECT c FROM UsuarioPapelUsuario c "
                + " LEFT JOIN FETCH c.papelUsuario"
                + " LEFT JOIN FETCH c.usuario ");

        Query qryMax = this.em.createQuery(" SELECT Count(c) FROM UsuarioPapelUsuario c ");

        qry.setFirstResult((pag - 1) * maxReg);
        qry.setMaxResults(maxReg);

        this.total = Integer.parseInt(qryMax.getSingleResult().toString());
        List<UsuarioPapelUsuario> result = qry.getResultList();
        return result;
    }

    @Override
    public UsuarioPapelUsuario recuperarUsuarioPapelUsuarioPorId(Long id) throws OPTException {
        try {
            Root<UsuarioPapelUsuario> from = this.optImpl.from(UsuarioPapelUsuario.class);
            from.fetch("papelUsuario", JoinType.LEFT);
            from.fetch("usuario", JoinType.LEFT);
            Order order = this.optImpl.criteriaBuilder().asc(from.get("id"));

            Predicate p1 = this.optImpl.criteriaBuilder().equal(from.get("id"), id);
            Query qry = this.em.createQuery(this.optImpl.criteriaQuery().select(from).where(p1).orderBy(order));
            UsuarioPapelUsuario object = (UsuarioPapelUsuario) qry.getSingleResult();
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
