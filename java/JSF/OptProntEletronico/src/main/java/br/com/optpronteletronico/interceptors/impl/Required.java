package br.com.optpronteletronico.interceptors.impl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author evandro
 */
@Target(value = {ElementType.FIELD, 
                ElementType.CONSTRUCTOR, 
                ElementType.METHOD, 
                ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {

    boolean isRequired() default false;
    String msg() default " CAMPO DE PREENCHIMENTO OBRIGATORIO. ";
    String campo() default "";
    int maxChars() default -1;
}