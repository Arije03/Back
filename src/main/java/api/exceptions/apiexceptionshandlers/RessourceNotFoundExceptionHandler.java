package api.exceptions.apiexceptionshandlers;

import api.exceptions.apiexceptions.ApiException;
import api.exceptions.apiexceptions.ApiRessourceNotFoundException;
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
public class RessourceNotFoundExceptionHandler {
    @ExceptionHandler(value = ApiRessourceNotFoundException.class)
    public ResponseEntity<Object> handleRessourceNotFoundException(ApiRessourceNotFoundException ressourceNotFoundException){
        HttpStatus ressourceNotFoundStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
            ressourceNotFoundException.getMessage(),
            ressourceNotFoundStatus,
            ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity(apiException, ressourceNotFoundStatus);
    }
}
