
package api.exceptions.apiexceptions;

import java.time.ZonedDateTime;
import lombok.*;
import org.springframework.http.HttpStatus;

/**
 *
 * @author makhlouf
 */
@AllArgsConstructor
@Getter
@Setter
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;
}
