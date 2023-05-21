/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.beans.impl;

import br.com.optpronteletronico.beans.api.IOpt;
import br.com.optpronteletronico.exception.OPTException;
import br.com.optpronteletronico.interceptors.impl.InterceptorOptImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class OptImpl<T> implements IOpt<T>{

    private @Inject EntityManager em;
    private @Inject UserTransaction utx;
    
    private CriteriaQuery criteriaQuery;
    private CriteriaBuilder criteriaBuilder;
    private Root<T> from;
    
    @Override
    public void executePackage(String packageName) throws OPTException {
        try {
            StoredProcedureQuery spq = this.em.createStoredProcedureQuery(packageName);
            spq.execute();
        } catch (Exception e) {
            throw new OPTException("--FALHA NA EXECUCAO DA PACKAGE--, NAME = " + packageName + ", MESSAGE = " + e.getMessage());
        }
    }

    @Override
    public void executePackage(String packageName, String nameParameter, Long id) throws OPTException {
        try {
            StoredProcedureQuery spq = this.em.createStoredProcedureQuery(packageName);
            spq.registerStoredProcedureParameter(nameParameter, Long.class, ParameterMode.IN);
            spq.setParameter(nameParameter, id);
            spq.execute();
        } catch (Exception e) {
            throw new OPTException("--FALHA NA EXECUCAO DA PACKAGE--, NAME = " + packageName + ", MESSAGE = " + e.getMessage());
        }
    }

    @Override
    public void executePackages(String... packages) throws OPTException {
        String packs = "";
        try {
            for (String pack : packages) {
                packs += pack;
                StoredProcedureQuery spq = this.em.createStoredProcedureQuery(pack);
                spq.execute();
            }
        } catch (Exception e) {
            throw new OPTException("--FALHA NA EXECUCAO DAS PACKAGES--, NAME = " + packs + ", MESSAGE = " + e.getMessage());
        }
    }

    @Override
    @InterceptorOptImpl
    public void persist(Object obj) throws OPTException{
        try {
            this.utx.begin();
            this.em.persist(obj);
            this.commit();
        } catch (NotSupportedException | SystemException | OPTException ex) {
            Logger.getLogger(OptImpl.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.rollback();
            } catch (OPTException e) {
                Logger.getLogger(OptImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    @InterceptorOptImpl
    public void persist(Object... obj) throws OPTException{
        try {
            this.utx.begin();
            for (Object o : obj) {
                this.em.persist(o);
            }
            this.commit();
        } catch (NotSupportedException | SystemException | OPTException ex) {
            Logger.getLogger(OptImpl.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.rollback();
            } catch (OPTException e) {
                Logger.getLogger(OptImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public void delete(Object obj) throws OPTException{
        try {
            this.utx.begin();
            Object aux = this.em.merge(obj);

            this.em.remove(aux);
            this.commit();
        } catch (NotSupportedException | SystemException | OPTException ex) {
            Logger.getLogger(OptImpl.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.rollback();
            } catch (OPTException e) {
                Logger.getLogger(OptImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public void delete(Object... obj) throws OPTException {
        try {
            this.utx.begin();
            for (Object o : obj) {
                Object aux = this.em.merge(o);
                this.em.remove(aux);
            }
            this.commit();
        } catch (NotSupportedException | SystemException | OPTException ex) {
            this.rollback();
            throw new OPTException(ex.getMessage());
        }
    }

    @Override
    @InterceptorOptImpl
    public Object merge(Object obj) throws OPTException {
        try {
            this.utx.begin();
            Object o = this.em.merge(obj);
            this.commit();
            return o;
        } catch (NotSupportedException | SystemException | OPTException ex) {
            this.rollback();
            throw new OPTException(ex.getMessage());
        }
    }

    @Override
    @InterceptorOptImpl
    public boolean merge(Object... obj) throws OPTException {
        try {
            this.utx.begin();
            for (Object o : obj) {
                this.em.merge(o);
            }
            this.commit();
        } catch (NotSupportedException | SystemException | OPTException ex) {
            this.rollback();
            throw new OPTException(ex.getMessage());
        }
        return true;
    }
    
    @Override
    public Object get(Long id) {
        return new Object();
    }

    @Override
    public List<T> collectionEntity(Class clazz, Root<T> from) {
        List<T> result = this.em.createQuery(this.criteriaQuery().select(from)).getResultList();
        return result;
    }

    @Override
    public List<T> collection(Class clazz, Root<T> from) {
        List<T> result = this.em.createQuery(this.criteriaQuery().select(from)).getResultList();
        return result;
    }

    @Override
    public List<T> collection(Class clazz, Root<T> from, Integer maxSize) {
        Query qry = this.em.createQuery(this.criteriaQuery().select(from));
        qry.setMaxResults(maxSize);

        List<T> result = qry.getResultList();
        return result;
    }
    
    @Override
    public CriteriaBuilder criteriaBuilder() {
        return this.createCriteriaBuilder();
    }

    @Override
    public CriteriaQuery criteriaQuery() {
        return this.criteriaQuery;
    }

    @Override
    public EntityManager entityManager() {
        return em;
    }

    @Override
    public Root<T> from(Class clazz) {
        this.from = this.createCriteriaQuery(clazz).from(clazz);
        return from;
    }

    private CriteriaBuilder createCriteriaBuilder() {
        this.criteriaBuilder = this.em.getCriteriaBuilder();
        return this.criteriaBuilder;
    }

    private CriteriaQuery createCriteriaQuery(Class clazz) {
        this.criteriaQuery = this.criteriaBuilder().createQuery(clazz);
        return this.criteriaQuery();
    }

    private void commit() throws OPTException {
        try {
            this.utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException ex) {
            throw new OPTException("---FALHA NA EXECUCAO DO COMMIT---, " + ex.getMessage());
        }
    }

    private void rollback() throws OPTException {
        try {
            this.utx.rollback();
        } catch (IllegalStateException | SystemException | SecurityException ex) {
            throw new OPTException("---FALHA NA EXECUCAO DO ROLLBACK---, " + ex.getMessage());
        }
    }
}
