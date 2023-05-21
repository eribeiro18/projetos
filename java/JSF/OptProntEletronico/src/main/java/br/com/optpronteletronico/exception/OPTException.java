
package br.com.optpronteletronico.exception;

/**
 *
 * @author evandro
 */
public class OPTException extends Exception {
    
    public OPTException() {}
    public OPTException(String message) {
        super(message);
    }
    public OPTException(Throwable cause) {
        super(cause);
    }
    public OPTException(String message, Throwable cause) {
        super(message, cause);
    }   
}