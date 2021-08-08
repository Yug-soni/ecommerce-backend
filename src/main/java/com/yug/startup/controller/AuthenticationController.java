package com.yug.startup.controller;

import com.yug.startup.controller.token.ConfirmationTokenService;
import com.yug.startup.email.EmailUtils;
import com.yug.startup.model.ERole;
import com.yug.startup.model.Role;
import com.yug.startup.model.User;
import com.yug.startup.payload.request.LoginRequest;
import com.yug.startup.payload.request.SignUpRequest;
import com.yug.startup.payload.response.JwtResponse;
import com.yug.startup.payload.response.MessageResponse;
import com.yug.startup.repository.RoleRepository;
import com.yug.startup.repository.UserRepository;
import com.yug.startup.security.jwt.JwtUtils;
import com.yug.startup.security.service.UserDetailsImplementation;
import com.yug.startup.security.service.UserDetailsServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserDetailsServiceImplementation userDetailsServiceImplementation;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final EmailUtils emailUtils;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImplementation userDetailsImplementation = (UserDetailsImplementation) authentication.getPrincipal();
        List<String> roles = userDetailsImplementation.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetailsImplementation.getId(),
                userDetailsImplementation.getUsername(),
                userDetailsImplementation.getEmail(),
                roles
        ));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (!userDetailsServiceImplementation.checkUserByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (!userDetailsServiceImplementation.checkUserByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                false,
                false
        );

        Set<String> strRole = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRole == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role not found"));
            roles.add(userRole);
        } else {
            strRole.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;

                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

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

        String token = emailUtils.sendMail(user);

        return ResponseEntity.ok(new MessageResponse("User Registered Successfully and confirmation token is :- "+token));
    }


    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return emailUtils.confirmToken(token);
    }

}
