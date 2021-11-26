/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.http.responseModel;
import lombok.*;
import org.springframework.http.HttpStatus;

/**
 *
 * @author makhlouf
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	private HttpStatus code;
	private String message;
	private Object response;
}
