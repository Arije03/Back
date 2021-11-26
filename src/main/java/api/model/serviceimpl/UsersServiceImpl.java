package api.model.serviceimpl;

import api.exceptions.apiexceptions.ApiBadRequestException;
import api.exceptions.apiexceptions.ApiInternalServerException;
import api.exceptions.apiexceptions.ApiRessourceNotFoundException;
import api.model.entities.ERoles;
import api.model.entities.Roles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import api.model.entities.Users;
import api.model.http.requestModel.LoginRequest;
import api.model.http.requestModel.SignupRequest;
import api.model.http.responseModel.JwtResponse;
import api.model.http.responseModel.MessageResponse;
import api.model.iservice.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import api.model.iservice.IUsersService;
import api.model.repository.UsersRepository;
import api.model.security.jwt.JwtUtils;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Makhlouf Helali
 */
@Service
public class UsersServiceImpl implements IUsersService {

    @Autowired
    private UsersRepository userRepository;
    
    @Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IRolesService roleService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

    @Override
    public Users createUser(Users user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ApiInternalServerException(e.getMessage());
        }
    }

    @Override
    public List<Users> readAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new ApiInternalServerException(e.getMessage());
        }
    }

    @Override
    public Users updateUser(Users user) {
        try {
           return userRepository.save(userRepository.getById(user.getId()));
        } catch (Exception e) {
            throw new ApiInternalServerException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteUser(Long id) {
        try {
            userRepository.delete(userRepository.findById(id).get());
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new ApiInternalServerException(e.getMessage());
        }
        
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new ApiInternalServerException(e.getMessage());
        }
    }

    @Override
    public Boolean existsByUsername(String username) {
        try {
            return userRepository.existsByUsername(username);
        } catch (Exception e) {
            throw new ApiInternalServerException(e.getMessage());
        }
    }

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        try {
            return userRepository.existsByPhoneNumber(phoneNumber);
        } catch (Exception e) {
            throw new ApiInternalServerException(e.getMessage());
        }
    }

    @Override
    public Users findByPhoneNumber(String phoneNumber) {
        try {
            return userRepository.findByPhoneNumber(phoneNumber).get();
        } catch (Exception e) {
            throw new ApiInternalServerException(e.getMessage());
        }
    }
    
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

		return new JwtResponse(HttpStatus.OK, "Authenticated successfully", jwt, findByUsername(loginRequest.getUsername()).get());
	}

	@Override
	public MessageResponse registerUser(SignupRequest signUpRequest) {
		if (existsByUsername(signUpRequest.getUsername())) {
			return new MessageResponse(HttpStatus.UNAUTHORIZED, "Error: Username is already taken!");
		}

		if (existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
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
			createUser(user);
			return new MessageResponse(HttpStatus.OK, "User registered successfully!");
		} catch (Exception e) {
			throw e;
		}

	}

}
