package api.controllers;

import api.exceptions.apiexceptions.ApiBadRequestException;
import api.exceptions.apiexceptions.ApiInternalServerException;
import api.exceptions.apiexceptions.ApiRessourceNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import api.model.entities.ERoles;
import api.model.entities.Roles;
import api.model.entities.Users;
import api.model.http.requestModel.LoginRequest;
import api.model.http.requestModel.SignupRequest;
import api.model.http.responseModel.ApiResponse;
import api.model.http.responseModel.JwtResponse;
import api.model.http.responseModel.MessageResponse;
import api.model.iservice.IAuthService;
import api.model.security.jwt.JwtUtils;
import api.model.serviceimpl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import api.model.iservice.IUsersService;
import api.model.iservice.IRolesService;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Makhlouf Helali
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private IAuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@NotNull @Valid @RequestBody(required = false) LoginRequest loginRequest) {
		try {
			if (loginRequest == null) {
				throw new ApiBadRequestException("invalid credentials, you shuld send a valid login & password!");
			} else {
				return ResponseEntity.ok(authService.authenticateUser(loginRequest));
			}
		} catch (ApiBadRequestException e) {
			throw e;
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@NotNull @Valid @RequestBody(required = false) SignupRequest signUpRequest) {
		try {
			if(signUpRequest == null){
				throw new ApiBadRequestException("you shuld send a user object!");
			}else{
				return ResponseEntity.ok(authService.registerUser(signUpRequest));
			}
		} catch(ApiBadRequestException e){
			throw e;
		}

	}
}
