package com.justice.nkopane.justnotes.auth;

import com.justice.nkopane.justnotes.service.TokenService;
import com.justice.nkopane.justnotes.user.Role;
import com.justice.nkopane.justnotes.user.User;
import com.justice.nkopane.justnotes.user.UserExistsException;
import com.justice.nkopane.justnotes.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService){
        this.passwordEncoder = passwordEncoder;
        this.userRepository= userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public void register(RegisterRequest registerRequest) throws UserExistsException {
        Optional<User> DbUser = userRepository.findByEmail(registerRequest.email());
        if(DbUser.isPresent()){
            throw new UserExistsException("Invalid Request");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(Role.MEMBER);

        User user = User.builder()
                .id(UUID.randomUUID())
                .username(registerRequest.username())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .roles(roles)
                .build();

        userRepository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateRequest.email(), authenticateRequest.password())
        );

        if(authentication.isAuthenticated()){
            String token = tokenService.generateToken(authentication);
            return AuthenticationResponse.builder().accessToken(token).build();
        }

        return AuthenticationResponse.builder().accessToken("").build();
    }
}
