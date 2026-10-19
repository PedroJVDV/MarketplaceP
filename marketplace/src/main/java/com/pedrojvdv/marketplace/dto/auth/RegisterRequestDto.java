package com.pedrojvdv.marketplace.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@NotBlank
public class RegisterRequestDto {

    String username;
    @Email
    String email;
    String usernameLogin;
    String password;
    // DEFAULT ROLE == USER!
}
