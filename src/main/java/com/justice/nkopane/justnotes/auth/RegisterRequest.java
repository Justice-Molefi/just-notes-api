package com.justice.nkopane.justnotes.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Username cannot be empty")
        @Size(min = 3, max = 20, message = "Username must be minimum 3 and maximum 20 characters")
        String username,
        @Email(message = "Email is not valid")
        String email,
        @NotBlank(message = "Password cannot be empty")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character")
        String password) {

}
