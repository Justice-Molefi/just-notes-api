package com.justice.nkopane.justnotes.auth;

import com.justice.nkopane.justnotes.user.Role;
import com.justice.nkopane.justnotes.user.User;
import com.justice.nkopane.justnotes.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private UserDetailsService userDetailsService;

    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userRepository= userRepository;
    }

    public void Register(RegisterRequest registerRequest){
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

//    public void Authenticate(@Valid @RequestBody AuthenticateRequest authenticateRequest){
////        UserDetails user = userDetailsService.loadUserByUsername(authenticateRequest.email());
////        if(user != null)
////            //return "Authentication Successful";
//    }
}
