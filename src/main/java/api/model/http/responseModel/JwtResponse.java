/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.http.responseModel;

import api.model.entities.Users;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Makhlouf Helali
 */
@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {
    private HttpStatus code;
    private String message;
    private String token;
    private String type = "Bearer";
    private Users user;

    public JwtResponse(HttpStatus code, String message, String token, Users user) {
        this.code = code;
        this.message = message;
        this.token = token;
        this.user = user;
    }
    
}
