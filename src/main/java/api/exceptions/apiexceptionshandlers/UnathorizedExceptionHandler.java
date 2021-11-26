/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.exceptions.apiexceptionshandlers;

import api.exceptions.apiexceptions.ApiException;
import api.exceptions.apiexceptions.ApiUnauthorizedException;
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
public class UnathorizedExceptionHandler {
    @ExceptionHandler(value = ApiUnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(ApiUnauthorizedException ressourceNotFoundException){
        HttpStatus unauthorizedStatus = HttpStatus.UNAUTHORIZED;
        ApiException apiException = new ApiException(
            ressourceNotFoundException.getMessage(),
            unauthorizedStatus,
            ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity(apiException, unauthorizedStatus);
    }
}
