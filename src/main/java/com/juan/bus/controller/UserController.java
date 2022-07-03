package com.juan.bus.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.juan.bus.models.Agency;
import com.juan.bus.models.ERole;
import com.juan.bus.models.Role;
import com.juan.bus.models.User;
import com.juan.bus.payload.request.SignupCustomRequest;
import com.juan.bus.payload.request.UserCustomRequest;
import com.juan.bus.payload.request.UserPasswordRequest;
import com.juan.bus.payload.response.MessageResponse;
import com.juan.bus.repository.AgencyRepository;
import com.juan.bus.repository.RoleRepository;
import com.juan.bus.repository.UserRepository;
import com.juan.bus.security.jwt.JwtUtils;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;


@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET })
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupCustomRequest signupCustomRequest) {
		if (userRepository.existsByUsername(signupCustomRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signupCustomRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		User user = new User(signupCustomRequest.getUsername(),signupCustomRequest.getEmail(),encoder.encode(signupCustomRequest.getPassword()), signupCustomRequest.getFirstName(), signupCustomRequest.getLastName(),
				signupCustomRequest.getMobileNumber());

		Set<String> strRoles = signupCustomRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long id,
			@Valid @RequestBody UserCustomRequest userCustomRequest) {
		User user = userRepository.findById(id).get();
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		user.setFirstName(userCustomRequest.getFirstName());
		user.setLastName(userCustomRequest.getLastName());
		user.setMobileNumber(userCustomRequest.getMobileNumber());

		User updatedUser = userRepository.save(user);

		return ResponseEntity.ok(updatedUser);
	}

	@PutMapping("/password/{id}")
	@ApiOperation(value = "", authorizations = { @Authorization(value = "apiKey") })
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> updatePassword(@PathVariable(value = "id") Long id,
			@Valid @RequestBody UserPasswordRequest userPasswordRequest) {
		User user = userRepository.findById(id).get();
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		user.setPassword(encoder.encode(userPasswordRequest.getPassword()));

		User updatedUser = userRepository.save(user);

		return ResponseEntity.ok(updatedUser);
	}
}
