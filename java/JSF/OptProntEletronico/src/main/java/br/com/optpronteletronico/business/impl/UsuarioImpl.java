/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.business.impl;

import br.com.optpronteletronico.beans.api.IOpt;
import br.com.optpronteletronico.business.api.IUsuario;
import br.com.optpronteletronico.entity.Usuario;
import br.com.optpronteletronico.entity.Usuario_;
import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author evandro
 */
public class UsuarioImpl implements IUsuario {

    private @Inject
    EntityManager em;
    private @Inject
    IOpt<Usuario> optImpl;
    private Integer total;

    @Override
    public Integer getTotal() {
        if (this.total == null) {
            this.total = 0;
        }
        return total;
    }

    @Override
    public Usuario recuperarUsuarioPorLoginSenha(String login, String Senha) throws OPTException {
        try {
            Root<Usuario> from = this.optImpl.from(Usuario.class);
            from.fetch(Usuario_.pessoa, JoinType.LEFT);
            Predicate p1 = this.optImpl.criteriaBuilder().equal(from.get("login"), login);
            Predicate p2 = this.optImpl.criteriaBuilder().equal(from.get("senha"), Senha);
            Query qry = this.em.createQuery(this.optImpl.criteriaQuery().select(from).where(p1, p2));
            Usuario result = (Usuario) qry.getSingleResult();
            return result;
        } catch (Exception e) {
            throw new OPTException(e.getMessage());
        }
    }

    @Override
    public List<Usuario> recuperarTodosRegistrosPaginacao(Usuario usuario, Integer pag, Integer maxReg) throws OPTException {

        Query qry = this.em.createQuery(" SELECT c FROM Usuario c "
                + " LEFT JOIN FETCH c.pessoa");

        Query qryMax = this.em.createQuery(" SELECT Count(c) FROM Usuario c ");

        qry.setFirstResult((pag - 1) * maxReg);
        qry.setMaxResults(maxReg);

        this.total = Integer.parseInt(qryMax.getSingleResult().toString());
        List<Usuario> result = qry.getResultList();
        return result;
    }

    @Override
    public Usuario recuperarUsuarioPorId(Long id) throws OPTException {
        try {
            Root<Usuario> from = this.optImpl.from(Usuario.class);
            Predicate p1 = this.optImpl.criteriaBuilder().equal(from.get("id"), id);

            Query qry = this.em.createQuery(this.optImpl.criteriaQuery().select(from).where(p1));
            Usuario result = (Usuario) qry.getSingleResult();
            return result;
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
