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
public class ApiConflictException extends RuntimeException {

    public ApiConflictException(String string) {
        super(string);
    }

    public ApiConflictException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
