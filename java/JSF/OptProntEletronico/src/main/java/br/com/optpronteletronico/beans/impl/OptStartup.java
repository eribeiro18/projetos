/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.beans.impl;


import br.com.optpronteletronico.beans.api.IOptStartup;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.apache.log4j.Logger;

/**
 *
 * @author evandro
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class OptStartup implements IOptStartup{

    private final Logger logger = Logger.getLogger(OptStartup.class);
    
    @Override
    public void initialize() {
        this.logger.info("---INICIALIZANDO SERVICOS VIEW---");
    }
    
}
