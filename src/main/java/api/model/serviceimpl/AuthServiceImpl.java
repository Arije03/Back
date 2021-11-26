/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.serviceimpl;

import api.exceptions.apiexceptions.ApiRessourceNotFoundException;
import api.model.entities.ERoles;
import api.model.entities.Roles;
import api.model.entities.Users;
import api.model.http.requestModel.LoginRequest;
import api.model.http.requestModel.SignupRequest;
import api.model.http.responseModel.JwtResponse;
import api.model.http.responseModel.MessageResponse;
import api.model.iservice.IAuthService;
import api.model.iservice.IRolesService;
import api.model.iservice.IUsersService;
import api.model.security.jwt.JwtUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Makhlouf Helali
 */
@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IUsersService userService;

	@Autowired
	private IRolesService roleService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public JwtResponse authenticateUser(LoginRequest loginRequest) {
		String jwt = "";
		UserDetailsImpl userDetails = null;
		List<String> roles = null;
		try {
			Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					loginRequest.getUsername(),
					loginRequest.getPassword()
				)
			);

			SecurityContextHolder.getContext().setAuthentication(authentication);
			jwt = jwtUtils.generateJwtToken(authentication);

			userDetails = (UserDetailsImpl) authentication.getPrincipal();
			roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		} catch (Exception ex) {
			return new JwtResponse(HttpStatus.BAD_REQUEST, "Invalid login or password!", null, null);
		}

		return new JwtResponse(HttpStatus.OK, "Authenticated successfully", jwt, userService.findByUsername(loginRequest.getUsername()).get());
	}

	@Override
	public MessageResponse registerUser(SignupRequest signUpRequest) {
		if (userService.existsByUsername(signUpRequest.getUsername())) {
			return new MessageResponse(HttpStatus.UNAUTHORIZED, "Error: Username is already taken!");
		}

		if (userService.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
			return new MessageResponse(HttpStatus.UNAUTHORIZED, "Error: Phone number is already in use!");
		}
		try {
			// Create new user's account
			Users user = new Users(
				signUpRequest.getFirstname(),
				signUpRequest.getLastname(),
				signUpRequest.getUsername(),
				encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getSex(),
				signUpRequest.getPhoneNumber(),
				signUpRequest.getBirthday()
			);

			Set<String> strRoles = signUpRequest.getRole();
			Set<Roles> roles = new HashSet<>();

			if (strRoles == null) {
				Roles userRole = roleService.findRolesByName(ERoles.ROLE_USER)
					.orElseThrow(() -> new ApiRessourceNotFoundException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
						case "admin":
							Roles adminRole = roleService.findRolesByName(ERoles.ROLE_ADMIN)
								.orElseThrow(() -> new ApiRessourceNotFoundException("Error: Role is not found."));
							roles.add(adminRole);

							break;
						default:
							Roles userRole = roleService.findRolesByName(ERoles.ROLE_USER)
								.orElseThrow(() -> new ApiRessourceNotFoundException("Error: Role is not found."));
							roles.add(userRole);
					}
				});
			}

			user.setRoles(roles);
			userService.createUser(user);
			return new MessageResponse(HttpStatus.OK, "User registered successfully!");
		} catch (Exception e) {
			throw e;
		}

	}

}
