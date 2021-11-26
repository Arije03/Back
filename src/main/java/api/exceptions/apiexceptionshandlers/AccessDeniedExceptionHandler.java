/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.exceptions.apiexceptionshandlers;

import api.exceptions.apiexceptions.ApiAccessDeniedException;
import api.exceptions.apiexceptions.ApiException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author makhlouf
 */
@ControllerAdvice
public class AccessDeniedExceptionHandler {
    @ExceptionHandler(value = ApiAccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(ApiAccessDeniedException accessDeniedException){
        HttpStatus accessDeniedStatus = HttpStatus.FORBIDDEN;
        ApiException apiException = new ApiException(
            accessDeniedException.getMessage(),
            accessDeniedStatus,
            ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity(apiException, accessDeniedStatus);
    }
}
