/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.beans.produtor;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author evandro
 */
public class OptProdutor {
    
    @PersistenceContext(unitName = "prontEletrPersistUnity")
    @Produces
    public EntityManager em;
}
