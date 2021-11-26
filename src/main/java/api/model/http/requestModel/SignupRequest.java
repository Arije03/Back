/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.http.requestModel;

import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;

/**
 *
 * @author Makhlouf Helali
 */
@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String firstname;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String lastname;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 3, max = 40)
    private String password;

    @NotBlank
    @Size(max = 6)
    private String sex;
    
    @NotBlank
    @Size(min = 8, max = 8)
    private String phoneNumber;
    
    @NotBlank
    private Date birthday;
    
    private Set<String> role;
}
