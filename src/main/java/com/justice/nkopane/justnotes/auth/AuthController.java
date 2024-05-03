package com.justice.nkopane.justnotes.auth;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) throws MethodArgumentNotValidException{
        authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successful");
    }

    @PostMapping("/login")
    public  AuthenticationResponse login(@Valid @RequestBody AuthenticateRequest authenticateRequest){
        return authService.authenticate(authenticateRequest);
    }
}
