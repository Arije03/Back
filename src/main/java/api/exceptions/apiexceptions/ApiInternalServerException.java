/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.exceptions.apiexceptions;

/**
 *
 * @author makhlouf
 */
public class ApiInternalServerException extends RuntimeException {

    public ApiInternalServerException(String string) {
        super(string);
    }

    public ApiInternalServerException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
