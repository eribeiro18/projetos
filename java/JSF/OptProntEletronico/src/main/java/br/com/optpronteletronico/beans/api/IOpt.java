/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.beans.api;

import br.com.optpronteletronico.exception.OPTException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author evandro
 */

public interface IOpt<T> extends ICrud{
    public void executePackage(String packageName) throws OPTException;
    public void executePackages(String... packages) throws OPTException;
    public void executePackage(String packageName, String nameParameter, Long id) throws OPTException;
    
    Object get(Long id);
    List<T> collection(Class clazz, Root<T> from);
    List<T> collection(Class clazz, Root<T> from, Integer maxSize);
    List<T> collectionEntity(Class clazz, Root<T> from);
    
    public CriteriaBuilder criteriaBuilder();
    public CriteriaQuery criteriaQuery();
    public EntityManager entityManager();
    public Root<T> from(Class clazz);
}
