/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.iservice;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import api.model.entities.Users;
import api.model.http.requestModel.LoginRequest;
import api.model.http.requestModel.SignupRequest;
import api.model.http.responseModel.JwtResponse;
import api.model.http.responseModel.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Makhlouf Helali
 */
@Service
public interface IUsersService {

	public Users createUser(Users user);

	public List<Users> readAllUsers();

	public Users updateUser(Users user);

	public Boolean deleteUser(Long id);

	public Optional<Users> findByUsername(String username);

	public Users findByPhoneNumber(String phoneNumber);

	public Boolean existsByUsername(String username);

	public Boolean existsByPhoneNumber(String phoneNumber);

	public JwtResponse authenticateUser(LoginRequest loginRequest);

	public MessageResponse registerUser(SignupRequest signUpRequest);
}
