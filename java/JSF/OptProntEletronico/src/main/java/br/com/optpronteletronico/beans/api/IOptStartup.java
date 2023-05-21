/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.optpronteletronico.beans.api;

import javax.ejb.Local;

/**
 *
 * @author evandro
 */
@Local
public interface IOptStartup {
    void initialize();
}
