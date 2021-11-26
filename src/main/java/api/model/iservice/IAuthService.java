/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.iservice;

import api.model.http.requestModel.LoginRequest;
import api.model.http.requestModel.SignupRequest;
import api.model.http.responseModel.JwtResponse;
import api.model.http.responseModel.MessageResponse;
import org.springframework.stereotype.Service;

/**
 *
 * @author Makhlouf Helali
 */
@Service
public interface IAuthService {
	public JwtResponse authenticateUser(LoginRequest loginRequest);
	public MessageResponse registerUser(SignupRequest signUpRequest);
}
